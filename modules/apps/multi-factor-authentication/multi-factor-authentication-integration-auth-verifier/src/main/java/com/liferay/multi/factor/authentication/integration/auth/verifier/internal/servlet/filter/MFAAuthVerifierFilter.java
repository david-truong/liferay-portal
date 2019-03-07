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

package com.liferay.multi.factor.authentication.integration.auth.verifier.internal.servlet.filter;

import com.liferay.multi.factor.authentication.api.MFARegistry;
import com.liferay.multi.factor.authentication.integration.auth.verifier.internal.spi.integration.AuthVerifierMFAIntegration;
import com.liferay.multi.factor.authentication.spi.checker.HeadlessMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.access.control.AccessControlUtil;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(
	immediate = true,
	property = {
		"after-filter=Upload Servlet Request Filter", "servlet-context-name=",
		"servlet-filter-name=MFA Auth Verifier Filter", "url-pattern=/",
		"url-pattern=/*"
	},
	service = Filter.class
)
public class MFAAuthVerifierFilter extends BaseFilter {

	@Reference(unbind = "-")
	public void setAuthVerifierMFAIntegration(
		AuthVerifierMFAIntegration authVerifierMFAIntegration) {

		_authVerifierMFAIntegration = authVerifierMFAIntegration;
	}

	@Reference(unbind = "-")
	public void setMfaRegistry(MFARegistry mfaRegistry) {
		_mfaRegistry = mfaRegistry;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		AccessControlContext accessControlContext =
			AccessControlUtil.getAccessControlContext();

		if (accessControlContext == null) {
			super.processFilter(request, response, filterChain);

			return;
		}

		AuthVerifierResult authVerifierResult =
			accessControlContext.getAuthVerifierResult();

		if ((authVerifierResult == null) ||
			!authVerifierResult.isPasswordBasedAuthentication()) {

			super.processFilter(request, response, filterChain);

			return;
		}

		if (Objects.equals(
				HttpServletRequest.FORM_AUTH, request.getAuthType())) {

			super.processFilter(request, response, filterChain);

			return;
		}

		MFAChecker mfaChecker = _mfaRegistry.getMFAIntegrationChecker(
			_authVerifierMFAIntegration.getName());

		if ((mfaChecker == null) || !mfaChecker.supportsHeadless() ||
			!mfaChecker.isEnabled()) {

			super.processFilter(request, response, filterChain);

			return;
		}

		HeadlessMFAChecker headlessMFAChecker = (HeadlessMFAChecker)mfaChecker;

		long userId = authVerifierResult.getUserId();

		if (mfaChecker.supportsSetup()) {
			MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

			if (!mfaCheckerSetup.isUserSetupComplete(userId)) {
				if (_authVerifierMFAIntegration.isRequireUserSetup()) {
					sendError(request, response);

					return;
				}

				super.processFilter(request, response, filterChain);

				return;
			}
		}

		if (headlessMFAChecker.isHeadlessVerified(request, userId)) {
			super.processFilter(request, response, filterChain);

			return;
		}

		if (headlessMFAChecker.verifyHeadlessRequest(request, userId)) {
			super.processFilter(request, response, filterChain);

			return;
		}

		sendError(request, response);
	}

	protected void sendError(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				StringBundler.concat(
					"Unable to verify Multi Factor Authentication token for ",
					request.getPathInfo()));
		}

		String accept = GetterUtil.getString(
			request.getHeader(HttpHeaders.ACCEPT));

		if (accept.contains("json")) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType(ContentTypes.APPLICATION_JSON);

			try (PrintWriter writer = response.getWriter()) {
				writer.write(
					"{'error':'Multi Factor Authentication Required'}");
			}
		}
		else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType(ContentTypes.TEXT_PLAIN_UTF8);

			try (PrintWriter writer = response.getWriter()) {
				writer.write("Multi Factor Authentication Required");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MFAAuthVerifierFilter.class);

	private AuthVerifierMFAIntegration _authVerifierMFAIntegration;
	private MFARegistry _mfaRegistry;

}