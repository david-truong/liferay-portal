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

package com.liferay.multi.factor.authentication.checker.email.otp.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.email.otp.model.MFAEmailOTP;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MFAEmailOTP in entity cache.
 *
 * @author arthurchan35
 * @generated
 */
@ProviderType
public class MFAEmailOTPCacheModel
	implements CacheModel<MFAEmailOTP>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MFAEmailOTPCacheModel)) {
			return false;
		}

		MFAEmailOTPCacheModel mfaEmailOTPCacheModel =
			(MFAEmailOTPCacheModel)obj;

		if (mfaEmailOTPId == mfaEmailOTPCacheModel.mfaEmailOTPId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mfaEmailOTPId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{mfaEmailOTPId=");
		sb.append(mfaEmailOTPId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", failedAttempts=");
		sb.append(failedAttempts);
		sb.append(", lastSuccessDate=");
		sb.append(lastSuccessDate);
		sb.append(", lastSuccessIP=");
		sb.append(lastSuccessIP);
		sb.append(", lastFailDate=");
		sb.append(lastFailDate);
		sb.append(", lastFailIP=");
		sb.append(lastFailIP);
		sb.append(", mfaCheckerName=");
		sb.append(mfaCheckerName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MFAEmailOTP toEntityModel() {
		MFAEmailOTPImpl mfaEmailOTPImpl = new MFAEmailOTPImpl();

		mfaEmailOTPImpl.setMfaEmailOTPId(mfaEmailOTPId);
		mfaEmailOTPImpl.setCompanyId(companyId);
		mfaEmailOTPImpl.setUserId(userId);

		if (userName == null) {
			mfaEmailOTPImpl.setUserName("");
		}
		else {
			mfaEmailOTPImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mfaEmailOTPImpl.setCreateDate(null);
		}
		else {
			mfaEmailOTPImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mfaEmailOTPImpl.setModifiedDate(null);
		}
		else {
			mfaEmailOTPImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (emailAddress == null) {
			mfaEmailOTPImpl.setEmailAddress("");
		}
		else {
			mfaEmailOTPImpl.setEmailAddress(emailAddress);
		}

		mfaEmailOTPImpl.setFailedAttempts(failedAttempts);

		if (lastSuccessDate == Long.MIN_VALUE) {
			mfaEmailOTPImpl.setLastSuccessDate(null);
		}
		else {
			mfaEmailOTPImpl.setLastSuccessDate(new Date(lastSuccessDate));
		}

		if (lastSuccessIP == null) {
			mfaEmailOTPImpl.setLastSuccessIP("");
		}
		else {
			mfaEmailOTPImpl.setLastSuccessIP(lastSuccessIP);
		}

		if (lastFailDate == Long.MIN_VALUE) {
			mfaEmailOTPImpl.setLastFailDate(null);
		}
		else {
			mfaEmailOTPImpl.setLastFailDate(new Date(lastFailDate));
		}

		if (lastFailIP == null) {
			mfaEmailOTPImpl.setLastFailIP("");
		}
		else {
			mfaEmailOTPImpl.setLastFailIP(lastFailIP);
		}

		if (mfaCheckerName == null) {
			mfaEmailOTPImpl.setMfaCheckerName("");
		}
		else {
			mfaEmailOTPImpl.setMfaCheckerName(mfaCheckerName);
		}

		mfaEmailOTPImpl.resetOriginalValues();

		return mfaEmailOTPImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mfaEmailOTPId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		emailAddress = objectInput.readUTF();

		failedAttempts = objectInput.readInt();
		lastSuccessDate = objectInput.readLong();
		lastSuccessIP = objectInput.readUTF();
		lastFailDate = objectInput.readLong();
		lastFailIP = objectInput.readUTF();
		mfaCheckerName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mfaEmailOTPId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (emailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		objectOutput.writeInt(failedAttempts);
		objectOutput.writeLong(lastSuccessDate);

		if (lastSuccessIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastSuccessIP);
		}

		objectOutput.writeLong(lastFailDate);

		if (lastFailIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastFailIP);
		}

		if (mfaCheckerName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mfaCheckerName);
		}
	}

	public long mfaEmailOTPId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String emailAddress;
	public int failedAttempts;
	public long lastSuccessDate;
	public String lastSuccessIP;
	public long lastFailDate;
	public String lastFailIP;
	public String mfaCheckerName;

}