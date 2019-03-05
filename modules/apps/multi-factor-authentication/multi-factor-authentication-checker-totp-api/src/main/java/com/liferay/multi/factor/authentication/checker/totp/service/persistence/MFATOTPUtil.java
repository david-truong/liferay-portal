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

package com.liferay.multi.factor.authentication.checker.totp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the mfatotp service. This utility wraps <code>com.liferay.multi.factor.authentication.checker.totp.service.persistence.impl.MFATOTPPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @see MFATOTPPersistence
 * @generated
 */
@ProviderType
public class MFATOTPUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(MFATOTP mfatotp) {
		getPersistence().clearCache(mfatotp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, MFATOTP> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MFATOTP> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MFATOTP> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MFATOTP> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MFATOTP update(MFATOTP mfatotp) {
		return getPersistence().update(mfatotp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MFATOTP update(
		MFATOTP mfatotp, ServiceContext serviceContext) {

		return getPersistence().update(mfatotp, serviceContext);
	}

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or throws a <code>NoSuchMFATOTPException</code> if it could not be found.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public static MFATOTP findByM_U(String mfaCheckerName, long userId)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public static MFATOTP fetchByM_U(String mfaCheckerName, long userId) {
		return getPersistence().fetchByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public static MFATOTP fetchByM_U(
		String mfaCheckerName, long userId, boolean retrieveFromCache) {

		return getPersistence().fetchByM_U(
			mfaCheckerName, userId, retrieveFromCache);
	}

	/**
	 * Removes the mfatotp where mfaCheckerName = &#63; and userId = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the mfatotp that was removed
	 */
	public static MFATOTP removeByM_U(String mfaCheckerName, long userId)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().removeByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns the number of mfatotps where mfaCheckerName = &#63; and userId = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the number of matching mfatotps
	 */
	public static int countByM_U(String mfaCheckerName, long userId) {
		return getPersistence().countByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns all the mfatotps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mfatotps
	 */
	public static List<MFATOTP> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the mfatotps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @return the range of matching mfatotps
	 */
	public static List<MFATOTP> findByUserId(long userId, int start, int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the mfatotps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mfatotps
	 */
	public static List<MFATOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mfatotps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching mfatotps
	 */
	public static List<MFATOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public static MFATOTP findByUserId_First(
			long userId, OrderByComparator<MFATOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public static MFATOTP fetchByUserId_First(
		long userId, OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public static MFATOTP findByUserId_Last(
			long userId, OrderByComparator<MFATOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public static MFATOTP fetchByUserId_Last(
		long userId, OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the mfatotps before and after the current mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param mfaTOTPId the primary key of the current mfatotp
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public static MFATOTP[] findByUserId_PrevAndNext(
			long mfaTOTPId, long userId,
			OrderByComparator<MFATOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByUserId_PrevAndNext(
			mfaTOTPId, userId, orderByComparator);
	}

	/**
	 * Removes all the mfatotps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of mfatotps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mfatotps
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the mfatotps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching mfatotps
	 */
	public static List<MFATOTP> findByMFACheckerName(String mfaCheckerName) {
		return getPersistence().findByMFACheckerName(mfaCheckerName);
	}

	/**
	 * Returns a range of all the mfatotps where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @return the range of matching mfatotps
	 */
	public static List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end) {

		return getPersistence().findByMFACheckerName(
			mfaCheckerName, start, end);
	}

	/**
	 * Returns an ordered range of all the mfatotps where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mfatotps
	 */
	public static List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mfatotps where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching mfatotps
	 */
	public static List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public static MFATOTP findByMFACheckerName_First(
			String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByMFACheckerName_First(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the first mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public static MFATOTP fetchByMFACheckerName_First(
		String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().fetchByMFACheckerName_First(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the last mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public static MFATOTP findByMFACheckerName_Last(
			String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByMFACheckerName_Last(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the last mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public static MFATOTP fetchByMFACheckerName_Last(
		String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().fetchByMFACheckerName_Last(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the mfatotps before and after the current mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaTOTPId the primary key of the current mfatotp
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public static MFATOTP[] findByMFACheckerName_PrevAndNext(
			long mfaTOTPId, String mfaCheckerName,
			OrderByComparator<MFATOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByMFACheckerName_PrevAndNext(
			mfaTOTPId, mfaCheckerName, orderByComparator);
	}

	/**
	 * Removes all the mfatotps where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	public static void removeByMFACheckerName(String mfaCheckerName) {
		getPersistence().removeByMFACheckerName(mfaCheckerName);
	}

	/**
	 * Returns the number of mfatotps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching mfatotps
	 */
	public static int countByMFACheckerName(String mfaCheckerName) {
		return getPersistence().countByMFACheckerName(mfaCheckerName);
	}

	/**
	 * Caches the mfatotp in the entity cache if it is enabled.
	 *
	 * @param mfatotp the mfatotp
	 */
	public static void cacheResult(MFATOTP mfatotp) {
		getPersistence().cacheResult(mfatotp);
	}

	/**
	 * Caches the mfatotps in the entity cache if it is enabled.
	 *
	 * @param mfatotps the mfatotps
	 */
	public static void cacheResult(List<MFATOTP> mfatotps) {
		getPersistence().cacheResult(mfatotps);
	}

	/**
	 * Creates a new mfatotp with the primary key. Does not add the mfatotp to the database.
	 *
	 * @param mfaTOTPId the primary key for the new mfatotp
	 * @return the new mfatotp
	 */
	public static MFATOTP create(long mfaTOTPId) {
		return getPersistence().create(mfaTOTPId);
	}

	/**
	 * Removes the mfatotp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp that was removed
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public static MFATOTP remove(long mfaTOTPId)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().remove(mfaTOTPId);
	}

	public static MFATOTP updateImpl(MFATOTP mfatotp) {
		return getPersistence().updateImpl(mfatotp);
	}

	/**
	 * Returns the mfatotp with the primary key or throws a <code>NoSuchMFATOTPException</code> if it could not be found.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public static MFATOTP findByPrimaryKey(long mfaTOTPId)
		throws com.liferay.multi.factor.authentication.checker.totp.exception.
			NoSuchMFATOTPException {

		return getPersistence().findByPrimaryKey(mfaTOTPId);
	}

	/**
	 * Returns the mfatotp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp, or <code>null</code> if a mfatotp with the primary key could not be found
	 */
	public static MFATOTP fetchByPrimaryKey(long mfaTOTPId) {
		return getPersistence().fetchByPrimaryKey(mfaTOTPId);
	}

	/**
	 * Returns all the mfatotps.
	 *
	 * @return the mfatotps
	 */
	public static List<MFATOTP> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the mfatotps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @return the range of mfatotps
	 */
	public static List<MFATOTP> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the mfatotps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mfatotps
	 */
	public static List<MFATOTP> findAll(
		int start, int end, OrderByComparator<MFATOTP> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mfatotps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFATOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfatotps
	 * @param end the upper bound of the range of mfatotps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of mfatotps
	 */
	public static List<MFATOTP> findAll(
		int start, int end, OrderByComparator<MFATOTP> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the mfatotps from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of mfatotps.
	 *
	 * @return the number of mfatotps
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MFATOTPPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MFATOTPPersistence, MFATOTPPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MFATOTPPersistence.class);

		ServiceTracker<MFATOTPPersistence, MFATOTPPersistence> serviceTracker =
			new ServiceTracker<MFATOTPPersistence, MFATOTPPersistence>(
				bundle.getBundleContext(), MFATOTPPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}