<%@include file="/html/init.jsp" %>

<%
	// permission
	String modelResource = "com.bosch.boschadministration.boschadminportlet";
	String modelResourceDescription = themeDisplay.getScopeGroupName();
	String resourcePrimKey = String.valueOf(scopeGroupId);
	boolean showPermissionsURL = true;
	showPermissionsURL = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
%>

<!-- liferay message -->
<liferay-ui:success key="layouts-deleted-successfully"
		message="layouts-deleted-successfully" />
<liferay-ui:error key="you-cannot-delete-layouts"
	message="you-cannot-delete-layouts" />
<liferay-ui:error key="invalid-plugin" message="invalid-plugin" />
<liferay-ui:error key="cannot-reset-build-number" message="cannot-reset-build-number" />
<liferay-ui:error key="cannot-remove-service-component" message="cannot-remove-service-component" />


<aui:field-wrapper>
	<aui:column >
		<div>
			<%= ReleaseInfo.getReleaseInfo() %><br />

			<%
				long uptimeDiff = System.currentTimeMillis() - PortalUtil.getUptime().getTime();
				long days = uptimeDiff / Time.DAY;
				long hours = (uptimeDiff / Time.HOUR) % 24;
				long minutes = (uptimeDiff / Time.MINUTE) % 60;
				long seconds = (uptimeDiff / Time.SECOND) % 60;

				NumberFormat numberFormat = NumberFormat.getInstance();

				numberFormat.setMaximumIntegerDigits(2);
				numberFormat.setMinimumIntegerDigits(2);
			%>

			<liferay-ui:message key="uptime" />:

			<c:if test="<%= days > 0 %>">
				<%= days %> <%= LanguageUtil.get(pageContext, ((days > 1) ? "days" : "day")) %>,
			</c:if>

			<%= numberFormat.format(hours) %>:<%= numberFormat.format(minutes) %>:<%= numberFormat.format(seconds) %>

			<br /><br />
		</div>
	</aui:column>
	<c:if test="<%=showPermissionsURL%>">
		<div class="rightDiv">
			<liferay-security:permissionsURL modelResource="<%=modelResource%>"
				modelResourceDescription="<%=HtmlUtil.escape(modelResourceDescription) %>"
				resourcePrimKey="<%=resourcePrimKey%>" var="permissionsURL" />
			<button onclick="location.href='<%=permissionsURL%>'">
				<liferay-ui:icon image="permissions" />
				<liferay-ui:message key="permissions" />
			</button>
		</div>
	</c:if>
</aui:field-wrapper>

<!-- LR Portal information -->

<!-- permission -->


<!-- baMainTabs -->
<liferay-ui:tabs param="baMainTabs"
	names="installed-patches,layout-importer,document-importer,log-files,log-levels,installed-plugins,bosch-plugins,server-administration,cache-viewer,advanced-tools,user"
	url="<%=portletURL.toString() %>" />

<!-- reused actionURL -->
<%-- <portlet:actionURL name="getFileAction" var="getFileActionURL" />
<portlet:resourceURL id="getTableDetailsResource" var="getTableDetailsResourceURL" /> --%>

<c:choose>
	<c:when test='<%=baMainTabs.equals(StringPool.BLANK)||baMainTabs.equals("installed-patches") %>'>
		<c:choose>
			<c:when
				test="<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_INSTALLED_PATCHES_TAB) %>">
				<liferay-util:include page="/html/boschadminportlet/installed_patches.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<%--<c:when test='<%=baMainTabs.equals("portlet-bridge") %>'>
		<c:choose>
			<c:when
				test="<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_PORTLET_BRIDGE_TAB) %>">
				<liferay-util:include page="/html/boschadminportlet/portlet_bridge.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>--%>
	<c:when test='<%=baMainTabs.equals("layout-importer") %>'>
		<c:choose>
			<c:when
				test="<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_LAYOUT_IMPORTER_TAB) %>">
				<liferay-util:include page="/html/boschadminportlet/layout_importer.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("document-importer") %>'>
		<c:choose>
			<c:when
				test="<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_DOCUMENT_IMPORTER_TAB) %>">
				<liferay-util:include page="/html/boschadminportlet/document_importer.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("log-files") %>'>
		<c:choose>
			<c:when
				test="<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_LOG_FILES_TAB) %>">
				<liferay-util:include page="/html/boschadminportlet/log_files.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("log-levels") %>'>
		<c:choose>
			<c:when test="<%= BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_LOG_LEVELS_TAB) %>">
				<liferay-util:include page="/html/boschadminportlet/log_level.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("installed-plugins") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_INSTALLED_PLUGIN_TAB) %>'>
				<liferay-util:include page="/html/boschadminportlet/installed_plugin.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("bosch-plugins") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_BOSCH_PLUGINS) %>'>
				<liferay-util:include page="/html/boschadminportlet/bosch_plugins.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("server-administration") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_SERVER_ADMINISTRATION_TAB) %>'>
				<liferay-util:include page="/html/boschadminportlet/server_administration.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("cache-viewer") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_CACHE_VIEWER_TAB) %>'>
				<liferay-util:include page="/html/boschadminportlet/cache_viewer.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>

	</c:when>
	<c:when test='<%=baMainTabs.equals("server-administration") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_SERVER_ADMINISTRATION_TAB) %>'>
				<liferay-util:include page="/html/boschadminportlet/server_administration.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("advanced-tools") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_ADVANCED_TOOLS) %>'>
				<liferay-util:include page="/html/boschadminportlet/advanced_tools.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%=baMainTabs.equals("user") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_USER_TAB) %>'>
				<liferay-util:include page="/html/boschadminportlet/user.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<%-- <c:when test='<%=baMainTabs.equals("bosch-commons") %>'>
		<c:choose>
			<c:when
				test='<%=BAPermission.contains(permissionChecker, BAPropsValues.BA_RESOURCE, scopeGroupId, BAActionKeys.VIEW_BOSCH_COMMONS) %>'>
				<liferay-util:include page="/html/boschadminportlet/bosch_commons.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/boschadminportlet/tab_access_denied.jsp" servletContext="<%=this.getServletContext() %>"/>
			</c:otherwise>
		</c:choose>
	</c:when>--%>
</c:choose>

<script>
	var ba_namespace = '<portlet:namespace/>';
</script>

<script type="text/javascript"
	src="<%=BAConstants.VIEW_JS%>">
</script>