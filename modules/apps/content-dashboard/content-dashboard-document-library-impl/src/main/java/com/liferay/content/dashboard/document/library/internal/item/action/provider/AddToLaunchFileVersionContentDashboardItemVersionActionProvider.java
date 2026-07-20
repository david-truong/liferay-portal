/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.document.library.internal.item.action.provider;

import com.liferay.content.dashboard.document.library.internal.item.action.AddToLaunchFileVersionContentDashboardItemVersionAction;
import com.liferay.content.dashboard.item.action.ContentDashboardItemVersionAction;
import com.liferay.content.dashboard.item.action.provider.ContentDashboardItemVersionActionProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import jakarta.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Truong
 */
@Component(service = ContentDashboardItemVersionActionProvider.class)
public class AddToLaunchFileVersionContentDashboardItemVersionActionProvider
	implements ContentDashboardItemVersionActionProvider<FileVersion> {

	@Override
	public ContentDashboardItemVersionAction
		getContentDashboardItemVersionAction(
			FileVersion fileVersion, HttpServletRequest httpServletRequest) {

		if (!isShow(fileVersion, httpServletRequest)) {
			return null;
		}

		return new AddToLaunchFileVersionContentDashboardItemVersionAction(
			fileVersion, _language);
	}

	@Override
	public boolean isShow(
		FileVersion fileVersion, HttpServletRequest httpServletRequest) {

		if ((fileVersion.getStatus() != WorkflowConstants.STATUS_APPROVED) ||
			!FeatureFlagManagerUtil.isEnabled(
				fileVersion.getCompanyId(), "LPD-72278")) {

			return false;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		try {
			return _modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), fileVersion.getFileEntry(),
				ActionKeys.UPDATE);
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddToLaunchFileVersionContentDashboardItemVersionActionProvider.class);

	@Reference
	private Language _language;

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.repository.model.FileEntry)"
	)
	private ModelResourcePermission<FileEntry> _modelResourcePermission;

}