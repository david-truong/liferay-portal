/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {postAgentInvocation} from '../../../js/agent/api';

const mockFetch = jest.fn();

jest.mock('frontend-js-web', () => ({
	fetch: (...args: unknown[]) => mockFetch(...args),
}));

jest.mock('eventsource', () => ({EventSource: jest.fn()}));

beforeAll(() => {
	(globalThis as any).Liferay.ThemeDisplay.getPortalURL = () =>
		'http://localhost:8080';
});

describe('postAgentInvocation', () => {
	beforeEach(() => {
		mockFetch.mockReset();
	});

	it('resolves with the response when the request succeeds', async () => {
		const response = {ok: true};

		mockFetch.mockResolvedValue(response);

		await expect(
			postAgentInvocation({
				agentExternalReferenceCode: 'L_TITLE_GENERATOR',
				context: {},
				sseEventSinkKey: 'sink-1',
			})
		).resolves.toBe(response);
	});

	it('throws immediately when the response is not ok', async () => {
		mockFetch.mockResolvedValue({
			ok: false,
			status: 404,
			text: () => Promise.resolve('Not Found'),
		});

		await expect(
			postAgentInvocation({
				agentExternalReferenceCode: 'L_TITLE_GENERATOR',
				context: {},
				sseEventSinkKey: 'sink-1',
			})
		).rejects.toThrow('404');
	});
});
