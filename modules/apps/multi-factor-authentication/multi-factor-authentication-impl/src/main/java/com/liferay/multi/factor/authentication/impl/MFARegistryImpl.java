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

package com.liferay.multi.factor.authentication.impl;

import com.liferay.multi.factor.authentication.api.MFARegistry;
import com.liferay.multi.factor.authentication.impl.checker.MandatoryCompositeMFAChecker;
import com.liferay.multi.factor.authentication.impl.checker.OptionalCompositeMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.integration.MFAIntegration;
import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapperFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Tomas Polesovsky
 */
@Component(immediate = true, service = MFARegistry.class)
public class MFARegistryImpl implements MFARegistry {

	@Override
	public MFAChecker getMFAChecker(String name) {
		return _mfaCheckersServiceTrackerMap.getService(name);
	}

	@Override
	public Set<String> getMFACheckerIntegrationNames(String mfaCheckerName) {
		Set<String> mfaIntegrationNames = new HashSet<>();

		for (MFAVerification mfaVerification :
				_mfaVerificationServiceTrackerMap.values()) {

			if (mfaVerification.hasMFAChecker(mfaCheckerName)) {
				mfaIntegrationNames.add(mfaVerification.getIntegrationName());
			}
		}

		return mfaIntegrationNames;
	}

	@Override
	public List<MFAChecker> getMFACheckers() {
		return new ArrayList(_mfaCheckersServiceTrackerMap.values());
	}

	@Override
	public MFAIntegration getMFAIntegration(String name) {
		return _mfaIntegrationServiceTrackerMap.getService(name);
	}

	@Override
	public MFAChecker getMFAIntegrationChecker(String mfaIntegrationName) {
		MFAVerification mfaVerification =
			_mfaVerificationServiceTrackerMap.getService(mfaIntegrationName);

		if (mfaVerification == null) {
			return null;
		}

		List<List<MFAChecker>> mfaCheckersList =
			mfaVerification.getMFACheckersList(this);

		if (mfaCheckersList == null) {
			_log.error(
				StringBundler.concat(
					"Unable to continue with a misconfigured MFA verification ",
					"for '", mfaIntegrationName));

			return null;
		}

		List<MFAChecker> mandatoryMFACheckers = new ArrayList<>(
			mfaCheckersList.size());

		for (List<MFAChecker> mfaCheckers : mfaCheckersList) {
			if (mfaCheckers.isEmpty()) {
				continue;
			}

			if (mfaCheckers.size() == 1) {
				mandatoryMFACheckers.add(mfaCheckers.get(0));
			}
			else {
				mandatoryMFACheckers.add(
					new OptionalCompositeMFAChecker(mfaCheckers));
			}
		}

		if (mandatoryMFACheckers.isEmpty()) {
			return null;
		}
		else if (mandatoryMFACheckers.size() == 1) {
			return mandatoryMFACheckers.get(0);
		}
		else {
			return new MandatoryCompositeMFAChecker(mandatoryMFACheckers);
		}
	}

	@Override
	public List<MFAIntegration> getMFAIntegrations() {
		return new ArrayList(_mfaIntegrationServiceTrackerMap.values());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_mfaCheckersServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, MFAChecker.class, null,
				ServiceReferenceMapperFactory.create(
					bundleContext,
					(service, emitter) -> emitter.emit(service.getName())));

		_mfaIntegrationServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, MFAIntegration.class, null,
				ServiceReferenceMapperFactory.create(
					bundleContext,
					(service, emitter) -> emitter.emit(service.getName())));

		_mfaVerificationServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, MFAVerification.class, null,
				ServiceReferenceMapperFactory.create(
					bundleContext,
					(service, emitter) -> emitter.emit(
						service.getIntegrationName())));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MFARegistryImpl.class);

	private ServiceTrackerMap<String, MFAChecker> _mfaCheckersServiceTrackerMap;
	private ServiceTrackerMap<String, MFAIntegration>
		_mfaIntegrationServiceTrackerMap;
	private ServiceTrackerMap<String, MFAVerification>
		_mfaVerificationServiceTrackerMap;

}