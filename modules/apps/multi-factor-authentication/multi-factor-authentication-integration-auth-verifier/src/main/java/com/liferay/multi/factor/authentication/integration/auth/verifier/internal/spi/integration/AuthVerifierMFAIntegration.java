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

package com.liferay.multi.factor.authentication.integration.auth.verifier.internal.spi.integration;

import com.liferay.multi.factor.authentication.integration.auth.verifier.internal.configuration.AuthVerifierMFAIntegrationConfiguration;
import com.liferay.multi.factor.authentication.spi.integration.MFAIntegration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.multi.factor.authentication.integration.auth.verifier.internal.configuration.AuthVerifierMFAIntegrationConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	service = {AuthVerifierMFAIntegration.class, MFAIntegration.class}
)
public class AuthVerifierMFAIntegration implements MFAIntegration {

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	public boolean isRequireUserSetup() {
		return _requireUserSetup;
	}

	public void setRequireUserSetup(boolean requireUserSetup) {
		_requireUserSetup = requireUserSetup;
	}

	@Override
	public boolean supportsBrowser() {
		return false;
	}

	@Override
	public boolean supportsHeadless() {
		return true;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		AuthVerifierMFAIntegrationConfiguration
			authVerifierMFAIntegrationConfiguration =
				ConfigurableUtil.createConfigurable(
					AuthVerifierMFAIntegrationConfiguration.class, properties);

		_enabled = authVerifierMFAIntegrationConfiguration.enabled();
		_name = authVerifierMFAIntegrationConfiguration.name();
		_requireUserSetup =
			authVerifierMFAIntegrationConfiguration.requireUserSetup();
	}

	private boolean _enabled;
	private String _name;
	private boolean _requireUserSetup;

}