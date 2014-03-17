<%@include file="/html/init.jsp" %>

<% 
	String baLogLevelTabs = ParamUtil.getString(request, "baLogLevelTabs", StringPool.BLANK); 
	portletURL.setParameter("baLogLevelTabs", baLogLevelTabs); 
	
%>
<liferay-ui:tabs param="baLogLevelTabs"
	names="update-categories,add-category"
	url="<%=portletURL.toString()%>" />
<portlet:actionURL name="submitLogLevelFormAction" var="submitLogLevelFormURL">
	<portlet:param name="baMainTabs" value="<%=baMainTabs%>" />
	<portlet:param name="baLogLevelTabs" value="<%=baLogLevelTabs%>" />
</portlet:actionURL>

<aui:form name="logLevelForm" method="POST" action="<%= submitLogLevelFormURL.toString() %>">
	<liferay-ui:panel-container extended="<%= true %>" id="logLevelPanelContainer" persistState="<%= true %>">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<c:choose>
			<c:when test='<%= baLogLevelTabs.equals(StringPool.BLANK) || baLogLevelTabs.equals("update-categories") %>'>

				<liferay-ui:panel collapsible="<%= true %>"
					helpMessage="log-level-help-message"
					title="log-level-title"
					extended="<%= true %>"
					persistState="<%= true %>" 
					id="logLevelsPanel"
					>
					
					<% String keywords = ParamUtil.getString(request, "keywords"); %>
					
					<div class="leftDiv">
						<liferay-ui:icon-help message="search-file-tooltip" />
					</div>
					
					<%--<aui:input inlineField="<%=true%>" label="" name="keywords" 
						title="search-files" type="text" value="<%=keywords%>" size="100" cssClass=""/>--%>
						
					<input type="text" value="" title="search-files" style="width: 629.2px" name="<%=renderResponse.getNamespace()%>keywords" 
						id="<%=renderResponse.getNamespace()%>keywords" class="aui-field-input aui-field-input-text">	
					
					<aui:button type="submit" value="search" onClick="<%= renderResponse.getNamespace() + \"submitLogLevelForm(\'\"+ Constants.SEARCH + \"\')\" %>" cssClass="rightDiv"/>	
						
							
					<%
					String orderByColLogLevelKey = "orderByColLogLevel";
					String orderByTypeLogLevelKey = "orderByTypeLogLevel";
					String orderByColLogLevel = ParamUtil.getString(request, orderByColLogLevelKey);
					String orderByTypeLogLevel = ParamUtil.getString(request, orderByTypeLogLevelKey);
					if (Validator.isNotNull(orderByColLogLevel ) && Validator.isNotNull(orderByTypeLogLevel )) { 
						prefs.setValue(orderByColLogLevelKey, orderByColLogLevel); 
						prefs.setValue(orderByTypeLogLevelKey, orderByTypeLogLevel); 
					} else { 
					    orderByColLogLevel = prefs.getValue(orderByColLogLevelKey, "itemName");
					    orderByTypeLogLevel = prefs.getValue(orderByTypeLogLevelKey, "desc");   
					}
					
						
					%>
					<liferay-ui:search-container iteratorURL="<%=iteratorURL%>" curParam="logLevelsCurParam"
						orderByCol="<%=orderByColLogLevel%>"
						orderByType="<%=orderByTypeLogLevel%>"
						orderByTypeParam="orderByTypeLogLevel"
						orderByColParam="orderByColLogLevel"
						emptyResultsMessage="there-are-no-log-level" delta="20">
						
						<%
						List<BAItemModel> logLevelList = BAActionUtil.getLogFileLevel(keywords);
						Collections.sort(logLevelList,BAActionUtil.getItemNameValueIdOrderByComparator(orderByColLogLevel, orderByTypeLogLevel));
						%>
						
						<liferay-ui:search-container-results 
							results="<%=ListUtil.subList(logLevelList, searchContainer.getStart(),searchContainer.getEnd()) %>"
							total="<%=logLevelList.size() %>"/>
						<liferay-ui:search-container-row
							className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
							
							<%
								String itemId = item.getItemId();
							%>
							
							<liferay-ui:search-container-column-text name="category" property="itemName" orderable="<%= true %>" orderableProperty="itemName"/>
				
							<liferay-ui:search-container-column-text name="level" orderable="<%= true %>" orderableProperty="itemValue" >
								<aui:select name="<%=\"logLevel\"+ item.getItemName() %>" label="" >
									<%
									for (int j = 0; j < Levels.ALL_LEVELS.length; j++) {
									    if (item.getItemValue().equals(Levels.ALL_LEVELS[j].toString())) {
							        %>
							        <aui:option value="<%=Levels.ALL_LEVELS[j] %>" selected="<%=true%>" label="<%= Levels.ALL_LEVELS[j] %>"/>
							        <%        
									    }else{
									%>
									<aui:option value="<%=Levels.ALL_LEVELS[j] %>" label="<%= Levels.ALL_LEVELS[j] %>"/>
									<%        
									    }
									}
									%>
								</aui:select>
								<aui:input type="hidden" name="<%=\"logLevel\"+ item.getItemName() + \"portletId\"%>" value="<%=itemId%>"/>
							</liferay-ui:search-container-column-text>
							
							<liferay-ui:search-container-column-text name="plugin" value="<%= itemId  %>" orderable="<%= true %>" orderableProperty="itemId"/>
				
						</liferay-ui:search-container-row>
				
						<liferay-ui:search-iterator />
				
					</liferay-ui:search-container>
					
					<aui:button-row>
						<aui:button value="save" onClick="<%= renderResponse.getNamespace() + \"submitLogLevelForm(\'\"+ BAConstants.UPDATE_CATEGORIES + \"\')\" %>" />
					</aui:button-row>
					
				</liferay-ui:panel>
				
				<!-- focus to search input field -->
				<aui:script>
					Liferay.Util.focusFormField(document.<portlet:namespace />logLevelForm.<portlet:namespace />keywords);
				</aui:script>
			</c:when>
			<c:when test='<%= baLogLevelTabs.equals("add-category") %>'>
			<liferay-ui:panel collapsible="<%= true %>"
					helpMessage="log-level-help-message"
					title="log-level-title"
					extended="<%= true %>"
					persistState="<%= true %>" 
					id="logLevelsPanel"
					>
				<aui:fieldset>
				
					<%
						List<BAItemModel> installedPlugins = BAActionUtil.getInstalledPlugins(true);
						pageContext.setAttribute("installedPlugins", installedPlugins);
					%>
					<aui:select label="plugin" name="logLevelServletContextName">
						
						<c:forEach var="baItemModel" items="${installedPlugins}">
							<aui:option label="${baItemModel.itemId}"  value="${baItemModel.itemId}" />
							<c:out value="${baItemModel.itemId}" />
						</c:forEach>  

					</aui:select>
					<aui:input cssClass="lfr-input-text-container" label="category" name="loggerName" type="text" />
					<aui:select label="priority" name="priority">
					<%
						for (int i = 0; i < Levels.ALL_LEVELS.length; i++) {
					%>

						<aui:option label="<%= Levels.ALL_LEVELS[i] %>" selected="<%= Level.INFO.equals(Levels.ALL_LEVELS[i]) %>" />

					<%
						}
					%>
					</aui:select>
				</aui:fieldset>

				<aui:button-row>
					<aui:button value="save" onClick="<%= renderResponse.getNamespace() + \"submitLogLevelForm(\'\"+ BAConstants.ADD_CAGEGORY + \"\')\" %>" />
				</aui:button-row>
			</liferay-ui:panel>
			</c:when>
		</c:choose>
		
	</liferay-ui:panel-container>
</aui:form>




