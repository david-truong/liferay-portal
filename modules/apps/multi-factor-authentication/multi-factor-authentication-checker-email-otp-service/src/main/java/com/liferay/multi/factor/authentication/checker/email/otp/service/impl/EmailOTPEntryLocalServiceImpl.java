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

import com.liferay.multi.factor.authentication.checker.email.otp.model.EmailOTPEntry;
import com.liferay.multi.factor.authentication.checker.email.otp.service.base.EmailOTPEntryLocalServiceBaseImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the email otp entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.multi.factor.authentication.checker.email.otp.service.EmailOTPEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author arthurchan35
 * @see EmailOTPEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.multi.factor.authentication.checker.email.otp.model.EmailOTPEntry",
	service = AopService.class
)
public class EmailOTPEntryLocalServiceImpl
	extends EmailOTPEntryLocalServiceBaseImpl {

	public EmailOTPEntry addEmailOTPEntry(
			String mfaCheckerName, String emailAddress, long userId)
		throws PortalException {

		EmailOTPEntry emailOTPEntry =
			emailOTPEntryLocalService.fetchEmailOTPEntry(
				userId, mfaCheckerName);

		if (emailOTPEntry != null) {
			throw new IllegalArgumentException(
				StringBundler.concat(
					"There is already one Email OTP Entry for user ", userId,
					" and checker ", mfaCheckerName));
		}

		User user = userLocalService.getUserById(userId);

		long entryId = counterLocalService.increment();

		emailOTPEntry = emailOTPEntryPersistence.create(entryId);

		emailOTPEntry.setCompanyId(user.getCompanyId());
		emailOTPEntry.setUserId(userId);
		emailOTPEntry.setUserName(user.getFullName());
		emailOTPEntry.setCreateDate(new Date());
		emailOTPEntry.setEmailAddress(emailAddress);
		emailOTPEntry.setMfaCheckerName(mfaCheckerName);

		emailOTPEntryPersistence.update(emailOTPEntry);

		return emailOTPEntry;
	}

	public EmailOTPEntry fetchEmailOTPEntry(
		long userId, String mfaCheckerName) {

		return emailOTPEntryPersistence.fetchByU_M(userId, mfaCheckerName);
	}

	public List<EmailOTPEntry> getEmailOTPEntriesByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<EmailOTPEntry> orderByComparator) {

		return emailOTPEntryPersistence.findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	public List<EmailOTPEntry> getEmailOTPEntriesByUserId(
		long userId, int start, int end,
		OrderByComparator<EmailOTPEntry> orderByComparator) {

		return emailOTPEntryPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	public boolean updateFailedAttempt(
		String mfaCheckerName, long userId, String userIP) {

		EmailOTPEntry emailOTPEntry =
			emailOTPEntryLocalService.fetchEmailOTPEntry(
				userId, mfaCheckerName);

		if (emailOTPEntry == null) {
			return false;
		}

		emailOTPEntry.setFailedAttempts(emailOTPEntry.getFailedAttempts() + 1);
		emailOTPEntry.setLastFailDate(new Date());
		emailOTPEntry.setLastFailIP(userIP);

		emailOTPEntryPersistence.update(emailOTPEntry);

		return true;
	}

	public boolean updateSuccessAttempt(
		String mfaCheckerName, long userId, String userIP) {

		EmailOTPEntry emailOTPEntry =
			emailOTPEntryLocalService.fetchEmailOTPEntry(
				userId, mfaCheckerName);

		if (emailOTPEntry == null) {
			return false;
		}

		emailOTPEntry.setFailedAttempts(0);
		emailOTPEntry.setLastSuccessDate(new Date());
		emailOTPEntry.setLastSuccessIP(userIP);

		emailOTPEntryPersistence.update(emailOTPEntry);

		return true;
	}

}