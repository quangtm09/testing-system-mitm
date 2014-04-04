function openOtherPermissionDialog(roleId) {
	$('#roleId').val(roleId);
	$('#otherPremissionResult').html('');
	$("#otherPermissionDialog").dialog("open");
}
function openAdminPermissionDialog(roleId) {
	$('#roleId').val(roleId);
	$('#adminPremissionResult').html('');
	$("#adminPermissionDialog").dialog("open");
}

function openLecPermissionDialog(roleId){
	$('#roleId').val(roleId);
	$('#lecPremissionResult').html('');
	$("#lecPermissionDialog").dialog("open");
}

function openStuPermissionDialog(roleId){
	$('#roleId').val(roleId);
	$('#stuPremissionResult').html('');
	$("#stuPermissionDialog").dialog("open");
}

$(function() {
	$("#adminPermissionDialog")
	.dialog(
			{
				resizable : false,
				height : 350,
				width : 300,
				modal : true,
				autoOpen : false,
				buttons : [
				           {
				        	   text : 'Update',
				        	   icons : {
				        		   primary : "ui-icon-pencil"
				        	   },
				        	   click : function() {
				        		   var adPermission = '';
				        		   $("input[name=adPermission]:Checked")
				        		   .each(
				        				   function() {
				        					   adPermission = adPermission
				        					   + $(this)
				        					   .val()
				        					   + ",";
				        				   });
				        		   if (adPermission == '') {
				        			   $('#adminPremissionResult')
				        			   .html(
				        			   '<span style="color: red">No value is checked</span>');
				        		   } else {
				        			   $
				        			   .ajax(
				        					   {
				        						   type : "POST",
				        						   url : "/TestingSystem/RoleManagementServlet",
				        						   data : {
				        							   cmd: 'updateRolePermission',
				        							   adPermission: adPermission,
				        							   roleId: $('#roleId').val()
				        						   },
				        						   success : function() {
				        							   $(
				        							   '#adminPremissionResult')
				        							   .html(
				        							   '<span style="color: green">Update Permission successfully</span>');
				        						   }
				        					   })
				        					   .done(
				        							   function() {
				        								   setTimeout(
				        										   function() {
				        											   location.reload();
				        										   }, 3000);
				        							   });
				        		   }

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

	$("#lecPermissionDialog")
	.dialog(
			{
				resizable : false,
				height : 450,
				width : 300,
				modal : true,
				autoOpen : false,
				buttons : [
				           {
				        	   text : 'Update',
				        	   icons : {
				        		   primary : "ui-icon-pencil"
				        	   },
				        	   click : function() {
				        		   var lecPermission = '';
				        		   $("input[name=lecPermission]:Checked")
				        		   .each(
				        				   function() {
				        					   lecPermission = lecPermission
				        					   + $(this)
				        					   .val()
				        					   + ",";
				        				   });
				        		   if (lecPermission == '') {
				        			   $('#lecPremissionResult')
				        			   .html(
				        			   '<span style="color: red">No value is checked</span>');
				        		   } else {
				        			   $
				        			   .ajax(
				        					   {
				        						   type : "POST",
				        						   url : "/TestingSystem/RoleManagementServlet",
				        						   data : {
				        							   cmd : 'updateRolePermission',
				        							   lecPermission : lecPermission,
				        							   roleId: $('#roleId').val()
				        						   },
				        						   success : function() {
				        							   $(
				        							   '#lecPremissionResult')
				        							   .html(
				        							   '<span style="color: green">Update Permission successfully</span>');
				        						   }
				        					   })
				        					   .done(
				        							   function() {
				        								   setTimeout(
				        										   function() {
				        											   location.reload();
				        										   }, 3000);
				        							   });
				        		   }

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
	$("#lecPermissionDialog").dialog("option", "hide");

	$("#stuPermissionDialog")
	.dialog(
			{
				resizable : false,
				height : 450,
				width : 300,
				modal : true,
				autoOpen : false,
				buttons : [
				           {
				        	   text : 'Update',
				        	   icons : {
				        		   primary : "ui-icon-pencil"
				        	   },
				        	   click : function() {
				        		   var stuPermission = '';
				        		   $("input[name=stuPermission]:Checked")
				        		   .each(
				        				   function() {
				        					   stuPermission = stuPermission
				        					   + $(this)
				        					   .val()
				        					   + ",";
				        				   });
				        		   if (stuPermission == '') {
				        			   $('#stuPremissionResult')
				        			   .html(
				        			   '<span style="color: red">No value is checked</span>');
				        		   } else {
				        			   $
				        			   .ajax(
				        					   {
				        						   type : "POST",
				        						   url : "/TestingSystem/RoleManagementServlet",
				        						   data : {
				        							   cmd : 'updateRolePermission',
				        							   stuPermission : stuPermission,
				        							   roleId: $('#roleId').val()
				        						   },
				        						   success : function() {
				        							   $(
				        							   '#stuPremissionResult')
				        							   .html(
				        							   '<span style="color: green">Update Permission successfully</span>');
				        						   }
				        					   })
				        					   .done(
				        							   function() {
				        								   setTimeout(
				        										   function() {
				        											   location.reload();
				        										   }, 3000);
				        							   });
				        		   }

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
	$("#stuPermissionDialog").dialog("option", "hide");

	$("#otherPermissionDialog")
	.dialog(
			{
				resizable : false,
				height : 350,
				width : 300,
				modal : true,
				autoOpen : false,
				buttons : [
				           {
				        	   text : 'Update',
				        	   icons : {
				        		   primary : "ui-icon-pencil"
				        	   },
				        	   click : function() {
				        		   var adPermission = '';
				        		   $("input[name=otherPermission]:Checked")
				        		   .each(
				        				   function() {
				        					   adPermission = adPermission
				        					   + $(this)
				        					   .val()
				        					   + ",";
				        				   });
				        		   if (adPermission == '') {
				        			   $('#otherPremissionResult')
				        			   .html(
				        			   '<span style="color: red">No value is checked</span>');
				        		   } else {
				        			   $
				        			   .ajax(
				        					   {
				        						   type : "POST",
				        						   url : "/TestingSystem/RoleManagementServlet",
				        						   data : {
				        							   cmd: 'updateRolePermission',
				        							   adPermission: adPermission,
				        							   roleId: $('#roleId').val()
				        						   },
				        						   success : function() {
				        							   $(
				        							   '#otherPremissionResult')
				        							   .html(
				        							   '<span style="color: green">Update Permission successfully</span>');
				        						   }
				        					   })
				        					   .done(
				        							   function() {
				        								   setTimeout(
				        										   function() {
				        											   location.reload();
				        										   }, 3000);
				        							   });
				        		   }

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
	$("#otherPermissionDialog").dialog("option", "hide");
});