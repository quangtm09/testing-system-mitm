<%
	String cacheName = ParamUtil.getString(request, "cacheName");
	String cacheKey = ParamUtil.getString(request, "cacheKey");
%>


<%@include file="/html/init.jsp" %>
<portlet:resourceURL var="viewCacheURL" id="viewCache" />
<portlet:resourceURL var="updateCacheURL" id="updateCache" />

<%-- <aui:form name="cVForm" action="<%= submitCVFormActionURL.toString() %>" method="POST"> --%>
<aui:form name="cVForm" onSubmit="event.preventDefault();">
	<div class="portlet-msg-alert">
		<liferay-ui:message key="cache-viewer-tip"/>
	</div>
	<aui:field-wrapper>
		<aui:column columnWidth="15">
			<b><label for="cacheName"><liferay-ui:message key="cache-name" /></label></b>
		</aui:column>
		<aui:column>
			<aui:input type="text" name="cacheName" label="" cssClass="cacheNameInput" value="<%=cacheName %>"/>
		</aui:column>
	</aui:field-wrapper>
	<aui:field-wrapper>
		<aui:column columnWidth="15">
			<b><label for="cacheKey"><liferay-ui:message key="cache-key" /></label></b>
		</aui:column>
		<aui:column>
			<aui:input type="text" name="cacheKey" label="" cssClass="cacheNameInput" value="<%=cacheKey %>"/>
		</aui:column>
	</aui:field-wrapper>
	<aui:field-wrapper>
		<aui:column>
			<button type="button" onclick="<portlet:namespace/>viewCache()">
				<liferay-ui:icon image="view" />
				<liferay-ui:message key="view-cache-value" />
			</button>
		</aui:column>
		<aui:column></aui:column>
	</aui:field-wrapper>
	
	<div class="separator"><!-- --></div>
	
	<div id="<portlet:namespace/>results">
		<aui:field-wrapper>
			<aui:column columnWidth="15">
				<b><label><liferay-ui:message key="cache-type" /></label></b>
			</aui:column>
			<aui:column>
				<div id="<portlet:namespace/>cacheType"/>
			</aui:column>
		</aui:field-wrapper>
		<aui:field-wrapper>
			<aui:column columnWidth="15">
				<b><label><liferay-ui:message key="cache-value" /></label></b>
			</aui:column>
			<aui:column>
					<div id="<portlet:namespace/>cacheValue"/>
			</aui:column>
		</aui:field-wrapper>
	</div>
	
</aui:form>

<!-- focus to cacheName input field -->
<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />cVForm.<portlet:namespace />cacheName);
</aui:script>

<script type="text/javascript">

// LRD-1307: Liferay.Language.get(key) doesn't look up in portlet context language resources (AJAX request)
var cacheIsNoteExistMsg = '<%= LanguageUtil.get(themeDisplay.getLocale(), "cache-is-not-exist") %>';
var confirmUpdateCacheMsg = '<%= LanguageUtil.get(themeDisplay.getLocale(), "do-you-really-want-to-update-this-cache-value") %>';
var newCacheIsNotCorrectMsg = '<%= LanguageUtil.get(themeDisplay.getLocale(), "the-new-cache-value-is-not-correct") %>';
var ajaxErrorMsg = '<%= LanguageUtil.get(themeDisplay.getLocale(), "ajax-error") %>';

