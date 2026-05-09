/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.web.internal.exporter;

import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.service.CTEntryLocalService;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationParameterMapFactory;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationSettingsMapFactory;
import com.liferay.exportimport.kernel.configuration.constants.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.exportimport.kernel.service.ExportImportService;
import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Truong
 */
@Component(service = CTCollectionLARExporter.class)
public class CTCollectionLARExporter {

	public void exportLayoutsAsFileInBackground(
			long ctCollectionId, long userId)
		throws PortalException {

		long layoutClassNameId = _portal.getClassNameId(Layout.class);

		Map<LayoutSetKey, List<Long>> layoutIdsMap = new HashMap<>();

		for (CTEntry ctEntry :
				_ctEntryLocalService.getCTEntries(
					ctCollectionId, layoutClassNameId)) {

			Layout layout = _layoutLocalService.fetchLayout(
				ctEntry.getModelClassPK());

			if (layout == null) {
				continue;
			}

			LayoutSetKey layoutSetKey = new LayoutSetKey(
				layout.getGroupId(), layout.isPrivateLayout());

			List<Long> layoutIds = layoutIdsMap.computeIfAbsent(
				layoutSetKey, key -> new ArrayList<>());

			layoutIds.add(layout.getLayoutId());
		}

		if (layoutIdsMap.isEmpty()) {
			return;
		}

		User user = _userLocalService.getUser(userId);

		Map<String, String[]> parameterMap =
			_exportImportConfigurationParameterMapFactory.buildParameterMap();

		for (Map.Entry<LayoutSetKey, List<Long>> entry :
				layoutIdsMap.entrySet()) {

			LayoutSetKey layoutSetKey = entry.getKey();

			long[] layoutIds = ArrayUtil.toLongArray(entry.getValue());

			Map<String, Serializable> settingsMap =
				_exportImportConfigurationSettingsMapFactory.
					buildExportLayoutSettingsMap(
						user, layoutSetKey._groupId,
						layoutSetKey._privateLayout, layoutIds, parameterMap);

			ExportImportConfiguration exportImportConfiguration =
				_exportImportConfigurationLocalService.
					addDraftExportImportConfiguration(
						userId,
						ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT,
						settingsMap);

			_exportImportService.exportLayoutsAsFileInBackground(
				exportImportConfiguration);
		}
	}

	@Reference
	private CTEntryLocalService _ctEntryLocalService;

	@Reference
	private ExportImportConfigurationLocalService
		_exportImportConfigurationLocalService;

	@Reference
	private ExportImportConfigurationParameterMapFactory
		_exportImportConfigurationParameterMapFactory;

	@Reference
	private ExportImportConfigurationSettingsMapFactory
		_exportImportConfigurationSettingsMapFactory;

	@Reference
	private ExportImportService _exportImportService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

	private static class LayoutSetKey {

		@Override
		public boolean equals(Object object) {
			if (this == object) {
				return true;
			}

			if (!(object instanceof LayoutSetKey)) {
				return false;
			}

			LayoutSetKey layoutSetKey = (LayoutSetKey)object;

			if ((_groupId == layoutSetKey._groupId) &&
				(_privateLayout == layoutSetKey._privateLayout)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hash = HashUtil.hash(0, _groupId);

			return HashUtil.hash(hash, _privateLayout);
		}

		private LayoutSetKey(long groupId, boolean privateLayout) {
			_groupId = groupId;
			_privateLayout = privateLayout;
		}

		private final long _groupId;
		private final boolean _privateLayout;

	}

}