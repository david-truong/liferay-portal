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

package com.liferay.multi.factor.authentication.checker.timebased.otp.service.impl;

import com.liferay.multi.factor.authentication.checker.timebased.otp.model.TimebasedOTPEntry;
import com.liferay.multi.factor.authentication.checker.timebased.otp.service.base.TimebasedOTPEntryLocalServiceBaseImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the timebased otp entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.multi.factor.authentication.checker.timebased.otp.service.TimebasedOTPEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author arthurchan35
 * @see TimebasedOTPEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.multi.factor.authentication.checker.timebased.otp.model.TimebasedOTPEntry",
	service = AopService.class
)
public class TimebasedOTPEntryLocalServiceImpl
	extends TimebasedOTPEntryLocalServiceBaseImpl {

	@Override
	public TimebasedOTPEntry addTimebasedOTPEntry(
			String mfaCheckerName, String sharedSecret, long userId)
		throws PortalException {

		TimebasedOTPEntry timebasedOTPEntry =
			timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(
				userId, mfaCheckerName);

		if (timebasedOTPEntry != null) {
			throw new IllegalArgumentException(
				StringBundler.concat(
					"There is already one TOTP Entry for user ", userId,
					" and checker ", mfaCheckerName));
		}

		User user = userLocalService.getUserById(userId);

		long entryId = counterLocalService.increment();

		timebasedOTPEntry = timebasedOTPEntryPersistence.create(entryId);

		timebasedOTPEntry.setCompanyId(user.getCompanyId());
		timebasedOTPEntry.setUserId(userId);
		timebasedOTPEntry.setUserName(user.getFullName());
		timebasedOTPEntry.setCreateDate(new Date());
		timebasedOTPEntry.setMfaCheckerName(mfaCheckerName);
		timebasedOTPEntry.setSharedSecret(sharedSecret);

		timebasedOTPEntryPersistence.update(timebasedOTPEntry);

		return timebasedOTPEntry;
	}

	@Override
	public TimebasedOTPEntry fetchTimebasedOTPEntry(
		long userId, String mfaCheckerName) {

		return timebasedOTPEntryPersistence.fetchByU_M(userId, mfaCheckerName);
	}

	@Override
	public List<TimebasedOTPEntry> geTimebasedOTPEntriesByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		return timebasedOTPEntryPersistence.findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	@Override
	public List<TimebasedOTPEntry> getTimebasedOTPEntriesByUserId(
		long userId, int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		return timebasedOTPEntryPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	@Override
	public boolean updateFailedAttempt(
		String mfaCheckerName, long userId, String userIP) {

		TimebasedOTPEntry mfaEmailOTP =
			timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(
				userId, mfaCheckerName);

		if (mfaEmailOTP == null) {
			return false;
		}

		mfaEmailOTP.setFailedAttempts(mfaEmailOTP.getFailedAttempts() + 1);
		mfaEmailOTP.setLastFailDate(new Date());
		mfaEmailOTP.setLastFailIP(userIP);

		timebasedOTPEntryPersistence.update(mfaEmailOTP);

		return true;
	}

	@Override
	public boolean updateSuccessAttempt(
		String mfaCheckerName, long userId, String userIP) {

		TimebasedOTPEntry mfaEmailOTP =
			timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(
				userId, mfaCheckerName);

		if (mfaEmailOTP == null) {
			return false;
		}

		mfaEmailOTP.setFailedAttempts(0);
		mfaEmailOTP.setLastSuccessDate(new Date());
		mfaEmailOTP.setLastSuccessIP(userIP);

		timebasedOTPEntryPersistence.update(mfaEmailOTP);

		return true;
	}

}