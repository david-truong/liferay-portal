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
public class OptionalCompositeMFAVerifier extends CompositeMFAVerifierImpl {

	public OptionalCompositeMFAVerifier(List<MFAChecker> mfaVerifiers) {
		super(mfaVerifiers);
	}

	@Override
	public List<BrowserMFAChecker> getMFAVerifiersAvailableForSetup(long userId) {
		List<BrowserMFAChecker> availableMFAVerifiers = new ArrayList<>(
			mfaVerifiers.size());

		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if (!browserMFAVerifier.forceUserSetup(userId)) {
				continue;
			}

			if (mfaVerifier instanceof CompositeMFAChecker) {
				CompositeMFAChecker compositeMFAVerifier =
					(CompositeMFAChecker)mfaVerifier;

				availableMFAVerifiers.addAll(
					compositeMFAVerifier.getMFAVerifiersAvailableForSetup(
						userId));
			}
			else {
				availableMFAVerifiers.add(browserMFAVerifier);
			}
		}

		return availableMFAVerifiers;
	}

	@Override
	public List<BrowserMFAChecker> getMFAVerifiersAvailableForVerify(
		HttpServletRequest httpServletRequest, long userId) {

		List<BrowserMFAChecker> availableMFAVerifiers = new ArrayList<>(
			mfaVerifiers.size());

		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if (!browserMFAVerifier.isBrowserSetupComplete(
				httpServletRequest, userId)) {

				continue;
			}

			if (browserMFAVerifier.isBrowserVerified(
				httpServletRequest, userId)){

				continue;
			}

			if (mfaVerifier instanceof CompositeMFAChecker) {
				CompositeMFAChecker compositeMFAVerifier =
					(CompositeMFAChecker)mfaVerifier;

				availableMFAVerifiers.addAll(
					compositeMFAVerifier.getMFAVerifiersAvailableForVerify(
						httpServletRequest, userId));
			}
			else {
				availableMFAVerifiers.add(browserMFAVerifier);
			}
		}

		return availableMFAVerifiers;
	}

	@Override
	public boolean isBrowserVerified(HttpServletRequest request, long userId) {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if(browserMFAVerifier.isBrowserVerified(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isHeadlessVerified(HttpServletRequest request, long userId) {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsHeadless()) {
				continue;
			}

			HeadlessMFAChecker headlessMFAVerifier =
				(HeadlessMFAChecker)mfaVerifier;

			if(headlessMFAVerifier.isHeadlessVerified(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isHeadlessSetupComplete(HttpServletRequest request, long userId) {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsHeadless()) {
				continue;
			}

			HeadlessMFAChecker headlessMFAVerifier =
				(HeadlessMFAChecker)mfaVerifier;

			if(headlessMFAVerifier.isHeadlessSetupComplete(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isBrowserSetupComplete(HttpServletRequest request, long userId) {
		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if(browserMFAVerifier.isBrowserSetupComplete(request, userId)) {
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

		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAVerifier =
				(BrowserMFAChecker)mfaVerifier;

			if(!browserMFAVerifier.isBrowserSetupComplete(
					originalServletRequest, userId)) {

				continue;
			}

			if (browserMFAVerifier.isBrowserVerified(
				originalServletRequest, userId)) {

				continue;
			}

			if(browserMFAVerifier.verifyBrowserRequest(
				actionRequest, actionResponse, userId)) {

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean verifyHeadlessRequest(
		HttpServletRequest request, long userId) {

		for (MFAChecker mfaVerifier : mfaVerifiers) {
			if (!mfaVerifier.supportsHeadless()) {
				continue;
			}

			HeadlessMFAChecker headlessMFAVerifier =
				(HeadlessMFAChecker)mfaVerifier;

			if(!headlessMFAVerifier.isHeadlessSetupComplete(
				request, userId)) {

				continue;
			}

			if (headlessMFAVerifier.isHeadlessVerified(request, userId)) {
				continue;
			}

			if (headlessMFAVerifier.verifyHeadlessRequest(request, userId)) {
				return true;
			}
		}

		return false;
	}
}
