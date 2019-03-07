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

package com.liferay.multi.factor.authentication.checker.ip.address.web.internal.checker;

import com.liferay.multi.factor.authentication.checker.ip.address.web.internal.configuration.IPAddressConfiguration;
import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.HeadlessMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.security.access.control.AccessControlUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author arthurchan35
 */
@Component(
	configurationPid = "com.liferay.multi.factor.authentication.checker.ip.address.web.internal.configuration.IPAddressConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = MFAChecker.class
)
public class IPAddressMFAChecker
	implements BrowserMFAChecker, HeadlessMFAChecker, MFAChecker {

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
			_servletContext.getRequestDispatcher("/error.jsp");

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			throw new IOException("Unable to include /error.jsp: " + se, se);
		}
	}

	@Override
	public boolean isBrowserVerified(HttpServletRequest request, long userId) {
		return AccessControlUtil.isAccessAllowed(request, _allowedIPsWithMasks);
	}

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public boolean isHeadlessVerified(HttpServletRequest request, long userId) {
		return AccessControlUtil.isAccessAllowed(request, _allowedIPsWithMasks);
	}

	@Override
	public boolean verifyBrowserRequest(
		HttpServletRequest request, HttpServletResponse response, long userId) {

		return AccessControlUtil.isAccessAllowed(request, _allowedIPsWithMasks);
	}

	@Override
	public boolean verifyHeadlessRequest(
		HttpServletRequest request, long userId) {

		return AccessControlUtil.isAccessAllowed(request, _allowedIPsWithMasks);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_ipAddressConfiguration = ConfigurableUtil.createConfigurable(
			IPAddressConfiguration.class, properties);

		_enabled = _ipAddressConfiguration.enabled();
		_name = _ipAddressConfiguration.name();
		_allowedIPsWithMasks = new HashSet<>(
			Arrays.asList(_ipAddressConfiguration.allowedIPsWithMasks()));
	}

	private Set<String> _allowedIPsWithMasks;
	private boolean _enabled;
	private IPAddressConfiguration _ipAddressConfiguration;
	private String _name;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.multi.factor.authentication.checker.ip.address.web)"
	)
	private ServletContext _servletContext;

	@Reference
	private UserLocalService _userLocalService;

}