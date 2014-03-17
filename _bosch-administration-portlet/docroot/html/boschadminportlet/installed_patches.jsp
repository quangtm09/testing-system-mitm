<%@include file="/html/init.jsp" %>
<liferay-ui:panel-container extended="<%= true %>" id="installedPatchesPanelContainer" persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>"
		helpMessage="installed-patches-help-message"
		title="installed-patches-title"
		extended="<%= true %>"
		persistState="<%= true %>" 
		id="installedPatchesPanel"
		>
		<% 
	    List<BAItemModel> installedPatchList = BAActionUtil.getInstalledPatches();
		String itemName = StringPool.BLANK;
		String itemValue = StringPool.BLANK;
		%>
		<liferay-ui:search-container
			iteratorURL="<%=iteratorURL%>"
			curParam="installedPatchesCurParam"
			emptyResultsMessage="there-are-no-installed-patches" delta="20">
	
			<div class="portlet-msg-info">
				<liferay-ui:message key="installed-patches-info-source"></liferay-ui:message>
			</div>
			<liferay-ui:search-container-results 
				results="<%= ListUtil.subList(installedPatchList, searchContainer.getStart(),searchContainer.getEnd()) %>" 
				total="<%= installedPatchList.size() %>"/>
	
			<liferay-ui:search-container-row
				className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
				<%itemName = item.getItemName();%>
				<c:choose>
					<c:when test='<%=itemName.equals("Liferay-Portal-Installed-Patches")%>'>
						<%
						itemValue = item.getItemValue();
						itemName += StringPool.SPACE + StringPool.OPEN_BRACKET + itemValue.split(StringPool.SPACE).length + StringPool.CLOSE_BRACKET;
						itemValue = itemValue.replace(StringPool.SPACE, "<br/>");
						%>
						<liferay-ui:search-container-column-text
							name="name-install-patch" value="<%=itemName%>"/>
						<liferay-ui:search-container-column-text
							name="value-install-patch" value="<%=itemValue%>"/>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-text name="name-install-patch" property="itemName"/>
						<liferay-ui:search-container-column-text name="value-install-patch" property="itemValue"/>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-row>
	
			<liferay-ui:search-iterator/>
		</liferay-ui:search-container>
	</liferay-ui:panel>
</liferay-ui:panel-container>
