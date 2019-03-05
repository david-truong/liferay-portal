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
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Polesovsky
 */
public class OptionalCompositeMFAChecker extends CompositeMFACheckerImpl {

	public OptionalCompositeMFAChecker(List<MFAChecker> mfaCheckers) {
		super(mfaCheckers);
	}

	@Override
	public List<BrowserMFAChecker> getMFACheckersAvailableForSetup(long userId) {
		List<BrowserMFAChecker> availableMFACheckers = new ArrayList<>(
			mfaCheckers.size());

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker =
				(BrowserMFAChecker)mfaChecker;

			if (!browserMFAChecker.forceUserSetup(userId)) {
				continue;
			}

			if (mfaChecker instanceof CompositeMFAChecker) {
				CompositeMFAChecker compositeMFAChecker =
					(CompositeMFAChecker)mfaChecker;

				availableMFACheckers.addAll(
					compositeMFAChecker.getMFACheckersAvailableForSetup(
						userId));
			}
			else {
				availableMFACheckers.add(browserMFAChecker);
			}
		}

		return availableMFACheckers;
	}

	@Override
	public List<BrowserMFAChecker> getMFACheckersAvailableForVerify(
		HttpServletRequest httpServletRequest, long userId) {

		List<BrowserMFAChecker> availableMFACheckers = new ArrayList<>(
			mfaCheckers.size());

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker =
				(BrowserMFAChecker)mfaChecker;

			if (!browserMFAChecker.isBrowserSetupComplete(
				httpServletRequest, userId)) {

				continue;
			}

			if (browserMFAChecker.isBrowserVerified(
				httpServletRequest, userId)){

				continue;
			}

			if (mfaChecker instanceof CompositeMFAChecker) {
				CompositeMFAChecker compositeMFAChecker =
					(CompositeMFAChecker)mfaChecker;

				availableMFACheckers.addAll(
					compositeMFAChecker.getMFACheckersAvailableForVerify(
						httpServletRequest, userId));
			}
			else {
				availableMFACheckers.add(browserMFAChecker);
			}
		}

		return availableMFACheckers;
	}

	@Override
	public boolean isBrowserVerified(HttpServletRequest request, long userId) {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker =
				(BrowserMFAChecker)mfaChecker;

			if(browserMFAChecker.isBrowserVerified(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isHeadlessVerified(HttpServletRequest request, long userId) {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsHeadless()) {
				continue;
			}

			HeadlessMFAChecker headlessMFAChecker =
				(HeadlessMFAChecker)mfaChecker;

			if(headlessMFAChecker.isHeadlessVerified(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isHeadlessSetupComplete(HttpServletRequest request, long userId) {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsHeadless()) {
				continue;
			}

			HeadlessMFAChecker headlessMFAChecker =
				(HeadlessMFAChecker)mfaChecker;

			if(headlessMFAChecker.isHeadlessSetupComplete(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isBrowserSetupComplete(HttpServletRequest request, long userId) {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker =
				(BrowserMFAChecker)mfaChecker;

			if(browserMFAChecker.isBrowserSetupComplete(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean verifyBrowserRequest(
		ActionRequest actionRequest, ActionResponse actionResponse,
		long userId) {

		HttpServletRequest originalServletRequest =
			PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(actionRequest));

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker =
				(BrowserMFAChecker)mfaChecker;

			if(!browserMFAChecker.isBrowserSetupComplete(
					originalServletRequest, userId)) {

				continue;
			}

			if (browserMFAChecker.isBrowserVerified(
				originalServletRequest, userId)) {

				continue;
			}

			if(browserMFAChecker.verifyBrowserRequest(
				actionRequest, actionResponse, userId)) {

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean verifyHeadlessRequest(
		HttpServletRequest request, long userId) {

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsHeadless()) {
				continue;
			}

			HeadlessMFAChecker headlessMFAChecker =
				(HeadlessMFAChecker)mfaChecker;

			if(!headlessMFAChecker.isHeadlessSetupComplete(
				request, userId)) {

				continue;
			}

			if (headlessMFAChecker.isHeadlessVerified(request, userId)) {
				continue;
			}

			if (headlessMFAChecker.verifyHeadlessRequest(request, userId)) {
				return true;
			}
		}

		return false;
	}
}
