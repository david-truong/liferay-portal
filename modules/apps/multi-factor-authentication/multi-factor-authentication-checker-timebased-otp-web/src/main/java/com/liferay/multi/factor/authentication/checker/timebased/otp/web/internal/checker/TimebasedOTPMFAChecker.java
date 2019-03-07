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

package com.liferay.multi.factor.authentication.checker.timebased.otp.web.internal.checker;

import com.liferay.multi.factor.authentication.checker.timebased.otp.model.TimebasedOTPEntry;
import com.liferay.multi.factor.authentication.checker.timebased.otp.service.TimebasedOTPEntryLocalService;
import com.liferay.multi.factor.authentication.checker.timebased.otp.web.internal.configuration.TimebasedOTPConfiguration;
import com.liferay.multi.factor.authentication.checker.timebased.otp.web.internal.util.TOTPUtil;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.HeadlessMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.io.BigEndianCodec;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.SecureRandomUtil;
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

import jodd.util.Base32;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.multi.factor.authentication.checker.timebased.otp.web.internal.configuration.TimebasedOTPConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = MFAChecker.class
)
public class TimebasedOTPMFAChecker
	implements BrowserMFAChecker, HeadlessMFAChecker, MFAChecker,
			   MFACheckerSetup {

	@Override
	public boolean forceUserSetup(long userId) {
		if (isUserSetUp(userId)) {
			return false;
		}

		if (_forceUserSetup) {
			return true;
		}

		return false;
	}

	@Override
	public String getLabel(Locale locale) {
		return _label;
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

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/verify_totp.jsp");

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			throw new IOException(
				"Unable to include /verify_totp.jsp: " + se, se);
		}
	}

	@Override
	public void includeSetup(
			long userId, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException {

		TimebasedOTPEntry timebasedOTPEntry =
			_timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(
				userId, _name);

		if (timebasedOTPEntry != null) {
			request.setAttribute(
				TimebasedOTPEntry.class.getName(), timebasedOTPEntry);
		}
		else {
			String sharedSecret = generateSharedSecret();

			request.setAttribute("sharedSecret", sharedSecret);

			HttpServletRequest originalServletRequest =
				_portal.getOriginalServletRequest(request);

			HttpSession session = originalServletRequest.getSession();

			session.setAttribute("sharedSecret", sharedSecret);
		}

		request.setAttribute(
			"mfaUser", _userLocalService.fetchUserById(userId));

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/setup_totp.jsp");

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			throw new IOException(
				"Unable to include /setup_totp.jsp: " + se, se);
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

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public boolean isHeadlessVerified(HttpServletRequest request, long userId) {
		return false;
	}

	@Override
	public boolean isUserSetupComplete(long userId) {
		return isUserSetUp(userId);
	}

	@Override
	public boolean setup(HttpServletRequest request, long userId) {
		HttpServletRequest originalServletRequest =
			_portal.getOriginalServletRequest(request);

		HttpSession session = originalServletRequest.getSession();

		String sharedSecret = (String)session.getAttribute("sharedSecret");

		String totpValue = ParamUtil.getString(request, "totp");

		try {
			if (TOTPUtil.verifyTOTP(
					Base32.decode(sharedSecret), totpValue, _clockSkew,
					_timeWindow, _digitsCount, _algorithm)) {

				TimebasedOTPEntry timebasedOTPEntry =
					_timebasedOTPEntryLocalService.addTimebasedOTPEntry(
						_name, sharedSecret, userId);

				if (timebasedOTPEntry != null) {
					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(
				StringBundler.concat(
					"Unable to generate TOTP value for user ", userId, ": ",
					e.getMessage()),
				e);
		}

		return false;
	}

	@Override
	public boolean supportsBrowser() {
		return true;
	}

	@Override
	public boolean supportsHeadless() {
		return true;
	}

	@Override
	public boolean supportsSetup() {
		return true;
	}

	@Override
	public boolean verifyBrowserRequest(
		HttpServletRequest request, HttpServletResponse response, long userId) {

		if (!isUserSetUp(userId)) {
			return false;
		}

		String totpValue = ParamUtil.getString(request, "totp");

		if (Validator.isBlank(totpValue)) {
			return false;
		}

		boolean verified = verify(totpValue, userId);

		HttpServletRequest originalRequest = _portal.getOriginalServletRequest(
			request);

		String userIP = originalRequest.getRemoteAddr();

		if (verified) {
			long validatedAt = System.currentTimeMillis();

			HttpSession session = originalRequest.getSession();

			Map<String, Object> validatedMap =
				(Map<String, Object>)session.getAttribute(_VALIDATED);

			if (validatedMap == null) {
				validatedMap = new HashMap(2);

				session.setAttribute(_VALIDATED, validatedMap);
			}

			validatedMap.put(_name + "validatedAt", validatedAt);
			validatedMap.put(_name + "userId", userId);

			_timebasedOTPEntryLocalService.updateSuccessAttempt(
				_name, userId, userIP);
		}
		else {
			_timebasedOTPEntryLocalService.updateFailedAttempt(
				_name, userId, userIP);
		}

		return verified;
	}

	@Override
	public boolean verifyHeadlessRequest(
		HttpServletRequest request, long userId) {

		if (!isUserSetUp(userId)) {
			return false;
		}

		String totpValue = request.getHeader(_headlessHeaderName);

		if (Validator.isBlank(totpValue)) {
			return false;
		}

		boolean verified = verify(totpValue, userId);

		if (verified) {
			_timebasedOTPEntryLocalService.updateSuccessAttempt(
				_name, userId, request.getRemoteAddr());
		}
		else {
			_timebasedOTPEntryLocalService.updateFailedAttempt(
				_name, userId, request.getRemoteAddr());
		}

		return verified;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		TimebasedOTPConfiguration totpConfiguration =
			ConfigurableUtil.createConfigurable(
				TimebasedOTPConfiguration.class, properties);

		_algorithm = totpConfiguration.algorithm();
		_algorithmKeySize = totpConfiguration.algorithmKeySize();
		_clockSkew = totpConfiguration.clockSkew();
		_digitsCount = totpConfiguration.digitsCount();
		_enabled = totpConfiguration.enabled();
		_forceUserSetup = totpConfiguration.forceUserSetup();
		_headlessHeaderName = totpConfiguration.headlessHeaderName();
		_label = totpConfiguration.label();
		_name = totpConfiguration.name();
		_timeWindow = totpConfiguration.timeWindow();
		_validationExpirationTime =
			totpConfiguration.validationExpirationTime();

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

	protected boolean isUserSetUp(long userId) {
		TimebasedOTPEntry timebasedOTPEntry =
			_timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(
				userId, _name);

		if (timebasedOTPEntry != null) {
			return true;
		}

		return false;
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

	protected boolean verify(String totpValue, long userId) {
		TimebasedOTPEntry timebasedOTPEntry =
			_timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(
				userId, _name);

		if (timebasedOTPEntry != null) {
			try {
				return TOTPUtil.verifyTOTP(
					Base32.decode(timebasedOTPEntry.getSharedSecret()),
					totpValue, _clockSkew, _timeWindow, _digitsCount,
					_algorithm);
			}
			catch (Exception e) {
				_log.error(
					StringBundler.concat(
						"Unable to generate TOTP value for user ", userId, ": ",
						e.getMessage()),
					e);

				return false;
			}
		}

		return false;
	}

	private String generateSharedSecret() {
		int count = (int)Math.ceil((double)_algorithmKeySize / 8);

		byte[] buffer = new byte[count * 8];

		for (int i = 0; i < count; i++) {
			BigEndianCodec.putLong(buffer, i * 8, SecureRandomUtil.nextLong());
		}

		byte[] secret = new byte[_algorithmKeySize];

		System.arraycopy(buffer, 0, secret, 0, _algorithmKeySize);

		return Base32.encode(secret);
	}

	private static final String _VALIDATED =
		TimebasedOTPMFAChecker.class.getName() + "#VALIDATED";

	private static final Log _log = LogFactoryUtil.getLog(
		TimebasedOTPMFAChecker.class);

	private String _algorithm = "HmacSHA1";
	private int _algorithmKeySize = 20;
	private long _clockSkew = 3 * 1000;
	private int _digitsCount = 6;
	private boolean _enabled;
	private boolean _forceUserSetup;
	private String _headlessHeaderName;
	private String _label;
	private String _name;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.multi.factor.authentication.checker.timebased.otp.web)"
	)
	private ServletContext _servletContext;

	@Reference
	private TimebasedOTPEntryLocalService _timebasedOTPEntryLocalService;

	private long _timeWindow = 30 * 1000;

	@Reference
	private UserLocalService _userLocalService;

	private long _validationExpirationTime;

}