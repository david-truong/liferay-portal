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

import org.eclipse.osgi.internal.hookregistry.ActivatorHookFactory;
import org.eclipse.osgi.internal.hookregistry.HookConfigurator;
import org.eclipse.osgi.internal.hookregistry.HookRegistry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Peter Shin
 */
public class AppHealthCheckHookConfigurator
	implements ActivatorHookFactory, BundleActivator, HookConfigurator {

	public AppHealthCheckHookConfigurator() {
		_appHealthCheckSynchronousBundleListener =
			new AppHealthCheckSynchronousBundleListener();
	}

	@Override
	public void addHooks(HookRegistry hookRegistry) {
		hookRegistry.addActivatorHookFactory(this);
	}

	@Override
	public BundleActivator createActivator() {
		return this;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		bundleContext.addBundleListener(
			_appHealthCheckSynchronousBundleListener);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.removeBundleListener(
			_appHealthCheckSynchronousBundleListener);
	}

	private final AppHealthCheckSynchronousBundleListener
		_appHealthCheckSynchronousBundleListener;

}