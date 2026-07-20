/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.document.library.internal.item.action;

import com.liferay.content.dashboard.item.action.ContentDashboardItemVersionAction;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;

import java.util.Locale;

/**
 * @author David Truong
 */
public class AddToLaunchFileVersionContentDashboardItemVersionAction
	implements ContentDashboardItemVersionAction {

	public AddToLaunchFileVersionContentDashboardItemVersionAction(
		FileVersion fileVersion, Language language) {

		_fileVersion = fileVersion;
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
			"?className=", FileEntry.class.getName(), "&classPK=",
			_fileVersion.getFileEntryId(), "&classVersion=",
			_fileVersion.getVersion());
	}

	private final FileVersion _fileVersion;
	private final Language _language;

}