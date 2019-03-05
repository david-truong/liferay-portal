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

package com.liferay.multi.factor.authentication.api;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.integration.MFAIntegration;

import java.util.List;
import java.util.Set;

/**
 * @author Tomas Polesovsky
 */
@ProviderType
public interface MFARegistry {

	public MFAChecker getMFAChecker(String name);

	public Set<String> getMFACheckerIntegrationNames(String mfaCheckerName);

	public List<MFAChecker> getMFACheckers();

	public MFAIntegration getMFAIntegration(String name);

	public MFAChecker getMFAIntegrationChecker(String mfaIntegrationName);

	public List<MFAIntegration> getMFAIntegrations();

}