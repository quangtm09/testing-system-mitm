function login(cmd){
	$('input[name="cmd"]').val(cmd);
}

function logout(){
	window.location.href = '/TestingSystem/LogoutServlet';
}

function openChangePasswordDialog(accountId){
	$('#accountId').val(accountId);
	$('#newPassword').val('');
	$('#confirmedPassword').val('');
	$( "#changePasswordDialog" ).dialog("open");
}

function changePassword(accountId){
	
}

// Document ready
$(function() {
	$( "#datepicker" ).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: 'dd-mm-yy'
    });
	
    $( "#changePasswordDialog" ).dialog({
      resizable: false,
      height:250,
      width: 350,
      modal: true,
      autoOpen: false,
      buttons: {
        'Change': function() {
        	var newPassword = $('#newPassword').val();
        	var confirmedPassword = $('#confirmedPassword').val();
        	
        	if(newPassword != confirmedPassword){
        		alert('Passwords do not match!');
        	} else {
        		$.ajax({
        		  type: "POST",
    			  url: "/TestingSystem/TestingSystemServlet",
    			  data: {
    				  cmd: changePassword,
    				  accountId: $('#accountId').val()
    			  }
    			}).done(function() {
    			  $( this ).addClass( "done" );
    			});
        	}
        },
        'Cancel': function() {
          $( this ).dialog( "close" );
        }
      }
    });
    
    $( "#changePasswordDialog" ).dialog( "option", "hide");
});
