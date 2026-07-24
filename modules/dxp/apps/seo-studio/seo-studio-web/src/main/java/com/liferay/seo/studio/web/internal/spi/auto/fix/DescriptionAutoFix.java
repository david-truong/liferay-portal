/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.web.internal.spi.auto.fix;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.seo.studio.spi.auto.fix.AutoFix;
import com.liferay.seo.studio.spi.auto.fix.BaseAutoFix;

import org.osgi.service.component.annotations.Component;

/**
 * @author David Truong
 */
@Component(service = AutoFix.class)
public class DescriptionAutoFix extends BaseAutoFix {

	@Override
	public String getInsightType() {
		return "missingMetaDescription";
	}

	@Override
	protected String getPatchBody(
		String languageId, JSONObject currentPageSettingsJSONObject,
		String value) {

		return buildPatchBody(
			"description_i18n", languageId, currentPageSettingsJSONObject,
			value);
	}

	@Override
	protected String[] getVerificationPaths(String languageId) {
		return new String[] {
			"JSONObject/pageSettings", "JSONObject/seoSettings",
			"JSONObject/description_i18n", "Object/" + languageId
		};
	}

}