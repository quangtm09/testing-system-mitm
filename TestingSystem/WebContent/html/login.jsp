<%@ include file="/html/init.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=TSConstants.LOGIN_PAGE_CSS%>" />

<%
	boolean isLoginSuccess = (Boolean) TSUtil.getAttribute(request, "isLoginSuccess", true);
	//if(session.getAttribute("username") != null){
	//	response.sendRedirect(TSConstants.TESTING_SYSTEM_SERVLET);
	//}
%>

<c:if test='<%=session.getAttribute("username") != null %>'>
	<c:redirect url="<%=TSConstants.TESTING_SYSTEM_SERVLET %>"/>
</c:if>

<!-- Begin Page Content -->
<div id="container">
	<form action="<%=TSConstants.TESTING_SYSTEM_SERVLET%>" name="loginForm"
		method="POST">
		<!-- Display error message if login is not success -->
		<!-- error message can be configured in servlet -->
		<c:choose>
			<c:when test="<%=!isLoginSuccess%>">
				<%@ include file="/html/message/errorMessage.jspf"%>
			</c:when>
		</c:choose>
		
		<input name="<%=TSConstants.CMD%>" type="hidden" /> <label
			for="username">Username:</label> <input type="text" id="username"
			name="username"> <label for="password">Password:</label> <input
			type="password" id="password" name="password">
		<div id="lower">
			<input type="checkbox"><label class="check" for="checkbox">Keep
				me logged in</label> <input type="submit" value="Login"
				onclick="login('<%=TSConstants.LOGIN%>');">
		</div>
		<!--/ lower-->
	</form>
</div>
<!--/ container-->
<!-- End Page Content -->