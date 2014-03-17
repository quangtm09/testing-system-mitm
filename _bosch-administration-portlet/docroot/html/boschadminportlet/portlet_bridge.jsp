<%@include file="/html/init.jsp" %>
<%
boolean isPortletBridge1Deployed = BAActionUtil.isPortletBridge1Deployed();
boolean isPortletBridge2Deployed = BAActionUtil.isPortletBridge2Deployed();
%>

<c:choose>
	<c:when test="<%=!isPortletBridge1Deployed && !isPortletBridge2Deployed%>">
		<liferay-ui:panel-container extended="<%= true %>" id="portletBridgePanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
							helpMessage="portlet-bridge-help-message" title="portlet-bridge-title" id="portletBridgePanel">
				<div class="portlet-msg-error">
					<liferay-ui:message key="no-deploy-pb-plugin"></liferay-ui:message>
				</div>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</c:when>
	<c:when test="<%=isPortletBridge1Deployed && isPortletBridge2Deployed%>">
		<liferay-ui:panel-container extended="<%= true %>" id="portletBridgePanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
							helpMessage="portlet-bridge-help-message" title="portlet-bridge-title" id="portletBridgePanel">
				<div class="portlet-msg-error">
					<liferay-ui:message key="both-deploy-pb-plugin"></liferay-ui:message>
				</div>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</c:when>
	<c:otherwise>
	
		<c:choose>
			<c:when test="<%=isPortletBridge1Deployed%>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="these-functionalities-come-from-portlet-bridge-1"/>
				</div>
					<liferay-ui:success key="allCachesCleared"
						message="all-caches-cleared-successfully" />
					<liferay-ui:error key="you-cannot-delete-layouts"
						message="you-cannot-delete-layouts" />
						
					<liferay-ui:panel-container extended="<%= true %>" id="portletBridgePanelContainer" persistState="<%= true %>">
					<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
						helpMessage="cache-block-help-message" title="cache-clearing-title" id="portletBridgePanel">
				
						<portlet:actionURL name="clearAllCaches" var="clearAllCacheURL"
							windowState="normal" />
						<aui:form action="<%=clearAllCacheURL.toString()%>"
							name="clearAllCacheForm" method="post">
							<aui:button-row cssClass="serefButtonRow">
								<center>
									<aui:button name="submit" type="submit"
										value="clearAllCacheButton"></aui:button>
								</center>
							</aui:button-row>
						</aui:form>
				
					</liferay-ui:panel>
					
					<%
					String orderByColXmlConfigKey = "orderByColXmlConfig";
					String orderByTypeXmlConfigKey = "orderByTypeXmlConfig";
			
					String orderByColXmlConfig = ParamUtil.getString(request, orderByColXmlConfigKey);
					String orderByTypeXmlConfig = ParamUtil.getString(request, orderByTypeXmlConfigKey);
			
					if (Validator.isNotNull(orderByColXmlConfig ) && Validator.isNotNull(orderByTypeXmlConfig )) { 
						prefs.setValue(orderByColXmlConfigKey, orderByColXmlConfig); 
						prefs.setValue(orderByTypeXmlConfigKey, orderByTypeXmlConfig); 
					} else { 
					    orderByColXmlConfig = prefs.getValue(orderByColXmlConfigKey, "itemName");
					    orderByTypeXmlConfig = prefs.getValue(orderByTypeXmlConfigKey, "asc");   
					}
					
				    List<BAItemModel> xmlConfigFileList = BAActionUtil.getFilesFromDirectory(System.getProperty("liferay.boschportletbridge.config.root"),"**/*.*", null, null);
					Collections.sort(xmlConfigFileList,BAActionUtil.getItemNameDateOrderByComparator(orderByColXmlConfig, orderByTypeXmlConfig));
					%>
				
					<liferay-ui:panel collapsible="true" id="xmlConfigPanel"
							helpMessage="xml-config-help-message" title="xml-config-title">
							
							<aui:form name="fileForm" method="POST"
								action="<%=getFileActionURL.toString()%>">
					
								<liferay-ui:search-container 
									iteratorURL="<%=iteratorURL%>"
									curParam="xmlConfigParam"
									emptyResultsMessage="there-are-no-xml-config" delta="20"
									orderByCol="<%=orderByColXmlConfig%>"
									orderByType="<%=orderByTypeXmlConfig%>"
									orderByTypeParam="orderByTypeXmlConfig"
									orderByColParam="orderByColXmlConfig">
									<div class="portlet-msg-info">
										<liferay-ui:message key="xml-config-info-source"></liferay-ui:message>
									</div>
									<liferay-ui:search-container-results 
										results="<%= ListUtil.subList(xmlConfigFileList, searchContainer.getStart(),searchContainer.getEnd()) %>" 
										total="<%= xmlConfigFileList.size() %>"/>
					
									<liferay-ui:search-container-row
										className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
					
										<liferay-ui:search-container-column-text
											name="xml-config-file-name" orderable="<%= true %>"
											orderableProperty="itemName">
											<a href="#"
												onclick="<portlet:namespace/>getFile('<c:out value="<%=item.getItemValue()%>"/>');return false;">
												<c:out value="<%=item.getItemName()%>" />
											</a>
										</liferay-ui:search-container-column-text>
					
										<liferay-ui:search-container-column-text
											name="last-modified-date" orderable="<%= true %>"
											orderableProperty="itemDate"
											value="<%=dateFormatDateTime.format(item.getItemDate()) %>" />
					
									</liferay-ui:search-container-row>
					
									<liferay-ui:search-iterator />
					
								</liferay-ui:search-container>
					
								<aui:input name="fileKey" type="hidden"></aui:input>
							</aui:form>
						</liferay-ui:panel>
					
				</liferay-ui:panel-container>
			</c:when>
			<c:when test="<%=isPortletBridge2Deployed%>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="these-functionalities-come-from-portlet-bridge-2"/>
				</div>
				<liferay-ui:panel-container extended="<%= true %>" id="portletBridgePanelContainer" persistState="<%= true %>">
					<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
						title="portlet-bridge-2-title" id="portletBridgePanel">
						
						<liferay-ui:success key="set-up-config-success-msg"
						message="set-up-config-success-msg" />
						<liferay-ui:error key="set-up-config-error-msg"
							message="set-up-config-error-msg" />
						<c:if test="<%=ParamUtil.getBoolean(request, \"isSettingUpSuccessful\", false) %>">
							<div class="portlet-msg-info">
								<liferay-ui:message key="click-here-to-render-to"/><aui:a href="<%=renderToLogFilesTab%>" label="log-files-tab"></aui:a>
							</div>
						</c:if>	
						
						<portlet:actionURL name="submitPB2Form" var="submitPB2FormURL"
							windowState="normal" />
						<aui:form action="<%=submitPB2FormURL.toString()%>" name="pB2Form" method="POST">
							<aui:input name="<%= Constants.CMD %>" type="hidden" />
							<aui:button-row cssClass="serefButtonRow">
								<center>
									<aui:button name="cleanCache" value="clean-cache" type="button" onClick="<%= renderResponse.getNamespace() + \"submitPB2Form(\'\"+ BAConstants.CLEAN_CACHE + \"\')\" %>"></aui:button>
								</center>
							</aui:button-row>
							<aui:button-row cssClass="serefButtonRow">
								<center>
									<aui:button name="setUpConfig" value="set-up-config" type="button" onClick="<%= renderResponse.getNamespace() + \"submitPB2Form(\'\"+ BAConstants.SET_UP_CONFIG + \"\')\" %>"></aui:button>
								</center>
							</aui:button-row>
						</aui:form>
				
					</liferay-ui:panel>
				</liferay-ui:panel-container>
				
			</c:when>

		</c:choose>
		
	</c:otherwise>
</c:choose>
