/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.blogs.internal.item.action.provider;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.content.dashboard.blogs.internal.item.action.AddToLaunchBlogsEntryContentDashboardItemAction;
import com.liferay.content.dashboard.item.action.ContentDashboardItemAction;
import com.liferay.content.dashboard.item.action.provider.ContentDashboardItemActionProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Truong
 */
@Component(service = ContentDashboardItemActionProvider.class)
public class AddToLaunchBlogsEntryContentDashboardItemActionProvider
	implements ContentDashboardItemActionProvider<BlogsEntry> {

	@Override
	public ContentDashboardItemAction getContentDashboardItemAction(
		BlogsEntry blogsEntry, HttpServletRequest httpServletRequest) {

		if (!isShow(blogsEntry, httpServletRequest)) {
			return null;
		}

		return new AddToLaunchBlogsEntryContentDashboardItemAction(_language);
	}

	@Override
	public String getKey() {
		return "addToLaunch";
	}

	@Override
	public ContentDashboardItemAction.Type getType() {
		return ContentDashboardItemAction.Type.ADD_TO_LAUNCH;
	}

	@Override
	public boolean isShow(
		BlogsEntry blogsEntry, HttpServletRequest httpServletRequest) {

		if (!blogsEntry.isDraft() || blogsEntry.isInTrash() ||
			!FeatureFlagManagerUtil.isEnabled(
				blogsEntry.getCompanyId(), "LPD-72278")) {

			return false;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		try {
			return _modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), blogsEntry,
				ActionKeys.UPDATE);
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddToLaunchBlogsEntryContentDashboardItemActionProvider.class);

	@Reference
	private Language _language;

	@Reference(target = "(model.class.name=com.liferay.blogs.model.BlogsEntry)")
	private ModelResourcePermission<BlogsEntry> _modelResourcePermission;

}