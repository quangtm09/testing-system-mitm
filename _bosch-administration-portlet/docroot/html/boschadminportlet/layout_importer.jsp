<%@include file="/html/init.jsp" %>
<%
	boolean isLIDeployed = BAActionUtil.isLayoutImporterDeployed();
	boolean isLIVersionSupported = BAActionUtil.isLayoutImporterVersionSupported();
	boolean isLITabsDisplayable = isLIDeployed && isLIVersionSupported;

	long mandatorSelectId = ParamUtil.getLong(request, "mandatorSelectId");
	String type = ParamUtil.getString(request, "type");
	String boschLayoutId = ParamUtil.getString(request, "boschLayoutId");
	String friendlyUrl = ParamUtil.getString(request, "friendlyUrl");
	boolean andOperator = ParamUtil.getBoolean(request,"andOperator",true);
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	
	String layoutId=ParamUtil.getString(request, "layoutId");
	
	iteratorURL.setParameter("mandatorSelectId", String.valueOf(mandatorSelectId));
	iteratorURL.setParameter("type", type);
	iteratorURL.setParameter("boschLayoutId", boschLayoutId);
	iteratorURL.setParameter("friendlyUrl", friendlyUrl);
	iteratorURL.setParameter("andOperator", String.valueOf(andOperator));
	iteratorURL.setParameter("cmd", cmd);
	
	String baLiTabs = ParamUtil.getString(request, "baLiTabs", StringPool.BLANK);
	portletURL.setParameter("baLiTabs", baLiTabs);
	
%>

