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

package com.liferay.multi.factor.authentication.checker.totp.service.impl;

import com.liferay.multi.factor.authentication.checker.totp.exception.NoSuchMFATOTPException;
import com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP;
import com.liferay.multi.factor.authentication.checker.totp.service.base.MFATOTPLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the mfatotp local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.multi.factor.authentication.checker.totp.service.MFATOTPLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author arthurchan35
 * @see MFATOTPLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP",
	service = AopService.class
)
public class MFATOTPLocalServiceImpl extends MFATOTPLocalServiceBaseImpl {

	public MFATOTP addMFATOTP(
			String mfaCheckerName, String sharedSecret, long userId)
		throws PortalException {

		List<MFATOTP> mfaTOTPs = mfatotpLocalService.getMFATOTPsByUserId(
			userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		if (mfaTOTPs.size() > 0) {
			throw new NoSuchMFATOTPException(
				"Each user can have only one TOTP configuration");
		}

		User user = userLocalService.getUserById(userId);

		long totpId = counterLocalService.increment();

		MFATOTP mfaTOTP = mfatotpPersistence.create(totpId);

		mfaTOTP.setBackupCodes(""); // TODO
		mfaTOTP.setCompanyId(user.getCompanyId());
		mfaTOTP.setCreateDate(new Date());
		mfaTOTP.setMfaCheckerName(mfaCheckerName);
		mfaTOTP.setSharedSecret(sharedSecret);
		mfaTOTP.setUserId(userId);
		mfaTOTP.setUserName(user.getFullName());

		mfatotpPersistence.update(mfaTOTP);

		return mfaTOTP;
	}

	public List<MFATOTP> getMFATOTPsByUserId(
		long userId, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator) {

		return mfatotpPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	public List<MFATOTP> getMFATOTPsByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator) {

		return mfatotpPersistence.findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	public MFATOTP fetchMFATOTP(String mfaCheckerName, long userId) {
		return mfatotpPersistence.fetchByM_U(mfaCheckerName, userId);
	}

}