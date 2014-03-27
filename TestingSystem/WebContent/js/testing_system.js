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

function checkAdminRole(roleID){
	$('#roleID').val(roleID);
	$('#createUser').val('8');
	$('#updateProfile').val('9');
	$('#removeUser').val('12');
	$('#manageUser').val('11');
	$('#createQuest').val('17');
	$('#updateQuest').val('19');
	$('#viewQuest').val('18');
	$('#dropQuest').val('20');
	$('#createAccount').val('7');
	$('#dropAccount').val('16');
	$('#viewAccount').val('15');
	$('#createTest').val('1');
	$('#editTest').val('2');
	$('#takeTest').val('3');
	$('#viewTest').val('4');
	$('#updateTest').val('13');
	$('#deployTest').val('14');
	$('#deleteTest').val('5');
	$('#viewHist').val('10');
	$('#viewResult').val('6');
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
