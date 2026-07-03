/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.rest.client.serdes.v1_0;

import com.liferay.seo.studio.rest.client.dto.v1_0.AgentInvocation;
import com.liferay.seo.studio.rest.client.json.BaseJSONParser;

import jakarta.annotation.Generated;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Brooke Dalton
 * @generated
 */
@Generated("")
public class AgentInvocationSerDes {

	public static AgentInvocation toDTO(String json) {
		AgentInvocationJSONParser agentInvocationJSONParser =
			new AgentInvocationJSONParser();

		return agentInvocationJSONParser.parseToDTO(json);
	}

	public static AgentInvocation[] toDTOs(String json) {
		AgentInvocationJSONParser agentInvocationJSONParser =
			new AgentInvocationJSONParser();

		return agentInvocationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AgentInvocation agentInvocation) {
		if (agentInvocation == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (agentInvocation.getAgentDefinitionExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"agentDefinitionExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(
					agentInvocation.getAgentDefinitionExternalReferenceCode()));

			sb.append("\"");
		}

		if (agentInvocation.getContext() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"context\": ");

			sb.append(_toJSON(agentInvocation.getContext()));
		}

		if (agentInvocation.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(agentInvocation.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (agentInvocation.getSseEventSinkKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sseEventSinkKey\": ");

			sb.append("\"");

			sb.append(_escape(agentInvocation.getSseEventSinkKey()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AgentInvocationJSONParser agentInvocationJSONParser =
			new AgentInvocationJSONParser();

		return agentInvocationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AgentInvocation agentInvocation) {
		if (agentInvocation == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (agentInvocation.getAgentDefinitionExternalReferenceCode() == null) {
			map.put("agentDefinitionExternalReferenceCode", null);
		}
		else {
			map.put(
				"agentDefinitionExternalReferenceCode",
				String.valueOf(
					agentInvocation.getAgentDefinitionExternalReferenceCode()));
		}

		if (agentInvocation.getContext() == null) {
			map.put("context", null);
		}
		else {
			map.put("context", String.valueOf(agentInvocation.getContext()));
		}

		if (agentInvocation.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(agentInvocation.getExternalReferenceCode()));
		}

		if (agentInvocation.getSseEventSinkKey() == null) {
			map.put("sseEventSinkKey", null);
		}
		else {
			map.put(
				"sseEventSinkKey",
				String.valueOf(agentInvocation.getSseEventSinkKey()));
		}

		return map;
	}

	public static class AgentInvocationJSONParser
		extends BaseJSONParser<AgentInvocation> {

		@Override
		protected AgentInvocation createDTO() {
			return new AgentInvocation();
		}

		@Override
		protected AgentInvocation[] createDTOArray(int size) {
			return new AgentInvocation[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(
					jsonParserFieldName,
					"agentDefinitionExternalReferenceCode")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "context")) {
				return true;
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "sseEventSinkKey")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			AgentInvocation agentInvocation, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName,
					"agentDefinitionExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					agentInvocation.setAgentDefinitionExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "context")) {
				if (jsonParserFieldValue != null) {
					agentInvocation.setContext(
						(Map<String, ?>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					agentInvocation.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "sseEventSinkKey")) {
				if (jsonParserFieldValue != null) {
					agentInvocation.setSseEventSinkKey(
						(String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value == null) {
			return "null";
		}

		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}
// LIFERAY-REST-BUILDER-HASH:241413361