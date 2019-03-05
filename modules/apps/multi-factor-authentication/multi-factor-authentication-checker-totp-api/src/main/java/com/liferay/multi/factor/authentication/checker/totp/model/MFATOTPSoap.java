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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author arthurchan35
 * @generated
 */
@ProviderType
public class MFATOTPSoap implements Serializable {

	public static MFATOTPSoap toSoapModel(MFATOTP model) {
		MFATOTPSoap soapModel = new MFATOTPSoap();

		soapModel.setMfaTOTPId(model.getMfaTOTPId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBackupCodes(model.getBackupCodes());
		soapModel.setFailedLoginAttempts(model.getFailedLoginAttempts());
		soapModel.setLastLoginDate(model.getLastLoginDate());
		soapModel.setLastLoginIP(model.getLastLoginIP());
		soapModel.setLastFailedLoginDate(model.getLastFailedLoginDate());
		soapModel.setMfaCheckerName(model.getMfaCheckerName());
		soapModel.setSharedSecret(model.getSharedSecret());

		return soapModel;
	}

	public static MFATOTPSoap[] toSoapModels(MFATOTP[] models) {
		MFATOTPSoap[] soapModels = new MFATOTPSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MFATOTPSoap[][] toSoapModels(MFATOTP[][] models) {
		MFATOTPSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MFATOTPSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MFATOTPSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MFATOTPSoap[] toSoapModels(List<MFATOTP> models) {
		List<MFATOTPSoap> soapModels = new ArrayList<MFATOTPSoap>(
			models.size());

		for (MFATOTP model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MFATOTPSoap[soapModels.size()]);
	}

	public MFATOTPSoap() {
	}

	public long getPrimaryKey() {
		return _mfaTOTPId;
	}

	public void setPrimaryKey(long pk) {
		setMfaTOTPId(pk);
	}

	public long getMfaTOTPId() {
		return _mfaTOTPId;
	}

	public void setMfaTOTPId(long mfaTOTPId) {
		_mfaTOTPId = mfaTOTPId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getBackupCodes() {
		return _backupCodes;
	}

	public void setBackupCodes(String backupCodes) {
		_backupCodes = backupCodes;
	}

	public int getFailedLoginAttempts() {
		return _failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		_failedLoginAttempts = failedLoginAttempts;
	}

	public Date getLastLoginDate() {
		return _lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		_lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIP() {
		return _lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		_lastLoginIP = lastLoginIP;
	}

	public Date getLastFailedLoginDate() {
		return _lastFailedLoginDate;
	}

	public void setLastFailedLoginDate(Date lastFailedLoginDate) {
		_lastFailedLoginDate = lastFailedLoginDate;
	}

	public String getMfaCheckerName() {
		return _mfaCheckerName;
	}

	public void setMfaCheckerName(String mfaCheckerName) {
		_mfaCheckerName = mfaCheckerName;
	}

	public String getSharedSecret() {
		return _sharedSecret;
	}

	public void setSharedSecret(String sharedSecret) {
		_sharedSecret = sharedSecret;
	}

	private long _mfaTOTPId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _backupCodes;
	private int _failedLoginAttempts;
	private Date _lastLoginDate;
	private String _lastLoginIP;
	private Date _lastFailedLoginDate;
	private String _mfaCheckerName;
	private String _sharedSecret;

}