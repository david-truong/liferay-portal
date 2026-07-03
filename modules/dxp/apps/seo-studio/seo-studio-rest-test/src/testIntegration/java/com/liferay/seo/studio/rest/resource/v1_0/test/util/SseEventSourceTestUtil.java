/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.rest.resource.v1_0.test.util;

import com.liferay.ai.hub.rest.resource.v1_0.util.SseUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsValues;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

/**
 * @author David Truong
 */
public class SseEventSourceTestUtil {

	public static String open(String uri) throws Exception {
		HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();

		HttpClient httpClient = httpClientBuilder.connectTimeout(
			Duration.ofSeconds(5)
		).build();

		String credentials =
			"test@liferay.com:" + PropsValues.DEFAULT_ADMIN_PASSWORD;

		HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder();

		HttpRequest httpRequest = httpRequestBuilder.header(
			"Accept", "text/event-stream"
		).header(
			"Authorization", "Basic " + Base64.encode(credentials.getBytes())
		).uri(
			URI.create(
				StringBundler.concat(
					"http://localhost:", PortalUtil.getPortalServerPort(false),
					"/o/seo-studio/v1.0/", uri))
		).GET(
		).build();

		CompletableFuture<HttpResponse<InputStream>> completableFuture =
			httpClient.sendAsync(
				httpRequest, HttpResponse.BodyHandlers.ofInputStream());

		List<String> lines = new ArrayList<>();
		CountDownLatch openConnectionCountDownLatch = new CountDownLatch(2);

		completableFuture.thenAccept(
			response -> {
				try (InputStream inputStream = response.body();

					BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream))) {

					String line = "";

					while ((line = bufferedReader.readLine()) != null) {
						if (line.isEmpty()) {
							continue;
						}

						lines.add(line);

						openConnectionCountDownLatch.countDown();
					}
				}
				catch (Exception exception) {
					_log.error(exception);
				}
			});

		Assert.assertTrue(
			openConnectionCountDownLatch.await(10, TimeUnit.SECONDS));

		Assert.assertEquals(lines.toString(), 2, lines.size());
		Assert.assertEquals("event: Subscribe", lines.get(0));

		Set<String> sseEventSinksKeys = SseUtil.getSSEEventSinksKeys();

		Assert.assertEquals(
			sseEventSinksKeys.toString(), 1, sseEventSinksKeys.size());

		Iterator<String> iterator = sseEventSinksKeys.iterator();

		String sseEventSinkKey = iterator.next();

		Assert.assertEquals("data: " + sseEventSinkKey, lines.get(1));

		return sseEventSinkKey;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SseEventSourceTestUtil.class);

}