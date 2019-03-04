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

package com.liferay.multi.factor.authentication.portlet.web.internal.user.settings;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.multi.factor.authentication.api.MFARegistry;
import com.liferay.multi.factor.authentication.portlet.api.constants.MFAPortletKeys;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.renderer.UserAccountSetupMFARenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.users.admin.constants.UserFormConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Marta Medio
 **/
public class UserAccountSetupMFAScreenNavigationEntry
	implements ScreenNavigationEntry<User> {

	private final UserAccountSetupMFARenderer _userAccountSetupMFAVerifier;
	private MFARegistry _mfaRegistry;

	public UserAccountSetupMFAScreenNavigationEntry(
		UserAccountSetupMFARenderer userAccountSetupMFAVerifier) {

		_userAccountSetupMFAVerifier = userAccountSetupMFAVerifier;
	}

	@Override
	public boolean isVisible(User user, User context) {
		MFAChecker mfaVerifier = (MFAChecker)_userAccountSetupMFAVerifier;

		if (!mfaVerifier.isEnabled()) {
			return false;
		}

		Set<String> verifierIntegrationNames =
			_mfaRegistry.getMFACheckerIntegrationNames(mfaVerifier.getName());

		if (verifierIntegrationNames.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public String getCategoryKey() {
		return MFAPortletKeys.CATEGORY_KEY_MFA;
	}

	@Override
	public String getEntryKey() {
		return ((MFAChecker)_userAccountSetupMFAVerifier).getProviderName();
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, getEntryKey());
	}

	@Override
	public String getScreenNavigationKey() {
		return UserFormConstants.SCREEN_NAVIGATION_KEY_USERS;
	}

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		request.setAttribute(
			UserAccountSetupMFARenderer.class.getName(),
			_userAccountSetupMFAVerifier);

		request.setAttribute("label", getLabel(request.getLocale()));
		request.setAttribute("screenNavigationCategoryKey", getCategoryKey());
		request.setAttribute("screenNavigationEntryKey", getEntryKey());


		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/user_account_setup.jsp");

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			_log.error("Unable to render JSP " + "/user_account_setup.jsp", se);

			throw new IOException(
				"Unable to render " + "/user_account_setup.jsp", se);
		}
	}

	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private ServletContext _servletContext;

	private static Log _log = LogFactoryUtil.getLog(
		UserAccountSetupMFAScreenNavigationEntry.class);

	public void setMFARegistry(MFARegistry mfaRegistry) {
		_mfaRegistry = mfaRegistry;
	}
}