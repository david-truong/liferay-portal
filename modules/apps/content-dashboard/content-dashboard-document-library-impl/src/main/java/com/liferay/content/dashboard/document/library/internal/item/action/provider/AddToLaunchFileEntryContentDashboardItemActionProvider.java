/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.document.library.internal.item.action.provider;

import com.liferay.content.dashboard.document.library.internal.item.action.AddToLaunchFileEntryContentDashboardItemAction;
import com.liferay.content.dashboard.item.action.ContentDashboardItemAction;
import com.liferay.content.dashboard.item.action.provider.ContentDashboardItemActionProvider;
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
@Component(service = ContentDashboardItemActionProvider.class)
public class AddToLaunchFileEntryContentDashboardItemActionProvider
	implements ContentDashboardItemActionProvider<FileEntry> {

	@Override
	public ContentDashboardItemAction getContentDashboardItemAction(
		FileEntry fileEntry, HttpServletRequest httpServletRequest) {

		if (!isShow(fileEntry, httpServletRequest)) {
			return null;
		}

		return new AddToLaunchFileEntryContentDashboardItemAction(_language);
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
		FileEntry fileEntry, HttpServletRequest httpServletRequest) {

		if (!FeatureFlagManagerUtil.isEnabled(
				fileEntry.getCompanyId(), "LPD-72278")) {

			return false;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		try {
			FileVersion fileVersion = fileEntry.getFileVersion();

			if (fileVersion.getStatus() != WorkflowConstants.STATUS_DRAFT) {
				return false;
			}

			return _modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), fileEntry,
				ActionKeys.UPDATE);
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddToLaunchFileEntryContentDashboardItemActionProvider.class);

	@Reference
	private Language _language;

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.repository.model.FileEntry)"
	)
	private ModelResourcePermission<FileEntry> _modelResourcePermission;

}