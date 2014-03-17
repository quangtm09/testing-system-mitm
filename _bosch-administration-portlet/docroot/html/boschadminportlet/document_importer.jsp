<%@include file="/html/init.jsp" %>
<%	
	// order
	String orderByCol = "name";
	String orderByType = "asc";
	
	boolean isDI1Deployed = BAActionUtil.isDI1Deployed();
	boolean isDI2Deployed = BAActionUtil.isDI2Deployed();
%>
<portlet:actionURL name="submitDIFormAction" var="submitDIFormActionURL">
	<portlet:param name="baMainTabs" value="<%=baMainTabs%>" />
</portlet:actionURL>
<aui:form action="<%=submitDIFormActionURL.toString()%>" method="post" name="documentImporterForm">

	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="mandatorLink" type="hidden" />
	<liferay-ui:panel-container extended="<%= true %>" id="documentImporterPanelContainer" persistState="<%= true %>">

		<c:choose>
			<c:when test="<%=!isDI1Deployed && !isDI2Deployed%>">
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
							title="document-importer" id="documentImporterPanel">
					<div class="portlet-msg-error">
						<liferay-ui:message key="no-deploy-di-plugin"></liferay-ui:message>
					</div>
				</liferay-ui:panel>
			</c:when>
			<c:when test="<%=isDI1Deployed && isDI2Deployed%>">
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
							title="document-importer" id="documentImporterPanel">	
					<div class="portlet-msg-error">
						<liferay-ui:message key="both-deploy-di-plugin"></liferay-ui:message>
					</div>
				</liferay-ui:panel>
			</c:when>
			<c:otherwise>
			
				<c:choose>
					<c:when test="<%=isDI1Deployed%>">
						<div class="portlet-msg-info">
							<liferay-ui:message key="these-functionalities-come-from-document-importer-1"/>
						</div>
							
						<liferay-ui:panel collapsible="<%= true %>"
							helpMessage="mandator-document-importer-help-message"
							title="mandator-document-importer-title"
							id="mandatorDocumentImporterPanel"
							extended="<%= true %>"
							persistState="<%= true %>"
							>
						<%List<BAItemModel> mandatorList = BAActionUtil.getMandatorsFromDIPortlet();%>
						<liferay-ui:search-container iteratorURL="<%=iteratorURL%>" curParam="diMandatorsParam"
							emptyResultsMessage="there-are-no-mandators" delta="10">
								
							<liferay-ui:search-container-results 
								results="<%=ListUtil.subList(mandatorList, searchContainer.getStart(),searchContainer.getEnd()) %>"
								total="<%=mandatorList.size() %>"/>
					
							<liferay-ui:search-container-row
								className="com.bosch.boschadministration.model.BAItemModel"
								modelVar="item">
								<liferay-ui:search-container-column-text
									name="name" property="itemName"></liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-text name="resource-link">
									<%
									String navLink = item.getItemValue();
									String navLinkParam = null;
									if(!HttpUtil.hasProtocol(navLink)){
									    navLinkParam = navLink.replace(StringPool.BACK_SLASH,"\\\\");
									}
									%>
									<a href="#" onclick="<portlet:namespace/>downloadXmlFile('<portlet:namespace/>documentImporterForm','<%= BAConstants.DOWNLOAD_FILE %>','<%=navLinkParam %>'); return false;">
										<%=navLink %>
									</a>
								</liferay-ui:search-container-column-text>
							</liferay-ui:search-container-row>
							
							<liferay-ui:search-iterator/>
								
						</liferay-ui:search-container>
					</liferay-ui:panel>
					</c:when>
					<c:when test="<%=isDI2Deployed%>">
						<!-- LRD-1468: Temporarily disable DI2 -->
						<%-- <liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
							title="document-importer-2-title" id="documentImporter2Panel">
							<div class="portlet-msg-info">
								<liferay-ui:message key="these-functionalities-come-from-document-importer-2"/>
							</div>
							<liferay-ui:success key="set-up-config-success-msg"
							message="set-up-config-success-msg" />
							<liferay-ui:error key="set-up-config-error-msg"
								message="set-up-config-error-msg" />
							<c:if test="<%=ParamUtil.getBoolean(request, \"isSettingUpSuccessful\", false) %>">
								<div class="portlet-msg-info">
									<liferay-ui:message key="click-here-to-render-to"/><aui:a href="<%=renderToLogFilesTab%>" label="log-files-tab"></aui:a>
								</div>
							</c:if>	
							
							<aui:button-row cssClass="serefButtonRow">
								<center>
									<aui:button name="setUpConfig" value="set-up-config" type="button" onClick="<%= renderResponse.getNamespace() + \"submitDIForm(\'\"+ BAConstants.SET_UP_CONFIG + \"\')\" %>"></aui:button>
								</center>
							</aui:button-row>
						</liferay-ui:panel> --%>
						<div class="portlet-msg-error">
							<liferay-ui:message key="temporarily-disable-di2"/>
						</div>
						
					</c:when>
				</c:choose>
				
			</c:otherwise>
		</c:choose>
	
	</liferay-ui:panel-container>
</aui:form>