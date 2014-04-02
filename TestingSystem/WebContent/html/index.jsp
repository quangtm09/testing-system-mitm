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

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="user_profile_url">
	<c:param name="tsTab" value="user-profile"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="home_url">
	<c:param name="tsTab" value="home"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="account_management_url">
	<c:param name="tsTab" value="account-management"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="user_management_url">
	<c:param name="tsTab" value="user-management"/>
</c:url>

<c:url value="<%=TSConstants.ROLE_MANAGEMENT_SERVLET_URL_PATTERN%>" var="role_management_url">
	<c:param name="tsTab" value="role-management"/>
</c:url>

<c:url value="<%=TSConstants.LOG_MANAGEMENT_SERVLET_URL_PATTERN%>" var="log_management_url">
	<c:param name="tsTab" value="log-management"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="contact_us_url">
	<c:param name="tsTab" value="contact-us"/>
</c:url>

<%
	String tsTab = (String) TSUtil.getAttribute(request, "tsTab", StringPool.BLANK);
%>

<div id="menu-wrapper">
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="${home_url}" onclick="blockUI()">Homepage</a></li>
			<li><a href="${user_profile_url}" onclick="blockUI()">User Profile</a></li>
			<li><a href="${account_management_url}" onclick="blockUI()">Account Management</a></li>
			<li><a href="${user_management_url}"onclick="blockUI()">User Management</a></li>
			<li><a href="${role_management_url}"onclick="blockUI()">Role Management</a></li>
			<li><a href="${log_management_url}"onclick="blockUI()">Log Management</a></li>
			<!-- <li><a href="${contact_us_url}">Contact Us</a></li> -->
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
					<c:when test='<%=tsTab.equals("account-management") %>'>
						<%@include file="/html/account_management.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("user-management") %>'>
						<%@include file="/html/user_management.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("role-management") %>'>
						<%@include file="/html/role_management.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("log-management") %>'>
						<%@include file="/html/log_management.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("contact-us") %>'>
						<%@include file="/html/contact_us.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("user-details") %>'>
						<%@include file="/html/user_details.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("edit-user") %>'>
						<%@include file="/html/edit_user.jspf" %>
					</c:when>
					<c:when test='<%=tsTab.equals("404") %>'>
						<%@include file="/html/404.jspf" %>
					</c:when>
					<c:otherwise>
						<%@include file="/html/home.jspf" %>
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
