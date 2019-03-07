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

package com.liferay.multi.factor.authentication.checker.timebased.otp.model;

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
public class TimebasedOTPEntrySoap implements Serializable {

	public static TimebasedOTPEntrySoap toSoapModel(TimebasedOTPEntry model) {
		TimebasedOTPEntrySoap soapModel = new TimebasedOTPEntrySoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFailedAttempts(model.getFailedAttempts());
		soapModel.setLastFailDate(model.getLastFailDate());
		soapModel.setLastFailIP(model.getLastFailIP());
		soapModel.setLastSuccessDate(model.getLastSuccessDate());
		soapModel.setLastSuccessIP(model.getLastSuccessIP());
		soapModel.setMfaCheckerName(model.getMfaCheckerName());
		soapModel.setSharedSecret(model.getSharedSecret());

		return soapModel;
	}

	public static TimebasedOTPEntrySoap[] toSoapModels(
		TimebasedOTPEntry[] models) {

		TimebasedOTPEntrySoap[] soapModels =
			new TimebasedOTPEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TimebasedOTPEntrySoap[][] toSoapModels(
		TimebasedOTPEntry[][] models) {

		TimebasedOTPEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TimebasedOTPEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TimebasedOTPEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TimebasedOTPEntrySoap[] toSoapModels(
		List<TimebasedOTPEntry> models) {

		List<TimebasedOTPEntrySoap> soapModels =
			new ArrayList<TimebasedOTPEntrySoap>(models.size());

		for (TimebasedOTPEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TimebasedOTPEntrySoap[soapModels.size()]);
	}

	public TimebasedOTPEntrySoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
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

	public int getFailedAttempts() {
		return _failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		_failedAttempts = failedAttempts;
	}

	public Date getLastFailDate() {
		return _lastFailDate;
	}

	public void setLastFailDate(Date lastFailDate) {
		_lastFailDate = lastFailDate;
	}

	public String getLastFailIP() {
		return _lastFailIP;
	}

	public void setLastFailIP(String lastFailIP) {
		_lastFailIP = lastFailIP;
	}

	public Date getLastSuccessDate() {
		return _lastSuccessDate;
	}

	public void setLastSuccessDate(Date lastSuccessDate) {
		_lastSuccessDate = lastSuccessDate;
	}

	public String getLastSuccessIP() {
		return _lastSuccessIP;
	}

	public void setLastSuccessIP(String lastSuccessIP) {
		_lastSuccessIP = lastSuccessIP;
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

	private long _entryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _failedAttempts;
	private Date _lastFailDate;
	private String _lastFailIP;
	private Date _lastSuccessDate;
	private String _lastSuccessIP;
	private String _mfaCheckerName;
	private String _sharedSecret;

}