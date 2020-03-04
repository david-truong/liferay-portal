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

package com.liferay.multi.factor.authentication.checker.email.otp.web.internal.checker;

import com.liferay.multi.factor.authentication.checker.email.otp.model.EmailOTPEntry;
import com.liferay.multi.factor.authentication.checker.email.otp.service.EmailOTPEntryLocalService;
import com.liferay.multi.factor.authentication.checker.email.otp.web.internal.configuration.EmailOTPConfiguration;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author arthurchan35
 */
@Component(
	configurationPid = "com.liferay.multi.factor.authentication.checker.email.otp.web.internal.configuration.EmailOTPConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	service = MFAChecker.class
)
public class EmailOTPMFAChecker
	implements BrowserMFAChecker, MFAChecker, MFACheckerSetup {

	@Override
	public boolean forceUserSetup(long userId) {
		if (isUserSetUp(userId)) {
			return false;
		}

		return _forceUserSetup;
	}

	public EmailOTPConfiguration getEmailOTPConfiguration() {
		return _emailOTPConfiguration;
	}

	public String getEmailOTPConfigurationPid() {
		return _emailOTPConfigurationPid;
	}

	@Override
	public String getLabel(Locale locale) {
		return _emailOTPConfiguration.label();
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void includeBrowserVerification(
			long userId, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException {

		EmailOTPEntry emailOTPEntry =
			_emailOTPEntryLocalService.fetchEmailOTPEntry(userId, _name);

		request.setAttribute("sendToEmail", emailOTPEntry.getEmailAddress());

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/verify_otp.jsp");

		try {
			request.setAttribute(
				"emailOTPConfiguration", _emailOTPConfiguration);

			requestDispatcher.include(request, response);

			HttpServletRequest originalRequest =
				_portal.getOriginalServletRequest(request);

			HttpSession session = originalRequest.getSession();

			session.setAttribute("otpPhase", "verify");
			session.setAttribute("userId", userId);
		}
		catch (ServletException se) {
			throw new IOException(
				"Unable to include /verify_otp.jsp: " + se, se);
		}
	}

	@Override
	public void includeSetup(
			long userId, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException {

		EmailOTPEntry emailOTPEntry =
			_emailOTPEntryLocalService.fetchEmailOTPEntry(userId, _name);

		//todo: include some parameter so we can allow user to re-setup

		if (emailOTPEntry != null) {
			_log.error("Setup is already finished for user: " + userId);

			return;
		}

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/setup_otp.jsp");

		try {
			request.setAttribute(
				"emailOTPConfiguration", _emailOTPConfiguration);

			requestDispatcher.include(request, response);

			HttpServletRequest originalRequest =
				_portal.getOriginalServletRequest(request);

			HttpSession session = originalRequest.getSession();

			session.setAttribute("otpPhase", "setup");
		}
		catch (ServletException se) {
			throw new IOException(
				"Unable to include /setup_otp.jsp: " + se, se);
		}
	}

	@Override
	public boolean isBrowserVerified(HttpServletRequest request, long userId) {
		HttpServletRequest originalServletRequest =
			_portal.getOriginalServletRequest(request);

		HttpSession session = originalServletRequest.getSession(false);

		if (isValid(session, userId)) {
			return true;
		}

		return false;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public boolean isUserSetupComplete(long userId) {
		return isUserSetUp(userId);
	}

	@Override
	public boolean setup(HttpServletRequest request, long userId) {
		String userInput = ParamUtil.getString(request, "otp");

		HttpServletRequest originalRequest = _portal.getOriginalServletRequest(
			request);

		HttpSession session = originalRequest.getSession();

		try {
			String email = (String)session.getAttribute("otpEmail");

			if (!_allowCustomEmail) {
				User user = _userLocalService.getUserById(userId);

				email = user.getEmailAddress();
			}

			String userIP = request.getRemoteAddr();

			if (_verify(session, userInput)) {
				_emailOTPEntryLocalService.addEmailOTPEntry(
					_name, email, userId);

				long validatedAt = System.currentTimeMillis();

				Map<String, Object> validatedMap =
					(Map<String, Object>)session.getAttribute(_VALIDATED);

				if (validatedMap == null) {
					validatedMap = new HashMap(2);

					session.setAttribute(_VALIDATED, validatedMap);
				}

				validatedMap.put(_name + "validatedAt", validatedAt);
				validatedMap.put(_name + "userId", userId);

				_emailOTPEntryLocalService.updateSuccessAttempt(
					_name, userId, userIP);

				return true;
			}
		}
		catch (Exception e) {
			_log.error("Unable to update emailOTP: " + e.getMessage(), e);

			return false;
		}

		return false;
	}

	@Override
	public boolean supportsBrowser() {
		return true;
	}

	@Override
	public boolean supportsHeadless() {
		return false;
	}

	@Override
	public boolean supportsSetup() {
		return true;
	}

	@Override
	public boolean verifyBrowserRequest(
		HttpServletRequest request, HttpServletResponse response, long userId) {

		String userInput = ParamUtil.getString(request, "otp");

		if (Validator.isBlank(userInput)) {
			return false;
		}

		HttpServletRequest originalRequest = _portal.getOriginalServletRequest(
			request);

		HttpSession session = originalRequest.getSession();

		try {
			boolean verified = _verify(session, userInput);

			String userIP = originalRequest.getRemoteAddr();

			if (verified) {
				long validatedAt = System.currentTimeMillis();

				Map<String, Object> validatedMap =
					(Map<String, Object>)session.getAttribute(_VALIDATED);

				if (validatedMap == null) {
					validatedMap = new HashMap(2);

					session.setAttribute(_VALIDATED, validatedMap);
				}

				validatedMap.put(_name + "validatedAt", validatedAt);
				validatedMap.put(_name + "userId", userId);

				_emailOTPEntryLocalService.updateSuccessAttempt(
					_name, userId, userIP);

				return true;
			}

			_emailOTPEntryLocalService.updateFailedAttempt(
				_name, userId, userIP);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}

		return false;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_emailOTPConfiguration = ConfigurableUtil.createConfigurable(
			EmailOTPConfiguration.class, properties);

		_emailOTPConfigurationPid = (String)properties.getOrDefault(
			"pid", EmailOTPConfiguration.class.getName());

		_allowCustomEmail = _emailOTPConfiguration.allowCustomEmail();
		_enabled = _emailOTPConfiguration.enabled();
		_forceUserSetup = _emailOTPConfiguration.forceUserSetup();
		_name = _emailOTPConfiguration.name();
		_validationExpirationTime =
			_emailOTPConfiguration.validationExpirationTime();

		if (PropsValues.SESSION_ENABLE_PHISHING_PROTECTION) {
			List<String> sessionPhishingProtectedAttributesList = new ArrayList(
				Arrays.asList(
					PropsValues.SESSION_PHISHING_PROTECTED_ATTRIBUTES));

			sessionPhishingProtectedAttributesList.add(_VALIDATED);

			PropsValues.SESSION_PHISHING_PROTECTED_ATTRIBUTES =
				sessionPhishingProtectedAttributesList.toArray(
					new String[sessionPhishingProtectedAttributesList.size()]);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (PropsValues.SESSION_ENABLE_PHISHING_PROTECTION) {
			List<String> sessionPhishingProtectedAttributesList = new ArrayList(
				Arrays.asList(
					PropsValues.SESSION_PHISHING_PROTECTED_ATTRIBUTES));

			sessionPhishingProtectedAttributesList.remove(_VALIDATED);

			PropsValues.SESSION_PHISHING_PROTECTED_ATTRIBUTES =
				sessionPhishingProtectedAttributesList.toArray(
					new String[sessionPhishingProtectedAttributesList.size()]);
		}
	}

	protected boolean isValid(HttpSession httpSession, long userId) {
		if (httpSession == null) {
			return false;
		}

		Map<String, Object> validatedMap = (Map)httpSession.getAttribute(
			_VALIDATED);

		if (validatedMap != null) {
			if (userId != MapUtil.getLong(validatedMap, _name + "userId")) {
				return false;
			}

			if (_validationExpirationTime < 0) {
				return true;
			}

			long validatedAt = MapUtil.getLong(
				validatedMap, _name + "validatedAt");

			if (validatedAt + _validationExpirationTime * 1000 >
					System.currentTimeMillis()) {

				return true;
			}
		}

		return false;
	}

	private boolean _verify(HttpSession session, String userInput)
		throws Exception {

		String expected = (String)session.getAttribute("otp");

		// user may make typo, not removing attributes to allow retry

		if ((expected == null) || !expected.equals(userInput)) {
			return false;
		}

		session.removeAttribute("otp");
		session.removeAttribute("otpSetAt");
		session.removeAttribute("otpPhase");
		session.removeAttribute("userId");

		return true;
	}

	private boolean isUserSetUp(long userId) {
		EmailOTPEntry emailOTPEntry =
			_emailOTPEntryLocalService.fetchEmailOTPEntry(userId, _name);

		if (emailOTPEntry != null) {
			return true;
		}

		return false;
	}

	private static final String _VALIDATED =
		EmailOTPMFAChecker.class.getName() + "#VALIDATED";

	private static final Log _log = LogFactoryUtil.getLog(
		EmailOTPMFAChecker.class);

	private boolean _allowCustomEmail;
	private EmailOTPConfiguration _emailOTPConfiguration;
	private String _emailOTPConfigurationPid;

	@Reference
	private EmailOTPEntryLocalService _emailOTPEntryLocalService;

	private boolean _enabled;
	private boolean _forceUserSetup;
	private String _name;

	@Reference
	private Portal _portal;

	private long _resendEmailTimeout;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.multi.factor.authentication.checker.email.otp.web)"
	)
	private ServletContext _servletContext;

	@Reference
	private UserLocalService _userLocalService;

	private long _validationExpirationTime;

}