//======== handling functions
function <portlet:namespace/>viewCache(){
	 AUI().use('aui-io-request','transition', function(A) {
	 	var cacheValueEditable =  null;
	 	var editableAUI = A.one('.aui-editable');
	 	var resultsDiv = A.one('#<portlet:namespace/>results');
		var cacheNameAUI = A.one('#<portlet:namespace/>cacheName');
		var cacheKeyAUI = A.one('#<portlet:namespace/>cacheKey');
		var cacheValueAUI = A.one('#<portlet:namespace/>cacheValue');
		var cacheTypeAUI = A.one('#<portlet:namespace/>cacheType');
		var cacheNameText = '';
		var cacheKeyText = '';
		var cacheValueText = '';
		if(null !== cacheNameAUI){
			cacheNameText = cacheNameAUI.get('value');
		}
		if(null !== cacheKeyAUI){
			cacheKeyText = cacheKeyAUI.get('value');
		}
		
		if(''===cacheNameText||''===cacheKeyText){
			if(null!==editableAUI){
				editableAUI.remove();
			}
			if(null!==cacheValueAUI && null!==cacheTypeAUI){
				cacheValueAUI.removeClass('aui-editable-hover');
				cacheValueAUI.text(cacheIsNoteExistMsg);
				cacheTypeAUI.text(cacheIsNoteExistMsg);
			}
		}else{
			// AJAX Call: get Cache Type and Cache Value
			A.io.request('<%= viewCacheURL.toString() %>',{
				method: "POST",
				data: {
					cacheName: cacheNameText,
					cacheKey: cacheKeyText,	
				},
				on:{
					error: function(){
						 alert(ajaxErrorMsg);
					},
					success: function(){
						var viewCacheResponseData = this.get('responseData');
						if ('false'!==viewCacheResponseData) {
							// cache is exist
							var viewCacheResultArray = viewCacheResponseData.split(",");
							if(null===viewCacheResultArray){
								alert(ajaxErrorMsg);
							}
							
							if(null!==editableAUI){
								editableAUI.remove();
							}
							if(null!==cacheValueAUI && null!==cacheTypeAUI){
								cacheValueAUI.removeClass('aui-editable-hover');
								// update Cache Value
								cacheTypeAUI.text(viewCacheResultArray[1]);
								cacheValueAUI.text(viewCacheResultArray[2]);
							}
							
							if('false'!==viewCacheResultArray[0]){
								//======== Start AUI Editable Component
								// 1. intial AUI Editable
								cacheValueEditable = new A.Editable(
									{
										node: '#<portlet:namespace/>cacheValue',
									}
								);
								// 2. rendering
								cacheValueEditable.render();
								// 3. handle Save function
								cacheValueEditable.on('save',function(){
									if(confirm(confirmUpdateCacheMsg)){
										cacheTypeText =A.one('#<portlet:namespace/>cacheType').get('text');
										cacheValueText =A.one('.aui-editable input[type=text]').get('value');
										A.io.request('<%= updateCacheURL.toString() %>',{
											method: "POST",
											data: {
												cacheName: cacheNameText,
												cacheKey: cacheKeyText,
												cacheType: cacheTypeText,
												cacheValue: cacheValueText,
											},
											on:{
												error: function(){
													alert(ajaxErrorMsg);
												},
												success: function(){
													var updateCacheResponseData = this.get('responseData');
													if('false'!==updateCacheResponseData){
														var updateCacheResultArray = updateCacheResponseData.split(",");
														A.one('.aui-editable input[type=text]').set('value',updateCacheResultArray[1]);
														A.one('#<portlet:namespace/>cacheValue').set('text',updateCacheResultArray[1]);
													}else{
														alert(newCacheIsNotCorrectMsg);
														// set original values back ???
													}
												}
											}
										});					
									}else{
										cacheValueEditable.cancel();
									}
								});
								//======== End AUI Editable Component 
							}
							// increase Cache Value input size
							A.one('.aui-editable').addClass('cacheNameInput');
							
						}else{
							// cache is not exist
							if(null!==editableAUI){
								editableAUI.remove();
							}
							if(null!==cacheValueAUI){
								cacheValueAUI.removeClass('aui-editable-hover');
								
								cacheValueAUI.text(cacheIsNoteExistMsg);
								cacheTypeAUI.text(cacheIsNoteExistMsg);
								
							}
				        	
						}
					}
				}
			});
		}
		
       	// fadeIn and fadeOut effect
       	cacheValueAUI.transition({
      		  opacity: 0,
      		  duration: 0.5
      		});
       	setTimeout(function(){
       		cacheValueAUI.transition({
        		  opacity: 1,
        		  duration: 0.5,	
       		});
       	},500);
       	cacheTypeAUI.transition({
    		  opacity: 0,
    		  duration: 0.5
    		});
     	setTimeout(function(){
     		cacheTypeAUI.transition({
      		  opacity: 1,
      		  duration: 0.5,	
     		});
     	},500);
		
	 });
};

</script>