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
import com.liferay.multi.factor.authentication.integration.internal.configuration.MFAIntegrationVerificationConfiguration;
import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.integration.MFAIntegration;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
	configurationPid = "com.liferay.multi.factor.authentication.integration.internal.configuration.MFAIntegrationVerificationConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	service = MFAIntegrationVerification.class
)
public class MFAIntegrationVerification {

	public String getIntegrationName() {
		return _mfaIntegrationName;
	}

	public List<List<MFAChecker>> getMFACheckersList(MFARegistry mfaRegistry) {
		MFAIntegration mfaIntegration = mfaRegistry.getMFAIntegration(
			_mfaIntegrationName);

		if (mfaIntegration == null) {
			_log.error(
				StringBundler.concat(
					"MFA Integration '", _mfaIntegrationName,
					"' is not registered!"));

			return null;
		}

		String[] verifierNamesArray =
			_mfaIntegrationVerificationConfiguration.verifierNames();

		List<List<MFAChecker>> mfaCheckersList = new ArrayList(
			verifierNamesArray.length);

		for (String verifierNames : verifierNamesArray) {
			String[] verifierNamesList = StringUtil.split(verifierNames);

			List<MFAChecker> mfaCheckers = new ArrayList<>(
				verifierNamesList.length);

			for (String verifierName : verifierNamesList) {
				verifierName = StringUtil.trim(verifierName);

				MFAChecker mfaChecker = mfaRegistry.getMFAChecker(verifierName);

				if (mfaChecker == null) {
					_log.error(
						StringBundler.concat(
							"MFA integration verification '",
							_mfaIntegrationName,
							"' contains unknown MFA Verifier '",
							_mfaIntegrationName, "'"));

					return null;
				}

				if ((mfaIntegration.supportsHeadless() &&
					 mfaChecker.supportsHeadless()) ||
					(mfaIntegration.supportsBrowser() &&
					 mfaChecker.supportsBrowser())) {

					mfaCheckers.add(mfaChecker);

					_mfaCheckersNames.add(mfaChecker.getName());
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
							"MFA Verifier ", verifierName,
							" is not compatible with integration ",
							mfaIntegration.getName(),
							". The integration supports ",
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
				MFAIntegrationVerificationConfiguration.class, properties);

		_mfaIntegrationName = StringUtil.trim(
			_mfaIntegrationVerificationConfiguration.integrationName());

		String[] verifierNamesArray =
			_mfaIntegrationVerificationConfiguration.verifierNames();

		for (String verifierNames : verifierNamesArray) {
			String[] verifierNamesList = StringUtil.split(verifierNames);

			for (String verifierName : verifierNamesList) {
				_mfaCheckersNames.add(StringUtil.trim(verifierName));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MFAIntegrationVerification.class);

	private final Set<String> _mfaCheckersNames = new HashSet<>();
	private String _mfaIntegrationName;
	private MFAIntegrationVerificationConfiguration
		_mfaIntegrationVerificationConfiguration;
	private List<List<String>> mfaCheckersList;

}