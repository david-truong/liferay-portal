/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.multi.factor.authentication.integration.internal.verifier;

import com.liferay.multi.factor.authentication.api.checker.CompositeMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.HeadlessMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import javax.portlet.ActionRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Tomas Polesovsky
 */
public abstract class CompositeMFAVerifierImpl
	implements BrowserMFAChecker, CompositeMFAChecker, HeadlessMFAChecker,
	MFAChecker {

	@Override
	public boolean supportsUserAccountSetup() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (mfaVerifier.isEnabled()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String getName() {
		if (mfaVerifiers.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(mfaVerifiers.size() * 2 - 1);
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA);
			}
			sb.append(mfaVerifier.getName());
		}

		return sb.toString();
	}

	@Override
	public String getProviderName() {
		if (mfaVerifiers.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(mfaVerifiers.size() * 2 - 1);
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA);
			}
			sb.append(mfaVerifier.getProviderName());
		}

		return sb.toString();
	}

	public CompositeMFAVerifierImpl(List<MFAChecker> mfaVerifiers) {
		this.mfaVerifiers = mfaVerifiers;
	}

	@Override
	public boolean supportsHeadless() {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (mfaVerifier.supportsHeadless()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean supportsBrowser() {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (mfaVerifier.supportsBrowser()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void includeSetup(
		long userId, HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if (!browserMFAVerifier.forceUserSetup(userId)) {
				continue;
			}

			browserMFAVerifier.includeSetup(userId, request, response);

			return;
		}
	}

	@Override
	public void includeBrowserVerification(
		long userId, HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if (!browserMFAVerifier.isBrowserSetupComplete(
				request, userId)) {

				continue;
			}

			if (browserMFAVerifier.isBrowserVerified(request, userId)) {
				continue;
			}

			browserMFAVerifier.includeBrowserVerification(
				userId, request, response);

			return;
		}
	}


	@Override
	public boolean forceUserSetup(long userId) {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if (browserMFAVerifier.forceUserSetup(userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean setup(ActionRequest actionRequest, long userId) {
		boolean setup = false;

		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if(!browserMFAVerifier.forceUserSetup(userId)) {
				continue;
			}

			setup |= browserMFAVerifier.setup(actionRequest, userId);
		}

		return setup;
	}

	protected List<MFAChecker> mfaVerifiers;

}