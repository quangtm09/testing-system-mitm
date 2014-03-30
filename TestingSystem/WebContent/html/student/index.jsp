<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@include file="/html/init.jsp"%>

<%
	String username = TSUtil.getUsernameByHttpSession(session);
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Testing System - G4</title>
<link href="<%=TSConstants.TESTING_SYSTEM_PATH + "/css/index.css"%>" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<c:url value="<%=TSConstants.STUDENT_SERVLET_URL_PATTERN%>" var="home_url">
	<c:param name="tsTab" value="home"/>
</c:url>

<c:url value="<%=TSConstants.STUDENT_SERVLET_URL_PATTERN%>" var="user_profile_url">
	<c:param name="tsTab" value="user-profile"/>
</c:url>

<c:url value="<%=TSConstants.STUDENT_SERVLET_URL_PATTERN%>" var="take_test_url">
	<c:param name="tsTab" value="take-test"/>
</c:url>

<c:url value="<%=TSConstants.STUDENT_SERVLET_URL_PATTERN%>" var="result_management_url">
	<c:param name="tsTab" value="result-management"/>
</c:url>

<%
	String tsTab = (String) TSUtil.getAttribute(request, "tsTab", StringPool.BLANK);
%>

<div id="menu-wrapper">
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="${home_url}">Homepage</a></li>
			<li><a href="${account_management_url}">User Profile</a></li>
			<li><a href="${take_test_url}">Take Test</a></li>
			<li><a href="${result_management_url}">Result Management</a></li>
			<li style="color: yellow;">
				&nbsp;Welcome, <%=username %>!&nbsp;
				<button id="logoutButton" onclick="logout();">Logout</button>
			</li>
		</ul>
	</div>
	<!-- end #menu --> 
</div>
<div id="header-wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="#">Testing System</a></h1>
			<p>Designed by Group 4</p>
		</div>
	</div>
</div>
<div id="wrapper"> 
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<c:choose>
					<c:when test='<%=tsTab.equals("user-profile") %>'>
						<%@include file="/html/user_profile.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("take-test") %>'>
						<%@include file="/html/student/take_test.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("result-management") %>'>
						<%@include file="/html/student/result_management.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("contact-us") %>'>
						<%@include file="/html/contact_us.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("user-details") %>'>
						<%@include file="/html/user_details.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("404") %>'>
						<%@include file="/html/404.jspf" %>
					</c:when>
					<c:otherwise>
						<%@include file="/html/student/home.jspf" %>
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</div>
	<!-- end #page --> 
</div>
<div id="footer">
	<p>&copy; 2014 testingsystem.com | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by Group 4</p>
</div>
<!-- end #footer -->
</body>
</html>
