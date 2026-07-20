/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.journal.internal.item.action;

import com.liferay.content.dashboard.item.action.ContentDashboardItemVersionAction;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.language.Language;

import java.util.Locale;

/**
 * @author David Truong
 */
public class AddToLaunchJournalArticleContentDashboardItemVersionAction
	implements ContentDashboardItemVersionAction {

	public AddToLaunchJournalArticleContentDashboardItemVersionAction(
		JournalArticle journalArticle, Language language) {

		_journalArticle = journalArticle;
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
		return StringBundler.concat(
			"?className=", JournalArticle.class.getName(), "&classPK=",
			_journalArticle.getResourcePrimKey(), "&classVersion=",
			_journalArticle.getVersion());
	}

	private final JournalArticle _journalArticle;
	private final Language _language;

}