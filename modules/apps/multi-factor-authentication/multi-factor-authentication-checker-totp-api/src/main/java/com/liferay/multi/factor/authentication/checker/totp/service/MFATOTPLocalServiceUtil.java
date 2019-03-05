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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MFATOTP. This utility wraps
 * <code>com.liferay.multi.factor.authentication.checker.totp.service.impl.MFATOTPLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author arthurchan35
 * @see MFATOTPLocalService
 * @generated
 */
@ProviderType
public class MFATOTPLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.multi.factor.authentication.checker.totp.service.impl.MFATOTPLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the mfatotp to the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfatotp the mfatotp
	 * @return the mfatotp that was added
	 */
	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			addMFATOTP(
				com.liferay.multi.factor.authentication.checker.totp.model.
					MFATOTP mfatotp) {

		return getService().addMFATOTP(mfatotp);
	}

	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
				addMFATOTP(
					String mfaCheckerName, String sharedSecret, long userId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addMFATOTP(mfaCheckerName, sharedSecret, userId);
	}

	/**
	 * Creates a new mfatotp with the primary key. Does not add the mfatotp to the database.
	 *
	 * @param mfaTOTPId the primary key for the new mfatotp
	 * @return the new mfatotp
	 */
	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			createMFATOTP(long mfaTOTPId) {

		return getService().createMFATOTP(mfaTOTPId);
	}

	/**
	 * Deletes the mfatotp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp that was removed
	 * @throws PortalException if a mfatotp with the primary key could not be found
	 */
	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
				deleteMFATOTP(long mfaTOTPId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteMFATOTP(mfaTOTPId);
	}

	/**
	 * Deletes the mfatotp from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfatotp the mfatotp
	 * @return the mfatotp that was removed
	 */
	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			deleteMFATOTP(
				com.liferay.multi.factor.authentication.checker.totp.model.
					MFATOTP mfatotp) {

		return getService().deleteMFATOTP(mfatotp);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			fetchMFATOTP(long mfaTOTPId) {

		return getService().fetchMFATOTP(mfaTOTPId);
	}

	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			fetchMFATOTP(String mfaCheckerName, long userId) {

		return getService().fetchMFATOTP(mfaCheckerName, userId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the mfatotp with the primary key.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp
	 * @throws PortalException if a mfatotp with the primary key could not be found
	 */
	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
				getMFATOTP(long mfaTOTPId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMFATOTP(mfaTOTPId);
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
	public static java.util.List
		<com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP>
			getMFATOTPs(int start, int end) {

		return getService().getMFATOTPs(start, end);
	}

	public static java.util.List
		<com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP>
			getMFATOTPsByMFACheckerName(
				String mfaCheckerName, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.multi.factor.authentication.checker.totp.model.
						MFATOTP> orderByComparator) {

		return getService().getMFATOTPsByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	public static java.util.List
		<com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP>
			getMFATOTPsByUserId(
				long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.multi.factor.authentication.checker.totp.model.
						MFATOTP> orderByComparator) {

		return getService().getMFATOTPsByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of mfatotps.
	 *
	 * @return the number of mfatotps
	 */
	public static int getMFATOTPsCount() {
		return getService().getMFATOTPsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the mfatotp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param mfatotp the mfatotp
	 * @return the mfatotp that was updated
	 */
	public static
		com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP
			updateMFATOTP(
				com.liferay.multi.factor.authentication.checker.totp.model.
					MFATOTP mfatotp) {

		return getService().updateMFATOTP(mfatotp);
	}

	public static MFATOTPLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MFATOTPLocalService, MFATOTPLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MFATOTPLocalService.class);

		ServiceTracker<MFATOTPLocalService, MFATOTPLocalService>
			serviceTracker =
				new ServiceTracker<MFATOTPLocalService, MFATOTPLocalService>(
					bundle.getBundleContext(), MFATOTPLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}