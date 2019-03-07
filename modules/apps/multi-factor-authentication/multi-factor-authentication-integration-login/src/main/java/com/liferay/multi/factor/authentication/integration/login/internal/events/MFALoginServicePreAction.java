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

package com.liferay.multi.factor.authentication.integration.login.internal.events;

import com.liferay.multi.factor.authentication.api.MFARegistry;
import com.liferay.multi.factor.authentication.integration.login.internal.spi.integration.LoginMFAIntegration;
import com.liferay.multi.factor.authentication.portlet.api.MFAPortletURLFactory;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.HeadlessMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(
	property = {
		"key=servlet.service.events.pre", "whitelisted.paths=/portal/error",
		"whitelisted.paths=/portal/expire_session",
		"whitelisted.paths=/portal/extend_session",
		"whitelisted.paths=/portal/logout",
		"whitelisted.paths=/portal/update_terms_of_use"
	},
	service = LifecycleAction.class
)
public class MFALoginServicePreAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		MFAChecker mfaChecker = _mfaRegistry.getMFAIntegrationChecker(
			_loginMFAIntegration.getName());

		if (mfaChecker == null) {
			return;
		}

		if (!mfaChecker.isEnabled()) {
			return;
		}

		if (!mfaChecker.supportsBrowser() && !mfaChecker.supportsHeadless()) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if ((themeDisplay == null) || !themeDisplay.isSignedIn() ||
			themeDisplay.isImpersonated()) {

			return;
		}

		if (ArrayUtil.contains(_whitelistedPaths, request.getPathInfo())) {
			return;
		}

		long userId = themeDisplay.getUserId();

		if (mfaChecker.supportsSetup()) {
			MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

			if (mfaCheckerSetup.forceUserSetup(userId)) {
				redirectToSetup(request, response, themeDisplay, userId);

				return;
			}

			if (!mfaCheckerSetup.isUserSetupComplete(userId)) {
				return;
			}
		}

		if (mfaChecker.supportsHeadless()) {
			HeadlessMFAChecker headlessMFAChecker =
				(HeadlessMFAChecker)mfaChecker;

			if (headlessMFAChecker.isHeadlessVerified(request, userId)) {
				return;
			}

			if (headlessMFAChecker.verifyHeadlessRequest(request, userId)) {
				return;
			}
		}

		if (mfaChecker.supportsBrowser()) {
			BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)mfaChecker;

			if (browserMFAChecker.isBrowserVerified(request, userId)) {
				return;
			}

			redirectToVerifyBrowserRequest(
				request, response, themeDisplay, userId);

			return;
		}

		throw new ActionException(
			new PrincipalException.MustBeAuthenticated(
				"Unable to verify Multi Factor Authentication"));
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_whitelistedPaths = (String[])properties.get("whitelisted.paths");
	}

	protected void redirectToSetup(
			HttpServletRequest request, HttpServletResponse response,
			ThemeDisplay themeDisplay, long userId)
		throws ActionException {

		LiferayPortletURL liferayPortletURL =
			_mfaPortletURLFactory.createSetupURL(
				request, _loginMFAIntegration.getName(),
				themeDisplay.getURLCurrent());

		if (themeDisplay.isStateMaximized() &&
			Objects.equals(
				liferayPortletURL.getPortletId(), themeDisplay.getPpid())) {

			return;
		}

		try {
			response.sendRedirect(liferayPortletURL.toString());
		}
		catch (Exception e) {
			throw new ActionException(
				"Unable to send login redirect: " + e.getMessage(), e);
		}
	}

	protected void redirectToVerifyBrowserRequest(
			HttpServletRequest request, HttpServletResponse response,
			ThemeDisplay themeDisplay, long userId)
		throws ActionException {

		LiferayPortletURL liferayPortletURL =
			_mfaPortletURLFactory.createVerifyURL(
				request, _loginMFAIntegration.getName(),
				themeDisplay.getURLCurrent(), userId);

		if (Objects.equals(
				liferayPortletURL.getPortletId(), themeDisplay.getPpid()) &&
			LiferayWindowState.isPopUp(request)) {

			return;
		}

		try {
			liferayPortletURL.setWindowState(LiferayWindowState.POP_UP);

			response.sendRedirect(liferayPortletURL.toString());
		}
		catch (Exception e) {
			throw new ActionException(
				"Unable to send login redirect: " + e.getMessage(), e);
		}
	}

	@Reference
	private LoginMFAIntegration _loginMFAIntegration;

	@Reference
	private MFAPortletURLFactory _mfaPortletURLFactory;

	@Reference
	private MFARegistry _mfaRegistry;

	private String[] _whitelistedPaths;

}