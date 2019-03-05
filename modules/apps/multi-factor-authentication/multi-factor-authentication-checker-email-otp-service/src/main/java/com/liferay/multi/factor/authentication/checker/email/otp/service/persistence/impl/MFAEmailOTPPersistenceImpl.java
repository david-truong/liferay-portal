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

package com.liferay.multi.factor.authentication.checker.email.otp.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.checker.email.otp.exception.NoSuchMFAEmailOTPException;
import com.liferay.multi.factor.authentication.checker.email.otp.model.MFAEmailOTP;
import com.liferay.multi.factor.authentication.checker.email.otp.model.impl.MFAEmailOTPImpl;
import com.liferay.multi.factor.authentication.checker.email.otp.model.impl.MFAEmailOTPModelImpl;
import com.liferay.multi.factor.authentication.checker.email.otp.service.persistence.MFAEmailOTPPersistence;
import com.liferay.multi.factor.authentication.checker.email.otp.service.persistence.impl.constants.EmailOTPPersistenceConstants;
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
 * The persistence implementation for the mfa email otp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author arthurchan35
 * @generated
 */
@Component(service = MFAEmailOTPPersistence.class)
@ProviderType
public class MFAEmailOTPPersistenceImpl
	extends BasePersistenceImpl<MFAEmailOTP> implements MFAEmailOTPPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MFAEmailOTPUtil</code> to access the mfa email otp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MFAEmailOTPImpl.class.getName();

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
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or throws a <code>NoSuchMFAEmailOTPException</code> if it could not be found.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP findByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = fetchByM_U(mfaCheckerName, userId);

		if (mfaEmailOTP == null) {
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

			throw new NoSuchMFAEmailOTPException(msg.toString());
		}

		return mfaEmailOTP;
	}

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP fetchByM_U(String mfaCheckerName, long userId) {
		return fetchByM_U(mfaCheckerName, userId, true);
	}

	/**
	 * Returns the mfa email otp where mfaCheckerName = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP fetchByM_U(
		String mfaCheckerName, long userId, boolean retrieveFromCache) {

		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		Object[] finderArgs = new Object[] {mfaCheckerName, userId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByM_U, finderArgs, this);
		}

		if (result instanceof MFAEmailOTP) {
			MFAEmailOTP mfaEmailOTP = (MFAEmailOTP)result;

			if (!Objects.equals(
					mfaCheckerName, mfaEmailOTP.getMfaCheckerName()) ||
				(userId != mfaEmailOTP.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MFAEMAILOTP_WHERE);

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

				List<MFAEmailOTP> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByM_U, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"MFAEmailOTPPersistenceImpl.fetchByM_U(String, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MFAEmailOTP mfaEmailOTP = list.get(0);

					result = mfaEmailOTP;

					cacheResult(mfaEmailOTP);
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
			return (MFAEmailOTP)result;
		}
	}

	/**
	 * Removes the mfa email otp where mfaCheckerName = &#63; and userId = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the mfa email otp that was removed
	 */
	@Override
	public MFAEmailOTP removeByM_U(String mfaCheckerName, long userId)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = findByM_U(mfaCheckerName, userId);

		return remove(mfaEmailOTP);
	}

	/**
	 * Returns the number of mfa email otps where mfaCheckerName = &#63; and userId = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param userId the user ID
	 * @return the number of matching mfa email otps
	 */
	@Override
	public int countByM_U(String mfaCheckerName, long userId) {
		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		FinderPath finderPath = _finderPathCountByM_U;

		Object[] finderArgs = new Object[] {mfaCheckerName, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MFAEMAILOTP_WHERE);

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
		"mfaEmailOTP.mfaCheckerName = ? AND ";

	private static final String _FINDER_COLUMN_M_U_MFACHECKERNAME_3 =
		"(mfaEmailOTP.mfaCheckerName IS NULL OR mfaEmailOTP.mfaCheckerName = '') AND ";

	private static final String _FINDER_COLUMN_M_U_USERID_2 =
		"mfaEmailOTP.userId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the mfa email otps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mfa email otps
	 */
	@Override
	public List<MFAEmailOTP> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MFAEmailOTP> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
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
	@Override
	public List<MFAEmailOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
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
	@Override
	public List<MFAEmailOTP> findByUserId(
		long userId, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator,
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

		List<MFAEmailOTP> list = null;

		if (retrieveFromCache) {
			list = (List<MFAEmailOTP>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MFAEmailOTP mfaEmailOTP : list) {
					if ((userId != mfaEmailOTP.getUserId())) {
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

			query.append(_SQL_SELECT_MFAEMAILOTP_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(MFAEmailOTPModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<MFAEmailOTP>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MFAEmailOTP>)QueryUtil.list(
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
	 * Returns the first mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP findByUserId_First(
			long userId, OrderByComparator<MFAEmailOTP> orderByComparator)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = fetchByUserId_First(
			userId, orderByComparator);

		if (mfaEmailOTP != null) {
			return mfaEmailOTP;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchMFAEmailOTPException(msg.toString());
	}

	/**
	 * Returns the first mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP fetchByUserId_First(
		long userId, OrderByComparator<MFAEmailOTP> orderByComparator) {

		List<MFAEmailOTP> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP findByUserId_Last(
			long userId, OrderByComparator<MFAEmailOTP> orderByComparator)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = fetchByUserId_Last(userId, orderByComparator);

		if (mfaEmailOTP != null) {
			return mfaEmailOTP;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchMFAEmailOTPException(msg.toString());
	}

	/**
	 * Returns the last mfa email otp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP fetchByUserId_Last(
		long userId, OrderByComparator<MFAEmailOTP> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<MFAEmailOTP> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MFAEmailOTP[] findByUserId_PrevAndNext(
			long mfaEmailOTPId, long userId,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = findByPrimaryKey(mfaEmailOTPId);

		Session session = null;

		try {
			session = openSession();

			MFAEmailOTP[] array = new MFAEmailOTPImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, mfaEmailOTP, userId, orderByComparator, true);

			array[1] = mfaEmailOTP;

			array[2] = getByUserId_PrevAndNext(
				session, mfaEmailOTP, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MFAEmailOTP getByUserId_PrevAndNext(
		Session session, MFAEmailOTP mfaEmailOTP, long userId,
		OrderByComparator<MFAEmailOTP> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MFAEMAILOTP_WHERE);

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
			query.append(MFAEmailOTPModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(mfaEmailOTP)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<MFAEmailOTP> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mfa email otps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (MFAEmailOTP mfaEmailOTP :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(mfaEmailOTP);
		}
	}

	/**
	 * Returns the number of mfa email otps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mfa email otps
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MFAEMAILOTP_WHERE);

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
		"mfaEmailOTP.userId = ?";

	private FinderPath _finderPathWithPaginationFindByMFACheckerName;
	private FinderPath _finderPathWithoutPaginationFindByMFACheckerName;
	private FinderPath _finderPathCountByMFACheckerName;

	/**
	 * Returns all the mfa email otps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the matching mfa email otps
	 */
	@Override
	public List<MFAEmailOTP> findByMFACheckerName(String mfaCheckerName) {
		return findByMFACheckerName(
			mfaCheckerName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end) {

		return findByMFACheckerName(mfaCheckerName, start, end, null);
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
	@Override
	public List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		return findByMFACheckerName(
			mfaCheckerName, start, end, orderByComparator, true);
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
	@Override
	public List<MFAEmailOTP> findByMFACheckerName(
		String mfaCheckerName, int start, int end,
		OrderByComparator<MFAEmailOTP> orderByComparator,
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

		List<MFAEmailOTP> list = null;

		if (retrieveFromCache) {
			list = (List<MFAEmailOTP>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MFAEmailOTP mfaEmailOTP : list) {
					if (!mfaCheckerName.equals(
							mfaEmailOTP.getMfaCheckerName())) {

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

			query.append(_SQL_SELECT_MFAEMAILOTP_WHERE);

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
				query.append(MFAEmailOTPModelImpl.ORDER_BY_JPQL);
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
					list = (List<MFAEmailOTP>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MFAEmailOTP>)QueryUtil.list(
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
	 * Returns the first mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP findByMFACheckerName_First(
			String mfaCheckerName,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = fetchByMFACheckerName_First(
			mfaCheckerName, orderByComparator);

		if (mfaEmailOTP != null) {
			return mfaEmailOTP;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mfaCheckerName=");
		msg.append(mfaCheckerName);

		msg.append("}");

		throw new NoSuchMFAEmailOTPException(msg.toString());
	}

	/**
	 * Returns the first mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP fetchByMFACheckerName_First(
		String mfaCheckerName,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		List<MFAEmailOTP> list = findByMFACheckerName(
			mfaCheckerName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP findByMFACheckerName_Last(
			String mfaCheckerName,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = fetchByMFACheckerName_Last(
			mfaCheckerName, orderByComparator);

		if (mfaEmailOTP != null) {
			return mfaEmailOTP;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mfaCheckerName=");
		msg.append(mfaCheckerName);

		msg.append("}");

		throw new NoSuchMFAEmailOTPException(msg.toString());
	}

	/**
	 * Returns the last mfa email otp in the ordered set where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mfa email otp, or <code>null</code> if a matching mfa email otp could not be found
	 */
	@Override
	public MFAEmailOTP fetchByMFACheckerName_Last(
		String mfaCheckerName,
		OrderByComparator<MFAEmailOTP> orderByComparator) {

		int count = countByMFACheckerName(mfaCheckerName);

		if (count == 0) {
			return null;
		}

		List<MFAEmailOTP> list = findByMFACheckerName(
			mfaCheckerName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MFAEmailOTP[] findByMFACheckerName_PrevAndNext(
			long mfaEmailOTPId, String mfaCheckerName,
			OrderByComparator<MFAEmailOTP> orderByComparator)
		throws NoSuchMFAEmailOTPException {

		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		MFAEmailOTP mfaEmailOTP = findByPrimaryKey(mfaEmailOTPId);

		Session session = null;

		try {
			session = openSession();

			MFAEmailOTP[] array = new MFAEmailOTPImpl[3];

			array[0] = getByMFACheckerName_PrevAndNext(
				session, mfaEmailOTP, mfaCheckerName, orderByComparator, true);

			array[1] = mfaEmailOTP;

			array[2] = getByMFACheckerName_PrevAndNext(
				session, mfaEmailOTP, mfaCheckerName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MFAEmailOTP getByMFACheckerName_PrevAndNext(
		Session session, MFAEmailOTP mfaEmailOTP, String mfaCheckerName,
		OrderByComparator<MFAEmailOTP> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MFAEMAILOTP_WHERE);

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
			query.append(MFAEmailOTPModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(mfaEmailOTP)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<MFAEmailOTP> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mfa email otps where mfaCheckerName = &#63; from the database.
	 *
	 * @param mfaCheckerName the mfa checker name
	 */
	@Override
	public void removeByMFACheckerName(String mfaCheckerName) {
		for (MFAEmailOTP mfaEmailOTP :
				findByMFACheckerName(
					mfaCheckerName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(mfaEmailOTP);
		}
	}

	/**
	 * Returns the number of mfa email otps where mfaCheckerName = &#63;.
	 *
	 * @param mfaCheckerName the mfa checker name
	 * @return the number of matching mfa email otps
	 */
	@Override
	public int countByMFACheckerName(String mfaCheckerName) {
		mfaCheckerName = Objects.toString(mfaCheckerName, "");

		FinderPath finderPath = _finderPathCountByMFACheckerName;

		Object[] finderArgs = new Object[] {mfaCheckerName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MFAEMAILOTP_WHERE);

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
		"mfaEmailOTP.mfaCheckerName = ?";

	private static final String _FINDER_COLUMN_MFACHECKERNAME_MFACHECKERNAME_3 =
		"(mfaEmailOTP.mfaCheckerName IS NULL OR mfaEmailOTP.mfaCheckerName = '')";

	public MFAEmailOTPPersistenceImpl() {
		setModelClass(MFAEmailOTP.class);

		setModelImplClass(MFAEmailOTPImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the mfa email otp in the entity cache if it is enabled.
	 *
	 * @param mfaEmailOTP the mfa email otp
	 */
	@Override
	public void cacheResult(MFAEmailOTP mfaEmailOTP) {
		entityCache.putResult(
			entityCacheEnabled, MFAEmailOTPImpl.class,
			mfaEmailOTP.getPrimaryKey(), mfaEmailOTP);

		finderCache.putResult(
			_finderPathFetchByM_U,
			new Object[] {
				mfaEmailOTP.getMfaCheckerName(), mfaEmailOTP.getUserId()
			},
			mfaEmailOTP);

		mfaEmailOTP.resetOriginalValues();
	}

	/**
	 * Caches the mfa email otps in the entity cache if it is enabled.
	 *
	 * @param mfaEmailOTPs the mfa email otps
	 */
	@Override
	public void cacheResult(List<MFAEmailOTP> mfaEmailOTPs) {
		for (MFAEmailOTP mfaEmailOTP : mfaEmailOTPs) {
			if (entityCache.getResult(
					entityCacheEnabled, MFAEmailOTPImpl.class,
					mfaEmailOTP.getPrimaryKey()) == null) {

				cacheResult(mfaEmailOTP);
			}
			else {
				mfaEmailOTP.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all mfa email otps.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MFAEmailOTPImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the mfa email otp.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MFAEmailOTP mfaEmailOTP) {
		entityCache.removeResult(
			entityCacheEnabled, MFAEmailOTPImpl.class,
			mfaEmailOTP.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MFAEmailOTPModelImpl)mfaEmailOTP, true);
	}

	@Override
	public void clearCache(List<MFAEmailOTP> mfaEmailOTPs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MFAEmailOTP mfaEmailOTP : mfaEmailOTPs) {
			entityCache.removeResult(
				entityCacheEnabled, MFAEmailOTPImpl.class,
				mfaEmailOTP.getPrimaryKey());

			clearUniqueFindersCache((MFAEmailOTPModelImpl)mfaEmailOTP, true);
		}
	}

	protected void cacheUniqueFindersCache(
		MFAEmailOTPModelImpl mfaEmailOTPModelImpl) {

		Object[] args = new Object[] {
			mfaEmailOTPModelImpl.getMfaCheckerName(),
			mfaEmailOTPModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountByM_U, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByM_U, args, mfaEmailOTPModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MFAEmailOTPModelImpl mfaEmailOTPModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				mfaEmailOTPModelImpl.getMfaCheckerName(),
				mfaEmailOTPModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByM_U, args);
			finderCache.removeResult(_finderPathFetchByM_U, args);
		}

		if ((mfaEmailOTPModelImpl.getColumnBitmask() &
			 _finderPathFetchByM_U.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				mfaEmailOTPModelImpl.getOriginalMfaCheckerName(),
				mfaEmailOTPModelImpl.getOriginalUserId()
			};

			finderCache.removeResult(_finderPathCountByM_U, args);
			finderCache.removeResult(_finderPathFetchByM_U, args);
		}
	}

	/**
	 * Creates a new mfa email otp with the primary key. Does not add the mfa email otp to the database.
	 *
	 * @param mfaEmailOTPId the primary key for the new mfa email otp
	 * @return the new mfa email otp
	 */
	@Override
	public MFAEmailOTP create(long mfaEmailOTPId) {
		MFAEmailOTP mfaEmailOTP = new MFAEmailOTPImpl();

		mfaEmailOTP.setNew(true);
		mfaEmailOTP.setPrimaryKey(mfaEmailOTPId);

		mfaEmailOTP.setCompanyId(companyProvider.getCompanyId());

		return mfaEmailOTP;
	}

	/**
	 * Removes the mfa email otp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp that was removed
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	@Override
	public MFAEmailOTP remove(long mfaEmailOTPId)
		throws NoSuchMFAEmailOTPException {

		return remove((Serializable)mfaEmailOTPId);
	}

	/**
	 * Removes the mfa email otp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mfa email otp
	 * @return the mfa email otp that was removed
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	@Override
	public MFAEmailOTP remove(Serializable primaryKey)
		throws NoSuchMFAEmailOTPException {

		Session session = null;

		try {
			session = openSession();

			MFAEmailOTP mfaEmailOTP = (MFAEmailOTP)session.get(
				MFAEmailOTPImpl.class, primaryKey);

			if (mfaEmailOTP == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMFAEmailOTPException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(mfaEmailOTP);
		}
		catch (NoSuchMFAEmailOTPException nsee) {
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
	protected MFAEmailOTP removeImpl(MFAEmailOTP mfaEmailOTP) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mfaEmailOTP)) {
				mfaEmailOTP = (MFAEmailOTP)session.get(
					MFAEmailOTPImpl.class, mfaEmailOTP.getPrimaryKeyObj());
			}

			if (mfaEmailOTP != null) {
				session.delete(mfaEmailOTP);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mfaEmailOTP != null) {
			clearCache(mfaEmailOTP);
		}

		return mfaEmailOTP;
	}

	@Override
	public MFAEmailOTP updateImpl(MFAEmailOTP mfaEmailOTP) {
		boolean isNew = mfaEmailOTP.isNew();

		if (!(mfaEmailOTP instanceof MFAEmailOTPModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(mfaEmailOTP.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(mfaEmailOTP);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in mfaEmailOTP proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MFAEmailOTP implementation " +
					mfaEmailOTP.getClass());
		}

		MFAEmailOTPModelImpl mfaEmailOTPModelImpl =
			(MFAEmailOTPModelImpl)mfaEmailOTP;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (mfaEmailOTP.getCreateDate() == null)) {
			if (serviceContext == null) {
				mfaEmailOTP.setCreateDate(now);
			}
			else {
				mfaEmailOTP.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!mfaEmailOTPModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				mfaEmailOTP.setModifiedDate(now);
			}
			else {
				mfaEmailOTP.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (mfaEmailOTP.isNew()) {
				session.save(mfaEmailOTP);

				mfaEmailOTP.setNew(false);
			}
			else {
				mfaEmailOTP = (MFAEmailOTP)session.merge(mfaEmailOTP);
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
			Object[] args = new Object[] {mfaEmailOTPModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {mfaEmailOTPModelImpl.getMfaCheckerName()};

			finderCache.removeResult(_finderPathCountByMFACheckerName, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByMFACheckerName, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((mfaEmailOTPModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					mfaEmailOTPModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {mfaEmailOTPModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((mfaEmailOTPModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByMFACheckerName.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					mfaEmailOTPModelImpl.getOriginalMfaCheckerName()
				};

				finderCache.removeResult(
					_finderPathCountByMFACheckerName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMFACheckerName, args);

				args = new Object[] {mfaEmailOTPModelImpl.getMfaCheckerName()};

				finderCache.removeResult(
					_finderPathCountByMFACheckerName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMFACheckerName, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, MFAEmailOTPImpl.class,
			mfaEmailOTP.getPrimaryKey(), mfaEmailOTP, false);

		clearUniqueFindersCache(mfaEmailOTPModelImpl, false);
		cacheUniqueFindersCache(mfaEmailOTPModelImpl);

		mfaEmailOTP.resetOriginalValues();

		return mfaEmailOTP;
	}

	/**
	 * Returns the mfa email otp with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mfa email otp
	 * @return the mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	@Override
	public MFAEmailOTP findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMFAEmailOTPException {

		MFAEmailOTP mfaEmailOTP = fetchByPrimaryKey(primaryKey);

		if (mfaEmailOTP == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMFAEmailOTPException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return mfaEmailOTP;
	}

	/**
	 * Returns the mfa email otp with the primary key or throws a <code>NoSuchMFAEmailOTPException</code> if it could not be found.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp
	 * @throws NoSuchMFAEmailOTPException if a mfa email otp with the primary key could not be found
	 */
	@Override
	public MFAEmailOTP findByPrimaryKey(long mfaEmailOTPId)
		throws NoSuchMFAEmailOTPException {

		return findByPrimaryKey((Serializable)mfaEmailOTPId);
	}

	/**
	 * Returns the mfa email otp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mfaEmailOTPId the primary key of the mfa email otp
	 * @return the mfa email otp, or <code>null</code> if a mfa email otp with the primary key could not be found
	 */
	@Override
	public MFAEmailOTP fetchByPrimaryKey(long mfaEmailOTPId) {
		return fetchByPrimaryKey((Serializable)mfaEmailOTPId);
	}

	/**
	 * Returns all the mfa email otps.
	 *
	 * @return the mfa email otps
	 */
	@Override
	public List<MFAEmailOTP> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MFAEmailOTP> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MFAEmailOTP> findAll(
		int start, int end, OrderByComparator<MFAEmailOTP> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MFAEmailOTP> findAll(
		int start, int end, OrderByComparator<MFAEmailOTP> orderByComparator,
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

		List<MFAEmailOTP> list = null;

		if (retrieveFromCache) {
			list = (List<MFAEmailOTP>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MFAEMAILOTP);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MFAEMAILOTP;

				if (pagination) {
					sql = sql.concat(MFAEmailOTPModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MFAEmailOTP>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MFAEmailOTP>)QueryUtil.list(
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
	 * Removes all the mfa email otps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MFAEmailOTP mfaEmailOTP : findAll()) {
			remove(mfaEmailOTP);
		}
	}

	/**
	 * Returns the number of mfa email otps.
	 *
	 * @return the number of mfa email otps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MFAEMAILOTP);

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
		return "mfaEmailOTPId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MFAEMAILOTP;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MFAEmailOTPModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the mfa email otp persistence.
	 */
	@Activate
	public void activate() {
		MFAEmailOTPModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		MFAEmailOTPModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFAEmailOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFAEmailOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByM_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFAEmailOTPImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByM_U",
			new String[] {String.class.getName(), Long.class.getName()},
			MFAEmailOTPModelImpl.MFACHECKERNAME_COLUMN_BITMASK |
			MFAEmailOTPModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByM_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_U",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFAEmailOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFAEmailOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			MFAEmailOTPModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFAEmailOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMFACheckerName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, MFAEmailOTPImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMFACheckerName",
			new String[] {String.class.getName()},
			MFAEmailOTPModelImpl.MFACHECKERNAME_COLUMN_BITMASK);

		_finderPathCountByMFACheckerName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMFACheckerName",
			new String[] {String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(MFAEmailOTPImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = EmailOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.multi.factor.authentication.checker.email.otp.model.MFAEmailOTP"),
			true);
	}

	@Override
	@Reference(
		target = EmailOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = EmailOTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_MFAEMAILOTP =
		"SELECT mfaEmailOTP FROM MFAEmailOTP mfaEmailOTP";

	private static final String _SQL_SELECT_MFAEMAILOTP_WHERE =
		"SELECT mfaEmailOTP FROM MFAEmailOTP mfaEmailOTP WHERE ";

	private static final String _SQL_COUNT_MFAEMAILOTP =
		"SELECT COUNT(mfaEmailOTP) FROM MFAEmailOTP mfaEmailOTP";

	private static final String _SQL_COUNT_MFAEMAILOTP_WHERE =
		"SELECT COUNT(mfaEmailOTP) FROM MFAEmailOTP mfaEmailOTP WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "mfaEmailOTP.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MFAEmailOTP exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MFAEmailOTP exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MFAEmailOTPPersistenceImpl.class);

}