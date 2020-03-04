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

package com.liferay.multi.factor.authentication.impl.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Tomas Polesovsky
 */
@ExtendedObjectClassDefinition(
	category = "multi-factor-authentication",
	factoryInstanceLabelAttribute = "mfaIntegrationName"
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.multi.factor.authentication.impl.configuration.MFAVerificationConfiguration",
	localization = "content/Language",
	name = "mfa-verification-configuration-name"
)
public interface MFAVerificationConfiguration {

	@Meta.AD(
		description = "mfa-integration-name-description",
		name = "mfa-integration-name", required = false
	)
	public String mfaIntegrationName();

	@Meta.AD(
		description = "mfa-checker-names-description",
		name = "mfa-checker-names", required = false
	)
	public String[] mfaCheckerNames();

}