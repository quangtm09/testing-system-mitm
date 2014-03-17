AUI().ready('node', function(A) {
	
	// show loading-masked
	window[ba_namespace+'showLoadingMask']=function (){
		AUI().use( 'aui-loading-mask', function(A){
			if (A.one('body').loadingmask == null)
				A.one('body').plug(A.LoadingMask, { background: '#000' });
			A.one('body').loadingmask.show();
		});
	};
	
	// hide loading-masked
	window[ba_namespace+'hideLoadingMask']=function (){
		AUI().use( 'aui-loading-mask', function(A){
			if (A.one('body').loadingmask == null)
				A.one('body').plug(A.LoadingMask, { background: '#000' });
			A.one('body').loadingmask.hide();
		});
	};
	
	// get the list of checked rows in search container
	window[ba_namespace+'listCheckedExcept']=function(form, except) {
		var s = [];

		form = A.one('#'+form);

		if (form) {
			form.all('input[type=checkbox]').each(
				function(item, index, collection) {
					var val = item.val();

					if (val && item.get('name') != except && item.get('checked')) {
						s.push(val);
					}
				}
			);
		}

		return s.join(',');
	};
	
	
});