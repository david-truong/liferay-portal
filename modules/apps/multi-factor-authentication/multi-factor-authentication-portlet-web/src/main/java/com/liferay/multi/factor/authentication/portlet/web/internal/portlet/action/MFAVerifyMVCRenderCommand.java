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
import com.liferay.multi.factor.authentication.portlet.api.MFAPortletURLFactory;
import com.liferay.multi.factor.authentication.portlet.api.constants.MFAPortletKeys;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(
	property = {
		"javax.portlet.name=" + MFAPortletKeys.MFA_VERIFY_PORTLET,
		"mvc.command.name=/mfa_verify/view"
	},
	service = MVCRenderCommand.class
)
public class MFAVerifyMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		long mfaUserId = getMFAUserId(renderRequest);

		if (mfaUserId == 0) {
			SessionErrors.add(renderRequest, "sessionExpired");

			return "/error.jsp";
		}

		renderRequest.setAttribute("mfaUserId", mfaUserId);

		String integrationName = ParamUtil.getString(
			renderRequest, "integrationName");

		BrowserMFAChecker browserMFAChecker =
			(BrowserMFAChecker)_mfaRegistry.getMFAIntegrationChecker(
				integrationName);

		renderRequest.setAttribute(
			BrowserMFAChecker.class.getName(), browserMFAChecker);

		List<BrowserMFAChecker> verifyMFACheckers = _getVerifyMFACheckers(
			browserMFAChecker, renderRequest, mfaUserId);

		renderRequest.setAttribute("verifyMFACheckers", verifyMFACheckers);

		return "/mfa_verify/verify.jsp";
	}

	private List<BrowserMFAChecker> _getVerifyMFACheckers(
		BrowserMFAChecker mfaChecker, PortletRequest portletRequest,
		long userId) {

		if (!(mfaChecker instanceof CompositeMFAChecker)) {
			return Collections.singletonList(mfaChecker);
		}

		HttpServletRequest httpServletRequest =
			_portal.getOriginalServletRequest(
				_portal.getHttpServletRequest(portletRequest));

		CompositeMFAChecker compositeMFAChecker =
			(CompositeMFAChecker)mfaChecker;

		return compositeMFAChecker.getMFACheckersWaitingForVerify(
			httpServletRequest, userId);
	}

	private long getMFAUserId(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay.isSignedIn()) {
			return themeDisplay.getUserId();
		}

		String integrationName = ParamUtil.getString(
			portletRequest, "integrationName");

		HttpServletRequest httpServletRequest =
			_portal.getOriginalServletRequest(
				_portal.getHttpServletRequest(portletRequest));

		HttpSession session = httpServletRequest.getSession();

		Object mfaUserId = session.getAttribute(
			MFAPortletURLFactory.MFA_USER_ID + integrationName);

		if (mfaUserId == null) {
			return 0;
		}

		return (Long)mfaUserId;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MFAVerifyMVCRenderCommand.class);

	@Reference
	private MFARegistry _mfaRegistry;

	@Reference
	private Portal _portal;

}