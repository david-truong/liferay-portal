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

package com.liferay.multi.factor.authentication.checker.email.otp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.email.otp.model.MFAEmailOTP;
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
 * The persistence utility for the mfa email otp service. This utility wraps <code>com.liferay.multi.factor.authentication.checker.email.otp.service.persistence.impl.MFAEmailOTPPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @see MFAEmailOTPPersistence
 * @generated
 */
@ProviderType
public class MFAEmailOTPUtil {

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
	public static void clearCache(MFAEmailOTP mfaEmailOTP) {
		getPersistence().clearCache(mfaEmailOTP);
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
	public static Map<Serializable, MFAEmailOTP> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MFAEmailOTP> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MFAEmailOTP> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MFAEmailOTP> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MFAEmailOTP update(MFAEmailOTP mfaEmailOTP) {
		return getPersistence().update(mfaEmailOTP);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MFAEmailOTP update(
		MFAEmailOTP mfaEmailOTP, ServiceContext serviceContext) {

		return getPersistence().update(mfaEmailOTP, serviceContext);
	}

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or throws a <code>NoSuchMFAEmailOTPException</code> if it could not be found.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP findByM_U(String mfaCheckerName, long userId)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP fetchByM_U(String mfaCheckerName, long userId) {
		return getPersistence().fetchByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP fetchByM_U(
		String mfaCheckerName, long userId, boolean retrieveFromCache) {

		return getPersistence().fetchByM_U(
			mfaCheckerName, userId, retrieveFromCache);
	}

	/**
	 * Removes the mfa email otp where mfaCheckerName = &#63; and userId = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the mfa email otp that was removed
	 */
	public static MFAEmailOTP removeByM_U(String mfaCheckerName, long userId)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().removeByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns the number of mfa email otps where mfaCheckerName = &#63; and userId = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the number of matching mfa email otps
	 */
	public static int countByM_U(String mfaCheckerName, long userId) {
		return getPersistence().countByM_U(mfaCheckerName, userId);
	}

	/**
	 * Returns all the mfa email otps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the mfa email otps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @return the range of matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the mfa email otps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mfa email otps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP findByUserId_First(
			long userId, OrderByComparator<MFAEmailOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP fetchByUserId_First(
		long userId, OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP findByUserId_Last(
			long userId, OrderByComparator<MFAEmailOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP fetchByUserId_Last(
		long userId, OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the mfa email otps before and after the current mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param mfaEmailOTPId the primary key of the current mfa email otp
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public static MFAEmailOTP[] findByUserId_PrevAndNext(
			long mfaEmailOTPId, long userId,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByUserId_PrevAndNext(
			mfaEmailOTPId, userId, orderByComparator);
	}

	/**
	 * Removes all the mfa email otps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of mfa email otps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mfa email otps
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the mfa email otps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName) {

		return getPersistence().findByMFACheckerName(mfaCheckerName);
	}

	/**
	 * Returns a range of all the mfa email otps where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @return the range of matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end) {

		return getPersistence().findByMFACheckerName(
			mfaCheckerName, start, end);
	}

	/**
	 * Returns an ordered range of all the mfa email otps where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mfa email otps where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching mfa email otps
	 */
	public static List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP findByMFACheckerName_First(
			String mfaCheckerName,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByMFACheckerName_First(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the first mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP fetchByMFACheckerName_First(
		String mfaCheckerName,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().fetchByMFACheckerName_First(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the last mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP findByMFACheckerName_Last(
			String mfaCheckerName,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByMFACheckerName_Last(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the last mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public static MFAEmailOTP fetchByMFACheckerName_Last(
		String mfaCheckerName,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().fetchByMFACheckerName_Last(
			mfaCheckerName, orderByComparator);
	}

	/**
	 * Returns the mfa email otps before and after the current mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaEmailOTPId the primary key of the current mfa email otp
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public static MFAEmailOTP[] findByMFACheckerName_PrevAndNext(
			long mfaEmailOTPId, String mfaCheckerName,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByMFACheckerName_PrevAndNext(
			mfaEmailOTPId, mfaCheckerName, orderByComparator);
	}

	/**
	 * Removes all the mfa email otps where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	public static void removeByMFACheckerName(String mfaCheckerName) {
		getPersistence().removeByMFACheckerName(mfaCheckerName);
	}

	/**
	 * Returns the number of mfa email otps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching mfa email otps
	 */
	public static int countByMFACheckerName(String mfaCheckerName) {
		return getPersistence().countByMFACheckerName(mfaCheckerName);
	}

	/**
	 * Caches the mfa email otp in the entity cache if it is enabled.
	 *
	 * @param mfaEmailOTP the mfa email otp
	 */
	public static void cacheResult(MFAEmailOTP mfaEmailOTP) {
		getPersistence().cacheResult(mfaEmailOTP);
	}

	/**
	 * Caches the mfa email otps in the entity cache if it is enabled.
	 *
	 * @param mfaEmailOTPs the mfa email otps
	 */
	public static void cacheResult(List<MFAEmailOTP> mfaEmailOTPs) {
		getPersistence().cacheResult(mfaEmailOTPs);
	}

	/**
	 * Creates a new mfa email otp with the primary key. Does not add the mfa email otp to the database.
	 *
	 * @param mfaEmailOTPId the primary key for the new mfa email otp
	 * @return the new mfa email otp
	 */
	public static MFAEmailOTP create(long mfaEmailOTPId) {
		return getPersistence().create(mfaEmailOTPId);
	}

	/**
	 * Removes the mfa email otp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp that was removed
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public static MFAEmailOTP remove(long mfaEmailOTPId)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().remove(mfaEmailOTPId);
	}

	public static MFAEmailOTP updateImpl(MFAEmailOTP mfaEmailOTP) {
		return getPersistence().updateImpl(mfaEmailOTP);
	}

	/**
	 * Returns the mfa email otp with the primary key or throws a <code>NoSuchMFAEmailOTPException</code> if it could not be found.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public static MFAEmailOTP findByPrimaryKey(long mfaEmailOTPId)
		throws com.liferay.multi.factor.authentication.checker.email.otp.
			exception.NoSuchMFAEmailOTPException {

		return getPersistence().findByPrimaryKey(mfaEmailOTPId);
	}

	/**
	 * Returns the mfa email otp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp, or <code>null</code> if a mfa email otp with the primary key could not be found
	 */
	public static MFAEmailOTP fetchByPrimaryKey(long mfaEmailOTPId) {
		return getPersistence().fetchByPrimaryKey(mfaEmailOTPId);
	}

	/**
	 * Returns all the mfa email otps.
	 *
	 * @return the mfa email otps
	 */
	public static List<MFAEmailOTP> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the mfa email otps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @return the range of mfa email otps
	 */
	public static List<MFAEmailOTP> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the mfa email otps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mfa email otps
	 */
	public static List<MFAEmailOTP> findAll(
		int start, int end, OrderByComparator<MFAEmailOTP> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mfa email otps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>MFAEmailOTPModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfa email otps
	 * @param end the upper bound of the range of mfa email otps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of mfa email otps
	 */
	public static List<MFAEmailOTP> findAll(
		int start, int end, OrderByComparator<MFAEmailOTP> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the mfa email otps from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of mfa email otps.
	 *
	 * @return the number of mfa email otps
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MFAEmailOTPPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<MFAEmailOTPPersistence, MFAEmailOTPPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MFAEmailOTPPersistence.class);

		ServiceTracker<MFAEmailOTPPersistence, MFAEmailOTPPersistence>
			serviceTracker =
				new ServiceTracker
					<MFAEmailOTPPersistence, MFAEmailOTPPersistence>(
						bundle.getBundleContext(), MFAEmailOTPPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}