function openDeleteLogDialog() {
	$('#deleteLogResult').html('');
	$("#deleteLogDialog").dialog("open");
}

$(function() {
	$("#deleteLogDialog")
	.dialog(
			{
				resizable : false,
				height : 200,
				width : 300,
				modal : true,
				autoOpen : false,
				buttons : [
				           {
				        	   text : 'Clear',
				        	   icons : {
				        		   primary : "ui-icon-pencil"
				        	   },
				        	   click : function() {
				        		   var deleteLog = "";
				        		   if ("input[type='radio'][name='radioDeleteLog']:checked") {
				        			   deleteLog = $("input[type='radio'][name='radioDeleteLog']:checked").val();
				        			   $
				        			   .ajax(
				        					   {
				        						   type : "POST",
				        						   url : "/TestingSystem/LogManagementServlet",
				        						   data : {
				        							   cmd: 'deleteLog',
				        							   deleteLog: deleteLog
				        						   },
				        						   success : function() {
				        							   $(
				        							   '#deleteLogResult')
				        							   .html(
				        							   '<span style="color: green">Delete Successful</span>');
				        						   }
				        					   })
				        					   .done(
				        							   function() {
				        								   setTimeout(
				        										   function() {
				        											   location.reload();
				        										   }, 3000);
				        							   });

				        		   } else {
				        			   $('#deleteLogResult')
				        			   .html(
				        			   '<span style="color: red">No value is selected</span>');
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
	$("#deleteLogDialog").dialog("option", "hide");
});