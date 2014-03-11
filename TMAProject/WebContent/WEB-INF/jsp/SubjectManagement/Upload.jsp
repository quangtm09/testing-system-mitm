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

<style type="text/css">
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
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>
<script type="text/javascript">
	/*function passSubject() {
		var myTest = upload.subjectList.options[upload.subjectList.selectedIndex].text;
		document.getElementById('subjectName').value = myTest;
	})*/

	function onClick() {
		var selectedIndex = $("#subjectList option:selected:first").text();
		$("#subjectName").val(selectedIndex);
	}
</script>

</head>
<body>
	<div class="wrapper p7">
		<div style="padding-left: 150px; padding-top: 0px; margin-top: 0px;">
			<h1 style="color: red;">
				<fmt:message key="Subject.Upload.Title" />
			</h1>
			<div class="clear"></div>
			<p />
			<form:form commandName="fileUploadForm" enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div" />
				<table id="tableupdate">
					<tr>
						<td><fmt:message key="SubjectName" />: <form:input
								path="subjectName" id="subjectName" /> <fmt:message
								key="Subject.Select" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td><fmt:message key="Upload.Select" /><input type="file"
							name="materialFile" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td><fmt:message key="Subject.List" /></br> <form:select
								path="subjectList" cssClass="select" items="${subjectList}"
								multiple="true" size="10" class="subjectList"
								onchange="onClick()">
							</form:select>
						</td>
					</tr>
				</table>
				<br />
				<input class="button1" id="Upload" type="submit" name="Upload"
					value="Upload" />
				<a href="../SubjectManagement/Home.html" class="button1">Back</a>
			</form:form>
		</div>
	</div>

</body>
</html>