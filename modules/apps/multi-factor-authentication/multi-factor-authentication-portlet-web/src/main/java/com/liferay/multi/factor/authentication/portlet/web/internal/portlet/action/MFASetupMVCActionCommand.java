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

package com.liferay.multi.factor.authentication.portlet.web.internal.portlet.action;

import com.liferay.multi.factor.authentication.api.MFARegistry;
import com.liferay.multi.factor.authentication.api.checker.CompositeMFAChecker;
import com.liferay.multi.factor.authentication.portlet.api.constants.MFAPortletKeys;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(
	property = {
		"javax.portlet.name=" + MFAPortletKeys.MFA_PORTLET,
		"mvc.command.name=/mfa/setup"
	},
	service = MVCActionCommand.class
)
public class MFASetupMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String integrationName = ParamUtil.getString(
			actionRequest, "integrationName");

		BrowserMFAChecker browserMFAChecker =
			(BrowserMFAChecker)_mfaRegistry.getMFAIntegrationChecker(
				integrationName);

		int setupMFACheckerIndex = ParamUtil.getInteger(
			actionRequest, "setupMFACheckerIndex", -1);

		if (setupMFACheckerIndex > 1) {
			List<BrowserMFAChecker> setupMFACheckers = _getSetupMFACheckers(
				browserMFAChecker, actionRequest);

			if (setupMFACheckerIndex < setupMFACheckers.size()) {
				browserMFAChecker = setupMFACheckers.get(setupMFACheckerIndex);
			}
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (browserMFAChecker.setup(actionRequest, themeDisplay.getUserId())) {
			String redirect = _portal.escapeRedirect(
				ParamUtil.getString(actionRequest, "redirect"));

			if (Validator.isBlank(redirect)) {
				redirect = themeDisplay.getPortalURL();
			}

			actionResponse.sendRedirect(redirect);

			return;
		}
	}

	private List<BrowserMFAChecker> _getSetupMFACheckers(
		BrowserMFAChecker mfaChecker, PortletRequest portletRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!(mfaChecker instanceof CompositeMFAChecker)) {
			return Collections.singletonList(mfaChecker);
		}

		CompositeMFAChecker compositeMFAChecker =
			(CompositeMFAChecker)mfaChecker;

		return compositeMFAChecker.getMFACheckersWaitingForSetup(
			true, themeDisplay.getUserId());
	}

	@Reference
	private MFARegistry _mfaRegistry;

	@Reference
	private Portal _portal;

}