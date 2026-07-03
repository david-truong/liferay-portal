/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	AgentContext,
	createAgentInvocationEventSource,
	postAgentInvocation,
} from './api';

const DEFAULT_TIMEOUT = 60000;

/**
 * Invokes an AI Hub agent by its external reference code and resolves with the
 * agent's raw response text. SEO Studio's own agent-invocations flow emits a
 * single SSE event named after the agent's external reference code, whose
 * JSON payload carries the full response in its "data" field.
 */
export function invokeAgent({
	agentExternalReferenceCode,
	context,
	signal,
	timeout = DEFAULT_TIMEOUT,
}: {
	agentExternalReferenceCode: string;
	context: AgentContext;
	signal?: AbortSignal;
	timeout?: number;
}): Promise<string> {
	return new Promise<string>((resolve, reject) => {
		let settled = false;

		const eventSource = createAgentInvocationEventSource();

		const timeoutId = setTimeout(() => {
			settle(() =>
				reject(new Error('Timed out waiting for the agent response'))
			);
		}, timeout);

		function settle(action: () => void) {
			if (settled) {
				return;
			}

			settled = true;

			clearTimeout(timeoutId);
			eventSource.close();

			action();
		}

		if (signal) {
			signal.addEventListener('abort', () => {
				settle(() =>
					reject(new Error('The agent invocation was cancelled'))
				);
			});

			if (signal.aborted) {
				settle(() =>
					reject(new Error('The agent invocation was cancelled'))
				);

				return;
			}
		}

		eventSource.addEventListener('error', () => {
			settle(() => reject(new Error('Unable to connect to the agent')));
		});

		eventSource.addEventListener(
			agentExternalReferenceCode,
			(event) => {
				try {
					const {data} = JSON.parse(event.data);

					settle(() => resolve(data ?? ''));
				}
				catch (error) {
					settle(() => reject(error as Error));
				}
			},
			{once: true}
		);

		eventSource.addEventListener(
			'Subscribe',
			(event) => {
				postAgentInvocation({
					agentExternalReferenceCode,
					context,
					sseEventSinkKey: event.data,
				}).catch((error) => {
					settle(() => reject(error));
				});
			},
			{once: true}
		);
	});
}
