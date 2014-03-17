<%@include file="/html/init.jsp" %>
<%
	String baLogFileTabs = ParamUtil.getString(request, "baLogFileTabs", StringPool.BLANK);
	portletURL.setParameter("baLogFileTabs", baLogFileTabs);
%>

<liferay-ui:tabs param="baLogFileTabs"
	names="liferay-log,tomcat-log,both-log"
	url="<%=portletURL.toString()%>" />

<aui:form name="fileForm" method="POST" action="<%=getFileActionURL.toString()%>">
	<liferay-ui:panel-container extended="<%= true %>" id="logFilesPanelContainer" persistState="<%= true %>">
		<c:choose>
			<c:when test='<%=baLogFileTabs.equals(StringPool.BLANK) || baLogFileTabs.equals("liferay-log")%>'>
				<liferay-ui:panel 
					collapsible="<%= true %>"
					helpMessage="liferay-log-files-help-message"
					title="liferay-log-files-title"
					id="liferayLogFilesPanel"
					extended="<%= true %>"
					persistState="<%= true %>"
					>
					<%
						String orderByColLiferayKey = "orderByColLiferayLog";
						String orderByTypeLiferayKey = "orderByTypeLiferayLog";
						String orderByColLiferayLog = ParamUtil.getString(request, orderByColLiferayKey);
						String orderByTypeLiferayLog = ParamUtil.getString(request, orderByTypeLiferayKey);
				
						if (Validator.isNotNull(orderByColLiferayLog ) && Validator.isNotNull(orderByTypeLiferayLog )) { 
							prefs.setValue(orderByColLiferayKey, orderByColLiferayLog); 
							prefs.setValue(orderByTypeLiferayKey, orderByTypeLiferayLog); 
						} else { 
						    orderByColLiferayLog = prefs.getValue(orderByColLiferayKey, "itemDate");
						    orderByTypeLiferayLog = prefs.getValue(orderByTypeLiferayKey, "desc");   
						}
				
					    List<BAItemModel> liferayLogfileList = BAActionUtil.getFilesFromDirectory(System.getProperty("liferay.log.root"), "**/*.*",null, null);
						Collections.sort(liferayLogfileList,BAActionUtil.getItemNameDateOrderByComparator(orderByColLiferayLog, orderByTypeLiferayLog));
					%>
					<liferay-ui:search-container iteratorURL="<%=iteratorURL%>"
						curParam="liferayLogParam"
						emptyResultsMessage="there-are-no-log-files" delta="20"
						orderByCol="<%=orderByColLiferayLog%>"
						orderByType="<%=orderByTypeLiferayLog%>"
						orderByTypeParam="orderByTypeLiferayLog"
						orderByColParam="orderByColLiferayLog">
						<div class="portlet-msg-info">
							<liferay-ui:message key="liferay-log-files-info-source"/>
						</div>
						<liferay-ui:search-container-results
								results = "<%=ListUtil.subList(liferayLogfileList, searchContainer.getStart(),searchContainer.getEnd())%>"
								total = "<%=liferayLogfileList.size()%>"/>
				
						<liferay-ui:search-container-row
							className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
				
							<liferay-ui:search-container-column-text name="log-file-name"
								orderable="<%= true %>" orderableProperty="itemName">
								<a href="#"
									onclick="<portlet:namespace/>getFile('<c:out value="<%=item.getItemValue()%>"/>');return false;">
									<c:out value="<%=item.getItemName()%>" />
								</a>
							</liferay-ui:search-container-column-text>
				
							<liferay-ui:search-container-column-text name="last-modified-date" 
								orderable="<%= true %>" orderableProperty="itemDate"
								value="<%=dateFormatDateTime.format(item.getItemDate()) %>" />
				
						</liferay-ui:search-container-row>
				
						<liferay-ui:search-iterator />
				
					</liferay-ui:search-container>
				</liferay-ui:panel>
			</c:when>
				
			<c:when test='<%=baLogFileTabs.equals("tomcat-log")%>'>
				<liferay-ui:panel collapsible="<%= true %>"
							helpMessage="tomcat-log-files-help-message"
							title="tomcat-log-files-title"
							id="tombatLogFilesPanel"
							extended="<%= true %>"
							persistState="<%= true %>"
							>
					<%
						String orderByColTomcatKey = "orderByColTomcatLog";
						String orderByTypeTomcatKey = "orderByTypeTomcatLog";
						String orderByColTomcatLog = ParamUtil.getString(request, orderByColTomcatKey);
						String orderByTypeTomcatLog = ParamUtil.getString(request, orderByTypeTomcatKey);
						if (Validator.isNotNull(orderByColTomcatLog ) && Validator.isNotNull(orderByTypeTomcatLog )) { 
							prefs.setValue(orderByColTomcatKey, orderByColTomcatLog); 
							prefs.setValue(orderByTypeTomcatKey, orderByTypeTomcatLog); 
						} else { 
						    orderByColTomcatLog = prefs.getValue(orderByColTomcatKey, "itemDate");
						    orderByTypeTomcatLog = prefs.getValue(orderByTypeTomcatKey, "desc");   
						}
						
					    List<BAItemModel> tomcatLogfileList = BAActionUtil.getFilesFromDirectory(BAPropsValues.TOMCAT_LOG_ROOT, "**/*.*",null, null);
						Collections.sort(tomcatLogfileList,BAActionUtil.getItemNameDateOrderByComparator(orderByColTomcatLog, orderByTypeTomcatLog));
					%>
					<liferay-ui:search-container 
						iteratorURL="<%=iteratorURL%>"
						curParam="tomcatLogCurParam"
						emptyResultsMessage="there-are-no-log-files" delta="20"
						orderByCol="<%=orderByColTomcatLog%>"
						orderByType="<%=orderByTypeTomcatLog%>"
						orderByTypeParam="orderByTypeTomcatLog"
						orderByColParam="orderByColTomcatLog">
						<div class="portlet-msg-info">
							<liferay-ui:message key="tomcat-log-files-info-source"></liferay-ui:message>
						</div>
						<liferay-ui:search-container-results
								results = "<%=ListUtil.subList(tomcatLogfileList, searchContainer.getStart(),searchContainer.getEnd())%>"
								total = "<%=tomcatLogfileList.size()%>" />
				
						<liferay-ui:search-container-row
							className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
				
							<liferay-ui:search-container-column-text name="log-file-name"
								orderable="<%= true %>" orderableProperty="itemName">
								<a href="#"
									onclick="<portlet:namespace/>getFile('<c:out value="<%=item.getItemValue()%>"/>');return false;">
									<c:out value="<%=item.getItemName()%>" />
								</a>
							</liferay-ui:search-container-column-text>
				
							<liferay-ui:search-container-column-text name="last-modified-date"
								orderable="<%= true %>" orderableProperty="itemDate"
								value="<%=dateFormatDateTime.format(item.getItemDate()) %>" />
				
						</liferay-ui:search-container-row>
				
						<liferay-ui:search-iterator />
				
					</liferay-ui:search-container>
				</liferay-ui:panel>
			</c:when>
				
			<c:when test='<%=baLogFileTabs.equals("both-log")%>'>
				<liferay-ui:panel collapsible="<%= true %>"
							helpMessage="both-log-files-help-message"
							title="both-log-files-title"
							id="bothLogFilesPanel"
							extended="<%= true %>"
							persistState="<%= true %>"
							>
					<%
						String orderByColBothKey = "orderByColBothLog";
						String orderByTypeBothKey = "orderByTypeBothLog";
						String orderByColBothLog = ParamUtil.getString(request, orderByColBothKey);
						String orderByTypeBothLog = ParamUtil.getString(request, orderByTypeBothKey);
				
						if (Validator.isNotNull(orderByColBothLog ) && Validator.isNotNull(orderByTypeBothLog )) { 
							prefs.setValue(orderByColBothKey, orderByColBothLog); 
							prefs.setValue(orderByTypeBothKey, orderByTypeBothLog); 
						} else { 
						    orderByColBothLog = prefs.getValue(orderByColBothKey, "itemDate");
						    orderByTypeBothLog = prefs.getValue(orderByTypeBothKey, "desc");   
						}
						List<BAItemModel> bothLogFileList = new ArrayList<BAItemModel>();
						bothLogFileList.addAll(BAActionUtil.getFilesFromDirectory(BAPropsValues.TOMCAT_LOG_ROOT, "**/*.*",null, null));
						bothLogFileList.addAll(BAActionUtil.getFilesFromDirectory(System.getProperty("liferay.log.root"), "**/*.*",null, null));
						Collections.sort(bothLogFileList,BAActionUtil.getItemNameDateOrderByComparator(orderByColBothLog, orderByTypeBothLog));
					%>
					<liferay-ui:search-container iteratorURL="<%=iteratorURL%>"
						curParam="bothLogCurParam"
						emptyResultsMessage="there-are-no-log-files" delta="20"
						orderByCol="<%=orderByColBothLog%>"
						orderByType="<%=orderByTypeBothLog%>"
						orderByTypeParam="orderByTypeBothLog"
						orderByColParam="orderByColBothLog">
						<div class="portlet-msg-info">
							<liferay-ui:message key="both-log-files-info-source"></liferay-ui:message>
						</div>
						<liferay-ui:search-container-results
								results = "<%=ListUtil.subList(bothLogFileList, searchContainer.getStart(),searchContainer.getEnd())%>"
								total = "<%=bothLogFileList.size()%>"
							/>
				
						<liferay-ui:search-container-row
							className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
				
							<liferay-ui:search-container-column-text name="log-file-name"
								orderable="<%= true %>" orderableProperty="itemName">
								<a href="#"
									onclick="<portlet:namespace/>getFile('<c:out value="<%=item.getItemValue()%>"/>');return false;">
									<c:out value="<%=item.getItemName()%>" />
								</a>
							</liferay-ui:search-container-column-text>
				
							<liferay-ui:search-container-column-text name="last-modified-date"
								orderable="<%= true %>" orderableProperty="itemDate"
								value="<%=dateFormatDateTime.format(item.getItemDate()) %>" />
				
						</liferay-ui:search-container-row>
				
						<liferay-ui:search-iterator />
				
					</liferay-ui:search-container>
				</liferay-ui:panel>	
			</c:when>
		</c:choose>	
	</liferay-ui:panel-container>	
	<aui:input name="fileKey" type="hidden"/>
</aui:form>
		