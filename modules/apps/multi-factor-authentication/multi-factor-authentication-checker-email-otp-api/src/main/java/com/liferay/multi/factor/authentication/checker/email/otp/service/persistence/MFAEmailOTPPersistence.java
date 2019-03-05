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

import com.liferay.multi.factor.authentication.checker.email.otp.exception.NoSuchMFAEmailOTPException;
import com.liferay.multi.factor.authentication.checker.email.otp.model.MFAEmailOTP;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the mfa email otp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @see MFAEmailOTPUtil
 * @generated
 */
@ProviderType
public interface MFAEmailOTPPersistence extends BasePersistence<MFAEmailOTP> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MFAEmailOTPUtil} to access the mfa email otp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or throws a <code>NoSuchMFAEmailOTPException</code> if it could not be found.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP findByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP fetchByM_U(String mfaCheckerName, long userId);

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP fetchByM_U(
		String mfaCheckerName, long userId, boolean retrieveFromCache);

	/**
	 * Removes the mfa email otp where mfaCheckerName = &#63; and userId = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the mfa email otp that was removed
	 */
	public MFAEmailOTP removeByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Returns the number of mfa email otps where mfaCheckerName = &#63; and userId = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the number of matching mfa email otps
	 */
	public int countByM_U(String mfaCheckerName, long userId);

	/**
	 * Returns all the mfa email otps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mfa email otps
	 */
	public java.util.List<MFAEmailOTP> findByUserId(long userId);

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
	public java.util.List<MFAEmailOTP> findByUserId(
		long userId, int start, int end);

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
	public java.util.List<MFAEmailOTP> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator);

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
	public java.util.List<MFAEmailOTP> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
				orderByComparator)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Returns the first mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator);

	/**
	 * Returns the last mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
				orderByComparator)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Returns the last mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator);

	/**
	 * Returns the mfa email otps before and after the current mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param mfaEmailOTPId the primary key of the current mfa email otp
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public MFAEmailOTP[] findByUserId_PrevAndNext(
			long mfaEmailOTPId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
				orderByComparator)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Removes all the mfa email otps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of mfa email otps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mfa email otps
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the mfa email otps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching mfa email otps
	 */
	public java.util.List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName);

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
	public java.util.List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end);

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
	public java.util.List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator);

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
	public java.util.List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP findByMFACheckerName_First(
			String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
				orderByComparator)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Returns the first mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP fetchByMFACheckerName_First(
		String mfaCheckerName,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator);

	/**
	 * Returns the last mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP findByMFACheckerName_Last(
			String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
				orderByComparator)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Returns the last mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	public MFAEmailOTP fetchByMFACheckerName_Last(
		String mfaCheckerName,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator);

	/**
	 * Returns the mfa email otps before and after the current mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaEmailOTPId the primary key of the current mfa email otp
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public MFAEmailOTP[] findByMFACheckerName_PrevAndNext(
			long mfaEmailOTPId, String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
				orderByComparator)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Removes all the mfa email otps where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	public void removeByMFACheckerName(String mfaCheckerName);

	/**
	 * Returns the number of mfa email otps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching mfa email otps
	 */
	public int countByMFACheckerName(String mfaCheckerName);

	/**
	 * Caches the mfa email otp in the entity cache if it is enabled.
	 *
	 * @param mfaEmailOTP the mfa email otp
	 */
	public void cacheResult(MFAEmailOTP mfaEmailOTP);

	/**
	 * Caches the mfa email otps in the entity cache if it is enabled.
	 *
	 * @param mfaEmailOTPs the mfa email otps
	 */
	public void cacheResult(java.util.List<MFAEmailOTP> mfaEmailOTPs);

	/**
	 * Creates a new mfa email otp with the primary key. Does not add the mfa email otp to the database.
	 *
	 * @param mfaEmailOTPId the primary key for the new mfa email otp
	 * @return the new mfa email otp
	 */
	public MFAEmailOTP create(long mfaEmailOTPId);

	/**
	 * Removes the mfa email otp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp that was removed
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public MFAEmailOTP remove(long mfaEmailOTPId)
		throws NoSuchMFAEmailOTPException;

	public MFAEmailOTP updateImpl(MFAEmailOTP mfaEmailOTP);

	/**
	 * Returns the mfa email otp with the primary key or throws a <code>NoSuchMFAEmailOTPException</code> if it could not be found.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	public MFAEmailOTP findByPrimaryKey(long mfaEmailOTPId)
		throws NoSuchMFAEmailOTPException;

	/**
	 * Returns the mfa email otp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp, or <code>null</code> if a mfa email otp with the primary key could not be found
	 */
	public MFAEmailOTP fetchByPrimaryKey(long mfaEmailOTPId);

	/**
	 * Returns all the mfa email otps.
	 *
	 * @return the mfa email otps
	 */
	public java.util.List<MFAEmailOTP> findAll();

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
	public java.util.List<MFAEmailOTP> findAll(int start, int end);

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
	public java.util.List<MFAEmailOTP> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator);

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
	public java.util.List<MFAEmailOTP> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFAEmailOTP>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the mfa email otps from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of mfa email otps.
	 *
	 * @return the number of mfa email otps
	 */
	public int countAll();

}