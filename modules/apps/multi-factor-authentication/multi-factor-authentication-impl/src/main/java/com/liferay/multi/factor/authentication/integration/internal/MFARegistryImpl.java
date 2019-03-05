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

package com.liferay.multi.factor.authentication.integration.internal;

import com.liferay.multi.factor.authentication.api.MFARegistry;
import com.liferay.multi.factor.authentication.integration.internal.verifier.MandatoryCompositeMFAChecker;
import com.liferay.multi.factor.authentication.integration.internal.verifier.OptionalCompositeMFAChecker;
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
		Set<String> verifierIntegrationNames = new HashSet<>();

		for (MFAIntegrationVerification mfaIntegrationVerification :
				_mfaIntegrationVerificationServiceTrackerMap.values()) {

			if (mfaIntegrationVerification.hasMFAChecker(mfaCheckerName)) {
				verifierIntegrationNames.add(
					mfaIntegrationVerification.getIntegrationName());
			}
		}

		return verifierIntegrationNames;
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
		MFAIntegrationVerification mfaIntegrationVerification =
			_mfaIntegrationVerificationServiceTrackerMap.getService(
				mfaIntegrationName);

		if (mfaIntegrationVerification == null) {
			return null;
		}

		List<List<MFAChecker>> mfaCheckersList =
			mfaIntegrationVerification.getMFACheckersList(this);

		if (mfaCheckersList == null) {
			_log.error(
				StringBundler.concat(
					"Unable to continue with MFA verification for '",
					mfaIntegrationName, "', integration verification is ",
					"misconfigured."));

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
		_mfaIntegrationServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, MFAIntegration.class, null,
				ServiceReferenceMapperFactory.create(
					bundleContext,
					(service, emitter) -> emitter.emit(service.getName())));

		_mfaIntegrationVerificationServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, MFAIntegrationVerification.class, null,
				ServiceReferenceMapperFactory.create(
					bundleContext,
					(service, emitter) -> emitter.emit(
						service.getIntegrationName())));

		_mfaCheckersServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, MFAChecker.class, null,
				ServiceReferenceMapperFactory.create(
					bundleContext,
					(service, emitter) -> emitter.emit(service.getName())));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MFARegistryImpl.class);

	private ServiceTrackerMap<String, MFAChecker> _mfaCheckersServiceTrackerMap;
	private ServiceTrackerMap<String, MFAIntegration>
		_mfaIntegrationServiceTrackerMap;
	private ServiceTrackerMap<String, MFAIntegrationVerification>
		_mfaIntegrationVerificationServiceTrackerMap;

}