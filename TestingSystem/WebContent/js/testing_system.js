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
		dateFormat: 'dd-mm-yy'
    });
	
	$("#datepicker").attr( 'readOnly' , 'true' );

    $( "#changePasswordDialog" ).dialog({
      resizable: false,
      height:250,
      width: 350,
      modal: true,
      autoOpen: false,
      buttons: {
        'Change': function() {

        	var newPassword = $.trim($('#newPassword').val());
        	var oldPassword = $.trim($('#oldPassword').val());
        	var confirmedPassword = $.trim($('#confirmedPassword').val());

        	if(newPassword !== confirmedPassword){
        		alert('Passwords do not match!');
        	} else if(newPassword == 0 || oldPassword == 0 || confirmedPassword == 0){
        		alert('Inputs cannot be blank!');
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
    
    $( "#addUserDialog" ).dialog({
        resizable: false,
        height:250,
        width: 350,
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
