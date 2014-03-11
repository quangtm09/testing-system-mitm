<%@ include file="/WEB-INF/jsp/include/lib.jsp"%>


<html>
<head>
<title><c:out value="${subjectBean.subjectName}"></c:out>
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


<script type="text/javascript">
	/*	function doAjaxSearch(key, pageNo) {
			if (key == 'Search...')
				key = '';
			$("#sresult").load("../CourseTemplate/Search.html", {
				keyword : key,
				page : pageNo
			});
		}*/

	function clearText() {
		if ($("#searchfield").val() == '') {
			document.getElementById('searchfield').value = 'Search...';
		} else {
			if ($("#searchfield").val() == 'Search...') {
				document.getElementById('searchfield').value = '';

			}
		}
	}

	function loadMaterialPage(subjectId) {
		$("#materialList").load("Material.html", {
			subjectId : subjectId
		});
	}

	$(document).ready(function() {
		var subjectId = $("td#subjectId").attr("title");
		loadMaterialPage(subjectId);

		$("#materialList").ajaxStart(function() {
			$("#wait").css("display", "block");
		});

		$("#materialList").ajaxComplete(function() {
			$("#wait").css("display", "none");
		});
	});
</script>
</head>
<body>
	<div class="wrapper p7">

		<div style="padding-left: 150px; padding-top: 0px; margin-top: 0px;">
			<h1 style="color: red;">
				<c:out value="${subjectBean.subjectName}"></c:out>
			</h1>
			<div id="wait"
				style="display: none; width: 69px; height: 89px; border: 1px solid black; position: absolute; top: 50%; left: 50%; padding: 2px;">
				<img
					src="F:\Study\Internship_Spring-Hibernate\project_web\WebContent\WEB-INF\jsp\SubjectManagement\demo_wait.gif"
					width="64" height="64" /><br />Loading...
			</div>
			<div style="float: right; margin-right: 50px;">
				<c:url value="../SubjectManagement/Modify.html" var="urlModify">
					<c:param name="subjectId" value="${subjectBean.subjectId}"></c:param>
				</c:url>
				<a href='<c:out value="${urlModify}"></c:out>' class="button1">
					Modify</a> <a
					href='../SubjectManagement/DeleteSubject.html?subjectId=<c:out value="${subjectBean.subjectId}"></c:out>'
					class="button1">Delete</a> <a href="../SubjectManagement/Home.html"
					class="button1">Back</a>
			</div>
			<div class="clear"></div>
			<table>
				<tr>
					<td id="column"><fmt:message key="Subject.ID" />
					</td>
					<td id="subjectId"
						title="<c:out value="${subjectBean.subjectId}"/>"><c:out
							value="${subjectBean.subjectId}"></c:out>
					</td>
					<td />
				</tr>
				<tr>
					<td id="column"><fmt:message key="Subject.Name" />
					</td>
					<td><c:out value="${subjectBean.subjectName}"></c:out>
					</td>
					<td />
				</tr>
				<tr>
					<td id="column"><fmt:message key="Subject.Description" />
					</td>
					<td><c:out value="${subjectBean.subjectDescription}"></c:out>
					</td>
					<td />
				</tr>
				<tr>
					<td id="column">List of material(s):</td>
					<td />
				</tr>
			</table>
		</div>
		<div id="materialList"></div>


	</div>
</body>
</html>