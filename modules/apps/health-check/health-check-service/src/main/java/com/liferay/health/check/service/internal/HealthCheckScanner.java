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

package com.liferay.health.check.service.internal;

import com.liferay.health.check.service.internal.configuration.HealthCheckScannerConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;

import org.apache.felix.service.command.CommandProcessor;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.runtime.ServiceComponentRuntime;

/**
 * @author Peter Shin
 */
@Component(
	configurationPid = "com.liferay.health.check.service.internal.configuration.HealthCheckScannerConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE
)
public class HealthCheckScanner {

	@Activate
	protected void activate(ComponentContext componentContext)
		throws Exception {

		HealthCheckScannerConfiguration healthCheckScannerConfiguration =
			ConfigurableUtil.createConfigurable(
				HealthCheckScannerConfiguration.class,
				componentContext.getProperties());

		boolean listAllBundles =
			healthCheckScannerConfiguration.healthCheckListAllBundles();
		long scanningInterval =
			healthCheckScannerConfiguration.healthCheckScanningInterval();

		if (scanningInterval > 0) {
			_healthCheckScanningThread = new HealthCheckScanningThread(
				scanningInterval * Time.SECOND, listAllBundles,
				_serviceComponentRuntime, _commandProcessor);

			_healthCheckScanningThread.start();
		}
	}

	@Deactivate
	protected void deactivate() throws InterruptedException {
		if (_healthCheckScanningThread != null) {
			_healthCheckScanningThread.interrupt();

			_healthCheckScanningThread.join();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HealthCheckScanner.class);

	@Reference
	private CommandProcessor _commandProcessor;

	private Thread _healthCheckScanningThread;

	@Reference
	private ServiceComponentRuntime _serviceComponentRuntime;

	private static class HealthCheckScanningThread extends Thread {

		@Override
		public void run() {
			try {
				HealthCheckUtil.waitForPortalStartup();

				while (true) {
					sleep(_scanningInterval);

					Bundle bundle = FrameworkUtil.getBundle(
						HealthCheckScanner.class);

					BundleContext bundleContext = bundle.getBundleContext();

					HealthCheckUtil.listBundles(
						_serviceComponentRuntime, bundleContext.getBundles(),
						_listAllBundles);

					HealthCheckUtil.diagnostics(_commandProcessor);
				}
			}
			catch (InterruptedException ie) {
				if (_log.isInfoEnabled()) {
					_log.info("Stopped scanner", ie);
				}
			}
		}

		private HealthCheckScanningThread(
			long scanningInterval, boolean listAllBundles,
			ServiceComponentRuntime serviceComponentRuntime,
			CommandProcessor commandProcessor) {

			_scanningInterval = scanningInterval;
			_listAllBundles = listAllBundles;
			_serviceComponentRuntime = serviceComponentRuntime;
			_commandProcessor = commandProcessor;

			setDaemon(true);
			setName("Health Check Scanner");
		}

		private final CommandProcessor _commandProcessor;
		private final boolean _listAllBundles;
		private final long _scanningInterval;
		private final ServiceComponentRuntime _serviceComponentRuntime;

	}

}