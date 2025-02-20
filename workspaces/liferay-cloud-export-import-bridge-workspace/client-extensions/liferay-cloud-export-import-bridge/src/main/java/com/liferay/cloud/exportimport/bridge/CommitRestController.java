/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.cloud.exportimport.bridge;

import com.liferay.client.extension.util.spring.boot.BaseRestController;
import com.liferay.portal.kernel.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;

import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David Truong
 */
@RequestMapping("/commit")
@RestController
public class CommitRestController extends BaseRestController {

	@PostMapping
	public ResponseEntity<String> post(
			@AuthenticationPrincipal Jwt jwt, @RequestBody String json)
		throws Exception {

		log(jwt, _log, json);

		return new ResponseEntity<>(
			new JSONObject(
			).put(
				"commit", _commit(json)
			).toString(),
			HttpStatus.OK);
	}

	private void _addFiles(Git git, JSONObject jsonObject) throws Exception {
		try (InputStream inputStream = new ByteArrayInputStream(
				Base64.decode(jsonObject.getString("file")))) {

			Path destination = Paths.get(jsonObject.getString("path"));

			Files.copy(
				inputStream, destination, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("File copied successfully!");

			AddCommand addCommand = git.add();

			addCommand = addCommand.addFilepattern(destination.toString());

			addCommand.call();
		}
	}

	private String _commit(String json) throws Exception {
		File file = new File(
			System.getProperty("user.home"), _DEFAULT_REPO_DIR);

		try (Git git = _getRepository(file)) {
			JSONObject jsonObject = new JSONObject(json);

			_addFiles(git, jsonObject);

			_commitFiles(git, jsonObject);

			return "";
		}
	}

	private void _commitFiles(Git git, JSONObject jsonObject) throws Exception {
		CommitCommand commitCommand = git.commit();

		commitCommand = commitCommand.setMessage(
			jsonObject.getString("message"));

		commitCommand.call();
	}

	private Git _getRepository(File file) throws Exception {
		if (!file.exists()) {
			return Git.cloneRepository(
			).setURI(
				_DEFAULT_REPO
			).setDirectory(
				file
			).setCloneAllBranches(
				true
			).call();
		}

		return Git.open(file);
	}

	private static final String _DEFAULT_REPO =
		"https://github.com/eclipse/jgit.git";

	private static final String _DEFAULT_REPO_DIR = "/liferay-workspace";

	private static final Log _log = LogFactory.getLog(
		BranchRestController.class);

}