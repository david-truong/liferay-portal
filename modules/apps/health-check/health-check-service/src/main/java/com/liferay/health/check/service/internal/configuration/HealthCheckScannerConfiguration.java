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

package com.liferay.health.check.service.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Shin
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.health.check.service.internal.configuration.HealthCheckScannerConfiguration",
	localization = "content/Language",
	name = "health-check-scanner-configuration-name"
)
public interface HealthCheckScannerConfiguration {

	@Meta.AD(
		deflt = "false", description = "health-check-list-all-bundles-help",
		required = false
	)
	public boolean healthCheckListAllBundles();

	@Meta.AD(
		deflt = "-1", description = "health-check-scanning-interval-help",
		required = false
	)
	public int healthCheckScanningInterval();

}