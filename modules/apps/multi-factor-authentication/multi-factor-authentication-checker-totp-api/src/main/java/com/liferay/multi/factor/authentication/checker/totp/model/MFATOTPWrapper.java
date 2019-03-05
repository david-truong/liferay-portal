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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MFATOTP}.
 * </p>
 *
 * @author arthurchan35
 * @see MFATOTP
 * @generated
 */
@ProviderType
public class MFATOTPWrapper
	extends BaseModelWrapper<MFATOTP>
	implements MFATOTP, ModelWrapper<MFATOTP> {

	public MFATOTPWrapper(MFATOTP mfatotp) {
		super(mfatotp);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mfaTOTPId", getMfaTOTPId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("backupCodes", getBackupCodes());
		attributes.put("failedLoginAttempts", getFailedLoginAttempts());
		attributes.put("lastLoginDate", getLastLoginDate());
		attributes.put("lastLoginIP", getLastLoginIP());
		attributes.put("lastFailedLoginDate", getLastFailedLoginDate());
		attributes.put("mfaCheckerName", getMfaCheckerName());
		attributes.put("sharedSecret", getSharedSecret());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mfaTOTPId = (Long)attributes.get("mfaTOTPId");

		if (mfaTOTPId != null) {
			setMfaTOTPId(mfaTOTPId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String backupCodes = (String)attributes.get("backupCodes");

		if (backupCodes != null) {
			setBackupCodes(backupCodes);
		}

		Integer failedLoginAttempts = (Integer)attributes.get(
			"failedLoginAttempts");

		if (failedLoginAttempts != null) {
			setFailedLoginAttempts(failedLoginAttempts);
		}

		Date lastLoginDate = (Date)attributes.get("lastLoginDate");

		if (lastLoginDate != null) {
			setLastLoginDate(lastLoginDate);
		}

		String lastLoginIP = (String)attributes.get("lastLoginIP");

		if (lastLoginIP != null) {
			setLastLoginIP(lastLoginIP);
		}

		Date lastFailedLoginDate = (Date)attributes.get("lastFailedLoginDate");

		if (lastFailedLoginDate != null) {
			setLastFailedLoginDate(lastFailedLoginDate);
		}

		String mfaCheckerName = (String)attributes.get("mfaCheckerName");

		if (mfaCheckerName != null) {
			setMfaCheckerName(mfaCheckerName);
		}

		String sharedSecret = (String)attributes.get("sharedSecret");

		if (sharedSecret != null) {
			setSharedSecret(sharedSecret);
		}
	}

	/**
	 * Returns the backup codes of this mfatotp.
	 *
	 * @return the backup codes of this mfatotp
	 */
	@Override
	public String getBackupCodes() {
		return model.getBackupCodes();
	}

	/**
	 * Returns the company ID of this mfatotp.
	 *
	 * @return the company ID of this mfatotp
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this mfatotp.
	 *
	 * @return the create date of this mfatotp
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the failed login attempts of this mfatotp.
	 *
	 * @return the failed login attempts of this mfatotp
	 */
	@Override
	public int getFailedLoginAttempts() {
		return model.getFailedLoginAttempts();
	}

	/**
	 * Returns the last failed login date of this mfatotp.
	 *
	 * @return the last failed login date of this mfatotp
	 */
	@Override
	public Date getLastFailedLoginDate() {
		return model.getLastFailedLoginDate();
	}

	/**
	 * Returns the last login date of this mfatotp.
	 *
	 * @return the last login date of this mfatotp
	 */
	@Override
	public Date getLastLoginDate() {
		return model.getLastLoginDate();
	}

	/**
	 * Returns the last login ip of this mfatotp.
	 *
	 * @return the last login ip of this mfatotp
	 */
	@Override
	public String getLastLoginIP() {
		return model.getLastLoginIP();
	}

	/**
	 * Returns the mfa checker name of this mfatotp.
	 *
	 * @return the mfa checker name of this mfatotp
	 */
	@Override
	public String getMfaCheckerName() {
		return model.getMfaCheckerName();
	}

	/**
	 * Returns the mfa totp ID of this mfatotp.
	 *
	 * @return the mfa totp ID of this mfatotp
	 */
	@Override
	public long getMfaTOTPId() {
		return model.getMfaTOTPId();
	}

	/**
	 * Returns the modified date of this mfatotp.
	 *
	 * @return the modified date of this mfatotp
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this mfatotp.
	 *
	 * @return the primary key of this mfatotp
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the shared secret of this mfatotp.
	 *
	 * @return the shared secret of this mfatotp
	 */
	@Override
	public String getSharedSecret() {
		return model.getSharedSecret();
	}

	/**
	 * Returns the user ID of this mfatotp.
	 *
	 * @return the user ID of this mfatotp
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this mfatotp.
	 *
	 * @return the user name of this mfatotp
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this mfatotp.
	 *
	 * @return the user uuid of this mfatotp
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the backup codes of this mfatotp.
	 *
	 * @param backupCodes the backup codes of this mfatotp
	 */
	@Override
	public void setBackupCodes(String backupCodes) {
		model.setBackupCodes(backupCodes);
	}

	/**
	 * Sets the company ID of this mfatotp.
	 *
	 * @param companyId the company ID of this mfatotp
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this mfatotp.
	 *
	 * @param createDate the create date of this mfatotp
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the failed login attempts of this mfatotp.
	 *
	 * @param failedLoginAttempts the failed login attempts of this mfatotp
	 */
	@Override
	public void setFailedLoginAttempts(int failedLoginAttempts) {
		model.setFailedLoginAttempts(failedLoginAttempts);
	}

	/**
	 * Sets the last failed login date of this mfatotp.
	 *
	 * @param lastFailedLoginDate the last failed login date of this mfatotp
	 */
	@Override
	public void setLastFailedLoginDate(Date lastFailedLoginDate) {
		model.setLastFailedLoginDate(lastFailedLoginDate);
	}

	/**
	 * Sets the last login date of this mfatotp.
	 *
	 * @param lastLoginDate the last login date of this mfatotp
	 */
	@Override
	public void setLastLoginDate(Date lastLoginDate) {
		model.setLastLoginDate(lastLoginDate);
	}

	/**
	 * Sets the last login ip of this mfatotp.
	 *
	 * @param lastLoginIP the last login ip of this mfatotp
	 */
	@Override
	public void setLastLoginIP(String lastLoginIP) {
		model.setLastLoginIP(lastLoginIP);
	}

	/**
	 * Sets the mfa checker name of this mfatotp.
	 *
	 * @param mfaCheckerName the mfa checker name of this mfatotp
	 */
	@Override
	public void setMfaCheckerName(String mfaCheckerName) {
		model.setMfaCheckerName(mfaCheckerName);
	}

	/**
	 * Sets the mfa totp ID of this mfatotp.
	 *
	 * @param mfaTOTPId the mfa totp ID of this mfatotp
	 */
	@Override
	public void setMfaTOTPId(long mfaTOTPId) {
		model.setMfaTOTPId(mfaTOTPId);
	}

	/**
	 * Sets the modified date of this mfatotp.
	 *
	 * @param modifiedDate the modified date of this mfatotp
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this mfatotp.
	 *
	 * @param primaryKey the primary key of this mfatotp
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the shared secret of this mfatotp.
	 *
	 * @param sharedSecret the shared secret of this mfatotp
	 */
	@Override
	public void setSharedSecret(String sharedSecret) {
		model.setSharedSecret(sharedSecret);
	}

	/**
	 * Sets the user ID of this mfatotp.
	 *
	 * @param userId the user ID of this mfatotp
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this mfatotp.
	 *
	 * @param userName the user name of this mfatotp
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this mfatotp.
	 *
	 * @param userUuid the user uuid of this mfatotp
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MFATOTPWrapper wrap(MFATOTP mfatotp) {
		return new MFATOTPWrapper(mfatotp);
	}

}