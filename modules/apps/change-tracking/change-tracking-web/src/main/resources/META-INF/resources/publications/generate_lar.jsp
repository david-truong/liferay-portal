<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/publications/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

long ctCollectionId = ParamUtil.getLong(request, "ctCollectionId");

renderResponse.setTitle(LanguageUtil.get(request, "generate-lar-from-publication"));
%>

<liferay-portlet:actionURL name="/change_tracking/generate_lar" var="actionURL">
	<liferay-portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="ctCollectionId" type="hidden" value="<%= ctCollectionId %>" />

	<div class="sheet">
		<clay:sheet-header>
			<h2 class="sheet-title">
				<liferay-ui:message key="generate-lar-from-publication" />
			</h2>
		</clay:sheet-header>

		<clay:sheet-section>
			<p>
				<liferay-ui:message key="this-will-export-the-asset-content-this-publication-touched-you-may-receive-multiple-lar-files-in-your-export-import-history" />
			</p>
		</clay:sheet-section>

		<clay:sheet-footer>
			<aui:button type="submit" value="generate-lar" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</clay:sheet-footer>
	</div>
</aui:form>