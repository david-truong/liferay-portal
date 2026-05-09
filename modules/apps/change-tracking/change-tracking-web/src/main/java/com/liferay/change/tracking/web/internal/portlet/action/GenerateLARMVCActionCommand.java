/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.web.internal.portlet.action;

import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.web.internal.exporter.CTCollectionLARExporter;
import com.liferay.change.tracking.web.internal.security.permission.resource.CTCollectionPermission;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Truong
 */
@Component(
	property = {
		"jakarta.portlet.name=" + CTPortletKeys.PUBLICATIONS,
		"mvc.command.name=/change_tracking/generate_lar"
	},
	service = MVCActionCommand.class
)
public class GenerateLARMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ctCollectionId = ParamUtil.getLong(
			actionRequest, "ctCollectionId");

		CTCollectionPermission.check(
			themeDisplay.getPermissionChecker(),
			_ctCollectionLocalService.getCTCollection(ctCollectionId),
			ActionKeys.VIEW);

		_ctCollectionLARExporter.exportLayoutsAsFileInBackground(
			ctCollectionId, themeDisplay.getUserId());

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			sendRedirect(actionRequest, actionResponse, redirect);
		}
	}

	@Reference
	private CTCollectionLARExporter _ctCollectionLARExporter;

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

}