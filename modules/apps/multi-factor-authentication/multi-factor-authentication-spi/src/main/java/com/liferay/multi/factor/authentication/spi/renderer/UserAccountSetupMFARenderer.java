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

package com.liferay.multi.factor.authentication.spi.renderer;

import java.io.IOException;

import javax.portlet.ActionRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 */
public interface UserAccountSetupMFARenderer {

	public String getLabel();

	public void includeUserAccountSetup(
			long userId, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException;

	public boolean setupUserAccount(ActionRequest actionRequest, long userId);

}