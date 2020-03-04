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
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 */
public class OptionalCompositeMFAChecker extends BaseCompositeMFACheckerImpl {

	public OptionalCompositeMFAChecker(List<MFAChecker> mfaCheckers) {
		super(mfaCheckers);
	}

	@Override
	public List<MFACheckerSetup> getMFACheckersWaitingForSetup(
		boolean onlyForcedSetup, long userId) {

		List<MFACheckerSetup> availableMFACheckers = new ArrayList<>(
			mfaCheckers.size());

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (mfaChecker instanceof CompositeMFAChecker) {
				CompositeMFAChecker compositeMFAChecker =
					(CompositeMFAChecker)mfaChecker;

				availableMFACheckers.addAll(
					compositeMFAChecker.getMFACheckersWaitingForSetup(
						onlyForcedSetup, userId));

				continue;
			}

			if (!mfaChecker.supportsSetup()) {
				continue;
			}

			MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

			if (mfaCheckerSetup.isUserSetupComplete(userId)) {
				continue;
			}

			if (onlyForcedSetup && !mfaCheckerSetup.forceUserSetup(userId)) {
				continue;
			}

			availableMFACheckers.add(mfaCheckerSetup);
		}

		return availableMFACheckers;
	}

	@Override
	public List<BrowserMFAChecker> getMFACheckersWaitingForVerify(
		HttpServletRequest request, long userId) {

		List<BrowserMFAChecker> availableMFACheckers = new ArrayList<>(
			mfaCheckers.size());

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			if (mfaChecker.supportsSetup()) {
				MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

				if (!mfaCheckerSetup.isUserSetupComplete(userId)) {
					continue;
				}
			}

			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (browserMFAChecker.isBrowserVerified(request, userId)) {
				continue;
			}

			if (mfaChecker instanceof CompositeMFAChecker) {
				CompositeMFAChecker compositeMFAChecker =
					(CompositeMFAChecker)mfaChecker;

				availableMFACheckers.addAll(
					compositeMFAChecker.getMFACheckersWaitingForVerify(
						request, userId));

				continue;
			}

			availableMFACheckers.add(browserMFAChecker);
		}

		return availableMFACheckers;
	}

	@Override
	public boolean isBrowserVerified(HttpServletRequest request, long userId) {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (browserMFAChecker.isBrowserVerified(request, userId)) {
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

			if (headlessMFAChecker.isHeadlessVerified(request, userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isUserSetupComplete(long userId) {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsSetup()) {
				continue;
			}

			MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

			if (mfaCheckerSetup.isUserSetupComplete(userId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean verifyBrowserRequest(
		HttpServletRequest request, HttpServletResponse response, long userId) {

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.supportsBrowser()) {
				continue;
			}

			if (mfaChecker.supportsSetup()) {
				MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

				if (!mfaCheckerSetup.isUserSetupComplete(userId)) {
					continue;
				}
			}

			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (browserMFAChecker.isBrowserVerified(request, userId)) {
				continue;
			}

			if (browserMFAChecker.verifyBrowserRequest(
					request, response, userId)) {

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

			if (mfaChecker.supportsSetup()) {
				MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

				if (!mfaCheckerSetup.isUserSetupComplete(userId)) {
					continue;
				}
			}

			HeadlessMFAChecker headlessMFAChecker =
				(HeadlessMFAChecker)mfaChecker;

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