/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.journal.internal.item.action;

import com.liferay.content.dashboard.item.action.ContentDashboardItemAction;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;

import java.util.Locale;

/**
 * @author David Truong
 */
public class AddToLaunchJournalArticleContentDashboardItemAction
	implements ContentDashboardItemAction {

	public AddToLaunchJournalArticleContentDashboardItemAction(
		Language language) {

		_language = language;
	}

	@Override
	public String getIcon() {
		return "arrow-right-full";
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "add-to-launch");
	}

	@Override
	public String getName() {
		return "addToLaunch";
	}

	@Override
	public Type getType() {
		return Type.ADD_TO_LAUNCH;
	}

	@Override
	public String getURL() {
		return StringPool.BLANK;
	}

	@Override
	public String getURL(Locale locale) {
		return StringPool.BLANK;
	}

	private final Language _language;

}