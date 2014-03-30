var deleteAccountId;

function login(cmd){
	$('input[name="cmd"]').val(cmd);
}

function logout(){
	window.location.href = '/TestingSystem/LogoutServlet';
}

function deleteAccount(accountId){
	deleteAccountId = accountId;
	var message = 'Are you sure you want to delete <span style="color: red">' + accountId + '</span> account?';
	$('#deleteAccountMessage').html(message);
	$( "#deleteAccountDialog" ).dialog("open");
}

function changeAccountRole(accountId){
	$.ajax({
	  type: "POST",
		  url: "/TestingSystem/TestingSystemServlet",
		  data: {
			  cmd: 'changeAccountRole',
			  accountId: accountId,
			  userId: $('#userId').val(),
			  roleId: $('select[name=\'changeRole' + accountId + '\']').val()
		  },
		  success: function(data){
			  if(data === 'false'){
				  alert('Error while changing role[=' + $('select[name=\'changeRole' + accountId + '\']').find('option:selected').text() + '] for ' + accountId);
			  }
		  }
	}).done(function() {
			
	});
}

function openAddAccountDialog(){
	$('#newAccountId').val('');
	$('#accountPassword').val('');
	$('#accountConfirmedPassword').val('');
	$('#addAccountResult').html('');
	$("#selectRoleCombobox").find('option:selected').attr('selected', false);
    $("#selectRoleCombobox").find('option:first').attr('selected', true);
	$( "#addAccountDialog" ).dialog("open");
}

function openChangePasswordDialog(accountId){
	$('#accountId').val(accountId);
	$('#newPassword').val('');
	$('#oldPassword').val('');
	$('#confirmedPassword').val('');
	$('#changePasswordResult').html('');
	$( "#changePasswordDialog" ).dialog("open");
}

function openAddUserDialog(accountId){
	$('#userId').val('');
	$('#firstName').val('');
  	$('#lastName').val('');
  	$('#email').val('');
  	$('#addUserResult').html('');
	$( "#addUserDialog" ).dialog("open");
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

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 

// Document ready
$(function() {
	$( "#datepicker" ).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: 'dd-mm-yy',
		yearRange: "1900:" + (new Date().getFullYear() + 100)
    });
	
	$("#datepicker").attr( 'readOnly' , 'true' );

    $( "#changePasswordDialog" ).dialog({
      resizable: false,
      height:250,
      width: 400,
      modal: true,
      autoOpen: false,
      buttons: 
    	  [
           {
               text: 'Change',
               icons: {primary: "ui-icon-pencil"},
               click: function(){
	            	var newPassword = $.trim($('#newPassword').val());
	               	var oldPassword = $.trim($('#oldPassword').val());
	               	var confirmedPassword = $.trim($('#confirmedPassword').val());
	
	               	if(newPassword !== confirmedPassword){
	               		$('#changePasswordResult').html('<span style="color: red">Passwords do not match!</span>');
	               	} else if(newPassword == 0 || oldPassword == 0 || confirmedPassword == 0){
	               		$('#changePasswordResult').html('<span style="color: red">Inputs cannot be blank!</span>');
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
	           				setTimeout(function(){
	           					$( this ).dialog( "close" );
            				}, 2000);
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

    $( "#changePasswordDialog" ).dialog( "option", "hide");
    
    $( "#addUserDialog" ).dialog({
        resizable: false,
        height:270,
        width: 360,
        modal: true,
        autoOpen: false,
        buttons: [
                  {
                      text: 'Add',
                      icons: {primary: "ui-icon-circle-plus"},
                      click: function(){
                    	var firstName = $.trim($('#firstName').val());
                    	var lastName = $.trim($('#lastName').val());
                    	var email = $.trim($('#email').val());
                    	var userId = $.trim($('#userId').val());

                    	if(firstName.length == 0 || lastName.length == 0 || email.length == 0 || userId.length == 0){
                    		$('#addUserResult').html('Inputs cannot be blank.');
                    	} else if(!validateEmail(email)){
                    		$('#addUserResult').html('Wrong email pattern!');
                    	} else {
                    		$.ajax({
                    		  type: "POST",
                			  url: "/TestingSystem/TestingSystemServlet",
                			  data: {
                				  cmd: 'addUser',
                				  userId: userId,
                				  firstName: firstName,
                				  lastName: lastName,
                				  email: email
                			  },
                			  success: function(data){
                				  $('#addUserResult').html(data);
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
    
    $( "#addUserDialog" ).dialog( "option", "hide");
    
    $( "#addAccountDialog" ).dialog({
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
  	            	var password = $.trim($('#accountPassword').val());
  	               	var confirmedPassword = $.trim($('#accountConfirmedPassword').val());
  	               	var newAccountId = $.trim($('#newAccountId').val());
  	               	var accountRoleId = $.trim($('#selectRoleCombobox').val());
  	               	var userId = $('#userId').val();
  	
  	               	if(password !== confirmedPassword){
  	               		$('#addAccountResult').html('<span style="color: red">Passwords do not match!</span>');
  	               	} else if(accountId == 0 || password == 0 || confirmedPassword == 0){
  	               		$('#addAccountResult').html('<span style="color: red">Inputs cannot be blank!</span>');
  	               	} else {
  	               		$.ajax({
  	               		  type: "POST",
  	           			  url: "/TestingSystem/TestingSystemServlet",
  	           			  data: {
  	           				  cmd: 'addAccount',
  	           				  userId: userId,
  	           				  newAccountId: newAccountId,
  	           				  password: password,
  	           				  accountRoleId: accountRoleId
  	           			  },
  	           			  success: function(data){
  	           				  $('#addAccountResult').html(data);
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

      $( "#addAccountDialog" ).dialog( "option", "hide");
      
      $( "#deleteAccountDialog" ).dialog({
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
            				  url: "/TestingSystem/TestingSystemServlet",
            				  data: {
            					  cmd: 'deleteAccount',
            					  accountId: deleteAccountId,
            				  },
            				  success: function(data){
            					  if(data === 'false'){
            						  alert('Error while deleting account: ' + deleteAccountId);
            					  } else {
            						  alert('Deleted ' + deleteAccountId + ' successfully!');
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

        $( "#deleteAccountDialog" ).dialog( "option", "hide");
    
    $( "#addAccountButton" ).button({
        icons: {
          primary: "ui-icon-circle-plus"
        }
     });
    
    $( "#searchButton" ).button({
        icons: {
          primary: "ui-icon-search"
        }
     });
    
    $( "#addUser" ).button({
        icons: {
          primary: "ui-icon-plusthick"
        }
     });
    
    $( "#editUserButton" ).button({
        icons: {
          primary: "ui-icon-wrench"
        }
     });
    
    
    $( "#logoutButton" ).button({
        icons: {
          primary: "ui-icon-power"
        }
     });
    
    $( "#backButton" ).button({
        icons: {
          primary: "ui-icon-arrowreturnthick-1-w"
        }
     });
    
});
