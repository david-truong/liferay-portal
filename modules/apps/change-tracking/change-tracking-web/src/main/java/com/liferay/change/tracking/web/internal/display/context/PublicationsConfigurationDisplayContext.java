/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.web.internal.display.context;

import com.liferay.change.tracking.configuration.CTSettingsConfiguration;
import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.web.internal.configuration.helper.CTSettingsConfigurationHelper;
import com.liferay.change.tracking.web.internal.portlet.PublicationsPortlet;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.configuration.kernel.util.PortletConfigurationApplicationType;
import com.liferay.roles.admin.constants.RolesAdminWebKeys;
import com.liferay.roles.admin.role.type.contributor.provider.RoleTypeContributorProvider;
import com.liferay.taglib.security.PermissionsURLTag;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Máté Thurzó
 * @author Samuel Trong Tran
 */
public class PublicationsConfigurationDisplayContext {

	public PublicationsConfigurationDisplayContext(
		CTSettingsConfigurationHelper ctSettingsConfigurationHelper,
		HttpServletRequest httpServletRequest, RenderResponse renderResponse,
		RoleTypeContributorProvider roleTypeContributorProvider) {

		_httpServletRequest = httpServletRequest;
		_renderResponse = renderResponse;
		_roleTypeContributorProvider = roleTypeContributorProvider;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CTSettingsConfiguration ctSettingsConfiguration =
			ctSettingsConfigurationHelper.getCTSettingsConfiguration(
				themeDisplay.getCompanyId());

		_publicationsEnabled = ctSettingsConfiguration.enabled();
		_remoteClientId = ctSettingsConfiguration.remoteClientId();
		_remoteClientSecret = ctSettingsConfiguration.remoteClientSecret();
		_remoteEnabled = ctSettingsConfiguration.remoteEnabled();
		_sandboxOnlyEnabled = ctSettingsConfiguration.sandboxEnabled();
		_unapprovedChangesAllowed =
			ctSettingsConfiguration.unapprovedChangesAllowed();
	}

	public String getActionURL() {
		return PortletURLBuilder.createActionURL(
			_renderResponse
		).setActionName(
			"/change_tracking/update_global_publications_configuration"
		).buildString();
	}

	public String getPermissionsHref()
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String testUrl= StringBundler.concat(
			"javascript:Liferay.Util.openModal({containerProps: {}, ",
			"iframeBodyCssClass: 'dialog-with-footer', title:'",
			LanguageUtil.get(themeDisplay.getLocale(), "permissions"), "', url:'",
			doTag(
				StringPool.BLANK, CTCollection.class.getName(),
				null,
				null,
				CTConstants.RESOURCE_NAME,
				LiferayWindowState.POP_UP.toString(),
//           new int[] {RoleConstants.TYPE_REGULAR}
				null, _httpServletRequest),
			"',});");


		return  testUrl;
	}

	public String doTag(
		String redirect, String modelResource,
		String modelResourceDescription, Object resourceGroupId,
		String resourcePrimKey, String windowState, int[] roleTypes,
		HttpServletRequest httpServletRequest)
		throws Exception {

		return PortletURLBuilder.create(
			_getPorletURL(
				httpServletRequest, modelResource, resourceGroupId, windowState)
		).setRedirect(
			_getRedirect(httpServletRequest, redirect, windowState)
		).setParameter(
			"modelResourceDescription", modelResourceDescription
		).setParameter(
			"resourcePrimKey", resourcePrimKey
		).setParameter(
			"returnToFullPageURL",
			() -> {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)httpServletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				if (!themeDisplay.isStateMaximized()) {
					return _getRedirect(
						httpServletRequest, redirect, windowState);
				}

				return null;
			}
		).setParameter(
			"roleTypes",
			() -> {
				if (roleTypes != null) {
					return StringUtil.merge(roleTypes);
				}

				return null;
			}
		).buildString();
	}

	private PortletURL _getPorletURL(
		HttpServletRequest httpServletRequest, String modelResource,
		Object resourceGroupId, String windowState)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);
		return PortletURLBuilder.create(
			_renderResponse.createRenderURL()
//		).setMVCPath(
//			"/publications/edit_publications_permissions.jsp"
		).setMVCRenderCommandName(
			   "/change_tracking/view_permissions"
		).setPortletResource(
			() -> {
				PortletDisplay portletDisplay =
					themeDisplay.getPortletDisplay();

				return portletDisplay.getId();
			}
		).setParameter(
			"modelResource", modelResource
		).setParameter(
			"portletConfiguration", true
		).setParameter(
			"resourceGroupId",
			_getResourceGroupId(resourceGroupId, themeDisplay)
		).setWindowState(
			LiferayWindowState.POP_UP
		).buildPortletURL();
	}

	private static String _getRedirect(
		HttpServletRequest httpServletRequest, String redirect,
		String windowState) {

		if (Validator.isNotNull(redirect) ||
			(Validator.isNotNull(windowState) &&
			 StringUtil.equals(
				 windowState, LiferayWindowState.POP_UP.toString()))) {

			return redirect;
		}

		return PortalUtil.getCurrentURL(httpServletRequest);
	}

	private static Object _getResourceGroupId(
		Object resourceGroupId, ThemeDisplay themeDisplay) {

		if (resourceGroupId instanceof Number) {
			Number resourceGroupIdNumber = (Number)resourceGroupId;

			if (resourceGroupIdNumber.longValue() < 0) {
				resourceGroupId = null;
			}
		}
		else if (resourceGroupId instanceof String) {
			String resourceGroupIdString = (String)resourceGroupId;

			if (resourceGroupIdString.length() == 0) {
				resourceGroupId = null;
			}
		}

		if (resourceGroupId == null) {
			resourceGroupId = String.valueOf(themeDisplay.getScopeGroupId());
		}

		return resourceGroupId;
	}

	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		if (isPublicationsEnabled()) {
			_navigation = ParamUtil.getString(
				_httpServletRequest, "navigation", "global-settings");
		}
		else {
			_navigation = "global-settings";
		}

		return _navigation;
	}

	public String getRemoteClientId() {
		return _remoteClientId;
	}

	public String getRemoteClientSecret() {
		return _remoteClientSecret;
	}

	public boolean isPublicationsEnabled() {
		return _publicationsEnabled;
	}

	public boolean isRemoteEnabled() {
		return _remoteEnabled;
	}

	public boolean isSandboxOnlyEnabled() {
		return _sandboxOnlyEnabled;
	}

	public boolean isUnapprovedChangesAllowed() {
		return _unapprovedChangesAllowed;
	}

	private final HttpServletRequest _httpServletRequest;
	private String _navigation;
	private final boolean _publicationsEnabled;
	private ThemeDisplay  _themeDisplay;
	private final String _remoteClientId;
	private final String _remoteClientSecret;
	private final boolean _remoteEnabled;
	private final RenderResponse _renderResponse;
	private final boolean _sandboxOnlyEnabled;
	private final boolean _unapprovedChangesAllowed;
	private RoleTypeContributorProvider _roleTypeContributorProvider;

}