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

package com.liferay.multi.factor.authentication.api.checker;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.spi.checker.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.MFACheckerSetup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tomas Polesovsky
 */
@ProviderType
public interface CompositeMFAChecker {

	public List<MFACheckerSetup> getMFACheckersWaitingForSetup(
		boolean onlyWithForcedSetup, long userId);

	public List<BrowserMFAChecker> getMFACheckersWaitingForVerify(
		HttpServletRequest request, long userId);

}