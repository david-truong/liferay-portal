<%--
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
--%>

<%@ include file="../init.jsp" %>

<%
BrowserMFAChecker browserMFAChecker = (BrowserMFAChecker)request.getAttribute(BrowserMFAChecker.class.getName());

List<BrowserMFAChecker> verifyMFACheckers = (List<BrowserMFAChecker>)request.getAttribute("verifyMFACheckers");

long mfaUserId = (Long)request.getAttribute("mfaUserId");

int mfaCheckerIndex = ParamUtil.getInteger(request, "mfaCheckerIndex", 0);

if ((mfaCheckerIndex > -1) && (mfaCheckerIndex < verifyMFACheckers.size())) {
	browserMFAChecker = verifyMFACheckers.get(mfaCheckerIndex);
}
%>

<portlet:actionURL name="/mfa_verify/verify" var="verifyActionURL">
	<portlet:param name="mvcRenderCommandName" value="/mfa_verify/view" />
</portlet:actionURL>

<aui:form action="<%= verifyActionURL %>" cssClass="container-fluid-1280 sign-in-form" method="post" name="fm">
	<aui:input name="integrationName" type="hidden" value='<%= ParamUtil.getString(request, "integrationName") %>' />
	<aui:input name="mfaCheckerIndex" type="hidden" value="<%= mfaCheckerIndex %>" />
	<aui:input name="redirect" type="hidden" value='<%= ParamUtil.getString(request, "redirect") %>' />
	<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />

	<h1>
		<liferay-ui:message key="<%= HtmlUtil.escape(((MFAChecker)browserMFAChecker).getLabel(locale)) %>" />
	</h1>

	<liferay-ui:error key="mfaFailed" message="multi-factor-authentication-failed" />

	<%
	browserMFAChecker.includeBrowserVerification(mfaUserId, request, response);
	%>

	<c:if test="<%= verifyMFACheckers.size() > 1 %>">
		<portlet:renderURL copyCurrentRenderParameters="<%= true %>" var="useAnotherMFAChecker">
			<portlet:param name="integrationName" value='<%= ParamUtil.getString(request, "integrationName") %>' />
			<portlet:param name="mfaCheckerIndex" value='<%= mfaCheckerIndex + 1 < verifyMFACheckers.size() ? String.valueOf(mfaCheckerIndex + 1) : "0" %>' />
			<portlet:param name="mvcRenderCommandName" value="/mfa_verify/view" />
			<portlet:param name="redirect" value='<%= ParamUtil.getString(request, "redirect") %>' />
			<portlet:param name="saveLastPath" value="<%= Boolean.FALSE.toString() %>" />
		</portlet:renderURL>

		<a href="<%= HtmlUtil.escapeAttribute(useAnotherMFAChecker) %>"><liferay-ui:message key="use-another-verifier" /></a>
	</c:if>

	<aui:button-row>
		<aui:button type="submit" value="submit" />
	</aui:button-row>
</aui:form>