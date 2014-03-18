<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="util.StringPool"%>
<%@include file="/html/init.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Testing System - G4</title>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700|Archivo+Narrow:400,700" rel="stylesheet" type="text/css">
<link href="<%=TSConstants.TESTING_SYSTEM_PATH + "/css/style.css"%>" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="home_url">
	<c:param name="ts-tab" value="home"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="account_management_url">
	<c:param name="ts-tab" value="account-management"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="user_management_url">
	<c:param name="ts-tab" value="user-management"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="role_management_url">
	<c:param name="ts-tab" value="role-management"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="log_management_url">
	<c:param name="ts-tab" value="log-management"/>
</c:url>

<c:url value="<%=TSConstants.TESTING_SYSTEM_SERVLET_URL_PATTERN%>" var="contact_us_url">
	<c:param name="ts-tab" value="contact-us"/>
</c:url>

<%
	String tsTab = (String) TSUtil.getAttribute(request, "ts-tab", StringPool.BLANK);
%>

<div id="menu-wrapper">
	<div id="menu">
		<ul>
			<li style="float: right;color: red;">Welcome, <%=session.getAttribute("username") %>!</li>
			<li class="current_page_item"><a href="${home_url}">Homepage</a></li>
			<li><a href="${account_management_url}">Account Management</a></li>
			<li><a href="${user_management_url}">User Management</a></li>
			<li><a href="${role_management_url}">Role Management</a></li>
			<li><a href="${log_management_url}">Log Management</a></li>
			<li><a href="${contact_us_url}">Contact Us</a></li>
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
					<c:when test='<%=tsTab.equals(StringPool.BLANK)||tsTab.equals("home") %>'>
						<%@include file="/html/home.jspf" %>
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
