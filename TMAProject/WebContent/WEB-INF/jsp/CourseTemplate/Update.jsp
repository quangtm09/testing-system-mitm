<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<html  style="margin: 0px auto;">
<head>
<title>Update</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../js/colour.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" href="../js/template.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" type="text/css" href="../js/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/tooltipsy.min.js">
</script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="../js/jquery-ui-1.8.16.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../js/jquery.autocomplete.css" />
<link rel="stylesheet" href="../css/header.css" type="text/css"
	media="screen" charset="utf-8" />


<link rel="stylesheet" type="text/css" href="../js/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

<meta charset="utf-8">  
    <link rel="icon" href="../resource/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../resource/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../resource/css/demo.css" type="text/css" media="all">  
    <script src="../resource/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="../resource/js/superfish.js" type="text/javascript"></script>
    <script src="../resource/js/jcarousellite_1.0.1.js" type="text/javascript"></script>
    <script type="text/javascript" src="../resource/js/easyTooltip.js"></script>
    <script src="../resource/js/script.js" type="text/javascript"></script>
    <script type="text/javascript" async="" src="../resource/js/ga.js"></script>
    <script type="text/javascript" src="../resource/js/tms-0.3.js"></script>
    <script type="text/javascript" src="../resource/js/tms_presets.js"></script>
    <script type="text/javascript" src="../resource/js/demo.js"></script> 
    <script src="../resource/js/jquery.hoverIntent.js" type="text/javascript"></script>


</head>
<body >
	<script type="text/javascript">

	var req;
	var target;
	var target1;
	var isIE;

	function initRequest(url) {
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	function validateUserId() {
		if (!target)
			target = document.getElementById("user");
		var url = "../ValidateUserServlet?user="
				+ escape(target.value);
		initRequest(url);
		req.onreadystatechange = processRequest;
		req.open("GET", url, true);
		req.send(null);
	}

	function processRequest() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				var message = req.responseXML.getElementsByTagName("valid")[0].childNodes[0].nodeValue;
				setMessageUsingDOM(message);

			}
		}
	}
	function setMessageUsingDOM(message) {
		var userMessageElement = document.getElementById("show");
		var messageText;
		if (message == "false") {
			userMessageElement.style.color = "red";
			messageText = "";
		} else {
			userMessageElement.style.color = "green";
			messageText = "Exist Name";
		}
		var messageBody = document.createTextNode(messageText);

		if (userMessageElement.childNodes[0]) {
			userMessageElement.replaceChild(messageBody,
					userMessageElement.childNodes[0]);
		} else {
			userMessageElement.appendChild(messageBody);
		}
	}

	$(document).ready(function() {
		$("#db").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy",
		});
		$("#db1").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy",
		});
	});
	
	$(document).ready(function(){
		$("#db").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy",
		});
		$("#db1").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy",
		});
		});
</script>

<style tyle="text/css">
	
	
h1,h2,h3,h4,h5,h6 {
	color: white;
	margin-top:-70px;
	font-family: 'Boogaloo', cursive;
	font-size:30px;
	color:#fff;
	line-height:1.2em;
	text-shadow:0 2px 2px #1d7eaf;
	font-weight:normal;
}
</style>
<div class="wrapper p7">
	<div style="padding-left: 150px;padding-top:0px; margin-top:0px;">
	<h1 style="color: red;"><fmt:message key="Update.title"/></h1>
	<div class="clear"></div>
	<p />
	<div>
	<form:form commandName="CourseTemplateBean">
		<table id="tableupdate">
			<tr>
				<td><fmt:message key="Update.name"/></td>
				<td style="padding-top: 40px;"><form:input path="name" /></td>
				<td><form:errors path="name" cssStyle="color:red;font-family: 'Boogaloo', cursive;" /></td>
			</tr>
			<tr>
				<td><fmt:message key="Update.startDay"/></td>
				<td style="padding-top: 40px;"><form:input path="startDay" id="db" /></td>
				<td><form:errors path="startDay" cssStyle="color:red;font-family: 'Boogaloo', cursive;" /></td>
			</tr>
			<tr>
				<td><fmt:message key="Update.endDay"/></td>
				<td style="padding-top: 40px;"><form:input id="db1" path="startEnd" /></td>
				<td style="width:300px;"><form:errors path="startEnd" cssStyle="color:red;font-family: 'Boogaloo', cursive;" /></td>
			</tr>
			<tr>
				<td class="description"><fmt:message key="Update.description"/></td>
				<td colspan="2"  style="padding-top: 40px;"><form:textarea path="description" cols="50"
						rows="10" /></td>
						<td><form:errors path="description" cssStyle="color:red;font-family: 'Boogaloo', cursive;" /></td>
			</tr>
			<tr>
				<td><fmt:message key="Update.subject"/></td>
				<td>
					<!-- <c:forEach items="${CourseTemplateBean.subject}"
						var="courseTemplate">
						<c:out value="${courseTemplate.subjectName}" />
						</br>

					</c:forEach> --> 
					<form:select path="inter" cssClass="select"
						items="${javaSkillsList}" multiple="true" size="10"  >
					</form:select>
				</td>
				<td><form:errors path="SubjectError" cssStyle="color:red;" /></td>
			</tr>
		</table>
		<br />
		<input class="button1" type="submit" name="update" value="Update" />
		&nbsp;&nbsp;&nbsp;
		<a
			href='../CourseTemplate/Delete.html?id=<c:out value="${CourseTemplateBean.id}"></c:out>'
			class="button1">Delete </a>
		&nbsp;&nbsp;&nbsp;
		<a href="../CourseTemplate/Home.html" class="button1">Back </a>
	</form:form>
	
	</div>
	</div>
</body>
</html>