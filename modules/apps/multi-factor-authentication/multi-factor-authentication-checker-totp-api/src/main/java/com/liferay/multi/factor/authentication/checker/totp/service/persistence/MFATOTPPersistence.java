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

import com.liferay.multi.factor.authentication.checker.totp.exception.NoSuchMFATOTPException;
import com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the mfatotp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @see MFATOTPUtil
 * @generated
 */
@ProviderType
public interface MFATOTPPersistence extends BasePersistence<MFATOTP> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MFATOTPUtil} to access the mfatotp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or throws a <code>NoSuchMFATOTPException</code> if it could not be found.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public MFATOTP findByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFATOTPException;

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public MFATOTP fetchByM_U(String mfaCheckerName, long userId);

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public MFATOTP fetchByM_U(
		String mfaCheckerName, long userId, boolean retrieveFromCache);

	/**
	 * Removes the mfatotp where mfaCheckerName = &#63; and userId = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the mfatotp that was removed
	 */
	public MFATOTP removeByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFATOTPException;

	/**
	 * Returns the number of mfatotps where mfaCheckerName = &#63; and userId = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the number of matching mfatotps
	 */
	public int countByM_U(String mfaCheckerName, long userId);

	/**
	 * Returns all the mfatotps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mfatotps
	 */
	public java.util.List<MFATOTP> findByUserId(long userId);

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
	public java.util.List<MFATOTP> findByUserId(
		long userId, int start, int end);

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
	public java.util.List<MFATOTP> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator);

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
	public java.util.List<MFATOTP> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public MFATOTP findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
				orderByComparator)
		throws NoSuchMFATOTPException;

	/**
	 * Returns the first mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public MFATOTP fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator);

	/**
	 * Returns the last mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public MFATOTP findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
				orderByComparator)
		throws NoSuchMFATOTPException;

	/**
	 * Returns the last mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public MFATOTP fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator);

	/**
	 * Returns the mfatotps before and after the current mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param mfaTOTPId the primary key of the current mfatotp
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public MFATOTP[] findByUserId_PrevAndNext(
			long mfaTOTPId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
				orderByComparator)
		throws NoSuchMFATOTPException;

	/**
	 * Removes all the mfatotps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of mfatotps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mfatotps
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the mfatotps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching mfatotps
	 */
	public java.util.List<MFATOTP> findByMFACheckerName(String mfaCheckerName);

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
	public java.util.List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end);

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
	public java.util.List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator);

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
	public java.util.List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public MFATOTP findByMFACheckerName_First(
			String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
				orderByComparator)
		throws NoSuchMFATOTPException;

	/**
	 * Returns the first mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public MFATOTP fetchByMFACheckerName_First(
		String mfaCheckerName,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator);

	/**
	 * Returns the last mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	public MFATOTP findByMFACheckerName_Last(
			String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
				orderByComparator)
		throws NoSuchMFATOTPException;

	/**
	 * Returns the last mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	public MFATOTP fetchByMFACheckerName_Last(
		String mfaCheckerName,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator);

	/**
	 * Returns the mfatotps before and after the current mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaTOTPId the primary key of the current mfatotp
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public MFATOTP[] findByMFACheckerName_PrevAndNext(
			long mfaTOTPId, String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
				orderByComparator)
		throws NoSuchMFATOTPException;

	/**
	 * Removes all the mfatotps where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	public void removeByMFACheckerName(String mfaCheckerName);

	/**
	 * Returns the number of mfatotps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching mfatotps
	 */
	public int countByMFACheckerName(String mfaCheckerName);

	/**
	 * Caches the mfatotp in the entity cache if it is enabled.
	 *
	 * @param mfatotp the mfatotp
	 */
	public void cacheResult(MFATOTP mfatotp);

	/**
	 * Caches the mfatotps in the entity cache if it is enabled.
	 *
	 * @param mfatotps the mfatotps
	 */
	public void cacheResult(java.util.List<MFATOTP> mfatotps);

	/**
	 * Creates a new mfatotp with the primary key. Does not add the mfatotp to the database.
	 *
	 * @param mfaTOTPId the primary key for the new mfatotp
	 * @return the new mfatotp
	 */
	public MFATOTP create(long mfaTOTPId);

	/**
	 * Removes the mfatotp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp that was removed
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public MFATOTP remove(long mfaTOTPId) throws NoSuchMFATOTPException;

	public MFATOTP updateImpl(MFATOTP mfatotp);

	/**
	 * Returns the mfatotp with the primary key or throws a <code>NoSuchMFATOTPException</code> if it could not be found.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	public MFATOTP findByPrimaryKey(long mfaTOTPId)
		throws NoSuchMFATOTPException;

	/**
	 * Returns the mfatotp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp, or <code>null</code> if a mfatotp with the primary key could not be found
	 */
	public MFATOTP fetchByPrimaryKey(long mfaTOTPId);

	/**
	 * Returns all the mfatotps.
	 *
	 * @return the mfatotps
	 */
	public java.util.List<MFATOTP> findAll();

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
	public java.util.List<MFATOTP> findAll(int start, int end);

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
	public java.util.List<MFATOTP> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator);

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
	public java.util.List<MFATOTP> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MFATOTP>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the mfatotps from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of mfatotps.
	 *
	 * @return the number of mfatotps
	 */
	public int countAll();

}