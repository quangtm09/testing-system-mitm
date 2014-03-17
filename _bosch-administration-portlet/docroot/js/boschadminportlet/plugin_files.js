AUI().ready('node', function(A) {
	// submit Plugin Form
	window[ba_namespace+'submitPluginForm']=function(fileKey,cmd) {
		
		A.one('#'+ba_namespace+'fileKey').set('value',fileKey);
		A.one('#'+ba_namespace+'cmd').set('value',cmd);
		
		A.one('#'+ba_namespace+'pluginFileForm').submit();
		
		if(''==cmd){
			window[ba_namespace+'showLoadingMask']();
		}
	};
	
	// Back link click
	A.one('#'+ba_namespace+'TabsBack').on('click',function(){
		
		window[ba_namespace+'showLoadingMask']();
		
	});

});