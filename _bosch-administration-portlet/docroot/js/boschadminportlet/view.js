AUI().ready('node',function(A) {
	
	// handle tab click
	A.all('li.aui-tab .aui-tab-content .aui-tab-label a').on('click',function() {
		
		window[ba_namespace+'showLoadingMask']();
		
	});
	
	/** LOG FILES TAB */
	
	//getFile function: used to submit fileForm (view.jsp)
	window[ba_namespace+'getFile']=function(fileKey) {
		
		A.one('#'+ba_namespace+'fileKey').set('value',fileKey);
		A.one('#'+ba_namespace+'fileForm').submit();
		
	};
	
	/** LOG LEVEL TAB */
	// submit PB Form function
	window[ba_namespace+'submitLogLevelForm']=function(cmd) {
			
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+ba_namespace+'logLevelForm').submit();
		
	};
	
	/** INSTALLED PLUGIN TAB */
	
	// submitInstalledPluginFrom function: render to pluginFile.jsp file
	// action while click on each installed portlets in the "installed portlet" tab
	window[ba_namespace+'submitInstalledPluginForm']=function(installedPluginContext, installedPluginName) {
		A.one('#'+ba_namespace+'installedPluginContext').set('value',installedPluginContext);
		A.one('#'+ba_namespace+'installedPluginName').set('value',installedPluginName);
		
		A.one('#'+ba_namespace+'installedPluginForm').submit();
		
		window[ba_namespace+'showLoadingMask']();
	};
	
	window[ba_namespace+'resetBuildNumber']=function(cmd) {
		var resetServiceComponents = window[ba_namespace+'listCheckedExcept'](ba_namespace+'iPForm', ba_namespace+'allRowIds');
		if(resetServiceComponents.length>0){
			
			if(confirm(Liferay.Language.get('reset-all-selected-service-components'))){
				A.one('#'+ba_namespace+'cmd').set('value',cmd);
				A.one('#'+ba_namespace+'resetServiceComponents').set('value',resetServiceComponents);
				window[ba_namespace+'showLoadingMask']();
				A.one('#'+ba_namespace+'iPForm').submit();
			};
		}else{
			alert(Liferay.Language.get('please-select-at-least-one-service-component'));
		}
	};
	
	window[ba_namespace+'removeServiceComponent']=function(cmd) {
		var resetServiceComponents = window[ba_namespace+'listCheckedExcept'](ba_namespace+'iPForm', ba_namespace+'allRowIds');
		if(resetServiceComponents.length>0){
			
			if(confirm(Liferay.Language.get('remove-all-selected-service-components'))){
				A.one('#'+ba_namespace+'cmd').set('value',cmd);
				A.one('#'+ba_namespace+'resetServiceComponents').set('value',resetServiceComponents);
				window[ba_namespace+'showLoadingMask']();
				A.one('#'+ba_namespace+'iPForm').submit();
			};
		}else{
			alert(Liferay.Language.get('please-select-at-least-one-service-component'));
		}
	};
	
	// reset function for iPForm
	window[ba_namespace+'resetIPFunction']=function() {
		
		A.one('#'+ba_namespace+'iPForm').reset();
		
	};
	
	
	/** LAYOUT IMPORTER TAB */
	
	// reset function
	window[ba_namespace+'resetFunction']=function() {
		
		A.one('#'+ba_namespace+'layoutImporterForm').reset();
		
	};
	
	// submit LI Form function for deleting the layouts of all the selected mandators
	window[ba_namespace+'submitLIForm']=function(cmd) {
		window[ba_namespace+'showLoadingMask']();
		if(cmd==='mandatorChange'){
			A.one('#'+ba_namespace+'cmd').set('value','advancedSearch');
		}
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+ba_namespace+'layoutImporterForm').submit();
	};
	
	// clear DB function for deleting the layouts of all the selected mandators
	window[ba_namespace+'clearDB']=function(cmd) {
		var deleteMandators = window[ba_namespace+'listCheckedExcept'](ba_namespace+'layoutImporterForm', ba_namespace+'allRowIds');
		
		
		
		if(deleteMandators.length>0){
			
			if('1'===A.one(':radio:checked').get('value')){
				// re-import: YES (LRD-1264)	
				if(confirm(Liferay.Language.get('delete-the-layouts-of-all-selected-mandators'))){
					A.one('#'+ba_namespace+'cmd').set('value',cmd);
					A.one('#'+ba_namespace+'deleteMandators').set('value',deleteMandators);
					window[ba_namespace+'showLoadingMask']();
					A.one('#'+ba_namespace+'layoutImporterForm').submit();
				};
			}else{
				// re-import: NO (LRD-1264)
				if(confirm(Liferay.Language.get('start-a-reimport-of-all-selected-mandators-without-deleting-the-pages'))){
					A.one('#'+ba_namespace+'cmd').set('value',cmd);
					A.one('#'+ba_namespace+'deleteMandators').set('value',deleteMandators);
					window[ba_namespace+'showLoadingMask']();
					A.one('#'+ba_namespace+'layoutImporterForm').submit();
				};
			}
		}else{
			alert(Liferay.Language.get('please-select-at-least-one-mandator'));
		}
	};
	
	// resend GSA feed - LRD 1060
	window[ba_namespace+'resendGSAFeed']=function(cmd) {		
		var selectedMandators = window[ba_namespace+'listCheckedExcept'](ba_namespace+'layoutImporterForm', ba_namespace+'allRowIds');
		if(selectedMandators.length>0){
			
			if(confirm(Liferay.Language.get('resend-gsa-feed-for-all-selected-mandators'))){
				A.one('#'+ba_namespace+'cmd').set('value',cmd);
				A.one('#'+ba_namespace+'selectedMandators').set('value',selectedMandators);
				window[ba_namespace+'showLoadingMask']();
				A.one('#'+ba_namespace+'layoutImporterForm').submit();
			};
		}else{
			alert(Liferay.Language.get('please-select-at-least-one-mandator'));
		}
	};
	
	// revertUpdatedLayout function for reverting all the selected udpated layouts
	window[ba_namespace+'revertUpdatedLayout']=function(cmd) {
		var revertUpdatedLayouts = window[ba_namespace+'listCheckedExcept'](ba_namespace+'layoutImporterForm', ba_namespace+'allRowIds');
		if(revertUpdatedLayouts.length>0){
			
			if(confirm(Liferay.Language.get('revert-all-selected-manually-updated-layouts'))){
				A.one('#'+ba_namespace+'cmd').set('value',cmd);
				A.one('#'+ba_namespace+'revertUpdatedLayouts').set('value',revertUpdatedLayouts);
				window[ba_namespace+'showLoadingMask']();
				A.one('#'+ba_namespace+'layoutImporterForm').submit();
			};
		}else{
			alert(Liferay.Language.get('please-select-at-least-one-manually-updated-layout'));
		}
	};
	
	// submit LI Form function for deleting the layouts of all the selected mandators
	window[ba_namespace+'downloadXmlFile']=function(formId,cmd,mandatorLink) {
		A.one('#'+ba_namespace+'mandatorLink').set('value',mandatorLink);
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+formId).submit();
		
	};
	
	
	/** DOCUMENT IMPORTER TAB */
	
	// submit PB Form function
	window[ba_namespace+'submitDIForm']=function(cmd) {
			
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+ba_namespace+'documentImporterForm').submit();
		
	};
	
	/** PORTLET BRIDGE TAB */
	
	// submit PB Form function
	window[ba_namespace+'submitPB2Form']=function(cmd) {
			
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+ba_namespace+'pB2Form').submit();
		
	};
	
	
	/** CACHE VIEWER TAB */
	
	/** SERVER ADMINISTRATION TAB */
	
	window[ba_namespace+'submitSAForm']=function(cmd) {
		
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+ba_namespace+'sAForm').submit();
		
	};
	
	/** ADVANCED TOOLS TAB */
	
	window[ba_namespace+'submitAdvToolsForm']=function(cmd) {
		
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+ba_namespace+'advToolsForm').submit();
		
	};
	
	/** USER TAB */
	
	window[ba_namespace+'submitUserForm']=function(cmd) {
		var selUserId = window[ba_namespace+'listCheckedExcept'](ba_namespace+'searchUserForm', ba_namespace+'allRowIds');
		if(selUserId.length > 0 && selUserId.indexOf(",") === -1){
			
			if(confirm(Liferay.Language.get('clone-this-user'))){
				A.one('#'+ba_namespace+'cmd').set('value',cmd);
				A.one('#'+ba_namespace+'selUserId').set('value',selUserId);
				window[ba_namespace+'showLoadingMask']();
				A.one('#'+ba_namespace+'searchUserForm').submit();
			};
		}else{
			alert(Liferay.Language.get('please-select-one-user-for-clone'));
		}		
	};
	
	/** BOSCH COMMONS TAB */
	
	// submit BC Form function
	window[ba_namespace+'submitBCForm']=function(cmd) {
			
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		A.one('#'+ba_namespace+'bCForm').submit();
		
	};
	
	/** BOSCH PLUGINS TAB */
	
	// submit BC Form function
	window[ba_namespace+'submitBPForm']=function(pluginName, className, methodName) {
		A.one('#'+ba_namespace+'pluginName').set('value',pluginName);	
		A.one('#'+ba_namespace+'className').set('value',className);
		A.one('#'+ba_namespace+'methodName').set('value',methodName);
		A.one('#'+ba_namespace+'bPForm').submit();
		
	};
	
});

