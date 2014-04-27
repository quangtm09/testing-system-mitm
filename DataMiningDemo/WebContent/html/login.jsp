<%@include file="/html/init.jsp" %>

<%
	boolean isWrongUsernameOrPassword = (Boolean) DMUtil.getAttribute(request, "isWrongUsernameOrPassword", true);
	boolean isUserLoggedIn = DMUtil.isUserLoggedIn(session);
%>

<c:if test='<%=isUserLoggedIn %>'>
	<c:redirect url="<%=DMConstant.DM_SERVLET %>"/>
</c:if>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Login Page</title>

<link rel="stylesheet" type="text/css"
	href="<%=DMConstant.LOGIN_PAGE_CSS%>" />
</head>

<body>

	<div class="container">
		<div align="center">
			<span style="font-size: 50px">Welcome to Data Mining System!</span><br>
			<span style="font-size: 23px">Applying Data Mining into Data Warehouse Database</span>
			<br><br>
			<img alt="Test" src="/DataMiningDemo/images/DataMining.jpg">
		</div>
		<form class="form-signin" action="<%=DMConstant.LOGIN_SERVLET%>" name="loginForm"
		method="POST" onsubmit="return toSubmit();">
			<h2 class="form-signin-heading">Please sign in</h2>
			
			<c:choose>
				<c:when test="<%=!isWrongUsernameOrPassword%>">
					<%@ include file="/html/messages/errorMessage.jspf"%>
				</c:when>
			</c:choose>
			
			<input name="<%=DMConstant.CMD%>" type="hidden" />
			
			<div id="error" style="color: red; padding-left: 17px; margin-bottom: 15px"></div>
			
			<input type="text" id="userId"
			name="userId" class="form-control" placeholder="User ID"
				required autofocus> <input type="password" id="password" name="password"
				class="form-control" placeholder="Password" required> <label
				class="checkbox"> <input type="checkbox" value="remember-me">
				Remember me
			</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit" value="Login"
				onclick="login('<%=DMConstant.LOGIN%>');">Login</button>
		</form>

	</div>
	<!-- /container -->
</body>
</html>

<div id="domMessage" style="display:none;"> 
    <img src="/DataMiningDemo/images/loader.gif" alt="Loading..."/><br/><h1>Now loading... Please wait</h1>
</div>

<script type="text/javascript">

	function toSubmit() {
		blockUI();
		
		var userId = $.trim($('#userId').val());
		var password = $.trim($('#password').val());
		
		if (userId.length == 0 || password.length == 0) {
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