<%@include file="/html/init.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=BAConstants.PLUGIN_FILES_CSS%>">

<%
    String keywords = ParamUtil.getString(request, "keywords");
	String installedPluginContext =  ParamUtil.getString(request, "installedPluginContext");
	String installedPluginName =  ParamUtil.getString(request, "installedPluginName");
	
	iteratorURL = renderResponse.createRenderURL(); 
    iteratorURL.setParameter(BAConstants.JSP_PAGE, BAConstants.PLUGIN_FILES_JSP);
    iteratorURL.setParameter("installedPluginContext",installedPluginContext);
    iteratorURL.setParameter("installedPluginName",installedPluginName);
    iteratorURL.setParameter("keywords", keywords);
%>


<portlet:renderURL var="backURL">
	<portlet:param name="<%=BAConstants.JSP_PAGE%>"
		value="<%=BAConstants.VIEW_JSP%>" />
	<portlet:param name="baMainTabs" value="installed-plugins" />
</portlet:renderURL>
<liferay-ui:header backURL="<%=backURL%>" 
	title="<%=installedPluginName%>" />

<portlet:actionURL name="submitPluginFileFormAction"
	var="submitPluginFileFormActionURL">
	<portlet:param name="installedPluginContext"
		value="<%=installedPluginContext%>" />
	<portlet:param name="installedPluginName"
		value="<%=installedPluginName%>" />
</portlet:actionURL>
<aui:form name="pluginFileForm" method="POST"
	action="<%=submitPluginFileFormActionURL.toString()%>">

	<%
	String plugindir = BAActionUtil.getAutoDeployDestDir() + StringPool.SLASH +installedPluginContext;
	String pluginExcludes = BAPropsValues.RESTRICTED_EXTENSION;
	if(Validator.isNotNull(pluginExcludes)){
	    pluginExcludes = "**/*." + pluginExcludes;
	    pluginExcludes = pluginExcludes.replace(StringPool.COMMA, ",**/*.");   
	}
    List<BAItemModel> pluginFileList = BAActionUtil.getFilesFromDirectory(plugindir,"**/*.*", pluginExcludes,keywords);
	%>
	
	<liferay-ui:search-container 
		id="pluginFileScID"	
		curParam="pluginFileScCurParam"
		iteratorURL="<%=iteratorURL%>"
		emptyResultsMessage="there-are-no-files" delta="20">

		<liferay-ui:search-container-results
			results = "<%=ListUtil.subList(pluginFileList, searchContainer.getStart(),searchContainer.getEnd())%>"
			total = "<%=pluginFileList.size()%>"/>
		
		<div class="rightDiv">
			<liferay-ui:icon-help message="search-file-tooltip" />
			<aui:input inlineField="<%=true%>" label="" name="keywords" 
				title="search-files" type="text" value="<%=keywords%>" />
			
			<aui:button type="submit" value="search" onClick="<%= renderResponse.getNamespace() + \"submitPluginForm(\'\',\'\"+ Constants.SEARCH + \"\')\" %>" />	
				
		</div>

		<liferay-ui:search-container-row className="com.bosch.boschadministration.model.BAItemModel"
			modelVar="item">
			
			<liferay-ui:search-container-column-text name="file-name">
				<a href="#"
					onclick="<portlet:namespace/>submitPluginForm('<%=item.getItemValue()%>','<%=BAConstants.DOWNLOAD_FILE%>');return false;">
					<%=item.getItemName() %></a>
			</liferay-ui:search-container-column-text>
			
			<liferay-ui:search-container-column-text name="last-modified-date"
							value="<%=dateFormatDateTime.format(item.getItemDate()) %>" />
							
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />

	</liferay-ui:search-container>

	<aui:input name="fileKey" type="hidden"/>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	
</aui:form>


<script type="text/javascript">
		
	AUI().ready('node', function(A) {
		//set focus and selection on search text
		A.one('#'+ba_namespace+'keywords').focus();
		A.one('#'+ba_namespace+'keywords').select();
	});
	
	// remove successful message
	var msgSuccess= document.getElementsByClassName('portlet-msg-success');
	var len = msgSuccess.length;
	for(var i = 0; i < len; i++) {
        msgSuccess[i].parentNode.removeChild(msgSuccess[i]);
	}
	
	var ba_namespace = "<portlet:namespace/>";
</script>

<script type="text/javascript" src="<%=BAConstants.PLUGIN_FILES_JS%>">
</script>

