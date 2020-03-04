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

package com.liferay.multi.factor.authentication.checker.timebased.otp.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TimebasedOTPEntryLocalService}.
 *
 * @author arthurchan35
 * @see TimebasedOTPEntryLocalService
 * @generated
 */
@ProviderType
public class TimebasedOTPEntryLocalServiceWrapper
	implements TimebasedOTPEntryLocalService,
			   ServiceWrapper<TimebasedOTPEntryLocalService> {

	public TimebasedOTPEntryLocalServiceWrapper(
		TimebasedOTPEntryLocalService timebasedOTPEntryLocalService) {

		_timebasedOTPEntryLocalService = timebasedOTPEntryLocalService;
	}

	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry addTimebasedOTPEntry(
				String mfaCheckerName, String sharedSecret, long userId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _timebasedOTPEntryLocalService.addTimebasedOTPEntry(
			mfaCheckerName, sharedSecret, userId);
	}

	/**
	 * Adds the timebased otp entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param timebasedOTPEntry the timebased otp entry
	 * @return the timebased otp entry that was added
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry addTimebasedOTPEntry(
			com.liferay.multi.factor.authentication.checker.timebased.otp.model.
				TimebasedOTPEntry timebasedOTPEntry) {

		return _timebasedOTPEntryLocalService.addTimebasedOTPEntry(
			timebasedOTPEntry);
	}

	/**
	 * Creates a new timebased otp entry with the primary key. Does not add the timebased otp entry to the database.
	 *
	 * @param entryId the primary key for the new timebased otp entry
	 * @return the new timebased otp entry
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry createTimebasedOTPEntry(long entryId) {

		return _timebasedOTPEntryLocalService.createTimebasedOTPEntry(entryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _timebasedOTPEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the timebased otp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry that was removed
	 * @throws PortalException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry deleteTimebasedOTPEntry(long entryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _timebasedOTPEntryLocalService.deleteTimebasedOTPEntry(entryId);
	}

	/**
	 * Deletes the timebased otp entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param timebasedOTPEntry the timebased otp entry
	 * @return the timebased otp entry that was removed
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry deleteTimebasedOTPEntry(
			com.liferay.multi.factor.authentication.checker.timebased.otp.model.
				TimebasedOTPEntry timebasedOTPEntry) {

		return _timebasedOTPEntryLocalService.deleteTimebasedOTPEntry(
			timebasedOTPEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _timebasedOTPEntryLocalService.dynamicQuery();
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

		return _timebasedOTPEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.checker.timebased.otp.model.impl.TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _timebasedOTPEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.checker.timebased.otp.model.impl.TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _timebasedOTPEntryLocalService.dynamicQuery(
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

		return _timebasedOTPEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _timebasedOTPEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry fetchTimebasedOTPEntry(long entryId) {

		return _timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(entryId);
	}

	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry fetchTimebasedOTPEntry(
			long userId, String mfaCheckerName) {

		return _timebasedOTPEntryLocalService.fetchTimebasedOTPEntry(
			userId, mfaCheckerName);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _timebasedOTPEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.liferay.multi.factor.authentication.checker.timebased.otp.model.
			TimebasedOTPEntry> geTimebasedOTPEntriesByMFACheckerName(
				String mfaCheckerName, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.multi.factor.authentication.checker.timebased.
						otp.model.TimebasedOTPEntry> orderByComparator) {

		return _timebasedOTPEntryLocalService.
			geTimebasedOTPEntriesByMFACheckerName(
				mfaCheckerName, start, end, orderByComparator);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _timebasedOTPEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _timebasedOTPEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _timebasedOTPEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the timebased otp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.checker.timebased.otp.model.impl.TimebasedOTPEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timebased otp entries
	 * @param end the upper bound of the range of timebased otp entries (not inclusive)
	 * @return the range of timebased otp entries
	 */
	@Override
	public java.util.List
		<com.liferay.multi.factor.authentication.checker.timebased.otp.model.
			TimebasedOTPEntry> getTimebasedOTPEntries(int start, int end) {

		return _timebasedOTPEntryLocalService.getTimebasedOTPEntries(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.multi.factor.authentication.checker.timebased.otp.model.
			TimebasedOTPEntry> getTimebasedOTPEntriesByUserId(
				long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.multi.factor.authentication.checker.timebased.
						otp.model.TimebasedOTPEntry> orderByComparator) {

		return _timebasedOTPEntryLocalService.getTimebasedOTPEntriesByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of timebased otp entries.
	 *
	 * @return the number of timebased otp entries
	 */
	@Override
	public int getTimebasedOTPEntriesCount() {
		return _timebasedOTPEntryLocalService.getTimebasedOTPEntriesCount();
	}

	/**
	 * Returns the timebased otp entry with the primary key.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry
	 * @throws PortalException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry getTimebasedOTPEntry(long entryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _timebasedOTPEntryLocalService.getTimebasedOTPEntry(entryId);
	}

	@Override
	public boolean updateFailedAttempt(
		String mfaCheckerName, long userId, String userIP) {

		return _timebasedOTPEntryLocalService.updateFailedAttempt(
			mfaCheckerName, userId, userIP);
	}

	@Override
	public boolean updateSuccessAttempt(
		String mfaCheckerName, long userId, String userIP) {

		return _timebasedOTPEntryLocalService.updateSuccessAttempt(
			mfaCheckerName, userId, userIP);
	}

	/**
	 * Updates the timebased otp entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param timebasedOTPEntry the timebased otp entry
	 * @return the timebased otp entry that was updated
	 */
	@Override
	public com.liferay.multi.factor.authentication.checker.timebased.otp.model.
		TimebasedOTPEntry updateTimebasedOTPEntry(
			com.liferay.multi.factor.authentication.checker.timebased.otp.model.
				TimebasedOTPEntry timebasedOTPEntry) {

		return _timebasedOTPEntryLocalService.updateTimebasedOTPEntry(
			timebasedOTPEntry);
	}

	@Override
	public TimebasedOTPEntryLocalService getWrappedService() {
		return _timebasedOTPEntryLocalService;
	}

	@Override
	public void setWrappedService(
		TimebasedOTPEntryLocalService timebasedOTPEntryLocalService) {

		_timebasedOTPEntryLocalService = timebasedOTPEntryLocalService;
	}

	private TimebasedOTPEntryLocalService _timebasedOTPEntryLocalService;

}