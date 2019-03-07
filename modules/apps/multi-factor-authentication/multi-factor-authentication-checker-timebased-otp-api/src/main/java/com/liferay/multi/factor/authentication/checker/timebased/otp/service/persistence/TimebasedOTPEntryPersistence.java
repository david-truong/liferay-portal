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

package com.liferay.multi.factor.authentication.checker.timebased.otp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.timebased.otp.exception.NoSuchTimebasedOTPEntryException;
import com.liferay.multi.factor.authentication.checker.timebased.otp.model.TimebasedOTPEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the timebased otp entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @see TimebasedOTPEntryUtil
 * @generated
 */
@ProviderType
public interface TimebasedOTPEntryPersistence
	extends BasePersistence<TimebasedOTPEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TimebasedOTPEntryUtil} to access the timebased otp entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the timebased otp entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByUserId(long userId);

	/**
	 * Returns a range of all the timebased otp entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @return the range of matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByUserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the timebased otp entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the timebased otp entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
				orderByComparator)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Returns the first timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator);

	/**
	 * Returns the last timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
				orderByComparator)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Returns the last timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator);

	/**
	 * Returns the timebased otp entries before and after the current timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param entryId the primary key of the current timebased otp entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	public TimebasedOTPEntry[] findByUserId_PrevAndNext(
			long entryId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
				orderByComparator)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Removes all the timebased otp entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of timebased otp entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching timebased otp entries
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the timebased otp entries where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByMFACheckerName(
		String mfaCheckerName);

	/**
	 * Returns a range of all the timebased otp entries where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @return the range of matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByMFACheckerName(
		String mfaCheckerName, int start, int end);

	/**
	 * Returns an ordered range of all the timebased otp entries where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the timebased otp entries where mfaCheckerName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry findByMFACheckerName_First(
			String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
				orderByComparator)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Returns the first timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry fetchByMFACheckerName_First(
		String mfaCheckerName,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator);

	/**
	 * Returns the last timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry findByMFACheckerName_Last(
			String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
				orderByComparator)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Returns the last timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry fetchByMFACheckerName_Last(
		String mfaCheckerName,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator);

	/**
	 * Returns the timebased otp entries before and after the current timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param entryId the primary key of the current timebased otp entry
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	public TimebasedOTPEntry[] findByMFACheckerName_PrevAndNext(
			long entryId, String mfaCheckerName,
			com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
				orderByComparator)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Removes all the timebased otp entries where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	public void removeByMFACheckerName(String mfaCheckerName);

	/**
	 * Returns the number of timebased otp entries where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching timebased otp entries
	 */
	public int countByMFACheckerName(String mfaCheckerName);

	/**
	 * Returns the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; or throws a <code>NoSuchTimebasedOTPEntryException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry findByU_M(long userId, String mfaCheckerName)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Returns the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry fetchByU_M(long userId, String mfaCheckerName);

	/**
	 * Returns the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	public TimebasedOTPEntry fetchByU_M(
		long userId, String mfaCheckerName, boolean retrieveFromCache);

	/**
	 * Removes the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the timebased otp entry that was removed
	 */
	public TimebasedOTPEntry removeByU_M(long userId, String mfaCheckerName)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Returns the number of timebased otp entries where userId = &#63; and mfaCheckerName = &#63;.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching timebased otp entries
	 */
	public int countByU_M(long userId, String mfaCheckerName);

	/**
	 * Caches the timebased otp entry in the entity cache if it is enabled.
	 *
	 * @param timebasedOTPEntry the timebased otp entry
	 */
	public void cacheResult(TimebasedOTPEntry timebasedOTPEntry);

	/**
	 * Caches the timebased otp entries in the entity cache if it is enabled.
	 *
	 * @param timebasedOTPEntries the timebased otp entries
	 */
	public void cacheResult(
		java.util.List<TimebasedOTPEntry> timebasedOTPEntries);

	/**
	 * Creates a new timebased otp entry with the primary key. Does not add the timebased otp entry to the database.
	 *
	 * @param entryId the primary key for the new timebased otp entry
	 * @return the new timebased otp entry
	 */
	public TimebasedOTPEntry create(long entryId);

	/**
	 * Removes the timebased otp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry that was removed
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	public TimebasedOTPEntry remove(long entryId)
		throws NoSuchTimebasedOTPEntryException;

	public TimebasedOTPEntry updateImpl(TimebasedOTPEntry timebasedOTPEntry);

	/**
	 * Returns the timebased otp entry with the primary key or throws a <code>NoSuchTimebasedOTPEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	public TimebasedOTPEntry findByPrimaryKey(long entryId)
		throws NoSuchTimebasedOTPEntryException;

	/**
	 * Returns the timebased otp entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry, or <code>null</code> if a timebased otp entry with the primary key could not be found
	 */
	public TimebasedOTPEntry fetchByPrimaryKey(long entryId);

	/**
	 * Returns all the timebased otp entries.
	 *
	 * @return the timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findAll();

	/**
	 * Returns a range of all the timebased otp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @return the range of timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the timebased otp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the timebased otp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of timebased otp entries
	 */
	public java.util.List<TimebasedOTPEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimebasedOTPEntry>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the timebased otp entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of timebased otp entries.
	 *
	 * @return the number of timebased otp entries
	 */
	public int countAll();

}