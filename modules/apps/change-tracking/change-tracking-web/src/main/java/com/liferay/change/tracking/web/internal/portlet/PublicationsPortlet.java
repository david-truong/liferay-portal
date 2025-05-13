/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.web.internal.portlet;

import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTPreferencesLocalService;
import com.liferay.change.tracking.service.CTRemoteLocalService;
import com.liferay.change.tracking.spi.display.CTDisplayRendererRegistry;
import com.liferay.change.tracking.web.internal.configuration.helper.CTSettingsConfigurationHelper;
import com.liferay.change.tracking.web.internal.constants.CTWebKeys;
import com.liferay.change.tracking.web.internal.display.context.PublicationsDisplayContext;
import com.liferay.change.tracking.web.internal.helper.PublicationHelper;
import com.liferay.change.tracking.web.internal.util.PublicationsPermissionsActionUtil;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.propagator.PermissionPropagator;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import javax.portlet.ActionParameters;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PropsValues;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(
	property = {
		"com.liferay.portlet.add-default-resource=false",
		"com.liferay.portlet.css-class-wrapper=portlet-publications",
		"com.liferay.portlet.header-portlet-css=/publications/css/main.css",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.show-portlet-access-denied=false",
		"com.liferay.portlet.show-portlet-inactive=false",
		"com.liferay.portlet.system=true",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Overview",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/publications/view_publications.jsp",
		"javax.portlet.name=" + CTPortletKeys.PUBLICATIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class PublicationsPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			checkPermissions(renderRequest);
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			include("/publications/error.jsp", renderRequest, renderResponse);

			return;
		}

		PublicationsDisplayContext publicationsDisplayContext =
			new PublicationsDisplayContext(
				_ctCollectionLocalService, _ctDisplayRendererRegistry,
				_ctPreferencesLocalService, _ctRemoteLocalService,
				_portal.getHttpServletRequest(renderRequest), _language,
				_publicationHelper, renderRequest, renderResponse);

		renderRequest.setAttribute(
			CTWebKeys.PUBLICATIONS_DISPLAY_CONTEXT, publicationsDisplayContext);

		super.render(renderRequest, renderResponse);
	}

	@Override
	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {

		if (!_ctSettingsConfigurationHelper.isEnabled(
				_portal.getCompanyId(portletRequest))) {

			String actionName = ParamUtil.getString(
				portletRequest, ActionRequest.ACTION_NAME);
			String mvcRenderCommandName = ParamUtil.getString(
				portletRequest, "mvcRenderCommandName");

			if (!actionName.equals(
					"/change_tracking" +
						"/update_global_publications_configuration") &&
				!mvcRenderCommandName.equals(
					"/change_tracking/view_settings")) {

				throw new PrincipalException("Publications are not enabled");
			}
		}

		PortletPermissionUtil.check(
			PermissionThreadLocal.getPermissionChecker(),
			CTPortletKeys.PUBLICATIONS, ActionKeys.VIEW);
	}

	public void updateRolePermissions(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");
		String modelResource = ParamUtil.getString(
			actionRequest, "modelResource");
		long[] roleIds = StringUtil.split(
			ParamUtil.getString(
				actionRequest, "rolesSearchContainerPrimaryKeys"),
			0L);

		String selResource = PortletIdCodec.decodePortletName(portletResource);

		if (Validator.isNotNull(modelResource)) {
			selResource = modelResource;
		}

		long resourceGroupId = ParamUtil.getLong(
			actionRequest, "resourceGroupId", themeDisplay.getScopeGroupId());
		String[] resourcePrimKeys = ParamUtil.getStringValues(
			actionRequest, "resourcePrimKey");

		Map<Long, String[]> roleIdCheckedActionIdsMap = new HashMap<>();

		for (long roleId : roleIds) {
			roleIdCheckedActionIdsMap.put(
				roleId,
				ArrayUtil.toStringArray(
					_getCheckedActionIds(
						actionRequest, roleId,
						value -> !Objects.equals(value, "indeterminate"))));
		}

		PermissionPropagator permissionPropagator = null;

		if (PropsValues.PERMISSIONS_PROPAGATION_ENABLED &&
			Validator.isNotNull(portletResource)) {

			com.liferay.portal.kernel.model.Portlet portlet = _portletLocalService.getPortletById(
				themeDisplay.getCompanyId(), portletResource);

			permissionPropagator = portlet.getPermissionPropagatorInstance();
		}

		for (String resourcePrimKey : resourcePrimKeys) {
			Map<Long, String[]> roleIdActionIdsMap = new HashMap<>(
				roleIdCheckedActionIdsMap);

			if (resourcePrimKeys.length > 1) {
				_addIndeterminateActionIds(
					actionRequest, themeDisplay.getCompanyId(), resourcePrimKey,
					roleIdActionIdsMap, selResource);
			}

			long ctCollectionId = 0;

			if (_serviceTrackerMap.containsKey(selResource)) {
				ctCollectionId = CTCollectionThreadLocal.getCTCollectionId();
			}

			Role role = _roleLocalService.getRole(themeDisplay.getCompanyId(), RoleConstants.OWNER);

			String[] rolePermissions = roleIdCheckedActionIdsMap.get(role.getRoleId());

			_ctSettingsConfigurationHelper.save(
				themeDisplay.getCompanyId(),
				HashMapBuilder.<String, Object>put(
					"defaultOwnerActionIds", rolePermissions
				).build());

			try (SafeCloseable safeCloseable =
					 CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
						 ctCollectionId)) {

				_resourcePermissionService.setIndividualResourcePermissions(
					resourceGroupId, themeDisplay.getCompanyId(), selResource,
					resourcePrimKey, roleIdActionIdsMap);
			}
//

//			List<CTCollection> ctCollections =
//				_ctCollectionLocalService.getCTCollections(
//					themeDisplay.getCompanyId(), WorkflowConstants.STATUS_DRAFT, QueryUtil.ALL_POS,
//					QueryUtil.ALL_POS, null);
//
//			for (CTCollection ctCollection : ctCollections) {
//					_resourcePermissionService.setIndividualResourcePermissions(
//						resourceGroupId, themeDisplay.getCompanyId(),
//						selResource,
//						String.valueOf(ctCollection.getCtCollectionId()), roleIdActionIdsMap);
//			}
//
			if (permissionPropagator != null) {
				permissionPropagator.propagateRolePermissions(
					actionRequest, modelResource, resourcePrimKey, roleIds);
			}
		}

		if (Validator.isNull(modelResource)) {

			// Force update of layout modified date. See LPS-59246.

			PortletPreferences portletPreferences =
				PublicationsPermissionsActionUtil.getLayoutPortletSetup(
					actionRequest, PublicationsPermissionsActionUtil.getPortlet(actionRequest));

			portletPreferences.store();
		}

		_updateLayoutStatus(
			themeDisplay.getLayout(),
			ServiceContextFactory.getInstance(actionRequest),
			themeDisplay.getUserId());

		if (resourcePrimKeys.length > 1) {
			SessionMessages.add(
				actionRequest, "requestProcessed",
				_language.format(
					themeDisplay.getLocale(),
					"x-permissions-were-updated-successfully",
					resourcePrimKeys.length));
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, (Class<CTService<?>>)(Class<?>)CTService.class, null,
			(serviceReference, emitter) -> {
				CTService<?> ctService = bundleContext.getService(
					serviceReference);

				Class<?> modelClass = ctService.getModelClass();

				emitter.emit(modelClass.getName());
			});
	}

	private void _addIndeterminateActionIds(
		ActionRequest actionRequest, long companyId, String resourcePrimKey,
		Map<Long, String[]> roleIdActionIdsMap, String selResource)
		throws Exception {

		for (Map.Entry<Long, String[]> entry : roleIdActionIdsMap.entrySet()) {
			Long roleId = entry.getKey();

			List<String> indeterminateActionIds = _getCheckedActionIds(
				actionRequest, roleId,
				value -> Objects.equals(value, "indeterminate"));

			if (ListUtil.isEmpty(indeterminateActionIds)) {
				continue;
			}

			List<String> availableActionIds =
				_resourcePermissionLocalService.
					getAvailableResourcePermissionActionIds(
						companyId, selResource,
						ResourceConstants.SCOPE_INDIVIDUAL, resourcePrimKey,
						roleId, indeterminateActionIds);

			entry.setValue(
				ArrayUtil.append(
					entry.getValue(),
					ArrayUtil.toStringArray(availableActionIds)));
		}
	}


	private List<String> _getCheckedActionIds(
		ActionRequest actionRequest, long roleId,
		Predicate<String> valuePredicate) {

		List<String> actionIds = new ArrayList<>();

		ActionParameters actionParameters = actionRequest.getActionParameters();

		for (String name : actionParameters.getNames()) {
			if (!name.startsWith(roleId + "_ACTION_")) {
				continue;
			}

			if (valuePredicate.test(actionParameters.getValue(name))) {
				int pos = name.indexOf("_ACTION_");

				String actionId = name.substring(
					pos + "_ACTION_".length());

				actionIds.add(actionId);
			}
		}

		return actionIds;
	}

	private void _updateLayoutStatus(
		Layout layout, ServiceContext serviceContext, long userId)
		throws Exception {

		if (layout.isDraftLayout()) {
			_layoutLocalService.updateStatus(
				userId, layout.getPlid(), WorkflowConstants.STATUS_DRAFT,
				serviceContext);
		}
	}
	@Reference
	private LayoutLocalService _layoutLocalService;
	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

	@Reference
	private CTDisplayRendererRegistry _ctDisplayRendererRegistry;

	@Reference
	private CTPreferencesLocalService _ctPreferencesLocalService;

	@Reference
	private CTRemoteLocalService _ctRemoteLocalService;

	@Reference
	private CTSettingsConfigurationHelper _ctSettingsConfigurationHelper;

	@Reference
	private Language _language;
	@Reference
	private PortletLocalService _portletLocalService;

	@Reference
	private RoleLocalService _roleLocalService;
	@Reference
	private Portal _portal;
	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;
	@Reference
	private PublicationHelper _publicationHelper;

	private ServiceTrackerMap<String, CTService<?>> _serviceTrackerMap;
	@Reference
	private ResourcePermissionService _resourcePermissionService;
	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.change.tracking.web)(&(release.schema.version>=1.0.2)(!(release.schema.version>=2.0.0))))"
	)
	private Release _release;

}