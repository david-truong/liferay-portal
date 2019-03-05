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

package com.liferay.multi.factor.authentication.checker.email.otp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MFAEmailOTP}.
 * </p>
 *
 * @author arthurchan35
 * @see MFAEmailOTP
 * @generated
 */
@ProviderType
public class MFAEmailOTPWrapper
	extends BaseModelWrapper<MFAEmailOTP>
	implements MFAEmailOTP, ModelWrapper<MFAEmailOTP> {

	public MFAEmailOTPWrapper(MFAEmailOTP mfaEmailOTP) {
		super(mfaEmailOTP);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mfaEmailOTPId", getMfaEmailOTPId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("failedAttempts", getFailedAttempts());
		attributes.put("lastSuccessDate", getLastSuccessDate());
		attributes.put("lastSuccessIP", getLastSuccessIP());
		attributes.put("lastFailDate", getLastFailDate());
		attributes.put("lastFailIP", getLastFailIP());
		attributes.put("mfaCheckerName", getMfaCheckerName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mfaEmailOTPId = (Long)attributes.get("mfaEmailOTPId");

		if (mfaEmailOTPId != null) {
			setMfaEmailOTPId(mfaEmailOTPId);
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

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Integer failedAttempts = (Integer)attributes.get("failedAttempts");

		if (failedAttempts != null) {
			setFailedAttempts(failedAttempts);
		}

		Date lastSuccessDate = (Date)attributes.get("lastSuccessDate");

		if (lastSuccessDate != null) {
			setLastSuccessDate(lastSuccessDate);
		}

		String lastSuccessIP = (String)attributes.get("lastSuccessIP");

		if (lastSuccessIP != null) {
			setLastSuccessIP(lastSuccessIP);
		}

		Date lastFailDate = (Date)attributes.get("lastFailDate");

		if (lastFailDate != null) {
			setLastFailDate(lastFailDate);
		}

		String lastFailIP = (String)attributes.get("lastFailIP");

		if (lastFailIP != null) {
			setLastFailIP(lastFailIP);
		}

		String mfaCheckerName = (String)attributes.get("mfaCheckerName");

		if (mfaCheckerName != null) {
			setMfaCheckerName(mfaCheckerName);
		}
	}

	/**
	 * Returns the company ID of this mfa email otp.
	 *
	 * @return the company ID of this mfa email otp
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this mfa email otp.
	 *
	 * @return the create date of this mfa email otp
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the email address of this mfa email otp.
	 *
	 * @return the email address of this mfa email otp
	 */
	@Override
	public String getEmailAddress() {
		return model.getEmailAddress();
	}

	/**
	 * Returns the failed attempts of this mfa email otp.
	 *
	 * @return the failed attempts of this mfa email otp
	 */
	@Override
	public int getFailedAttempts() {
		return model.getFailedAttempts();
	}

	/**
	 * Returns the last fail date of this mfa email otp.
	 *
	 * @return the last fail date of this mfa email otp
	 */
	@Override
	public Date getLastFailDate() {
		return model.getLastFailDate();
	}

	/**
	 * Returns the last fail ip of this mfa email otp.
	 *
	 * @return the last fail ip of this mfa email otp
	 */
	@Override
	public String getLastFailIP() {
		return model.getLastFailIP();
	}

	/**
	 * Returns the last success date of this mfa email otp.
	 *
	 * @return the last success date of this mfa email otp
	 */
	@Override
	public Date getLastSuccessDate() {
		return model.getLastSuccessDate();
	}

	/**
	 * Returns the last success ip of this mfa email otp.
	 *
	 * @return the last success ip of this mfa email otp
	 */
	@Override
	public String getLastSuccessIP() {
		return model.getLastSuccessIP();
	}

	/**
	 * Returns the mfa checker name of this mfa email otp.
	 *
	 * @return the mfa checker name of this mfa email otp
	 */
	@Override
	public String getMfaCheckerName() {
		return model.getMfaCheckerName();
	}

	/**
	 * Returns the mfa email otp ID of this mfa email otp.
	 *
	 * @return the mfa email otp ID of this mfa email otp
	 */
	@Override
	public long getMfaEmailOTPId() {
		return model.getMfaEmailOTPId();
	}

	/**
	 * Returns the modified date of this mfa email otp.
	 *
	 * @return the modified date of this mfa email otp
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this mfa email otp.
	 *
	 * @return the primary key of this mfa email otp
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this mfa email otp.
	 *
	 * @return the user ID of this mfa email otp
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this mfa email otp.
	 *
	 * @return the user name of this mfa email otp
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this mfa email otp.
	 *
	 * @return the user uuid of this mfa email otp
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
	 * Sets the company ID of this mfa email otp.
	 *
	 * @param companyId the company ID of this mfa email otp
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this mfa email otp.
	 *
	 * @param createDate the create date of this mfa email otp
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the email address of this mfa email otp.
	 *
	 * @param emailAddress the email address of this mfa email otp
	 */
	@Override
	public void setEmailAddress(String emailAddress) {
		model.setEmailAddress(emailAddress);
	}

	/**
	 * Sets the failed attempts of this mfa email otp.
	 *
	 * @param failedAttempts the failed attempts of this mfa email otp
	 */
	@Override
	public void setFailedAttempts(int failedAttempts) {
		model.setFailedAttempts(failedAttempts);
	}

	/**
	 * Sets the last fail date of this mfa email otp.
	 *
	 * @param lastFailDate the last fail date of this mfa email otp
	 */
	@Override
	public void setLastFailDate(Date lastFailDate) {
		model.setLastFailDate(lastFailDate);
	}

	/**
	 * Sets the last fail ip of this mfa email otp.
	 *
	 * @param lastFailIP the last fail ip of this mfa email otp
	 */
	@Override
	public void setLastFailIP(String lastFailIP) {
		model.setLastFailIP(lastFailIP);
	}

	/**
	 * Sets the last success date of this mfa email otp.
	 *
	 * @param lastSuccessDate the last success date of this mfa email otp
	 */
	@Override
	public void setLastSuccessDate(Date lastSuccessDate) {
		model.setLastSuccessDate(lastSuccessDate);
	}

	/**
	 * Sets the last success ip of this mfa email otp.
	 *
	 * @param lastSuccessIP the last success ip of this mfa email otp
	 */
	@Override
	public void setLastSuccessIP(String lastSuccessIP) {
		model.setLastSuccessIP(lastSuccessIP);
	}

	/**
	 * Sets the mfa checker name of this mfa email otp.
	 *
	 * @param mfaCheckerName the mfa checker name of this mfa email otp
	 */
	@Override
	public void setMfaCheckerName(String mfaCheckerName) {
		model.setMfaCheckerName(mfaCheckerName);
	}

	/**
	 * Sets the mfa email otp ID of this mfa email otp.
	 *
	 * @param mfaEmailOTPId the mfa email otp ID of this mfa email otp
	 */
	@Override
	public void setMfaEmailOTPId(long mfaEmailOTPId) {
		model.setMfaEmailOTPId(mfaEmailOTPId);
	}

	/**
	 * Sets the modified date of this mfa email otp.
	 *
	 * @param modifiedDate the modified date of this mfa email otp
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this mfa email otp.
	 *
	 * @param primaryKey the primary key of this mfa email otp
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this mfa email otp.
	 *
	 * @param userId the user ID of this mfa email otp
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this mfa email otp.
	 *
	 * @param userName the user name of this mfa email otp
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this mfa email otp.
	 *
	 * @param userUuid the user uuid of this mfa email otp
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MFAEmailOTPWrapper wrap(MFAEmailOTP mfaEmailOTP) {
		return new MFAEmailOTPWrapper(mfaEmailOTP);
	}

}