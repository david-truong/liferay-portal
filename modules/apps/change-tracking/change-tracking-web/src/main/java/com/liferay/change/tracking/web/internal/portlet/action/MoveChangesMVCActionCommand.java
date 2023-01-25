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

package com.liferay.change.tracking.web.internal.portlet.action;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cheryl Tang
 */
@Component(
	property = {
		"javax.portlet.name=" + CTPortletKeys.PUBLICATIONS,
		"mvc.command.name=/change_tracking/move_changes"
	},
	service = MVCActionCommand.class
)
public class MoveChangesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] modelClassNameIds = ParamUtil.getLongValues(
			actionRequest, "modelClassNameIds");

		long[] modelClassPKs = ParamUtil.getLongValues(
			actionRequest, "modelClassPKs");

		if (modelClassNameIds.length != modelClassPKs.length) {
			throw new IllegalArgumentException(
				"Length of modelClassNameIds and modelClassPKs do not match");
		}

		long sourceCtCollectionId = ParamUtil.getLong(
			actionRequest, "sourceCtCollectionId");

		long targetCtCollectionId = ParamUtil.getLong(
			actionRequest, "targetCTCollectionId");

		if ((sourceCtCollectionId != targetCtCollectionId) &&
			(targetCtCollectionId != CTConstants.CT_COLLECTION_ID_PRODUCTION)) {

			for (int i = 0; i < modelClassNameIds.length; i++) {
				_ctCollectionLocalService.moveCTEntries(
					sourceCtCollectionId, modelClassNameIds[i],
					modelClassPKs[i], targetCtCollectionId);
			}
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse,
			JSONUtil.put(
				"redirect", true
			).put(
				"redirectURL", redirect
			));
	}

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

}