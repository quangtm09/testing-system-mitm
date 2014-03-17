<%@include file="/html/init.jsp" %>

<div class="portlet-msg-alert">
	<liferay-ui:message key="warning-message"/>
</div>

<liferay-ui:success key="generate-thread-dump-success" message="generate-thread-dump-success" />
<liferay-ui:error key="generate-thread-dump-fail" message="generate-thread-dump-fail" />

<liferay-ui:success key="generate-heap-dump-success" message="generate-heap-dump-success" />
<liferay-ui:error key="generate-heap-dump-fail" message="generate-heap-dump-fail" />

<c:if test="<%=ParamUtil.getBoolean(request, \"isGeneratingSuccessfully\", false) %>">
	<div class="portlet-msg-info">
		<liferay-ui:message key="click-here-to-render-to"/><aui:a href="<%=renderToLogFilesTab%>" label="log-files-tab"></aui:a>
	</div>
</c:if>

<portlet:actionURL name="submitAdvToolsForm" var="submitAdvToolsFormURL" windowState="normal" />

<aui:form action="<%=submitAdvToolsFormURL.toString()%>" name="advToolsForm" method="POST">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<table>
		<tr>
			<td><aui:button name="<%=BAConstants.GENERATE_THREAD_DUMP%>" value="generate-thread-dump" type="button"/></td>
			<td>&nbsp;<liferay-ui:message key="thread-dump-info-message"/></td>
		</tr>
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
		<tr>
			<td><aui:button name="<%=BAConstants.GENERATE_HEAP_DUMP%>" value="generate-heap-dump" type="button"/></td>
			<td>&nbsp;<liferay-ui:message key="heap-dump-info-message"/></td>
		</tr>
	</table>
	
	
		
	
</aui:form>

<script>
	var ba_namespace = '<portlet:namespace/>';
</script>
<%@ include file="/html/boschadminportlet/advanced_tools_js.jspf" %>

<script type="text/javascript" src="<%=BAConstants.VIEW_JS%>"></script>