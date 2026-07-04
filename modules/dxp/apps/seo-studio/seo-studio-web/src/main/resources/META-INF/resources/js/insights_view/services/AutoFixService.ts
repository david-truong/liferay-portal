/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import {invokeAgent} from '../../agent/invokeAgent';
import {TitleCandidate} from '../types/AutoFix';

const SEO_STUDIO_TITLE_GENERATOR_ERC = 'L_SEO_STUDIO_TITLE_GENERATOR';

// Scan insight state is stored as a Liferay workflow status: pending means the
// insight is still open, approved means it has been fixed.

export const WORKFLOW_STATUS_APPROVED = 0;
export const WORKFLOW_STATUS_PENDING = 1;

// The only insight type the Title Generator agent knows how to fix.

export const MISSING_OR_EMPTY_TITLE_TAG_INSIGHT_TYPE_NAME =
	'missingOrEmptyTitleTag';

/**
 * Extracts the {"candidates":[...]} object from the agent's raw response,
 * tolerating a markdown code fence and any leading/trailing prose around it.
 * Returns undefined (rather than throwing) when no candidates array can be
 * recovered, so the caller can fall back to showing the raw response text.
 */
function _parseCandidates(response: string): TitleCandidate[] | undefined {
	const braceMatch = response.match(/\{[\s\S]*\}/);
	const fenceMatch = response.match(/```(?:json)?\s*([\s\S]*?)```/);

	for (const candidate of [fenceMatch?.[1], response, braceMatch?.[0]]) {
		if (!candidate) {
			continue;
		}

		try {
			const candidates = JSON.parse(candidate)?.candidates;

			if (Array.isArray(candidates)) {
				return candidates.filter((item) => Boolean(item?.title));
			}
		}
		catch {
			continue;
		}
	}

	return undefined;
}

/**
 * Invokes the SEO Studio Title Generator agent and parses its JSON response
 * into title candidates. The agent returns a single string whose content is a
 * JSON object of the shape {"candidates":[{"title","rationale"}]}, sometimes
 * wrapped in a markdown code fence.
 */
export async function generateTitleCandidates(
	pageContent: string,
	signal?: AbortSignal
): Promise<TitleCandidate[]> {
	const response = await invokeAgent({
		agentExternalReferenceCode: SEO_STUDIO_TITLE_GENERATOR_ERC,
		context: {pageContent},
		signal,
	});

	const candidates = _parseCandidates(response);

	if (!Array.isArray(candidates)) {

		// The agent did not return the expected JSON shape (a quota or
		// guardrail rejection message, for example) — surface its raw text
		// rather than a generic error, since it is the most useful message
		// available to show the user.

		throw new Error(response.trim());
	}

	return candidates;
}

/**
 * Writes the chosen title back to the page on the remote customer instance
 * through the SEO Studio backend, which resolves the target page from its
 * friendly URL and signs the request with the connection credentials. The
 * connection secret never reaches the browser.
 */
export async function applyTitle({
	htmlTitle,
	pageURL,
}: {
	htmlTitle: string;
	pageURL: string;
}): Promise<void> {
	const body = new URLSearchParams();

	body.append('htmlTitle', htmlTitle);
	body.append('pageURL', pageURL);

	const response = await fetch('/o/seo-studio-auto-fix/apply-title', {
		body,
		headers: new Headers({
			'Accept': 'application/json',
			'Content-Type': 'application/x-www-form-urlencoded',
		}),
		method: 'POST',
	});

	if (!response.ok) {
		throw new Error('Unable to apply the title');
	}
}

export async function resolveInsight(scanInsightId: number): Promise<void> {
	const response = await fetch(
		`/o/seo-studio/scan-insights/${scanInsightId}`,
		{
			body: JSON.stringify({
				resolvedDate: new Date().toISOString(),
				state: WORKFLOW_STATUS_APPROVED,
			}),
			headers: new Headers({
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			}),
			method: 'PATCH',
		}
	);

	if (!response.ok) {
		throw new Error('Unable to mark the insight as resolved');
	}
}