<portlet:actionURL name="submitLIFormAction" var="submitLIFormActionURL"/>
<aui:form action="<%=submitLIFormActionURL.toString()%>" method="post" name="layoutImporterForm" onSubmit="event.preventDefault();">
	<liferay-ui:tabs param="baLiTabs"
			names="manage-mandator,manage-layout"
			url="<%=portletURL.toString()%>" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="revertUpdatedLayouts" type="hidden" />
	<aui:input name="selectedMandators" type="hidden" />
	<liferay-ui:panel-container extended="<%= true %>" id="layoutImporterPanelContainer" persistState="<%= true %>">	
	
	<c:choose>
		<c:when test="<%=!isLIDeployed %>">
			<div class="portlet-msg-error">
				<liferay-ui:message key="li-portlet-not-deployed"/>
			</div>
		</c:when>
		<c:when test="<%= !isLIVersionSupported %>">
			<div class="portlet-msg-error">
				<liferay-ui:message key="li-version-not-supported"/>
			</div>
		</c:when>
		<c:otherwise>
		<%
			List<Group> groupList = BAActionUtil.getMandatorList(themeDisplay.getCompanyId());
		%>
		<c:choose>
			<c:when test='<%=Validator.isNull(groupList)  %>'>
				<div class="portlet-msg-error">
					<liferay-ui:message key="ba-is-not-able-to-get-li-service-properly" />
				</div>
			</c:when>
			
			<c:when test='<%=baLiTabs.equals(StringPool.BLANK) || baLiTabs.equals("manage-mandator")%>'>
			
					<liferay-ui:panel collapsible="<%= true %>"
						helpMessage="manage-mandator-importer-help-message"
						title="manage-mandator-importer-title"
						id="deteleLayoutImporterPanel"
						extended="<%= true %>"
						persistState="<%= true %>"
						>
						<div class="portlet-msg-alert">
							<liferay-ui:message key="li-disclaimer"/>
						</div>
						
						<aui:input name="deleteMandators" type="hidden" />
						<aui:input name="mandatorLink" type="hidden" />
						
						<aui:field-wrapper cssClass="reimportWrapper lfr-form-row">
							<aui:column cssClass="reimportLabel">
								<liferay-ui:message key="delete"/>
							</aui:column>
							<aui:column cssClass="radioButtunOption">
								<aui:input inlineLabel="right" name="restartImport" type="radio"
									value="1" label="Yes" />
							</aui:column>
							<aui:column cssClass="radioButtunOption">
								<aui:input inlineLabel="right" name="restartImport" type="radio"
									value="2" label="No" checked="true" />
							</aui:column>
							<aui:column>
								<liferay-ui:icon-help message="automatically-restart-import-of-deleted-content" />
							</aui:column>
						</aui:field-wrapper>
						
						<%List<BAItemModel> mandatorList = BAActionUtil.getLIMandators(groupList);%>
						<liferay-ui:search-container 
							rowChecker="<%= rowChecker %>"
							iteratorURL="<%=iteratorURL%>"
							curParam="mandatorsParam"
							id="mandatorsSearchContainer"
							emptyResultsMessage="there-are-no-mandators" 
							delta="10">
					
							<liferay-ui:search-container-results 
								results="<%=ListUtil.subList(mandatorList, searchContainer.getStart(),searchContainer.getEnd()) %>"
								total="<%=mandatorList.size() %>"/>
					
							<liferay-ui:search-container-row keyProperty="itemName"
								className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
								<liferay-ui:search-container-column-text name="name" property="itemName"/>
								<liferay-ui:search-container-column-text name="navigation-link">
									<%
										String navLink = item.getItemValue();
										if(!HttpUtil.hasProtocol(navLink)){
										    navLink = navLink.replace(StringPool.BACK_SLASH,"\\\\");
										}
									%>
									<a href="#" onclick="<portlet:namespace/>downloadXmlFile('<portlet:namespace/>layoutImporterForm','<%= BAConstants.DOWNLOAD_FILE %>','<%=navLink %>'); return false;">
										<%=item.getItemValue() %>
									</a>
								</liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-text name="date-of-last-import" value="<%=dateFormatDateTime.format(item.getItemDate()) %>"/>
							</liferay-ui:search-container-row>
							
							<liferay-ui:search-iterator/>
						</liferay-ui:search-container>
						
						<aui:button-row>
							<aui:button value="submit" onClick="<%= renderResponse.getNamespace() + \"clearDB(\'\"+ BAConstants.CLEAR_DB + \"\')\" %>" />
							<aui:button value="resend-gsa-feed" onClick="<%= renderResponse.getNamespace() + \"resendGSAFeed(\'\"+ BAConstants.RESEND_GSA_FEED + \"\')\" %>" />
							<aui:button value="cancel" onClick="<%= renderResponse.getNamespace() + \"resetFunction()\" %>" />
						</aui:button-row>
				
					</liferay-ui:panel>
						
			</c:when>
			
			<c:when test='<%=baLiTabs.equals("manage-layout")%>'>
				<liferay-ui:panel collapsible="<%= true %>"
					helpMessage="manage-layout-help-message"
					title="manage-layout-title"
					id="manageLayoutPanel"
					extended="<%= true %>"
					persistState="<%= true %>"
					>
					<!-- search (start) -->
					<div id="toggle_id_layout_advanced_search" style="margin-bottom:10px;" valign="top">
						<div style="margin-left:5px;">
							<liferay-util:buffer var="andOperatorBuff">
							<aui:select cssClass="inline-control" inlineField="<%= true %>" label="" name="andOperator">
								<aui:option label="all" selected="<%= andOperator %>" value="1" />
								<aui:option label="any" selected="<%= !andOperator %>" value="0" />
							</aui:select>
							</liferay-util:buffer>
							<liferay-ui:message arguments="<%= andOperatorBuff %>" key="match-x-of-the-following-fields" />
						</div>
					
						<aui:field-wrapper>
							<aui:column cssClass="liLabelForInput">
								<label for="mandatorSelect"><liferay-ui:message key="mandator"></liferay-ui:message></label>
							</aui:column>
							<aui:column>
								<aui:select name="mandatorSelect" label="" cssClass="liSearchName"
											onChange="<%= renderResponse.getNamespace() + \"submitLIForm(\'\"+ BAConstants.MANDATOR_CHANGE + \"\')\" %>">
									<aui:option label="select-mandator"  value="0L"/>
									<%
										for(Group group: groupList){
										    if(Validator.isNotNull(mandatorSelectId) && mandatorSelectId==group.getGroupId()){
							        %>
										<aui:option label="<%=group.getName() %>"  value="<%=group.getGroupId() %>" selected="true"/>
									<%	        
										    }else{
									%>
										<aui:option label="<%=group.getName() %>"  value="<%=group.getGroupId() %>" />
									<%	    
										    }
										}
									%>
									</aui:select>
							</aui:column>
						</aui:field-wrapper>
						
						<aui:field-wrapper>
							<aui:column cssClass="liLabelForInput">
								<label for="boschLayoutId"><liferay-ui:message key="bosch-layout-id"/></label>
							</aui:column>
							<aui:column>
								<aui:input inlineField="<%=true%>" name="boschLayoutId" label=""
									 type="text" value="<%=boschLayoutId%>" cssClass="liSearchName" />
							</aui:column>
						</aui:field-wrapper>
						
						<aui:field-wrapper>
							<aui:column cssClass="liLabelForInput">
								<label for="type"><liferay-ui:message key="type"/></label>
							</aui:column>
							<aui:column>
								<aui:input inlineField="<%=true%>" name="type" label=""
									 type="text" value="<%=boschLayoutId%>" cssClass="liSearchName" />
							</aui:column>
						</aui:field-wrapper>
						
						<aui:field-wrapper>
							<aui:column cssClass="liLabelForInput">
								<label for="friendlyUrl"><liferay-ui:message key="friendly-url"/></label>
							</aui:column>
							<aui:column>
								<aui:input inlineField="<%=true%>" name="friendlyUrl" label=""
									title="search-files" type="text" value="<%=friendlyUrl%>" cssClass="urlInputText"/>
								<liferay-ui:icon-help message="search-file-tooltip" />
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
					<!-- search (end) -->
					<%
					String orderByColLayoutItemKey = "orderByColLayoutItem";
					String orderByTypeLayoutKey = "orderByTypeLayoutItem";
					String orderByColLayoutItem = ParamUtil.getString(request, orderByColLayoutItemKey);
					String orderByTypeLayoutItem = ParamUtil.getString(request, orderByTypeLayoutKey);
			
					if (Validator.isNotNull(orderByColLayoutItem ) && Validator.isNotNull(orderByTypeLayoutItem )) { 
						prefs.setValue(orderByColLayoutItemKey, orderByColLayoutItem); 
						prefs.setValue(orderByTypeLayoutKey, orderByTypeLayoutItem); 
					} else { 
					    orderByColLayoutItem = prefs.getValue(orderByColLayoutItemKey, "itemName");
					    orderByTypeLayoutItem = prefs.getValue(orderByTypeLayoutKey, "asc");   
					}
					
					%>
					
					<div class="word-break">
						<liferay-ui:search-container 
							id="layoutsSearchContainer"
							rowChecker="<%= rowChecker %>"
							iteratorURL="<%=iteratorURL%>"
							curParam="layoutsParam"
							orderByCol="<%=orderByColLayoutItem%>"
							orderByType="<%=orderByTypeLayoutItem%>"
							orderByTypeParam="orderByTypeLayoutItem"
							orderByColParam="orderByColLayoutItem"
							emptyResultsMessage="there-are-no-layouts" delta="20"
							>
							<%
							List<BAItemModel> layoutItemList = BAActionUtil.getLayouts(andOperator,company.getCompanyId(),
						            mandatorSelectId, type, boschLayoutId, friendlyUrl, searchContainer.getStart(),
						            searchContainer.getEnd());
					        Collections.sort(layoutItemList,BAActionUtil.getItemSecondValueNameTypeUrlValueOrderByComparator(orderByColLayoutItem, orderByTypeLayoutItem));
							%>
							
							<liferay-ui:search-container-results
								results="<%=layoutItemList %>"
								total="<%=  BAActionUtil.getTotalLayouts(andOperator,company.getCompanyId(),
						            mandatorSelectId, type, boschLayoutId, friendlyUrl)%>"/>
						
							<liferay-ui:search-container-row keyProperty="itemId"
								className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
								<% final String itemSecondValue = item.getItemSecondValue(); %>
								<aui:input name="rowCheckerBoschLayoutId" label="" type="hidden" value="<%=itemSecondValue %>" />
								<liferay-ui:search-container-column-text name="bosch-layout-id" orderable="<%= true %>" orderableProperty="itemSecondValue" cssClass="layoutBoschLayoutIdColumn">
									<%=(null == itemSecondValue) ? (LanguageUtil.get(portletConfig, locale,
								                    "bosch-layout-id-display-without-layout"))
								                    : (StringPool.BLANK.equals(itemSecondValue) ? (LanguageUtil.get(portletConfig, locale,
								                            "just-reverted")) : (itemSecondValue))%>
								</liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-text name="mandator" property="itemName" orderable="<%= true %>" orderableProperty="itemName" cssClass="layoutMandatorColumn"/>
								<liferay-ui:search-container-column-text name="type" property="itemType" orderable="<%= true %>" orderableProperty="itemType" cssClass="layoutTypeColumn"/>
								<liferay-ui:search-container-column-text name="friendly-url" property="itemUrl" orderable="<%= true %>" orderableProperty="itemUrl" />
								<liferay-ui:search-container-column-text name="layout-template-id" property="itemValue" orderable="<%= true %>" orderableProperty="itemValue" cssClass="layoutLayoutTemplateIdColumn"/>
							</liferay-ui:search-container-row>
							
							<liferay-ui:search-iterator/>
								
						</liferay-ui:search-container>
					</div>
					<aui:button-row>
						<aui:button value="revert" onClick="<%= renderResponse.getNamespace() + \"revertUpdatedLayout(\'\"+ BAConstants.REVERT_UPDATED_LAYOUT + \"\')\" %>" />
						<aui:button value="cancel" onClick="<%= renderResponse.getNamespace() + \"resetFunction()\" %>" />
					</aui:button-row>
					
				</liferay-ui:panel>
				
				<!-- focus to layoutID input field -->
				<aui:script>
					Liferay.Util.focusFormField(document.<portlet:namespace />layoutImporterForm.<portlet:namespace />boschLayoutId);
				</aui:script>
			</c:when>
			
		</c:choose>
			
		</c:otherwise>
	</c:choose>
		
	</liferay-ui:panel-container>
</aui:form>

<%@ include file="/html/boschadminportlet/layout_importer_js.jspf" %>