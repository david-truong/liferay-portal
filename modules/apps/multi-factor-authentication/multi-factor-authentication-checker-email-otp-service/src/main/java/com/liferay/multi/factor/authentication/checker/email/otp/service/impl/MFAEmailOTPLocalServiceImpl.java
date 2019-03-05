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

package com.liferay.multi.factor.authentication.checker.email.otp.service.impl;

import com.liferay.multi.factor.authentication.checker.email.otp.exception.NoSuchMFAEmailOTPException;
import com.liferay.multi.factor.authentication.checker.email.otp.model.MFAEmailOTP;
import com.liferay.multi.factor.authentication.checker.email.otp.service.base.MFAEmailOTPLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the mfa email otp local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.multi.factor.authentication.checker.email.otp.service.MFAEmailOTPLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author arthurchan35
 * @see MFAEmailOTPLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.multi.factor.authentication.checker.email.otp.model.MFAEmailOTP",
	service = AopService.class
)
public class MFAEmailOTPLocalServiceImpl
	extends MFAEmailOTPLocalServiceBaseImpl {

	public MFAEmailOTP addMFAEmailTOTP(
			String mfaCheckerName, String emailAddress, long userId)
		throws PortalException {

		List<MFAEmailOTP> mfaEmailOTPs =
			mfaEmailOTPLocalService.getMFAEmailOTPsByUserId(
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		if (mfaEmailOTPs.size() > 0) {
			throw new NoSuchMFAEmailOTPException(
				"Each user can have only one Email OTP configuration");
		}

		User user = userLocalService.getUserById(userId);

		long mfaEmailOTPId = counterLocalService.increment();

		MFAEmailOTP mfaEmailOTP = mfaEmailOTPPersistence.create(mfaEmailOTPId);

		mfaEmailOTP.setCompanyId(user.getCompanyId());
		mfaEmailOTP.setUserId(userId);
		mfaEmailOTP.setUserName(user.getFullName());
		mfaEmailOTP.setCreateDate(new Date());
		mfaEmailOTP.setEmailAddress(emailAddress);
		mfaEmailOTP.setMfaCheckerName(mfaCheckerName);

		mfaEmailOTPPersistence.update(mfaEmailOTP);

		return mfaEmailOTP;
	}

	public MFAEmailOTP fetchMFAEmailOTP(String mfaCheckerName, long userId) {
		return mfaEmailOTPPersistence.fetchByM_U(mfaCheckerName, userId);
	}

	public List<MFAEmailOTP> getMFAEmailOTPsByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return mfaEmailOTPPersistence.findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	public List<MFAEmailOTP> getMFAEmailOTPsByUserId(
		long userId, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return mfaEmailOTPPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	public boolean updateFailedAttempt(
		String mfaCheckerName, long userId, String userIP) {

		MFAEmailOTP mfaEmailOTP = mfaEmailOTPLocalService.fetchMFAEmailOTP(
			mfaCheckerName, userId);

		if (mfaEmailOTP == null) {
			return false;
		}

		mfaEmailOTP.setFailedAttempts(mfaEmailOTP.getFailedAttempts() + 1);
		mfaEmailOTP.setLastFailDate(new Date());
		mfaEmailOTP.setLastFailIP(userIP);

		mfaEmailOTPPersistence.update(mfaEmailOTP);

		return true;
	}

	public boolean updateSuccessAttempt(
		String mfaCheckerName, long userId, String userIP) {

		MFAEmailOTP mfaEmailOTP = mfaEmailOTPLocalService.fetchMFAEmailOTP(
			mfaCheckerName, userId);

		if (mfaEmailOTP == null) {
			return false;
		}

		mfaEmailOTP.setFailedAttempts(0);
		mfaEmailOTP.setLastSuccessDate(new Date());
		mfaEmailOTP.setLastSuccessIP(userIP);

		mfaEmailOTPPersistence.update(mfaEmailOTP);

		return true;
	}

}