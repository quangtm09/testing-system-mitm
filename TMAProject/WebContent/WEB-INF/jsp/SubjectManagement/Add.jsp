<%@ include file="/WEB-INF/jsp/include/lib.jsp"%>

<html>
<head>
<title><fmt:message key="Insert.titlte" />
</title>
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


<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

</head>
<body>
	<div class="wrapper p7">
		<div style="padding-left: 150px; padding-top: 0px; margin-top: 0px;">
			<h1 style="color: red">
				<fmt:message key="Subject.Add" />
			</h1>
			<div class="clear"></div>
			<p />
			<form:form commandName="Add">
				<form:errors path="*" cssClass="errorblock" element="div" />
				<table id="tableupdate">
					<tr>
						<td><fmt:message key="Subject.Name" />
						</td>
						<td style="padding-top: 40px;"><form:input path="subjectName" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="Subject.Description" />
						</td>
						<td style="padding-top: 40px;"><form:textarea
								path="subjectDescription" cols="50" rows="10" />
						</td>
					</tr>
				</table>
				<br />
				<input class="button1" type="submit" name="Add Subject"
					value="Add Subject" />
		&nbsp;&nbsp;&nbsp;
		
		<a href="../SubjectManagement/Home.html" class="button1">Back </a>
			</form:form>
		</div>
	</div>

</body>
</html>