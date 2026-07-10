/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.crawler;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.seo.studio.model.CrawlHit;

import java.net.URI;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Noor Najjar
 */
@Component
public class TitleTagDetectionCrawler extends BaseDetectionCrawler {

	@Override
	public void detect(
			long accountEntryId, List<CrawlHit> crawlHits, URI hostname,
			long seoStudioScanId)
		throws Exception {

		Set<String> pageURLs = new LinkedHashSet<>();

		for (CrawlHit crawlHit : crawlHits) {
			if (Validator.isNotNull(crawlHit.getTitle())) {
				continue;
			}

			String pageURL = getPageURL(crawlHit);

			if (pageURL != null) {
				pageURLs.add(pageURL);
			}
		}

		if (pageURLs.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"No missing or empty title tags were detected for scan " +
						seoStudioScanId);
			}

			return;
		}

		List<String> titleTagPageURLs = new ArrayList<>(pageURLs);

		writeInsights(
			accountEntryId, _DEFINITION_JSON_OBJECT,
			ensurePages(accountEntryId, titleTagPageURLs, seoStudioScanId),
			titleTagPageURLs, seoStudioScanId);
	}

	private static final JSONObject _DEFINITION_JSON_OBJECT = new JSONObject(
	).put(
		"category", "metadata"
	).put(
		"classification", "problem"
	).put(
		"description",
		StringBundler.concat(
			"One or more pages are missing a <title> tag or have an empty ",
			"one. The title tag is the primary label search engines show in ",
			"results and the strongest on-page signal for what a page is ",
			"about; without it, rankings and click-through suffer.")
	).put(
		"name", "missingOrEmptyTitleTag"
	).put(
		"severity", "3"
	);

	private static final Log _log = LogFactory.getLog(
		TitleTagDetectionCrawler.class);

}