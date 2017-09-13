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

package com.liferay.portal.app.health.check.hook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.SynchronousBundleListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Peter Shin
 */
public class AppHealthCheckSynchronousBundleListener
	implements SynchronousBundleListener {

	@Override
	public void bundleChanged(BundleEvent bundleEvent) {
		File file = getFile();

		try (Writer writer = new OutputStreamWriter(
				new FileOutputStream(file, true), "UTF-8")) {

			writer.write(getMessage(bundleEvent));
		}
		catch (IOException ioe) {
			_log.error("Unable to write to " + file, ioe);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(getType(bundleEvent) + " " + getSource(bundleEvent));
		}
	}

	protected static File getFile() {
		if (_file == null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			StringBuilder sb = new StringBuilder(4);

			sb.append(System.getProperty("liferay.home"));
			sb.append("/logs/app-health-check.");
			sb.append(dateFormat.format(new Date()));
			sb.append(".txt");

			Path path = Paths.get(sb.toString());

			if (!Files.exists(path)) {
				try {
					Path parentPath = path.getParent();

					if ((parentPath != null) && !Files.exists(parentPath)) {
						Files.createDirectories(parentPath);
					}

					Files.createFile(path);
				}
				catch (IOException ioe) {
					_log.error("Unable to create " + sb.toString(), ioe);
				}
			}

			_file = new File(sb.toString());
		}

		return _file;
	}

	protected static String getMessage(BundleEvent bundleEvent) {
		DateFormat dateFormat = new SimpleDateFormat(_DATE_FORMAT_PATTERN);

		StringBuilder sb = new StringBuilder(6);

		sb.append(dateFormat.format(new Date()));
		sb.append(" ");
		sb.append(getType(bundleEvent));
		sb.append(" ");
		sb.append(getSource(bundleEvent));
		sb.append("\n");

		return sb.toString();
	}

	protected static String getSource(BundleEvent bundleEvent) {
		if (bundleEvent == null) {
			return null;
		}

		if (bundleEvent.getSource() == null) {
			return String.valueOf(bundleEvent);
		}

		return String.valueOf(bundleEvent.getSource());
	}

	protected static String getType(BundleEvent bundleEvent) {
		int type = bundleEvent.getType();

		String label = null;

		if (type == BundleEvent.INSTALLED) {
			label = "INSTALLED";
		}
		else if (type == BundleEvent.LAZY_ACTIVATION) {
			label = "LAZY ACTIVATION";
		}
		else if (type == BundleEvent.RESOLVED) {
			label = "RESOLVED";
		}
		else if (type == BundleEvent.STARTED) {
			label = "STARTED";
		}
		else if (type == BundleEvent.STARTING) {
			label = "STARTING";
		}
		else if (type == BundleEvent.STOPPED) {
			label = "STOPPED";
		}
		else if (type == BundleEvent.STOPPING) {
			label = "STOPPING";
		}
		else if (type == BundleEvent.UNINSTALLED) {
			label = "UNINSTALLED";
		}
		else if (type == BundleEvent.UNRESOLVED) {
			label = "UNRESOLVED";
		}
		else if (type == BundleEvent.UPDATED) {
			label = "UPDATED";
		}
		else {
			label = String.valueOf(type);
		}

		return String.format("%1$-16s", label);
	}

	private static final String _DATE_FORMAT_PATTERN = "MM-dd HH:mm:ss.SSS";

	private static final Logger _log = LoggerFactory.getLogger(
		AppHealthCheckSynchronousBundleListener.class);

	private static File _file;

}