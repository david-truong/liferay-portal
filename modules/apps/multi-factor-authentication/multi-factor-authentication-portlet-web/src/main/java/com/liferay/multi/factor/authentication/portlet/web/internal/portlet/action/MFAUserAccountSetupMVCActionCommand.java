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
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(
	property = {
		"javax.portlet.name=" + UsersAdminPortletKeys.MY_ACCOUNT,
		"mvc.command.name=/my_account/setup_mfa"
	},
	service = MVCActionCommand.class
)
public class MFAUserAccountSetupMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String userAccountSetupMFACheckerName = ParamUtil.getString(
			actionRequest, "userAccountSetupMFACheckerName");

		MFAChecker mfaChecker = _mfaRegistry.getMFAChecker(
			userAccountSetupMFACheckerName);

		if ((mfaChecker == null) || !mfaChecker.supportsSetup()) {
			SessionErrors.add(actionRequest, "userAccountSetupFailed");

			return;
		}

		MFACheckerSetup userAccountSetupMFAChecker =
			(MFACheckerSetup)mfaChecker;

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (userAccountSetupMFAChecker.setup(
				_portal.getHttpServletRequest(actionRequest),
				themeDisplay.getUserId())) {

			String redirect = _portal.escapeRedirect(
				ParamUtil.getString(actionRequest, "redirect"));

			if (Validator.isBlank(redirect)) {
				redirect = themeDisplay.getPortalURL();
			}

			actionResponse.sendRedirect(redirect);

			return;
		}

		SessionErrors.add(actionRequest, "userAccountSetupFailed");
	}

	@Reference
	private MFARegistry _mfaRegistry;

	@Reference
	private Portal _portal;

}