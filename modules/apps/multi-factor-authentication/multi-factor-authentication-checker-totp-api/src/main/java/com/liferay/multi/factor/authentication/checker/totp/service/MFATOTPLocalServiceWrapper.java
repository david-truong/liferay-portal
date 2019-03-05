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

package com.liferay.multi.factor.authentication.checker.totp.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MFATOTPLocalService}.
 *
 * @author arthurchan35
 * @see MFATOTPLocalService
 * @generated
 */
@ProviderType
public class MFATOTPLocalServiceWrapper
	implements MFATOTPLocalService, ServiceWrapper<MFATOTPLocalService> {

	public MFATOTPLocalServiceWrapper(MFATOTPLocalService mfatotpLocalService) {
		_mfatotpLocalService = mfatotpLocalService;
	}

	/**
	 * Adds the mfatotp to the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfatotp the mfatotp
	 * @return the mfatotp that was added
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
		addMFATOTP(
			com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
				mfatotp) {

		return _mfatotpLocalService.addMFATOTP(mfatotp);
	}

	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			addMFATOTP(String mfaCheckerName, String sharedSecret, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mfatotpLocalService.addMFATOTP(
			mfaCheckerName, sharedSecret, userId);
	}

	/**
	 * Creates a new mfatotp with the primary key. Does not add the mfatotp to the database.
	 *
	 * @param mfaTOTPId the primary key for the new mfatotp
	 * @return the new mfatotp
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
		createMFATOTP(long mfaTOTPId) {

		return _mfatotpLocalService.createMFATOTP(mfaTOTPId);
	}

	/**
	 * Deletes the mfatotp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp that was removed
	 * @throws PortalException if a mfatotp with the primary key could not be found
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			deleteMFATOTP(long mfaTOTPId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mfatotpLocalService.deleteMFATOTP(mfaTOTPId);
	}

	/**
	 * Deletes the mfatotp from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfatotp the mfatotp
	 * @return the mfatotp that was removed
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
		deleteMFATOTP(
			com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
				mfatotp) {

		return _mfatotpLocalService.deleteMFATOTP(mfatotp);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mfatotpLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mfatotpLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _mfatotpLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.checker.totp.model.impl.MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _mfatotpLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.checker.totp.model.impl.MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _mfatotpLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _mfatotpLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _mfatotpLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
		fetchMFATOTP(long mfaTOTPId) {

		return _mfatotpLocalService.fetchMFATOTP(mfaTOTPId);
	}

	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
		fetchMFATOTP(String mfaCheckerName, long userId) {

		return _mfatotpLocalService.fetchMFATOTP(mfaCheckerName, userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _mfatotpLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _mfatotpLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the mfatotp with the primary key.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp
	 * @throws PortalException if a mfatotp with the primary key could not be found
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			getMFATOTP(long mfaTOTPId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mfatotpLocalService.getMFATOTP(mfaTOTPId);
	}

	/**
	 * Returns a range of all the mfatotps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.checker.totp.model.impl.MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @return the range of mfatotps
	 */
	@Override
	public java.util.List
		<com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP>
			getMFATOTPs(int start, int end) {

		return _mfatotpLocalService.getMFATOTPs(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP>
			getMFATOTPsByMFACheckerName(
				String mfaCheckerName, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.multi.factor.authentication.checker.totp.model.
						MFATOTP> orderByComparator) {

		return _mfatotpLocalService.getMFATOTPsByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	@Override
	public java.util.List
		<com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP>
			getMFATOTPsByUserId(
				long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.multi.factor.authentication.checker.totp.model.
						MFATOTP> orderByComparator) {

		return _mfatotpLocalService.getMFATOTPsByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of mfatotps.
	 *
	 * @return the number of mfatotps
	 */
	@Override
	public int getMFATOTPsCount() {
		return _mfatotpLocalService.getMFATOTPsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mfatotpLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mfatotpLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the mfatotp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param mfatotp the mfatotp
	 * @return the mfatotp that was updated
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
		updateMFATOTP(
			com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
				mfatotp) {

		return _mfatotpLocalService.updateMFATOTP(mfatotp);
	}

	@Override
	public MFATOTPLocalService getWrappedService() {
		return _mfatotpLocalService;
	}

	@Override
	public void setWrappedService(MFATOTPLocalService mfatotpLocalService) {
		_mfatotpLocalService = mfatotpLocalService;
	}

	private MFATOTPLocalService _mfatotpLocalService;

}