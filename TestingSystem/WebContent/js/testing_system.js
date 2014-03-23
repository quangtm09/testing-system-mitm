function login(cmd){
	$('input[name="cmd"]').val(cmd);
}

function logout(){
	window.location.href = '/TestingSystem/LogoutServlet';
}

// Document ready
$(function() {

});
