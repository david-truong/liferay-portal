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

import com.liferay.gradle.plugins.workspace.internal.util.GradleUtil;

import groovy.lang.Closure;

import java.io.File;

import java.util.Set;

import org.gradle.api.file.CopySpec;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;

/**
 * @author Simon Jiang
 */
public class DistBundleTask extends Copy {

	@Optional
	public String getConfigCommon() {
		return _configCommon;
	}

	@Input
	@Optional
	public File getConfigsDir() {
		return GradleUtil.toFile(getProject(), _configsDir);
	}

	@Input
	@Optional
	public String getEnvironment() {
		return _environment;
	}

	@Override
	public Set<String> getExcludes() {
		Set<String> excludes = super.getExcludes();

		excludes.add("**/.touch");

		return excludes;
	}

	@Optional
	public FileCollection getProvidedModules() {
		return _providedModules;
	}

	@Input
	@Optional
	public boolean isIncludeMetadata() {
		return _includeMetadata;
	}

	@SuppressWarnings("serial")
	public void setConfigCommon(String configCommon) {
		_configCommon = configCommon;

		from(
			new File(getConfigsDir(), _configCommon),
			new Closure<Void>(getProject()) {

				@SuppressWarnings("unused")
				public void doCall(CopySpec copySpec) {
					copySpec.exclude(getExcludes());
				}

			});
	}

	public void setConfigsDir(Object configsDir) {
		_configsDir = configsDir;
	}

	@SuppressWarnings("serial")
	public void setEnvironment(String environment) {
		_environment = environment;

		from(
			new File(getConfigsDir(), _environment),
			new Closure<Void>(getProject()) {

				@SuppressWarnings("unused")
				public void doCall(CopySpec copySpec) {
					copySpec.exclude(getExcludes());
				}

			});
	}

	public void setIncludeMetadata(boolean includeMetadata) {
		_includeMetadata = includeMetadata;
	}

	@SuppressWarnings("serial")
	public void setProvidedModules(FileCollection providedModules) {
		_providedModules = providedModules;

		from(
			_providedModules,
			new Closure<Void>(getProject()) {

				@SuppressWarnings("unused")
				public void doCall(CopySpec copySpec) {
					copySpec.into("osgi/modules");
				}

			});
	}

	private String _configCommon;
	private Object _configsDir;
	private String _environment;
	private boolean _includeMetadata;
	private FileCollection _providedModules;

}