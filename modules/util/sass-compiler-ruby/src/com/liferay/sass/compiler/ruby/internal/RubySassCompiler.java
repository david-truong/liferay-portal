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

package com.liferay.sass.compiler.ruby.internal;

import com.liferay.sass.compiler.SassCompiler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jodd.io.ZipUtil;

import org.apache.commons.io.FileUtils;

import org.jruby.RubyInstanceConfig;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.internal.LocalContextProvider;

/**
 * @author David Truong
 */
public class RubySassCompiler implements SassCompiler {

	public RubySassCompiler() throws Exception {
		this(
			_COMPILE_MODE_JIT, _COMPILE_THRESHOLD_DEFAULT,
			System.getProperty("java.io.tmpdir"));
	}

	public RubySassCompiler(
			String compileMode, int compilerThreshold, String tmpDir)
		throws Exception {

		_tmpDir = tmpDir;

		_scriptingContainer = new ScriptingContainer(
			LocalContextScope.SINGLETHREAD);

		LocalContextProvider localContextProvider =
			_scriptingContainer.getProvider();

		RubyInstanceConfig rubyInstanceConfig =
			localContextProvider.getRubyInstanceConfig();

		if (_COMPILE_MODE_FORCE.equals(compileMode)) {
			rubyInstanceConfig.setCompileMode(
				RubyInstanceConfig.CompileMode.FORCE);
		}
		else if (_COMPILE_MODE_JIT.equals(compileMode)) {
			rubyInstanceConfig.setCompileMode(
				RubyInstanceConfig.CompileMode.JIT);
		}

		_initRubyGems();

		String loadPathsDir = _tmpDir + "/liferay/ruby/";

		List<String> loadPaths = new ArrayList<>();

		loadPaths.add(
			loadPathsDir + "META-INF/jruby.home/lib/ruby/site_ruby/1.8");
		loadPaths.add(
			loadPathsDir + "META-INF/jruby.home/lib/ruby/site_ruby/shared");
		loadPaths.add(loadPathsDir + "META-INF/jruby.home/lib/ruby/1.8");
		loadPaths.add(loadPathsDir + "gems/chunky_png-1.3.4/lib");
		loadPaths.add(loadPathsDir + "gems/compass-1.0.1/lib");
		loadPaths.add(loadPathsDir + "gems/compass-core-1.0.3/lib");
		loadPaths.add(loadPathsDir + "gems/compass-import-once-1.0.5/lib");
		loadPaths.add(loadPathsDir + "gems/ffi-1.9.6-java/lib");
		loadPaths.add(loadPathsDir + "gems/multi_json-1.10.1/lib");
		loadPaths.add(loadPathsDir + "gems/rb-fsevent-0.9.4/lib");
		loadPaths.add(loadPathsDir + "gems/rb-inotify-0.9.5/lib");
		loadPaths.add(loadPathsDir + "gems/sass-3.4.13/lib");

		rubyInstanceConfig.setLoadPaths(loadPaths);

		rubyInstanceConfig.setJitThreshold(compilerThreshold);

		String rubyScript = null;

		Class<?> clazz = getClass();

		try (InputStream inputStream =
				clazz.getResourceAsStream("dependencies/main.rb")) {

			Scanner scanner = new Scanner(inputStream, "UTF-8");

			scanner.useDelimiter("\\A");

			rubyScript = scanner.next();
		}

		_scriptObject = _scriptingContainer.runScriptlet(rubyScript);
	}

	@Override
	public String compileFile(
			String inputFileName, String includeDirName, String imgDirName)
		throws RubySassCompilerException {

		try {
			Path path = Paths.get(inputFileName);

			String input = new String(Files.readAllBytes(path));

			return compileString(input, includeDirName, imgDirName);
		}
		catch (Exception e) {
			throw new RubySassCompilerException(
				"Unable to parse " + inputFileName);
		}
	}

	@Override
	public String compileString(
			String input, String includeDirName, String imgDirName)
		throws RubySassCompilerException {

		try {
			return _scriptingContainer.callMethod(
				_scriptObject, "process",
				new Object[] {input, includeDirName, _tmpDir, false},
				String.class);
		}
		catch (Exception e) {
			throw new RubySassCompilerException(e);
		}
	}

	private void _initRubyGems() throws Exception {
		Class<?> clazz = getClass();

		URL url = clazz.getResource("dependencies/com.liferay.ruby.gems.jar");

		File rubyGemsJarFile = new File(url.getFile());

		if (!rubyGemsJarFile.exists()) {
			throw new RubySassCompilerException(
				rubyGemsJarFile + " does not exist");
		}

		File rubyDir = new File(_tmpDir + "/liferay/ruby");

		if (!rubyDir.exists() ||
			(rubyDir.lastModified() < rubyGemsJarFile.lastModified())) {

			FileUtils.deleteDirectory(rubyDir);

			try {
				FileUtils.forceMkdir(rubyDir);

				ZipUtil.unzip(rubyGemsJarFile, rubyDir);

				rubyDir.setLastModified(rubyGemsJarFile.lastModified());
			}
			catch (IOException ioe) {
				throw new RubySassCompilerException(
					"Unable to unzip " + rubyGemsJarFile + " to " + rubyDir,
					ioe);
			}
		}
	}

	private static final String _COMPILE_MODE_FORCE = "force";

	private static final String _COMPILE_MODE_JIT = "jit";

	private static final int _COMPILE_THRESHOLD_DEFAULT = 5;

	private final ScriptingContainer _scriptingContainer;
	private final Object _scriptObject;
	private final String _tmpDir;

}