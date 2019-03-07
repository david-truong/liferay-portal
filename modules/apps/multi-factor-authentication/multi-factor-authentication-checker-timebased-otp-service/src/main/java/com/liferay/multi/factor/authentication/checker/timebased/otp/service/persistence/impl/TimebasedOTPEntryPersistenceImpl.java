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

package com.liferay.multi.factor.authentication.checker.timebased.otp.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.timebased.otp.exception.NoSuchTimebasedOTPEntryException;
import com.liferay.multi.factor.authentication.checker.timebased.otp.model.TimebasedOTPEntry;
import com.liferay.multi.factor.authentication.checker.timebased.otp.model.impl.TimebasedOTPEntryImpl;
import com.liferay.multi.factor.authentication.checker.timebased.otp.model.impl.TimebasedOTPEntryModelImpl;
import com.liferay.multi.factor.authentication.checker.timebased.otp.service.persistence.TimebasedOTPEntryPersistence;
import com.liferay.multi.factor.authentication.checker.timebased.otp.service.persistence.impl.constants.MFATOTPPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the timebased otp entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @generated
 */
@Component(service = TimebasedOTPEntryPersistence.class)
@ProviderType
public class TimebasedOTPEntryPersistenceImpl
	extends BasePersistenceImpl<TimebasedOTPEntry>
	implements TimebasedOTPEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TimebasedOTPEntryUtil</code> to access the timebased otp entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TimebasedOTPEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the timebased otp entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching timebased otp entries
	 */
	@Override
	public List<TimebasedOTPEntry> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<TimebasedOTPEntry> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

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
	@Override
	public List<TimebasedOTPEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<TimebasedOTPEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUserId;
			finderArgs = new Object[] {userId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<TimebasedOTPEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TimebasedOTPEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimebasedOTPEntry timebasedOTPEntry : list) {
					if ((userId != timebasedOTPEntry.getUserId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TIMEBASEDOTPENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(TimebasedOTPEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<TimebasedOTPEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimebasedOTPEntry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry findByUserId_First(
			long userId, OrderByComparator<TimebasedOTPEntry> orderByComparator)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = fetchByUserId_First(
			userId, orderByComparator);

		if (timebasedOTPEntry != null) {
			return timebasedOTPEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTimebasedOTPEntryException(msg.toString());
	}

	/**
	 * Returns the first timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry fetchByUserId_First(
		long userId, OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		List<TimebasedOTPEntry> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry findByUserId_Last(
			long userId, OrderByComparator<TimebasedOTPEntry> orderByComparator)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = fetchByUserId_Last(
			userId, orderByComparator);

		if (timebasedOTPEntry != null) {
			return timebasedOTPEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTimebasedOTPEntryException(msg.toString());
	}

	/**
	 * Returns the last timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry fetchByUserId_Last(
		long userId, OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<TimebasedOTPEntry> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timebased otp entries before and after the current timebased otp entry in the ordered set where userId = &#63;.
	 *
	 * @param entryId the primary key of the current timebased otp entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public TimebasedOTPEntry[] findByUserId_PrevAndNext(
			long entryId, long userId,
			OrderByComparator<TimebasedOTPEntry> orderByComparator)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			TimebasedOTPEntry[] array = new TimebasedOTPEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, timebasedOTPEntry, userId, orderByComparator, true);

			array[1] = timebasedOTPEntry;

			array[2] = getByUserId_PrevAndNext(
				session, timebasedOTPEntry, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimebasedOTPEntry getByUserId_PrevAndNext(
		Session session, TimebasedOTPEntry timebasedOTPEntry, long userId,
		OrderByComparator<TimebasedOTPEntry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TIMEBASEDOTPENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimebasedOTPEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						timebasedOTPEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TimebasedOTPEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timebased otp entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (TimebasedOTPEntry timebasedOTPEntry :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(timebasedOTPEntry);
		}
	}

	/**
	 * Returns the number of timebased otp entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching timebased otp entries
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TIMEBASEDOTPENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"timebasedOTPEntry.userId = ?";

	private FinderPath _finderPathWithPaginationFindByMFACheckerName;
	private FinderPath _finderPathWithoutPaginationFindByMFACheckerName;
	private FinderPath _finderPathCountByMFACheckerName;

	/**
	 * Returns all the timebased otp entries where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching timebased otp entries
	 */
	@Override
	public List<TimebasedOTPEntry> findByMFACheckerName(String mfaCheckerName) {
		return findByMFACheckerName(
			mfaCheckerName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<TimebasedOTPEntry> findByMFACheckerName(
		String mfaCheckerName, int start, int end) {

		return findByMFACheckerName(mfaCheckerName, start, end, null);
	}

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
	@Override
	public List<TimebasedOTPEntry> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		return findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator, true);
	}

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
	@Override
	public List<TimebasedOTPEntry> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator,
		boolean retrieveFromCache) {

		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByMFACheckerName;
			finderArgs = new Object[] {mfaCheckerName};
		}
		else {
			finderPath = _finderPathWithPaginationFindByMFACheckerName;
			finderArgs = new Object[] {
				mfaCheckerName, start, end, orderByComparator
			};
		}

		List<TimebasedOTPEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TimebasedOTPEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimebasedOTPEntry timebasedOTPEntry : list) {
					if (!mfaCheckerName.equals(
							timebasedOTPEntry.getMfaCheckerName())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TIMEBASEDOTPENTRY_WHERE);

			boolean bindMfaCheckerName = false;

			if (mfaCheckerName.isEmpty()) {
				query.append(_FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_3);
			}
			else {
				bindMfaCheckerName = true;

				query.append(_FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(TimebasedOTPEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMfaCheckerName) {
					qPos.add(mfaCheckerName);
				}

				if (!pagination) {
					list = (List<TimebasedOTPEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimebasedOTPEntry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry findByMFACheckerName_First(
			String mfaCheckerName,
			OrderByComparator<TimebasedOTPEntry> orderByComparator)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = fetchByMFACheckerName_First(
			mfaCheckerName, orderByComparator);

		if (timebasedOTPEntry != null) {
			return timebasedOTPEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mfaCheckerName=");
		msg.append(mfaCheckerName);

		msg.append("}");

		throw new NoSuchTimebasedOTPEntryException(msg.toString());
	}

	/**
	 * Returns the first timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry fetchByMFACheckerName_First(
		String mfaCheckerName,
		OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		List<TimebasedOTPEntry> list = findByMFACheckerName(
			mfaCheckerName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry findByMFACheckerName_Last(
			String mfaCheckerName,
			OrderByComparator<TimebasedOTPEntry> orderByComparator)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = fetchByMFACheckerName_Last(
			mfaCheckerName, orderByComparator);

		if (timebasedOTPEntry != null) {
			return timebasedOTPEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mfaCheckerName=");
		msg.append(mfaCheckerName);

		msg.append("}");

		throw new NoSuchTimebasedOTPEntryException(msg.toString());
	}

	/**
	 * Returns the last timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry fetchByMFACheckerName_Last(
		String mfaCheckerName,
		OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		int count = countByMFACheckerName(mfaCheckerName);

		if (count == 0) {
			return null;
		}

		List<TimebasedOTPEntry> list = findByMFACheckerName(
			mfaCheckerName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timebased otp entries before and after the current timebased otp entry in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param entryId the primary key of the current timebased otp entry
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public TimebasedOTPEntry[] findByMFACheckerName_PrevAndNext(
			long entryId, String mfaCheckerName,
			OrderByComparator<TimebasedOTPEntry> orderByComparator)
		throws NoSuchTimebasedOTPEntryException {

		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		TimebasedOTPEntry timebasedOTPEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			TimebasedOTPEntry[] array = new TimebasedOTPEntryImpl[3];

			array[0] = getByMFACheckerName_PrevAndNext(
				session, timebasedOTPEntry, mfaCheckerName, orderByComparator,
				true);

			array[1] = timebasedOTPEntry;

			array[2] = getByMFACheckerName_PrevAndNext(
				session, timebasedOTPEntry, mfaCheckerName, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimebasedOTPEntry getByMFACheckerName_PrevAndNext(
		Session session, TimebasedOTPEntry timebasedOTPEntry,
		String mfaCheckerName,
		OrderByComparator<TimebasedOTPEntry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TIMEBASEDOTPENTRY_WHERE);

		boolean bindMfaCheckerName = false;

		if (mfaCheckerName.isEmpty()) {
			query.append(_FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_3);
		}
		else {
			bindMfaCheckerName = true;

			query.append(_FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimebasedOTPEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindMfaCheckerName) {
			qPos.add(mfaCheckerName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						timebasedOTPEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TimebasedOTPEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timebased otp entries where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	@Override
	public void removeByMFACheckerName(String mfaCheckerName) {
		for (TimebasedOTPEntry timebasedOTPEntry :
				findByMFACheckerName(
					mfaCheckerName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(timebasedOTPEntry);
		}
	}

	/**
	 * Returns the number of timebased otp entries where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching timebased otp entries
	 */
	@Override
	public int countByMFACheckerName(String mfaCheckerName) {
		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		FinderPath finderPath = _finderPathCountByMFACheckerName;

		Object[] finderArgs = new Object[] {mfaCheckerName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TIMEBASEDOTPENTRY_WHERE);

			boolean bindMfaCheckerName = false;

			if (mfaCheckerName.isEmpty()) {
				query.append(_FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_3);
			}
			else {
				bindMfaCheckerName = true;

				query.append(_FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMfaCheckerName) {
					qPos.add(mfaCheckerName);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_2 =
		"timebasedOTPEntry.mfaCheckerName = ?";

	private static final String _FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_3 =
		"(timebasedOTPEntry.mfaCheckerName IS NULL OR timebasedOTPEntry.mfaCheckerName = '')";

	private FinderPath _finderPathFetchByU_M;
	private FinderPath _finderPathCountByU_M;

	/**
	 * Returns the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; or throws a <code>NoSuchTimebasedOTPEntryException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry findByU_M(long userId, String mfaCheckerName)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = fetchByU_M(
			userId, mfaCheckerName);

		if (timebasedOTPEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", mfaCheckerName=");
			msg.append(mfaCheckerName);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTimebasedOTPEntryException(msg.toString());
		}

		return timebasedOTPEntry;
	}

	/**
	 * Returns the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry fetchByU_M(long userId, String mfaCheckerName) {
		return fetchByU_M(userId, mfaCheckerName, true);
	}

	/**
	 * Returns the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching timebased otp entry, or <code>null</code> if a matching timebased otp entry could not be found
	 */
	@Override
	public TimebasedOTPEntry fetchByU_M(
		long userId, String mfaCheckerName, boolean retrieveFromCache) {

		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		Object[] finderArgs = new Object[] {userId, mfaCheckerName};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByU_M, finderArgs, this);
		}

		if (result instanceof TimebasedOTPEntry) {
			TimebasedOTPEntry timebasedOTPEntry = (TimebasedOTPEntry)result;

			if ((userId != timebasedOTPEntry.getUserId()) ||
				!Objects.equals(
					mfaCheckerName, timebasedOTPEntry.getMfaCheckerName())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TIMEBASEDOTPENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_M_USERID_2);

			boolean bindMfaCheckerName = false;

			if (mfaCheckerName.isEmpty()) {
				query.append(_FINDER_COLUMN_U_M_MFACHECKERNAME_3);
			}
			else {
				bindMfaCheckerName = true;

				query.append(_FINDER_COLUMN_U_M_MFACHECKERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindMfaCheckerName) {
					qPos.add(mfaCheckerName);
				}

				List<TimebasedOTPEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByU_M, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"TimebasedOTPEntryPersistenceImpl.fetchByU_M(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TimebasedOTPEntry timebasedOTPEntry = list.get(0);

					result = timebasedOTPEntry;

					cacheResult(timebasedOTPEntry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByU_M, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (TimebasedOTPEntry)result;
		}
	}

	/**
	 * Removes the timebased otp entry where userId = &#63; and mfaCheckerName = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the timebased otp entry that was removed
	 */
	@Override
	public TimebasedOTPEntry removeByU_M(long userId, String mfaCheckerName)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = findByU_M(userId, mfaCheckerName);

		return remove(timebasedOTPEntry);
	}

	/**
	 * Returns the number of timebased otp entries where userId = &#63; and mfaCheckerName = &#63;.
	 *
	 * @param userId the user ID
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching timebased otp entries
	 */
	@Override
	public int countByU_M(long userId, String mfaCheckerName) {
		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		FinderPath finderPath = _finderPathCountByU_M;

		Object[] finderArgs = new Object[] {userId, mfaCheckerName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TIMEBASEDOTPENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_M_USERID_2);

			boolean bindMfaCheckerName = false;

			if (mfaCheckerName.isEmpty()) {
				query.append(_FINDER_COLUMN_U_M_MFACHECKERNAME_3);
			}
			else {
				bindMfaCheckerName = true;

				query.append(_FINDER_COLUMN_U_M_MFACHECKERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindMfaCheckerName) {
					qPos.add(mfaCheckerName);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_M_USERID_2 =
		"timebasedOTPEntry.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_M_MFACHECKERNAME_2 =
		"timebasedOTPEntry.mfaCheckerName = ?";

	private static final String _FINDER_COLUMN_U_M_MFACHECKERNAME_3 =
		"(timebasedOTPEntry.mfaCheckerName IS NULL OR timebasedOTPEntry.mfaCheckerName = '')";

	public TimebasedOTPEntryPersistenceImpl() {
		setModelClass(TimebasedOTPEntry.class);

		setModelImplClass(TimebasedOTPEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the timebased otp entry in the entity cache if it is enabled.
	 *
	 * @param timebasedOTPEntry the timebased otp entry
	 */
	@Override
	public void cacheResult(TimebasedOTPEntry timebasedOTPEntry) {
		entityCache.putResult(
			entityCacheEnabled, TimebasedOTPEntryImpl.class,
			timebasedOTPEntry.getPrimaryKey(), timebasedOTPEntry);

		finderCache.putResult(
			_finderPathFetchByU_M,
			new Object[] {
				timebasedOTPEntry.getUserId(),
				timebasedOTPEntry.getMfaCheckerName()
			},
			timebasedOTPEntry);

		timebasedOTPEntry.resetOriginalValues();
	}

	/**
	 * Caches the timebased otp entries in the entity cache if it is enabled.
	 *
	 * @param timebasedOTPEntries the timebased otp entries
	 */
	@Override
	public void cacheResult(List<TimebasedOTPEntry> timebasedOTPEntries) {
		for (TimebasedOTPEntry timebasedOTPEntry : timebasedOTPEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, TimebasedOTPEntryImpl.class,
					timebasedOTPEntry.getPrimaryKey()) == null) {

				cacheResult(timebasedOTPEntry);
			}
			else {
				timebasedOTPEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all timebased otp entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TimebasedOTPEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the timebased otp entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TimebasedOTPEntry timebasedOTPEntry) {
		entityCache.removeResult(
			entityCacheEnabled, TimebasedOTPEntryImpl.class,
			timebasedOTPEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(TimebasedOTPEntryModelImpl)timebasedOTPEntry, true);
	}

	@Override
	public void clearCache(List<TimebasedOTPEntry> timebasedOTPEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TimebasedOTPEntry timebasedOTPEntry : timebasedOTPEntries) {
			entityCache.removeResult(
				entityCacheEnabled, TimebasedOTPEntryImpl.class,
				timebasedOTPEntry.getPrimaryKey());

			clearUniqueFindersCache(
				(TimebasedOTPEntryModelImpl)timebasedOTPEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		TimebasedOTPEntryModelImpl timebasedOTPEntryModelImpl) {

		Object[] args = new Object[] {
			timebasedOTPEntryModelImpl.getUserId(),
			timebasedOTPEntryModelImpl.getMfaCheckerName()
		};

		finderCache.putResult(
			_finderPathCountByU_M, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByU_M, args, timebasedOTPEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TimebasedOTPEntryModelImpl timebasedOTPEntryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				timebasedOTPEntryModelImpl.getUserId(),
				timebasedOTPEntryModelImpl.getMfaCheckerName()
			};

			finderCache.removeResult(_finderPathCountByU_M, args);
			finderCache.removeResult(_finderPathFetchByU_M, args);
		}

		if ((timebasedOTPEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByU_M.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				timebasedOTPEntryModelImpl.getOriginalUserId(),
				timebasedOTPEntryModelImpl.getOriginalMfaCheckerName()
			};

			finderCache.removeResult(_finderPathCountByU_M, args);
			finderCache.removeResult(_finderPathFetchByU_M, args);
		}
	}

	/**
	 * Creates a new timebased otp entry with the primary key. Does not add the timebased otp entry to the database.
	 *
	 * @param entryId the primary key for the new timebased otp entry
	 * @return the new timebased otp entry
	 */
	@Override
	public TimebasedOTPEntry create(long entryId) {
		TimebasedOTPEntry timebasedOTPEntry = new TimebasedOTPEntryImpl();

		timebasedOTPEntry.setNew(true);
		timebasedOTPEntry.setPrimaryKey(entryId);

		timebasedOTPEntry.setCompanyId(companyProvider.getCompanyId());

		return timebasedOTPEntry;
	}

	/**
	 * Removes the timebased otp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry that was removed
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public TimebasedOTPEntry remove(long entryId)
		throws NoSuchTimebasedOTPEntryException {

		return remove((Serializable)entryId);
	}

	/**
	 * Removes the timebased otp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the timebased otp entry
	 * @return the timebased otp entry that was removed
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public TimebasedOTPEntry remove(Serializable primaryKey)
		throws NoSuchTimebasedOTPEntryException {

		Session session = null;

		try {
			session = openSession();

			TimebasedOTPEntry timebasedOTPEntry =
				(TimebasedOTPEntry)session.get(
					TimebasedOTPEntryImpl.class, primaryKey);

			if (timebasedOTPEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTimebasedOTPEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(timebasedOTPEntry);
		}
		catch (NoSuchTimebasedOTPEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TimebasedOTPEntry removeImpl(
		TimebasedOTPEntry timebasedOTPEntry) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(timebasedOTPEntry)) {
				timebasedOTPEntry = (TimebasedOTPEntry)session.get(
					TimebasedOTPEntryImpl.class,
					timebasedOTPEntry.getPrimaryKeyObj());
			}

			if (timebasedOTPEntry != null) {
				session.delete(timebasedOTPEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (timebasedOTPEntry != null) {
			clearCache(timebasedOTPEntry);
		}

		return timebasedOTPEntry;
	}

	@Override
	public TimebasedOTPEntry updateImpl(TimebasedOTPEntry timebasedOTPEntry) {
		boolean isNew = timebasedOTPEntry.isNew();

		if (!(timebasedOTPEntry instanceof TimebasedOTPEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(timebasedOTPEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					timebasedOTPEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in timebasedOTPEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TimebasedOTPEntry implementation " +
					timebasedOTPEntry.getClass());
		}

		TimebasedOTPEntryModelImpl timebasedOTPEntryModelImpl =
			(TimebasedOTPEntryModelImpl)timebasedOTPEntry;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (timebasedOTPEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				timebasedOTPEntry.setCreateDate(now);
			}
			else {
				timebasedOTPEntry.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!timebasedOTPEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				timebasedOTPEntry.setModifiedDate(now);
			}
			else {
				timebasedOTPEntry.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (timebasedOTPEntry.isNew()) {
				session.save(timebasedOTPEntry);

				timebasedOTPEntry.setNew(false);
			}
			else {
				timebasedOTPEntry = (TimebasedOTPEntry)session.merge(
					timebasedOTPEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				timebasedOTPEntryModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {
				timebasedOTPEntryModelImpl.getMfaCheckerName()
			};

			finderCache.removeResult(_finderPathCountByMFACheckerName, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByMFACheckerName, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((timebasedOTPEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					timebasedOTPEntryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {timebasedOTPEntryModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((timebasedOTPEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByMFACheckerName.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					timebasedOTPEntryModelImpl.getOriginalMfaCheckerName()
				};

				finderCache.removeResult(
					_finderPathCountByMFACheckerName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMFACheckerName, args);

				args = new Object[] {
					timebasedOTPEntryModelImpl.getMfaCheckerName()
				};

				finderCache.removeResult(
					_finderPathCountByMFACheckerName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMFACheckerName, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, TimebasedOTPEntryImpl.class,
			timebasedOTPEntry.getPrimaryKey(), timebasedOTPEntry, false);

		clearUniqueFindersCache(timebasedOTPEntryModelImpl, false);
		cacheUniqueFindersCache(timebasedOTPEntryModelImpl);

		timebasedOTPEntry.resetOriginalValues();

		return timebasedOTPEntry;
	}

	/**
	 * Returns the timebased otp entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the timebased otp entry
	 * @return the timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public TimebasedOTPEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTimebasedOTPEntryException {

		TimebasedOTPEntry timebasedOTPEntry = fetchByPrimaryKey(primaryKey);

		if (timebasedOTPEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTimebasedOTPEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return timebasedOTPEntry;
	}

	/**
	 * Returns the timebased otp entry with the primary key or throws a <code>NoSuchTimebasedOTPEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry
	 * @throws NoSuchTimebasedOTPEntryException if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public TimebasedOTPEntry findByPrimaryKey(long entryId)
		throws NoSuchTimebasedOTPEntryException {

		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the timebased otp entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the timebased otp entry
	 * @return the timebased otp entry, or <code>null</code> if a timebased otp entry with the primary key could not be found
	 */
	@Override
	public TimebasedOTPEntry fetchByPrimaryKey(long entryId) {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the timebased otp entries.
	 *
	 * @return the timebased otp entries
	 */
	@Override
	public List<TimebasedOTPEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<TimebasedOTPEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<TimebasedOTPEntry> findAll(
		int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<TimebasedOTPEntry> findAll(
		int start, int end,
		OrderByComparator<TimebasedOTPEntry> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<TimebasedOTPEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TimebasedOTPEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TIMEBASEDOTPENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TIMEBASEDOTPENTRY;

				if (pagination) {
					sql = sql.concat(TimebasedOTPEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TimebasedOTPEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimebasedOTPEntry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the timebased otp entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TimebasedOTPEntry timebasedOTPEntry : findAll()) {
			remove(timebasedOTPEntry);
		}
	}

	/**
	 * Returns the number of timebased otp entries.
	 *
	 * @return the number of timebased otp entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TIMEBASEDOTPENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "entryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TIMEBASEDOTPENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TimebasedOTPEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the timebased otp entry persistence.
	 */
	@Activate
	public void activate() {
		TimebasedOTPEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		TimebasedOTPEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TimebasedOTPEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TimebasedOTPEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TimebasedOTPEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TimebasedOTPEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			TimebasedOTPEntryModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TimebasedOTPEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMFACheckerName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TimebasedOTPEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMFACheckerName",
			new String[] {String.class.getName()},
			TimebasedOTPEntryModelImpl.MFACHECKERNAME_COLUMN_BITMASK);

		_finderPathCountByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMFACheckerName",
			new String[] {String.class.getName()});

		_finderPathFetchByU_M = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TimebasedOTPEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_M",
			new String[] {Long.class.getName(), String.class.getName()},
			TimebasedOTPEntryModelImpl.USERID_COLUMN_BITMASK |
			TimebasedOTPEntryModelImpl.MFACHECKERNAME_COLUMN_BITMASK);

		_finderPathCountByU_M = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_M",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(TimebasedOTPEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = MFATOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.multi.factor.authentication.checker.timebased.otp.model.TimebasedOTPEntry"),
			true);
	}

	@Override
	@Reference(
		target = MFATOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = MFATOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference(service = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TIMEBASEDOTPENTRY =
		"SELECT timebasedOTPEntry FROM TimebasedOTPEntry timebasedOTPEntry";

	private static final String _SQL_SELECT_TIMEBASEDOTPENTRY_WHERE =
		"SELECT timebasedOTPEntry FROM TimebasedOTPEntry timebasedOTPEntry WHERE ";

	private static final String _SQL_COUNT_TIMEBASEDOTPENTRY =
		"SELECT COUNT(timebasedOTPEntry) FROM TimebasedOTPEntry timebasedOTPEntry";

	private static final String _SQL_COUNT_TIMEBASEDOTPENTRY_WHERE =
		"SELECT COUNT(timebasedOTPEntry) FROM TimebasedOTPEntry timebasedOTPEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "timebasedOTPEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TimebasedOTPEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TimebasedOTPEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TimebasedOTPEntryPersistenceImpl.class);

}