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

package com.liferay.gradle.plugins.workspace.tasks;

import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;

/**
 * @author Simon Jiang
 */
public class DistBundleTask extends Copy {

	@Input
	@Optional
	public String getWorkspacEnvironment() {
		return _workspaceEnvironment;
	}

	@Input
	@Optional
	public Boolean isBuildFileMetaData() {
		return _buildFileMetaData;
	}

	public void setBuildFileMetaData(Boolean buildFileMetaData) {
		_buildFileMetaData = buildFileMetaData;
	}

	public void setWorkspaceEnvironmentl(String workspaceEnvironment) {
		_workspaceEnvironment = workspaceEnvironment;
	}

	private Boolean _buildFileMetaData;
	private String _workspaceEnvironment;

}