/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {EventSource} from 'eventsource';
import {fetch} from 'frontend-js-web';

const SEO_STUDIO_ENDPOINT = '/o/seo-studio/v1.0';

export type AgentContext = Record<string, unknown>;

export function createAgentInvocationEventSource() {
	return new EventSource(
		`${Liferay.ThemeDisplay.getPortalURL()}${SEO_STUDIO_ENDPOINT}/agent-invocations/subscribe`,
		{
			fetch: (input, init) =>
				fetch(input as RequestInfo, {
					...init,
					headers: new Headers({Accept: 'text/event-stream'}),
				}),
			withCredentials: true,
		}
	);
}

export async function postAgentInvocation({
	agentExternalReferenceCode,
	context,
	sseEventSinkKey,
}: {
	agentExternalReferenceCode: string;
	context: AgentContext;
	sseEventSinkKey: string;
}) {
	const response = await fetch(
		`${Liferay.ThemeDisplay.getPortalURL()}${SEO_STUDIO_ENDPOINT}/agent-invocations`,
		{
			body: JSON.stringify({
				agentDefinitionExternalReferenceCode:
					agentExternalReferenceCode,
				context,
				sseEventSinkKey,
			}),
			headers: new Headers({
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			}),
			method: 'POST',
		}
	);

	if (!response.ok) {
		throw new Error(
			`Agent invocation failed with status ${response.status}: ${await response.text()}`
		);
	}

	return response;
}
