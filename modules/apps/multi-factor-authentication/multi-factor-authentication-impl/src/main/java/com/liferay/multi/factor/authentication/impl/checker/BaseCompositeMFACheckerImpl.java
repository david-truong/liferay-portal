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

package com.liferay.multi.factor.authentication.impl.checker;

import com.liferay.multi.factor.authentication.api.checker.CompositeMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.HeadlessMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.io.IOException;

import java.util.List;

import javax.portlet.ActionRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 */
public abstract class BaseCompositeMFACheckerImpl
	implements BrowserMFAChecker, CompositeMFAChecker, HeadlessMFAChecker,
			   MFAChecker {

	public BaseCompositeMFACheckerImpl(List<MFAChecker> mfaCheckers) {
		this.mfaCheckers = mfaCheckers;
	}

	@Override
	public boolean forceUserSetup(long userId) {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (browserMFAChecker.forceUserSetup(userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String getName() {
		if (mfaCheckers.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(mfaCheckers.size() * 2 - 1);

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA);
			}

			sb.append(mfaChecker.getName());
		}

		return sb.toString();
	}

	@Override
	public void includeBrowserVerification(
			long userId, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException {

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (!browserMFAChecker.isBrowserSetupComplete(userId)) {
				continue;
			}

			if (browserMFAChecker.isBrowserVerified(request, userId)) {
				continue;
			}

			browserMFAChecker.includeBrowserVerification(
				userId, request, response);

			return;
		}
	}

	@Override
	public void includeSetup(
			long userId, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException {

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (!browserMFAChecker.forceUserSetup(userId)) {
				continue;
			}

			browserMFAChecker.includeSetup(userId, request, response);

			return;
		}
	}

	@Override
	public boolean isEnabled() {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (mfaChecker.isEnabled()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean setup(ActionRequest actionRequest, long userId) {
		boolean setup = false;

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (browserMFAChecker.isBrowserSetupComplete(userId)) {
				continue;
			}

			setup |= browserMFAChecker.setup(actionRequest, userId);
		}

		return setup;
	}

	@Override
	public boolean supportsBrowser() {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (mfaChecker.supportsBrowser()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean supportsHeadless() {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (mfaChecker.supportsHeadless()) {
				return true;
			}
		}

		return false;
	}

	protected List<MFAChecker> mfaCheckers;

}