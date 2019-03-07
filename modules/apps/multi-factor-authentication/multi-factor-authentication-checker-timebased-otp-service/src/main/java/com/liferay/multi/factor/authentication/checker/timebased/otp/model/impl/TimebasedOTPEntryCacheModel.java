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

package com.liferay.multi.factor.authentication.checker.timebased.otp.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.timebased.otp.model.TimebasedOTPEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TimebasedOTPEntry in entity cache.
 *
 * @author arthurchan35
 * @generated
 */
@ProviderType
public class TimebasedOTPEntryCacheModel
	implements CacheModel<TimebasedOTPEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TimebasedOTPEntryCacheModel)) {
			return false;
		}

		TimebasedOTPEntryCacheModel timebasedOTPEntryCacheModel =
			(TimebasedOTPEntryCacheModel)obj;

		if (entryId == timebasedOTPEntryCacheModel.entryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{entryId=");
		sb.append(entryId);
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
		sb.append(", failedAttempts=");
		sb.append(failedAttempts);
		sb.append(", lastFailDate=");
		sb.append(lastFailDate);
		sb.append(", lastFailIP=");
		sb.append(lastFailIP);
		sb.append(", lastSuccessDate=");
		sb.append(lastSuccessDate);
		sb.append(", lastSuccessIP=");
		sb.append(lastSuccessIP);
		sb.append(", mfaCheckerName=");
		sb.append(mfaCheckerName);
		sb.append(", sharedSecret=");
		sb.append(sharedSecret);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TimebasedOTPEntry toEntityModel() {
		TimebasedOTPEntryImpl timebasedOTPEntryImpl =
			new TimebasedOTPEntryImpl();

		timebasedOTPEntryImpl.setEntryId(entryId);
		timebasedOTPEntryImpl.setCompanyId(companyId);
		timebasedOTPEntryImpl.setUserId(userId);

		if (userName == null) {
			timebasedOTPEntryImpl.setUserName("");
		}
		else {
			timebasedOTPEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			timebasedOTPEntryImpl.setCreateDate(null);
		}
		else {
			timebasedOTPEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			timebasedOTPEntryImpl.setModifiedDate(null);
		}
		else {
			timebasedOTPEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		timebasedOTPEntryImpl.setFailedAttempts(failedAttempts);

		if (lastFailDate == Long.MIN_VALUE) {
			timebasedOTPEntryImpl.setLastFailDate(null);
		}
		else {
			timebasedOTPEntryImpl.setLastFailDate(new Date(lastFailDate));
		}

		if (lastFailIP == null) {
			timebasedOTPEntryImpl.setLastFailIP("");
		}
		else {
			timebasedOTPEntryImpl.setLastFailIP(lastFailIP);
		}

		if (lastSuccessDate == Long.MIN_VALUE) {
			timebasedOTPEntryImpl.setLastSuccessDate(null);
		}
		else {
			timebasedOTPEntryImpl.setLastSuccessDate(new Date(lastSuccessDate));
		}

		if (lastSuccessIP == null) {
			timebasedOTPEntryImpl.setLastSuccessIP("");
		}
		else {
			timebasedOTPEntryImpl.setLastSuccessIP(lastSuccessIP);
		}

		if (mfaCheckerName == null) {
			timebasedOTPEntryImpl.setMfaCheckerName("");
		}
		else {
			timebasedOTPEntryImpl.setMfaCheckerName(mfaCheckerName);
		}

		if (sharedSecret == null) {
			timebasedOTPEntryImpl.setSharedSecret("");
		}
		else {
			timebasedOTPEntryImpl.setSharedSecret(sharedSecret);
		}

		timebasedOTPEntryImpl.resetOriginalValues();

		return timebasedOTPEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		failedAttempts = objectInput.readInt();
		lastFailDate = objectInput.readLong();
		lastFailIP = objectInput.readUTF();
		lastSuccessDate = objectInput.readLong();
		lastSuccessIP = objectInput.readUTF();
		mfaCheckerName = objectInput.readUTF();
		sharedSecret = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(entryId);

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

		objectOutput.writeInt(failedAttempts);
		objectOutput.writeLong(lastFailDate);

		if (lastFailIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastFailIP);
		}

		objectOutput.writeLong(lastSuccessDate);

		if (lastSuccessIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastSuccessIP);
		}

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

	public long entryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int failedAttempts;
	public long lastFailDate;
	public String lastFailIP;
	public long lastSuccessDate;
	public String lastSuccessIP;
	public String mfaCheckerName;
	public String sharedSecret;

}