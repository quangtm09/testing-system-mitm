var deleteRoleId;
function deleteRole(roleId){
	deleteRoleId = roleId;
	var message = 'Are you sure you want to delete <span style="color: red">' + roleId + '</span> account?';
	$('#deleteRoleMessage').html(message);
	$( "#deleteRoleDialog" ).dialog("open");
}
function openOtherPermissionDialog(roleId) {
	$('#roleId').val(roleId);
	$('#otherPremissionResult').html('');
	$("#otherPermissionDialog").dialog("open");
}

function openAddNewRoleDialog() {
	$('#roleName').val('');
	$('#roleDesc').val('');
	$('#addNewRoleResult').html('');
	$("#addNewRoleDialog").dialog("open");
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
				        							   updatePermission: adPermission,
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
				        							   updatePermission : lecPermission,
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
				        							   updatePermission : stuPermission,
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
				        		   var otherPermission = '';
				        		   $("input[name=otherPermission]:Checked")
				        		   .each(
				        				   function() {
				        					   otherPermission = otherPermission
				        					   + $(this)
				        					   .val()
				        					   + ",";
				        				   });
				        		   if (otherPermission == '') {
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
				        							   updatePermission: otherPermission,
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
	
	$( "#addNewRoleDialog" ).dialog({
        resizable: false,
        height:270,
        width: 400,
        modal: true,
        autoOpen: false,
        buttons:
      	  [
             {
                 text: 'Add',
                 icons: {primary: "ui-icon-circle-plus"},
                 click: function(){
  	            	var roleName = $.trim($('#roleName').val());
  	               	var roleDesc = $.trim($('#roleDesc').val());

  	               	if(roleName == ''){
  	               		$('#addNewRoleResult').html('<span style="color: red">Required fied is not empty</span>');
  	               	} else {
  	               		$.ajax({
  	               		  type: "POST",
  	           			  url: "/TestingSystem/RoleManagementServlet",
  	           			  data: {
  	           				  cmd: 'addNewRole',
  	           				  roleName: roleName,
  	           				  roleDesc: roleDesc
  	           			  },
  	           			  success: function(data){
  	           				  $('#addNewRoleResult').html(data);
  	                         }
  	           			}).done(function() {
	  	           			setTimeout(function(){
	  	           				location.reload();
	        				}, 3000);
  	           			});
  	               	}
                 }
             },
             {
                 text: 'Cancel',
                 icons: { primary: "ui-icon-circle-close"},
             	  click: function(){
             		$( this ).dialog( "close" );
             	  }
             }
         ]
      });

      $( "#addNewRoleDialog" ).dialog( "option", "hide");
      
      $( "#deleteRoleDialog" ).dialog({
          resizable: false,
          width:'auto',
          height: 'auto',
          modal: true,
          autoOpen: false,
          buttons:
        	  [
               {
                   text: 'Delete',
                   icons: {primary: "ui-icon-trash"},
                   click: function(){
                	   $.ajax({
            			  type: "POST",
            				  url: "/TestingSystem/RoleManagementServlet",
            				  data: {
            					  cmd: 'deleteRole',
            					  deleteRoleId: deleteRoleId,
            				  },
            				  success: function(data){
            					  if(data === 'false'){
            						  alert('Error while deleting account: ' + deleteRoleId);
            					  } else {
            						  alert('Deleted ' + deleteRoleId + ' successfully!');
            						  location.reload();
            					  }
            				  }
            			}).done(function() {

            			});
                   }
               },
               {
                   text: 'Cancel',
                   icons: { primary: "ui-icon-circle-close"},
               	  click: function(){
               		$( this ).dialog( "close" );
               	  }
               }
           ]
        });

        $( "#deleteRoleDialog" ).dialog( "option", "hide");
});