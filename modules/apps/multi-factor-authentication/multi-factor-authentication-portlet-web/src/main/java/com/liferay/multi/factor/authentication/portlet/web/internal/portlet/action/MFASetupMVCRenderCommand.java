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
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
	service = MVCRenderCommand.class
)
public class MFASetupMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		String integrationName = ParamUtil.getString(
			renderRequest, "integrationName");

		BrowserMFAChecker browserMFAChecker =
			(BrowserMFAChecker)_mfaRegistry.getMFAIntegrationChecker(
				integrationName);

		renderRequest.setAttribute(
			BrowserMFAChecker.class.getName(), browserMFAChecker);

		List<BrowserMFAChecker> setupMFACheckers = _getSetupMFACheckers(
			browserMFAChecker, renderRequest);

		renderRequest.setAttribute("setupMFACheckers", setupMFACheckers);

		return "/setup.jsp";
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

		return compositeMFAChecker.getMFACheckersAvailableForSetup(
			themeDisplay.getUserId());
	}

	@Reference
	private MFARegistry _mfaRegistry;

}