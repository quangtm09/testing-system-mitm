<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>Registration Page</title>
<link href="tablecloth.css" rel="stylesheet" type="text/css"  />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href=" js/colour.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" href=" js/template.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" type="text/css" href=" js/jquery-ui.css" />
<script type="text/javascript" src=" js/jquery.js"></script>
<script type="text/javascript" src=" js/tooltipsy.min.js">
</script>
<script type="text/javascript" src=" js/jquery.form.js"></script>
<script type="text/javascript" src=" js/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src=" js/jquery-ui-1.8.16.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href=" js/jquery.autocomplete.css" />
<link rel="stylesheet" href=" css/header.css" type="text/css"
	media="screen" charset="utf-8" />


<link rel="stylesheet" type="text/css" href=" js/jquery-ui.css" />
<script type="text/javascript" src=" js/jquery.min.js"></script>
<script type="text/javascript" src=" js/jquery-ui.min.js"></script>

<meta charset="utf-8">  
    <link rel="icon" href=" resource/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href=" resource/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href=" resource/css/demo.css" type="text/css" media="all">  
    <script src=" resource/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src=" resource/js/superfish.js" type="text/javascript"></script>
    <script src=" resource/js/jcarousellite_1.0.1.js" type="text/javascript"></script>
    <script type="text/javascript" src=" resource/js/easyTooltip.js"></script>
    <script src=" resource/js/script.js" type="text/javascript"></script>
    <script type="text/javascript" async="" src=" resource/js/ga.js"></script>
    <script type="text/javascript" src=" resource/js/tms-0.3.js"></script>
    <script type="text/javascript" src=" resource/js/tms_presets.js"></script>
    <script type="text/javascript" src=" resource/js/demo.js"></script> 
    <script src=" resource/js/jquery.hoverIntent.js" type="text/javascript"></script>


</head>
<body>
<div class="wrapper p7" >
	 	<fieldset style="width:700px;">
	 		<legend ><b style="margin-left: 320px;">Login Form</b></legend>
	 		<div>
		 		<form:form commandName="accountBean" action="login.html">
		 			<table style="margin-left: 250px;">
		 				<tr>
		 					<td>User Name</td>
		 					<td><form:input path="accountName" /><span style="color:red">*</span></td>
		 					<td><form:errors path="accountName" cssClass="error"/></td>
		 				</tr>
		 				<tr>
		 					<td>Password</td>
		 					<td><form:password path="password" /><span style="color:red">*</span></td>
		 					<td><form:errors path="password" cssClass="error"/></td>
		 				</tr>
		 				<tr>
		    				<td colspan="2"><span style="color:blue;font-weight:bold;">${message}</span></td>
		    			</tr>
		 				<tr>
			    			<td colspan="2" align="right">
								<input type="submit" value="Login" class='button medium blue'/>
								<input type="reset" value="Clear" class='button medium blue'/>
			    			</td>
    					</tr>
		 			</table>
		 		</form:form>
	 		</div>
	 	</fieldset>
	 
	 </div>
</body>
</html>