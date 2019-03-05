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

package com.liferay.multi.factor.authentication.checker.totp.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.totp.exception.NoSuchMFATOTPException;
import com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP;
import com.liferay.multi.factor.authentication.checker.totp.model.impl.MFATOTPImpl;
import com.liferay.multi.factor.authentication.checker.totp.model.impl.MFATOTPModelImpl;
import com.liferay.multi.factor.authentication.checker.totp.service.persistence.MFATOTPPersistence;
import com.liferay.multi.factor.authentication.checker.totp.service.persistence.impl.constants.TOTPPersistenceConstants;
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
 * The persistence implementation for the mfatotp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @generated
 */
@Component(service = MFATOTPPersistence.class)
@ProviderType
public class MFATOTPPersistenceImpl
	extends BasePersistenceImpl<MFATOTP> implements MFATOTPPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MFATOTPUtil</code> to access the mfatotp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MFATOTPImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByM_U;
	private FinderPath _finderPathCountByM_U;

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or throws a <code>NoSuchMFATOTPException</code> if it could not be found.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP findByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = fetchByM_U(mfaCheckerName, userId);

		if (mfatotp == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("mfaCheckerName=");
			msg.append(mfaCheckerName);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchMFATOTPException(msg.toString());
		}

		return mfatotp;
	}

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP fetchByM_U(String mfaCheckerName, long userId) {
		return fetchByM_U(mfaCheckerName, userId, true);
	}

	/**
	 * Returns the mfatotp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP fetchByM_U(
		String mfaCheckerName, long userId, boolean retrieveFromCache) {

		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		Object[] finderArgs = new Object[] {mfaCheckerName, userId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByM_U, finderArgs, this);
		}

		if (result instanceof MFATOTP) {
			MFATOTP mfatotp = (MFATOTP)result;

			if (!Objects.equals(mfaCheckerName, mfatotp.getMfaCheckerName()) ||
				(userId != mfatotp.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MFATOTP_WHERE);

			boolean bindMfaCheckerName = false;

			if (mfaCheckerName.isEmpty()) {
				query.append(_FINDER_COLUMN_M_U_MFACHECKERNAME_3);
			}
			else {
				bindMfaCheckerName = true;

				query.append(_FINDER_COLUMN_M_U_MFACHECKERNAME_2);
			}

			query.append(_FINDER_COLUMN_M_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMfaCheckerName) {
					qPos.add(mfaCheckerName);
				}

				qPos.add(userId);

				List<MFATOTP> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByM_U, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"MFATOTPPersistenceImpl.fetchByM_U(String, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MFATOTP mfatotp = list.get(0);

					result = mfatotp;

					cacheResult(mfatotp);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByM_U, finderArgs);

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
			return (MFATOTP)result;
		}
	}

	/**
	 * Removes the mfatotp where mfaCheckerName = &#63; and userId = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the mfatotp that was removed
	 */
	@Override
	public MFATOTP removeByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = findByM_U(mfaCheckerName, userId);

		return remove(mfatotp);
	}

	/**
	 * Returns the number of mfatotps where mfaCheckerName = &#63; and userId = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the number of matching mfatotps
	 */
	@Override
	public int countByM_U(String mfaCheckerName, long userId) {
		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		FinderPath finderPath = _finderPathCountByM_U;

		Object[] finderArgs = new Object[] {mfaCheckerName, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MFATOTP_WHERE);

			boolean bindMfaCheckerName = false;

			if (mfaCheckerName.isEmpty()) {
				query.append(_FINDER_COLUMN_M_U_MFACHECKERNAME_3);
			}
			else {
				bindMfaCheckerName = true;

				query.append(_FINDER_COLUMN_M_U_MFACHECKERNAME_2);
			}

			query.append(_FINDER_COLUMN_M_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMfaCheckerName) {
					qPos.add(mfaCheckerName);
				}

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

	private static final String _FINDER_COLUMN_M_U_MFACHECKERNAME_2 =
		"mfatotp.mfaCheckerName = ? AND ";

	private static final String _FINDER_COLUMN_M_U_MFACHECKERNAME_3 =
		"(mfatotp.mfaCheckerName IS NULL OR mfatotp.mfaCheckerName = '') AND ";

	private static final String _FINDER_COLUMN_M_U_USERID_2 =
		"mfatotp.userId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the mfatotps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mfatotps
	 */
	@Override
	public List<MFATOTP> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MFATOTP> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
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
	@Override
	public List<MFATOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
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
	@Override
	public List<MFATOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator,
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

		List<MFATOTP> list = null;

		if (retrieveFromCache) {
			list = (List<MFATOTP>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MFATOTP mfatotp : list) {
					if ((userId != mfatotp.getUserId())) {
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

			query.append(_SQL_SELECT_MFATOTP_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(MFATOTPModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<MFATOTP>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MFATOTP>)QueryUtil.list(
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
	 * Returns the first mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP findByUserId_First(
			long userId, OrderByComparator<MFATOTP> orderByComparator)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = fetchByUserId_First(userId, orderByComparator);

		if (mfatotp != null) {
			return mfatotp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchMFATOTPException(msg.toString());
	}

	/**
	 * Returns the first mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP fetchByUserId_First(
		long userId, OrderByComparator<MFATOTP> orderByComparator) {

		List<MFATOTP> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP findByUserId_Last(
			long userId, OrderByComparator<MFATOTP> orderByComparator)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = fetchByUserId_Last(userId, orderByComparator);

		if (mfatotp != null) {
			return mfatotp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchMFATOTPException(msg.toString());
	}

	/**
	 * Returns the last mfatotp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP fetchByUserId_Last(
		long userId, OrderByComparator<MFATOTP> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<MFATOTP> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MFATOTP[] findByUserId_PrevAndNext(
			long mfaTOTPId, long userId,
			OrderByComparator<MFATOTP> orderByComparator)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = findByPrimaryKey(mfaTOTPId);

		Session session = null;

		try {
			session = openSession();

			MFATOTP[] array = new MFATOTPImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, mfatotp, userId, orderByComparator, true);

			array[1] = mfatotp;

			array[2] = getByUserId_PrevAndNext(
				session, mfatotp, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MFATOTP getByUserId_PrevAndNext(
		Session session, MFATOTP mfatotp, long userId,
		OrderByComparator<MFATOTP> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MFATOTP_WHERE);

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
			query.append(MFATOTPModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(mfatotp)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<MFATOTP> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mfatotps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (MFATOTP mfatotp :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(mfatotp);
		}
	}

	/**
	 * Returns the number of mfatotps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mfatotps
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MFATOTP_WHERE);

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
		"mfatotp.userId = ?";

	private FinderPath _finderPathWithPaginationFindByMFACheckerName;
	private FinderPath _finderPathWithoutPaginationFindByMFACheckerName;
	private FinderPath _finderPathCountByMFACheckerName;

	/**
	 * Returns all the mfatotps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching mfatotps
	 */
	@Override
	public List<MFATOTP> findByMFACheckerName(String mfaCheckerName) {
		return findByMFACheckerName(
			mfaCheckerName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end) {

		return findByMFACheckerName(mfaCheckerName, start, end, null);
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
	@Override
	public List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator) {

		return findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator, true);
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
	@Override
	public List<MFATOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFATOTP> orderByComparator,
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

		List<MFATOTP> list = null;

		if (retrieveFromCache) {
			list = (List<MFATOTP>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MFATOTP mfatotp : list) {
					if (!mfaCheckerName.equals(mfatotp.getMfaCheckerName())) {
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

			query.append(_SQL_SELECT_MFATOTP_WHERE);

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
				query.append(MFATOTPModelImpl.ORDER_BY_JPQL);
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
					list = (List<MFATOTP>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MFATOTP>)QueryUtil.list(
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
	 * Returns the first mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP findByMFACheckerName_First(
			String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = fetchByMFACheckerName_First(
			mfaCheckerName, orderByComparator);

		if (mfatotp != null) {
			return mfatotp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mfaCheckerName=");
		msg.append(mfaCheckerName);

		msg.append("}");

		throw new NoSuchMFATOTPException(msg.toString());
	}

	/**
	 * Returns the first mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP fetchByMFACheckerName_First(
		String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator) {

		List<MFATOTP> list = findByMFACheckerName(
			mfaCheckerName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp
	 * @throws NoSuchMFATOTPException if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP findByMFACheckerName_Last(
			String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = fetchByMFACheckerName_Last(
			mfaCheckerName, orderByComparator);

		if (mfatotp != null) {
			return mfatotp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mfaCheckerName=");
		msg.append(mfaCheckerName);

		msg.append("}");

		throw new NoSuchMFATOTPException(msg.toString());
	}

	/**
	 * Returns the last mfatotp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfatotp, or <code>null</code> if a matching mfatotp could not be found
	 */
	@Override
	public MFATOTP fetchByMFACheckerName_Last(
		String mfaCheckerName, OrderByComparator<MFATOTP> orderByComparator) {

		int count = countByMFACheckerName(mfaCheckerName);

		if (count == 0) {
			return null;
		}

		List<MFATOTP> list = findByMFACheckerName(
			mfaCheckerName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MFATOTP[] findByMFACheckerName_PrevAndNext(
			long mfaTOTPId, String mfaCheckerName,
			OrderByComparator<MFATOTP> orderByComparator)
		throws NoSuchMFATOTPException {

		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		MFATOTP mfatotp = findByPrimaryKey(mfaTOTPId);

		Session session = null;

		try {
			session = openSession();

			MFATOTP[] array = new MFATOTPImpl[3];

			array[0] = getByMFACheckerName_PrevAndNext(
				session, mfatotp, mfaCheckerName, orderByComparator, true);

			array[1] = mfatotp;

			array[2] = getByMFACheckerName_PrevAndNext(
				session, mfatotp, mfaCheckerName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MFATOTP getByMFACheckerName_PrevAndNext(
		Session session, MFATOTP mfatotp, String mfaCheckerName,
		OrderByComparator<MFATOTP> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MFATOTP_WHERE);

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
			query.append(MFATOTPModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(mfatotp)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<MFATOTP> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mfatotps where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	@Override
	public void removeByMFACheckerName(String mfaCheckerName) {
		for (MFATOTP mfatotp :
				findByMFACheckerName(
					mfaCheckerName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(mfatotp);
		}
	}

	/**
	 * Returns the number of mfatotps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching mfatotps
	 */
	@Override
	public int countByMFACheckerName(String mfaCheckerName) {
		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		FinderPath finderPath = _finderPathCountByMFACheckerName;

		Object[] finderArgs = new Object[] {mfaCheckerName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MFATOTP_WHERE);

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
		"mfatotp.mfaCheckerName = ?";

	private static final String _FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_3 =
		"(mfatotp.mfaCheckerName IS NULL OR mfatotp.mfaCheckerName = '')";

	public MFATOTPPersistenceImpl() {
		setModelClass(MFATOTP.class);

		setModelImplClass(MFATOTPImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the mfatotp in the entity cache if it is enabled.
	 *
	 * @param mfatotp the mfatotp
	 */
	@Override
	public void cacheResult(MFATOTP mfatotp) {
		entityCache.putResult(
			entityCacheEnabled, MFATOTPImpl.class, mfatotp.getPrimaryKey(),
			mfatotp);

		finderCache.putResult(
			_finderPathFetchByM_U,
			new Object[] {mfatotp.getMfaCheckerName(), mfatotp.getUserId()},
			mfatotp);

		mfatotp.resetOriginalValues();
	}

	/**
	 * Caches the mfatotps in the entity cache if it is enabled.
	 *
	 * @param mfatotps the mfatotps
	 */
	@Override
	public void cacheResult(List<MFATOTP> mfatotps) {
		for (MFATOTP mfatotp : mfatotps) {
			if (entityCache.getResult(
					entityCacheEnabled, MFATOTPImpl.class,
					mfatotp.getPrimaryKey()) == null) {

				cacheResult(mfatotp);
			}
			else {
				mfatotp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all mfatotps.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MFATOTPImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the mfatotp.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MFATOTP mfatotp) {
		entityCache.removeResult(
			entityCacheEnabled, MFATOTPImpl.class, mfatotp.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MFATOTPModelImpl)mfatotp, true);
	}

	@Override
	public void clearCache(List<MFATOTP> mfatotps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MFATOTP mfatotp : mfatotps) {
			entityCache.removeResult(
				entityCacheEnabled, MFATOTPImpl.class, mfatotp.getPrimaryKey());

			clearUniqueFindersCache((MFATOTPModelImpl)mfatotp, true);
		}
	}

	protected void cacheUniqueFindersCache(MFATOTPModelImpl mfatotpModelImpl) {
		Object[] args = new Object[] {
			mfatotpModelImpl.getMfaCheckerName(), mfatotpModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountByM_U, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByM_U, args, mfatotpModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MFATOTPModelImpl mfatotpModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				mfatotpModelImpl.getMfaCheckerName(),
				mfatotpModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByM_U, args);
			finderCache.removeResult(_finderPathFetchByM_U, args);
		}

		if ((mfatotpModelImpl.getColumnBitmask() &
			 _finderPathFetchByM_U.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				mfatotpModelImpl.getOriginalMfaCheckerName(),
				mfatotpModelImpl.getOriginalUserId()
			};

			finderCache.removeResult(_finderPathCountByM_U, args);
			finderCache.removeResult(_finderPathFetchByM_U, args);
		}
	}

	/**
	 * Creates a new mfatotp with the primary key. Does not add the mfatotp to the database.
	 *
	 * @param mfaTOTPId the primary key for the new mfatotp
	 * @return the new mfatotp
	 */
	@Override
	public MFATOTP create(long mfaTOTPId) {
		MFATOTP mfatotp = new MFATOTPImpl();

		mfatotp.setNew(true);
		mfatotp.setPrimaryKey(mfaTOTPId);

		mfatotp.setCompanyId(companyProvider.getCompanyId());

		return mfatotp;
	}

	/**
	 * Removes the mfatotp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp that was removed
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	@Override
	public MFATOTP remove(long mfaTOTPId) throws NoSuchMFATOTPException {
		return remove((Serializable)mfaTOTPId);
	}

	/**
	 * Removes the mfatotp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mfatotp
	 * @return the mfatotp that was removed
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	@Override
	public MFATOTP remove(Serializable primaryKey)
		throws NoSuchMFATOTPException {

		Session session = null;

		try {
			session = openSession();

			MFATOTP mfatotp = (MFATOTP)session.get(
				MFATOTPImpl.class, primaryKey);

			if (mfatotp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMFATOTPException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(mfatotp);
		}
		catch (NoSuchMFATOTPException nsee) {
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
	protected MFATOTP removeImpl(MFATOTP mfatotp) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mfatotp)) {
				mfatotp = (MFATOTP)session.get(
					MFATOTPImpl.class, mfatotp.getPrimaryKeyObj());
			}

			if (mfatotp != null) {
				session.delete(mfatotp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mfatotp != null) {
			clearCache(mfatotp);
		}

		return mfatotp;
	}

	@Override
	public MFATOTP updateImpl(MFATOTP mfatotp) {
		boolean isNew = mfatotp.isNew();

		if (!(mfatotp instanceof MFATOTPModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(mfatotp.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(mfatotp);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in mfatotp proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MFATOTP implementation " +
					mfatotp.getClass());
		}

		MFATOTPModelImpl mfatotpModelImpl = (MFATOTPModelImpl)mfatotp;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (mfatotp.getCreateDate() == null)) {
			if (serviceContext == null) {
				mfatotp.setCreateDate(now);
			}
			else {
				mfatotp.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!mfatotpModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				mfatotp.setModifiedDate(now);
			}
			else {
				mfatotp.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (mfatotp.isNew()) {
				session.save(mfatotp);

				mfatotp.setNew(false);
			}
			else {
				mfatotp = (MFATOTP)session.merge(mfatotp);
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
			Object[] args = new Object[] {mfatotpModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {mfatotpModelImpl.getMfaCheckerName()};

			finderCache.removeResult(_finderPathCountByMFACheckerName, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByMFACheckerName, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((mfatotpModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					mfatotpModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {mfatotpModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((mfatotpModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByMFACheckerName.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					mfatotpModelImpl.getOriginalMfaCheckerName()
				};

				finderCache.removeResult(
					_finderPathCountByMFACheckerName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMFACheckerName, args);

				args = new Object[] {mfatotpModelImpl.getMfaCheckerName()};

				finderCache.removeResult(
					_finderPathCountByMFACheckerName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMFACheckerName, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, MFATOTPImpl.class, mfatotp.getPrimaryKey(),
			mfatotp, false);

		clearUniqueFindersCache(mfatotpModelImpl, false);
		cacheUniqueFindersCache(mfatotpModelImpl);

		mfatotp.resetOriginalValues();

		return mfatotp;
	}

	/**
	 * Returns the mfatotp with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mfatotp
	 * @return the mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	@Override
	public MFATOTP findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMFATOTPException {

		MFATOTP mfatotp = fetchByPrimaryKey(primaryKey);

		if (mfatotp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMFATOTPException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return mfatotp;
	}

	/**
	 * Returns the mfatotp with the primary key or throws a <code>NoSuchMFATOTPException</code> if it could not be found.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp
	 * @throws NoSuchMFATOTPException if a mfatotp with the primary key could not be found
	 */
	@Override
	public MFATOTP findByPrimaryKey(long mfaTOTPId)
		throws NoSuchMFATOTPException {

		return findByPrimaryKey((Serializable)mfaTOTPId);
	}

	/**
	 * Returns the mfatotp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mfaTOTPId the primary key of the mfatotp
	 * @return the mfatotp, or <code>null</code> if a mfatotp with the primary key could not be found
	 */
	@Override
	public MFATOTP fetchByPrimaryKey(long mfaTOTPId) {
		return fetchByPrimaryKey((Serializable)mfaTOTPId);
	}

	/**
	 * Returns all the mfatotps.
	 *
	 * @return the mfatotps
	 */
	@Override
	public List<MFATOTP> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MFATOTP> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MFATOTP> findAll(
		int start, int end, OrderByComparator<MFATOTP> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MFATOTP> findAll(
		int start, int end, OrderByComparator<MFATOTP> orderByComparator,
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

		List<MFATOTP> list = null;

		if (retrieveFromCache) {
			list = (List<MFATOTP>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MFATOTP);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MFATOTP;

				if (pagination) {
					sql = sql.concat(MFATOTPModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MFATOTP>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MFATOTP>)QueryUtil.list(
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
	 * Removes all the mfatotps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MFATOTP mfatotp : findAll()) {
			remove(mfatotp);
		}
	}

	/**
	 * Returns the number of mfatotps.
	 *
	 * @return the number of mfatotps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MFATOTP);

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
		return "mfaTOTPId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MFATOTP;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MFATOTPModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the mfatotp persistence.
	 */
	@Activate
	public void activate() {
		MFATOTPModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		MFATOTPModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFATOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFATOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByM_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFATOTPImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByM_U",
			new String[] {String.class.getName(), Long.class.getName()},
			MFATOTPModelImpl.MFACHECKERNAME_COLUMN_BITMASK |
			MFATOTPModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByM_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_U",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFATOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFATOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			MFATOTPModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFATOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMFACheckerName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFATOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMFACheckerName",
			new String[] {String.class.getName()},
			MFATOTPModelImpl.MFACHECKERNAME_COLUMN_BITMASK);

		_finderPathCountByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMFACheckerName",
			new String[] {String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(MFATOTPImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = TOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.multi.factor.authentication.checker.totp.model.MFATOTP"),
			true);
	}

	@Override
	@Reference(
		target = TOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = TOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_MFATOTP =
		"SELECT mfatotp FROM MFATOTP mfatotp";

	private static final String _SQL_SELECT_MFATOTP_WHERE =
		"SELECT mfatotp FROM MFATOTP mfatotp WHERE ";

	private static final String _SQL_COUNT_MFATOTP =
		"SELECT COUNT(mfatotp) FROM MFATOTP mfatotp";

	private static final String _SQL_COUNT_MFATOTP_WHERE =
		"SELECT COUNT(mfatotp) FROM MFATOTP mfatotp WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "mfatotp.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MFATOTP exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MFATOTP exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MFATOTPPersistenceImpl.class);

}