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

package com.liferay.multi.factor.authentication.checker.totp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MFATOTP service. Represents a row in the &quot;MFATOTP&quot; database table, with each column mapped to a property of this class.
 *
 * @author arthurchan35
 * @see MFATOTPModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.multi.factor.authentication.checker.totp.model.impl.MFATOTPImpl"
)
@ProviderType
public interface MFATOTP extends MFATOTPModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.multi.factor.authentication.checker.totp.model.impl.MFATOTPImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MFATOTP, Long> MFA_TOTP_ID_ACCESSOR =
		new Accessor<MFATOTP, Long>() {

			@Override
			public Long get(MFATOTP mfatotp) {
				return mfatotp.getMfaTOTPId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MFATOTP> getTypeClass() {
				return MFATOTP.class;
			}

		};

}