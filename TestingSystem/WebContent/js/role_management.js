function openAdminPermissionDialog(roleId) {
//	$('#roleId').val(roleId);
	$('#createUser').val('');
	$('#updateProfile').val('');
	$('#removeUser').val('');
	$('#manageUser').val('');
	$('#createAccount').val('');
	$('#viewAccount').val('');
	$('#dropAccount').val('');
	$('#viewHist').val('');
  	$('#adminPremissionResult').html('');
	$( "#adminPermissionDialog" ).dialog("open");
}

$(function() {
	$("#adminPermissionDialog").dialog({
		resizable : false,
		height : 250,
		width : 400,
		modal : true,
		autoOpen : false,
		buttons : [ {
			text : 'Update',
			icons : {
				primary : "ui-icon-pencil"
			},
			click : function() {
				var createUser = $.trim($('#createUser').val());
				var updateProfile = $.trim($('#updateProfile').val());
				var removeUser = $.trim($('#removeUser').val());
				var manageUser = $.trim($('#manageUser').val());
				var createAccount = $.trim($('#createAccount').val());
				var viewAccount = $.trim($('#viewAccount').val());
				var dropAccount = $.trim($('#dropAccount').val());
				var viewHist = $.trim($('#viewHist').val());
				$.ajax({
					type : "POST",
					url : "/TestingSystem/RoleManagementServlet",
					data : {
						cmd : 'updateAdminPermission',
						accountId : $('#roleId').val(),
						createUser : createUser,
						updateProfile : updateProfile,
						removeUser : removeUser,
						manageUser : manageUser,
						createAccount : createAccount,
						viewAccount : viewAccount,
						dropAccount : dropAccount,
						viewHist : viewHist
					},
					success : function(data) {
						$('#adminPremissionResult').html(data);
					}
				}).done(function() {
					setTimeout(function() {
						$(this).dialog("close");
					}, 2000);
				});
			}
		}, {
			text : 'Cancel',
			icons : {
				primary : "ui-icon-circle-close"
			},
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
	$("#adminPermissionDialog").dialog("option", "hide");
});