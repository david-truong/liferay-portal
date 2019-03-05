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

package com.liferay.multi.factor.authentication.checker.totp.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MFATOTP in entity cache.
 *
 * @author arthurchan35
 * @generated
 */
@ProviderType
public class MFATOTPCacheModel implements CacheModel<MFATOTP>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MFATOTPCacheModel)) {
			return false;
		}

		MFATOTPCacheModel mfatotpCacheModel = (MFATOTPCacheModel)obj;

		if (mfaTOTPId == mfatotpCacheModel.mfaTOTPId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mfaTOTPId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{mfaTOTPId=");
		sb.append(mfaTOTPId);
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
		sb.append(", backupCodes=");
		sb.append(backupCodes);
		sb.append(", failedLoginAttempts=");
		sb.append(failedLoginAttempts);
		sb.append(", lastLoginDate=");
		sb.append(lastLoginDate);
		sb.append(", lastLoginIP=");
		sb.append(lastLoginIP);
		sb.append(", lastFailedLoginDate=");
		sb.append(lastFailedLoginDate);
		sb.append(", mfaCheckerName=");
		sb.append(mfaCheckerName);
		sb.append(", sharedSecret=");
		sb.append(sharedSecret);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MFATOTP toEntityModel() {
		MFATOTPImpl mfatotpImpl = new MFATOTPImpl();

		mfatotpImpl.setMfaTOTPId(mfaTOTPId);
		mfatotpImpl.setCompanyId(companyId);
		mfatotpImpl.setUserId(userId);

		if (userName == null) {
			mfatotpImpl.setUserName("");
		}
		else {
			mfatotpImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mfatotpImpl.setCreateDate(null);
		}
		else {
			mfatotpImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mfatotpImpl.setModifiedDate(null);
		}
		else {
			mfatotpImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (backupCodes == null) {
			mfatotpImpl.setBackupCodes("");
		}
		else {
			mfatotpImpl.setBackupCodes(backupCodes);
		}

		mfatotpImpl.setFailedLoginAttempts(failedLoginAttempts);

		if (lastLoginDate == Long.MIN_VALUE) {
			mfatotpImpl.setLastLoginDate(null);
		}
		else {
			mfatotpImpl.setLastLoginDate(new Date(lastLoginDate));
		}

		if (lastLoginIP == null) {
			mfatotpImpl.setLastLoginIP("");
		}
		else {
			mfatotpImpl.setLastLoginIP(lastLoginIP);
		}

		if (lastFailedLoginDate == Long.MIN_VALUE) {
			mfatotpImpl.setLastFailedLoginDate(null);
		}
		else {
			mfatotpImpl.setLastFailedLoginDate(new Date(lastFailedLoginDate));
		}

		if (mfaCheckerName == null) {
			mfatotpImpl.setMfaCheckerName("");
		}
		else {
			mfatotpImpl.setMfaCheckerName(mfaCheckerName);
		}

		if (sharedSecret == null) {
			mfatotpImpl.setSharedSecret("");
		}
		else {
			mfatotpImpl.setSharedSecret(sharedSecret);
		}

		mfatotpImpl.resetOriginalValues();

		return mfatotpImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mfaTOTPId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		backupCodes = objectInput.readUTF();

		failedLoginAttempts = objectInput.readInt();
		lastLoginDate = objectInput.readLong();
		lastLoginIP = objectInput.readUTF();
		lastFailedLoginDate = objectInput.readLong();
		mfaCheckerName = objectInput.readUTF();
		sharedSecret = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mfaTOTPId);

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

		if (backupCodes == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(backupCodes);
		}

		objectOutput.writeInt(failedLoginAttempts);
		objectOutput.writeLong(lastLoginDate);

		if (lastLoginIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastLoginIP);
		}

		objectOutput.writeLong(lastFailedLoginDate);

		if (mfaCheckerName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mfaCheckerName);
		}

		if (sharedSecret == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sharedSecret);
		}
	}

	public long mfaTOTPId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String backupCodes;
	public int failedLoginAttempts;
	public long lastLoginDate;
	public String lastLoginIP;
	public long lastFailedLoginDate;
	public String mfaCheckerName;
	public String sharedSecret;

}