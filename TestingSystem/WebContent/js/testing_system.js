function login(cmd){
	$('input[name="cmd"]').val(cmd);
}

function logout(){
	window.location.href = '/TestingSystem/LogoutServlet';
}

function openChangePasswordDialog(accountId){
	$('#accountId').val(accountId);
	$('#newPassword').val('');
	$('#oldPassword').val('');
	$('#confirmedPassword').val('');
	$('#changePasswordResult').html('');
	$( "#changePasswordDialog" ).dialog("open");
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
        	var oldPassword = $('#oldPassword').val();
        	var confirmedPassword = $('#confirmedPassword').val();
        	
        	if(newPassword !== confirmedPassword){
        		alert('Passwords do not match!');
        	} else {
        		$.ajax({
        		  type: "POST",
    			  url: "/TestingSystem/TestingSystemServlet",
    			  data: {
    				  cmd: 'changePassword',
    				  accountId: $('#accountId').val(),
    				  newPassword: newPassword,
    				  oldPassword: oldPassword
    			  },
    			  success: function(data){
    				  $('#changePasswordResult').html(data);
                  }
    			}).done(function() {
    				// Do something
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
