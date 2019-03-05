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
import com.liferay.multi.factor.authentication.impl.configuration.MFAVerificationConfiguration;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.integration.MFAIntegration;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.multi.factor.authentication.impl.configuration.MFAVerificationConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	service = MFAVerification.class
)
public class MFAVerification {

	public String getIntegrationName() {
		return _mfaIntegrationName;
	}

	public List<List<MFAChecker>> getMFACheckersList(MFARegistry mfaRegistry) {
		MFAIntegration mfaIntegration = mfaRegistry.getMFAIntegration(
			_mfaIntegrationName);

		if (mfaIntegration == null) {
			_log.error(
				StringBundler.concat(
					"MFA integration '", _mfaIntegrationName,
					"' is not registered!"));

			return null;
		}

		String[] mfaCheckerNamesArray =
			_mfaIntegrationVerificationConfiguration.mfaCheckerNames();

		List<List<MFAChecker>> mfaCheckersList = new ArrayList(
			mfaCheckerNamesArray.length);

		for (String mfaCheckerNames : mfaCheckerNamesArray) {
			String[] mfaCheckerNamesParts = StringUtil.split(mfaCheckerNames);

			List<MFAChecker> mfaCheckers = new ArrayList(
				mfaCheckerNamesParts.length);

			for (String mfaCheckerName : mfaCheckerNamesParts) {
				mfaCheckerName = StringUtil.trim(mfaCheckerName);

				if (Validator.isBlank(mfaCheckerName)) {
					continue;
				}

				MFAChecker mfaChecker = mfaRegistry.getMFAChecker(
					mfaCheckerName);

				if (mfaChecker == null) {
					_log.error(
						StringBundler.concat(
							"MFA integration verification '",
							_mfaIntegrationName,
							"' contains unknown MFA checker '", mfaCheckerName,
							"'"));

					return null;
				}

				if ((mfaIntegration.supportsHeadless() &&
					 mfaChecker.supportsHeadless()) ||
					(mfaIntegration.supportsBrowser() &&
					 mfaChecker.supportsBrowser())) {

					mfaCheckers.add(mfaChecker);
				}
				else {
					String mfaIntegrationSupports = "headless";

					if (mfaIntegration.supportsBrowser()) {
						mfaIntegrationSupports = "browser";
					}

					String mfaCheckerSupports = "headless";

					if (mfaChecker.supportsBrowser()) {
						mfaCheckerSupports = "browser";
					}

					_log.error(
						StringBundler.concat(
							"MFA Checker '", mfaCheckerName,
							"' is not compatible with MFA integration '",
							mfaIntegration.getName(),
							"'. The integration supports ",
							mfaIntegrationSupports, " but verifier supports ",
							mfaCheckerSupports));

					return null;
				}
			}

			if (!mfaCheckers.isEmpty()) {
				mfaCheckersList.add(mfaCheckers);
			}
		}

		return mfaCheckersList;
	}

	public boolean hasMFAChecker(String mfaCheckerName) {
		return _mfaCheckersNames.contains(mfaCheckerName);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_mfaIntegrationVerificationConfiguration =
			ConfigurableUtil.createConfigurable(
				MFAVerificationConfiguration.class, properties);

		_mfaIntegrationName = StringUtil.trim(
			_mfaIntegrationVerificationConfiguration.mfaIntegrationName());

		String[] verifierNamesArray =
			_mfaIntegrationVerificationConfiguration.mfaCheckerNames();

		for (String verifierNames : verifierNamesArray) {
			String[] verifierNamesList = StringUtil.split(verifierNames);

			for (String verifierName : verifierNamesList) {
				_mfaCheckersNames.add(StringUtil.trim(verifierName));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MFAVerification.class);

	private final Set<String> _mfaCheckersNames = new HashSet<>();
	private String _mfaIntegrationName;
	private MFAVerificationConfiguration
		_mfaIntegrationVerificationConfiguration;

}