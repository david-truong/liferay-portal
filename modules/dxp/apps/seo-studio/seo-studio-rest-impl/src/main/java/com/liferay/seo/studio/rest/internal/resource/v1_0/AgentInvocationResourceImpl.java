/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.rest.internal.resource.v1_0;

import com.liferay.ai.hub.agent.AgentContext;
import com.liferay.ai.hub.agent.DefaultAgent;
import com.liferay.ai.hub.rest.dto.v1_0.AgentDefinition;
import com.liferay.ai.hub.rest.dto.v1_0.Variable;
import com.liferay.ai.hub.rest.manager.v1_0.AgentDefinitionManager;
import com.liferay.ai.hub.rest.resource.v1_0.util.SseUtil;
import com.liferay.ai.hub.util.AccountEntryUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.seo.studio.rest.dto.v1_0.AgentInvocation;
import com.liferay.seo.studio.rest.resource.v1_0.AgentInvocationResource;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Truong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/agent-invocation.properties",
	scope = ServiceScope.PROTOTYPE, service = AgentInvocationResource.class
)
public class AgentInvocationResourceImpl
	extends BaseAgentInvocationResourceImpl {

	@Override
	public void getAgentInvocationSubscribe(SseEventSink sseEventSink) {
		if (!FeatureFlagManagerUtil.isEnabled(
				contextCompany.getCompanyId(), "LPD-44511")) {

			throw new UnsupportedOperationException();
		}

		SseUtil.initialize(_sse, sseEventSink);
	}

	@Override
	public AgentInvocation postAgentInvocation(AgentInvocation agentInvocation)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				contextCompany.getCompanyId(), "LPD-44511")) {

			throw new UnsupportedOperationException();
		}

		if (!Objects.equals(
				agentInvocation.getAgentDefinitionExternalReferenceCode(),
				"L_SEO_STUDIO_TITLE_GENERATOR")) {

			throw new NotFoundException();
		}

		AgentDefinition agentDefinition =
			_agentDefinitionManager.getAgentDefinition(
				contextCompany.getCompanyId(),
				new DefaultDTOConverterContext(
					contextAcceptLanguage.isAcceptAllLanguages(), null,
					_dtoConverterRegistry, contextHttpServletRequest, null,
					contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
					contextUser),
				agentInvocation.getAgentDefinitionExternalReferenceCode());

		AgentContext.Builder agentContextBuilder = AgentContext.builder();

		AgentContext agentContext =
			agentContextBuilder.agentDefinitionExternalReferenceCode(
				agentDefinition.getExternalReferenceCode()
			).companyId(
				contextCompany.getCompanyId()
			).groupId(
				AccountEntryUtil.getUserAccountEntryGroupId(
					contextUser.getUserId())
			).input(
				agentInvocation.getContext()
			).inputVariableNames(
				transformToList(
					agentDefinition.getInputVariables(), Variable::getName)
			).serviceContext(
				ServiceContextFactory.getInstance(contextHttpServletRequest)
			).sseEventSinkKey(
				agentInvocation.getSseEventSinkKey()
			).userId(
				contextUser.getUserId()
			).workflowDefinitionName(
				agentDefinition.getWorkflowDefinitionName()
			).build();

		long workflowInstanceId = _defaultAgent.invoke(agentContext);

		return new AgentInvocation() {
			{
				setAgentDefinitionExternalReferenceCode(
					agentDefinition::getExternalReferenceCode);
				setExternalReferenceCode(
					() -> String.valueOf(workflowInstanceId));
			}
		};
	}

	@Reference
	private AgentDefinitionManager _agentDefinitionManager;

	@Reference
	private DefaultAgent _defaultAgent;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Context
	private Sse _sse;

}