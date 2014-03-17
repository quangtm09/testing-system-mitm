<%@include file="/html/init.jsp" %>
<%
	List<BAItemModel> availableSBPlugins = BAActionUtil.getAvailableSBPlugins();
%>

<div class="portlet-msg-info">
	<liferay-ui:message key="manage-bosch-plugins"/>
</div>

<portlet:actionURL name="submitBPForm" var="submitBPFormURL" windowState="normal" />
<aui:form action="<%=submitBPFormURL.toString()%>" name="bPForm" method="POST">
	<aui:input name="pluginName" type="hidden" />		
	<aui:input name="className" type="hidden" />
	<aui:input name="methodName" type="hidden" />
	
	<c:choose>
		<c:when test="<%=availableSBPlugins.size() > 0 %>">
			<liferay-ui:panel-container extended="<%= true %>" id="boschPluginsPanelContainer" persistState="<%= true %>" accordion="true">
				<%
					for(BAItemModel sbPlugin: availableSBPlugins){
						final String pluginName = sbPlugin.getItemId();
						
						final ClassLoader classLoader = ClassLoaderPool.getClassLoaderByContextName(pluginName);
						final Configuration conf = ConfigurationFactoryUtil.getConfiguration(classLoader, BAConstants.BOSCH_ADMINISTRATION);
						
						final String panelTitle = pluginName + BAConstants.TITLE;
						final String panelId = pluginName + BAConstants.PANEL;
						final String helpMessage = pluginName + BAConstants.HELP_MESSAGE;
						final String setupConfigSuccess = pluginName + BAConstants.SUCCESS_MESSAGE;
						final String setupConfigError = pluginName + BAConstants.ERROR_MESSAGE;
				%>
				
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
					title="<%=pluginName%>" id="<%=panelId %>" helpMessage="<%=helpMessage%>" defaultState="closed">
					
					<liferay-ui:error key="<%=setupConfigError%>" message="<%=setupConfigError%>" />
					
					<br/>
					<table>
						
					<%
						for (final String className : conf.getArray(BAConstants.SERVICE_CLASS_NAMES)) {
							for (final String methodName : conf.getArray(className)) {
								final String methodDescription = conf.get(className + StringPool.PERIOD + methodName);				
					%>
					
						<tr>
							<td><aui:button name="<%=methodName%>" value="<%=methodName%>" type="button" 
									onClick="<%= renderResponse.getNamespace() + \"submitBPForm(\'\"+ pluginName + \"\',\'\" + className + \"\',\'\" + methodName +\"\')\" %>"/></td>
							<td>&nbsp;<%=methodDescription %></td>
						</tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>			
					
					<%
							}
						}		
					%>
					
					</table>
				</liferay-ui:panel>
				
				<%
					}
				%>
			</liferay-ui:panel-container>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-alert">
				<liferay-ui:message key="no-sb-plugins-available"/>
			</div>
		</c:otherwise>
	</c:choose>
</aui:form>