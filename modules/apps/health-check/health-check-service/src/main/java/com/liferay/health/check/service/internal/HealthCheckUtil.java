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

package com.liferay.health.check.service.internal;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.PrintStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.apache.felix.service.command.CommandProcessor;
import org.apache.felix.service.command.CommandSession;

import org.osgi.framework.Bundle;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentConfigurationDTO;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;
import org.osgi.service.component.runtime.dto.UnsatisfiedReferenceDTO;

/**
 * @author Peter Shin
 */
public class HealthCheckUtil {

	public static void diagnostics(CommandProcessor commandProcessor) {
		if (!_log.isInfoEnabled()) {
			return;
		}

		try (UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
				new UnsyncByteArrayOutputStream();
			PrintStream printStream = new PrintStream(
				unsyncByteArrayOutputStream);) {

			CommandSession commandSession = commandProcessor.createSession(
				null, printStream, printStream);

			commandSession.execute("diag");

			printStream.flush();

			String s = StringUtil.trim(unsyncByteArrayOutputStream.toString());

			for (String line : StringUtil.splitLines(s)) {
				_log.info(line);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void listBundles(
		ServiceComponentRuntime serviceComponentRuntime, Bundle[] bundles,
		boolean listAllBundles) {

		Map<String, Bundle> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

		int idLength = 0;
		int stateLength = 0;

		for (Bundle bundle : bundles) {
			map.put(bundle.getSymbolicName(), bundle);

			String id = String.valueOf(bundle.getBundleId());

			if (idLength < id.length()) {
				idLength = id.length();
			}

			String state = getState(bundle);

			if (stateLength < state.length()) {
				stateLength = state.length();
			}
		}

		for (Map.Entry<String, Bundle> entry : map.entrySet()) {
			Bundle bundle = entry.getValue();

			String unsatisfiedReferences = null;

			if (bundle.getState() == Bundle.ACTIVE) {
				unsatisfiedReferences = getUnsatisfiedReferences(
					serviceComponentRuntime, bundle);
			}

			if (Validator.isNotNull(unsatisfiedReferences) ||
				(bundle.getState() != Bundle.ACTIVE) || listAllBundles) {

				String formattedID = String.format(
					"%1$-" + (idLength + 1) + "s", bundle.getBundleId());
				String formattedState = String.format(
					"%1$-" + (stateLength + 1) + "s", getState(bundle));

				StringBundler sb = new StringBundler(7);

				sb.append("Bundle: ");
				sb.append(formattedID);
				sb.append(formattedState);
				sb.append(bundle.getSymbolicName());
				sb.append(" [");
				sb.append(bundle.getVersion());
				sb.append("]");

				if (Validator.isNull(unsatisfiedReferences)) {
					if (_log.isInfoEnabled()) {
						_log.info(sb.toString());
					}

					continue;
				}

				if (!_log.isWarnEnabled()) {
					continue;
				}

				_log.warn(sb.toString());

				for (String s : StringUtil.splitLines(unsatisfiedReferences)) {
					_log.warn(s);
				}
			}
		}
	}

	public static void waitForPortalStartup() {
		for (int i = 0; i < 30; i++) {
			try {
				Company company = CompanyLocalServiceUtil.getCompany(
					PortalUtil.getDefaultCompanyId());

				String portalURL = PortalUtil.getPortalURL(
					company.getVirtualHostname(),
					PortalUtil.getPortalServerPort(false), false);

				URL url = new URL(portalURL);

				HttpURLConnection httpURLConnection =
					(HttpURLConnection)url.openConnection();

				if (httpURLConnection.getResponseCode() ==
						HttpURLConnection.HTTP_OK) {

					break;
				}
			}
			catch (Exception e) {
			}

			if (_log.isInfoEnabled()) {
				_log.info("Waiting for portal startup");
			}

			try {
				Thread.sleep(20000);
			}
			catch (InterruptedException ie) {
			}
		}
	}

	protected static String getState(Bundle bundle) {
		int state = bundle.getState();

		String label = null;

		if (state == Bundle.ACTIVE) {
			label = "Active";
		}
		else if (state == Bundle.INSTALLED) {
			label = "Installed";
		}
		else if (state == Bundle.RESOLVED) {
			label = "Resolved";
		}
		else if (state == Bundle.SIGNERS_ALL) {
			label = "Signers all";
		}
		else if (state == Bundle.SIGNERS_TRUSTED) {
			label = "Signers Trusted";
		}
		else if (state == Bundle.START_ACTIVATION_POLICY) {
			label = "Start Activation Policy";
		}
		else if (state == Bundle.START_TRANSIENT) {
			label = "Start Transient";
		}
		else if (state == Bundle.STARTING) {
			label = "Starting";
		}
		else if (state == Bundle.STOP_TRANSIENT) {
			label = "Stop Transient";
		}
		else if (state == Bundle.STOPPING) {
			label = "Stopping";
		}
		else if (state == Bundle.UNINSTALLED) {
			label = "Uninstalled";
		}
		else {
			label = String.valueOf(state);
		}

		return label;
	}

	protected static String getUnsatisfiedReferences(
		ServiceComponentRuntime serviceComponentRuntime, Bundle bundle) {

		StringBundler sb = new StringBundler();

		Collection<ComponentDescriptionDTO> componentDescriptionDTOs =
			serviceComponentRuntime.getComponentDescriptionDTOs(bundle);

		for (ComponentDescriptionDTO componentDescriptionDTO :
				componentDescriptionDTOs) {

			Collection<ComponentConfigurationDTO> componentConfigurationDTOs =
				serviceComponentRuntime.getComponentConfigurationDTOs(
					componentDescriptionDTO);

			for (ComponentConfigurationDTO componentConfigurationDTO :
					componentConfigurationDTOs) {

				if (componentConfigurationDTO.state ==
						ComponentConfigurationDTO.UNSATISFIED_REFERENCE) {

					sb.append("\n\tDeclarative Service {id: ");
					sb.append(componentConfigurationDTO.id);
					sb.append(", name: ");
					sb.append(componentDescriptionDTO.name);
					sb.append(", unsatisfied references: ");

					for (UnsatisfiedReferenceDTO unsatisfiedReferenceDTO :
							componentConfigurationDTO.unsatisfiedReferences) {

						sb.append("\n\t\t{name: ");
						sb.append(unsatisfiedReferenceDTO.name);
						sb.append(", target: ");
						sb.append(unsatisfiedReferenceDTO.target);
						sb.append("}");
					}

					sb.append("\n\t}");
				}
			}
		}

		return StringUtil.trim(sb.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HealthCheckUtil.class);

}