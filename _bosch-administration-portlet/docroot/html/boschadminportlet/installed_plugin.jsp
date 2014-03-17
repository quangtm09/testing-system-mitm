<%@include file="/html/init.jsp" %>
<%
	String baIpTabs = ParamUtil.getString(request, "baIpTabs", StringPool.BLANK);
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	portletURL.setParameter("baIpTabs", baIpTabs);
	
	String serviceName = ParamUtil.getString(request, "serviceName");
	
	//iteratorURL.
%>

<liferay-ui:tabs param="baIpTabs"
	names="installed-plugins,service-administration"
	url="<%=portletURL.toString() %>" />

<liferay-ui:panel-container extended="<%= true %>" id="installedPluginPanelContainer" persistState="<%= true %>">
	<c:choose>
		<c:when test='<%=baIpTabs.equals(StringPool.BLANK) || baIpTabs.equals("installed-plugins") %>'>
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
				helpMessage="installed-plugins-help-message"
				title="installed-plugins-title"
				id="installedPluginPanel"
				>
				
				<portlet:actionURL name="renderToPluginFilePageAction"
					var="renderToPluginFilePageActionURL">
				</portlet:actionURL>
				<aui:form name="installedPluginForm" method="POST"
					action="<%=renderToPluginFilePageActionURL.toString()%>">
					
					<div class="portlet-msg-info">
						<liferay-ui:message key="installed-plugins-info-source"></liferay-ui:message>
					</div>
					
					<%List<BAItemModel> installedPluginList = BAActionUtil.getInstalledPlugins(false);%>
					<liferay-ui:search-container
						curParam="installedPluginParam" 
						iteratorURL="<%=iteratorURL%>"
						emptyResultsMessage="there-are-no-installed-plugins" delta="20">
						<liferay-ui:search-container-results
							results = "<%=ListUtil.subList(installedPluginList, searchContainer.getStart(),searchContainer.getEnd())%>"
							total = "<%=installedPluginList.size()%>"/>
			
			
						<liferay-ui:search-container-row
							className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
			
							<liferay-ui:search-container-column-text
								name="installed-plugin-name">
								<a href="#"
									onclick="<portlet:namespace/>submitInstalledPluginForm('<%=item.getItemId()%>','<%=item.getItemId() %>');return false;">
									<%=item.getItemId() %></a>
							</liferay-ui:search-container-column-text>
			
						</liferay-ui:search-container-row>
						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
			
					<aui:input name="installedPluginContext" type="hidden"></aui:input>
					<aui:input name="installedPluginName" type="hidden"></aui:input>
				</aui:form>
			</liferay-ui:panel>
		</c:when>
		<c:when test='<%=baIpTabs.equals("service-administration") %>'>
			<liferay-ui:panel collapsible="<%= true %>"
				helpMessage="service-administration-help-message"
				title="service-administration-title" id="serviceAdministrationPanel"
				extended="<%= true %>" persistState="<%= true %>">
				
				<portlet:actionURL name="submitIPForm" var="submitIPFormURL"
					windowState="normal" />
				<aui:form action="<%=submitIPFormURL.toString() %>" name="iPForm" method="POST">
				
					<div id="toggle_id_service_advanced_search" style="margin-bottom:10px;" valign="top">						
						<aui:field-wrapper>
							<aui:column cssClass="liLabelForInput">
								<label for="type"><liferay-ui:message key="service-name"/></label>
							</aui:column>
							<aui:column>
								<aui:input inlineField="<%=true%>" name="serviceName" label=""
									 type="text" value="<%=serviceName%>" cssClass="liSearchName" />
							</aui:column>
						</aui:field-wrapper>
						
						<aui:field-wrapper>
							<aui:column columnWidth="25">
								<aui:button type="submit" value="search"/>
							</aui:column>
							<aui:column>
							</aui:column>
						</aui:field-wrapper>
					</div>
					
					<div class="separator"><!-- --></div>

					<aui:input name="resetServiceComponents" type="hidden"/>
					<aui:input name="<%= Constants.CMD %>" type="hidden" />		
					
					<liferay-ui:search-container 
						rowChecker="<%= rowChecker %>"
						iteratorURL="<%=iteratorURL%>"
						curParam="serviceComponentsParam"
						id="serviceComponentsSearchContainer"
						emptyResultsMessage="there-are-no-service-components" 
						delta="10">
				
						<%
							List<ServiceComponent> serviceComponentList = BAActionUtil.getServices(serviceName, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						%>
						
						<liferay-ui:search-container-results 
							results="<%=ListUtil.subList(serviceComponentList, searchContainer.getStart(),searchContainer.getEnd()) %>"
							total="<%=serviceComponentList.size() %>"/>
				
						<liferay-ui:search-container-row keyProperty="serviceComponentId"
							className="com.liferay.portal.model.ServiceComponent" modelVar="serviceComponent">
							<liferay-ui:search-container-column-text name="service-name" property="buildNamespace"/>
							<liferay-ui:search-container-column-text name="build-number" property="buildNumber"/>
							<%
								boolean isDateMeaningful = serviceComponent.getBuildDate() > 0;
							%>
							<c:choose>
								<c:when test="<%= isDateMeaningful%>">
									<liferay-ui:search-container-column-text name="build-date" value="<%=dateFormatDateTime.format(serviceComponent.getBuildDate()) %>"/>
								</c:when>
								<c:otherwise>
									<liferay-ui:search-container-column-text name="build-date" value="N/A"/>
								</c:otherwise>
							</c:choose>
							
						</liferay-ui:search-container-row>
						
						<liferay-ui:search-iterator/>
					</liferay-ui:search-container>
					
					<aui:button-row>
						<aui:button value="reset-build-number" onClick="<%= renderResponse.getNamespace() + \"resetBuildNumber(\'\"+ BAConstants.RESET_BUILD_NUMBER + \"\')\" %>" />
						<aui:button value="remove-service-component" onClick="<%= renderResponse.getNamespace() + \"removeServiceComponent(\'\"+ BAConstants.REMOVE_SERVICE_COMPONENT + \"\')\" %>" />
						<aui:button value="cancel" onClick="<%= renderResponse.getNamespace() + \"resetIPFunction()\" %>" />
					</aui:button-row>
				</aui:form>
			</liferay-ui:panel>
			
			<!-- focus to serviceName input field -->
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />iPForm.<portlet:namespace />serviceName);
			</aui:script>
		</c:when>
	</c:choose>
</liferay-ui:panel-container>

<%@ include file="/html/boschadminportlet/installed_plugin_js.jspf" %>