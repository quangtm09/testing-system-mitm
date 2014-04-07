<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="constants.TSConstants"%>

<%
	boolean isWrongUsernameOrPassword = (Boolean) TSUtil.getAttribute(request, "isWrongUsernameOrPassword", true);
	boolean isUserLoggedIn = TSUtil.isUserLoggedIn(session);
%>

<c:if test='<%=isUserLoggedIn %>'>
	<c:redirect url="<%=TSConstants.TESTING_SYSTEM_SERVLET %>"/>
</c:if>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Login Page</title>
<script type="text/javascript" src="/TestingSystem/js/jquery.js"></script>
<script type="text/javascript" src="/TestingSystem/js/jquery.blockUI.js"></script>
<link rel="stylesheet"
	href="/TestingSystem/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/TestingSystem/bootstrap/css/bootstrap-theme.min.css">
<script src="/TestingSystem/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=TSConstants.LOGIN_PAGE_CSS%>" />
</head>

<body>

	<div class="container">
		<div align="center"><span style="font-size: 50px">Welcome to Testing System!</span><br><br>
			<img alt="Test" src="/TestingSystem/images/online-testing-assessments2.jpg">
		</div>
		<form class="form-signin" action="<%=TSConstants.TESTING_SYSTEM_SERVLET%>" name="loginForm"
		method="POST" onsubmit="return toSubmit();">
			<h2 class="form-signin-heading">Please sign in</h2>
			
			<c:choose>
				<c:when test="<%=!isWrongUsernameOrPassword%>">
					<%@ include file="/html/message/errorMessage.jspf"%>
				</c:when>
			</c:choose>
			
			<div id="error" style="color: red; padding-left: 17px; margin-bottom: 15px"></div>
			
			<input name="<%=TSConstants.CMD%>" type="hidden" />
			
			<input type="text" id="accountId"
			name="accountId" class="form-control" placeholder="Account ID"
				required autofocus> <input type="password" id="password" name="password"
				class="form-control" placeholder="Password" required> <label
				class="checkbox"> <input type="checkbox" value="remember-me">
				Remember me
			</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit" value="Login"
				onclick="login('<%=TSConstants.LOGIN%>');">Login</button>
		</form>

	</div>
	<!-- /container -->
</body>
</html>

<div id="domMessage" style="display:none;"> 
    <img src="/TestingSystem/images/loader.gif" alt="Loading..."/><br/><h1>Now loading... Please wait</h1>
</div>

<script type="text/javascript">

	function toSubmit() {
		blockUI();
		
		var accountId = $.trim($('#accountId').val());
		var password = $.trim($('#password').val());
		
		if (accountId.length == 0 || password.length == 0) {
			$.unblockUI();
			return false;
		}
		
	}

	function login(cmd) {
		$('input[name="cmd"]').val(cmd);
	}

	function blockUI() {
		$.blockUI({
			message : $('#domMessage'),
			css : {
				border : 'none',
				padding : '15px',
				backgroundColor : '#000',
				'-webkit-border-radius' : '10px',
				'-moz-border-radius' : '10px',
				opacity : .5,
				color : '#fff'
			}
		});
	}
</script>