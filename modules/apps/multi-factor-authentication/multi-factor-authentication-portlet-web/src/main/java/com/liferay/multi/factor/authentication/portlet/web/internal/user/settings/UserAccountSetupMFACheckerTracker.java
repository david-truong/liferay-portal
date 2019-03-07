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

package com.liferay.multi.factor.authentication.portlet.web.internal.user.settings;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.multi.factor.authentication.api.MFARegistry;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Tomas Polesovsky
 */
@Component(immediate = true)
public class UserAccountSetupMFACheckerTracker {

	private ServiceTracker
		<MFAChecker, ServiceRegistration<ScreenNavigationEntry>>
			_serviceTracker;
	private BundleContext _bundleContext;

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_serviceTracker = ServiceTrackerFactory.open(
			bundleContext, MFAChecker.class,
			new MFACheckerSetupServiceTrackerCustomizer());
	}

	@Deactivate
	protected void deactivate() {
		_serviceTracker.close();
	}

	class MFACheckerSetupServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<MFAChecker, ServiceRegistration<ScreenNavigationEntry>> {

		@Override
		public ServiceRegistration<ScreenNavigationEntry> addingService(
			ServiceReference<MFAChecker> reference) {

			MFAChecker mfaChecker = _bundleContext.getService(reference);

			if (!mfaChecker.supportsSetup()) {
				_bundleContext.ungetService(reference);

				return null;
			}

			MFACheckerSetup mfaCheckerSetup = (MFACheckerSetup)mfaChecker;

			Dictionary<String, Object> dictionary = new HashMapDictionary<>();

			String name = mfaChecker.getName();

			dictionary.put("screen.navigation.entry.order", name.hashCode());

			UserAccountSetupMFAScreenNavigationEntry
				userAccountSetupMFAScreenNavigationEntry =
					new UserAccountSetupMFAScreenNavigationEntry(
						mfaCheckerSetup);

			userAccountSetupMFAScreenNavigationEntry.setServletContext(
				_servletContext);

			userAccountSetupMFAScreenNavigationEntry.setMFARegistry(
				_mfaRegistry);

			return _bundleContext.registerService(
				ScreenNavigationEntry.class,
				userAccountSetupMFAScreenNavigationEntry, dictionary);
		}

		@Override
		public void modifiedService(
			ServiceReference<MFAChecker> reference,
			ServiceRegistration<ScreenNavigationEntry> service) {

			removedService(reference, service);

			addingService(reference);
		}

		@Override
		public void removedService(
			ServiceReference<MFAChecker> reference,
			ServiceRegistration<ScreenNavigationEntry> service) {

			service.unregister();

			_bundleContext.ungetService(reference);
		}

	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.multi.factor.authentication.portlet.web)"
	)
	private ServletContext _servletContext;

	@Reference
	private MFARegistry _mfaRegistry;

}