<%@ include file="/WEB-INF/jsp/include/lib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CourseTemplate</title>
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
<body>
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

		function loadSubjectPage() {
			$("#sresult").load("SubjectManagement.html");
		}

		function loadViewPage(subjectId) {
			$("#sresult").load("SubjectManagement.html", {
				subjectId : subjectId
			});
		}

		$(document).ready(function() {
			$("#sresult").ajaxStart(function() {
				$("#wait").css("display", "block");
			});

			$("#sresult").ajaxComplete(function() {
				$("#wait").css("display", "none");
			});

			loadSubjectPage();
		});
	</script>
	<div></div>
	<div class="wrapper p7">
		<div id="search-form" method="get" enctype="multipart/form-data">
			<fieldset>
				<div class="search-form">
					<input type="text" id="searchfield" maxlength="50"
						value="Search..." onfocus="clearText()" onblur="clearText()">
					<a href="#" onclick="doAjaxSearch($('#searchfield').val(),1)"></a>
				</div>

			</fieldset>

		</div>

		<div id="wait"
			style="display: none; width: 69px; height: 89px; border: 1px solid black; position: absolute; top: 50%; left: 50%; padding: 2px;">
			<img
				src="F:\Study\Internship_Spring-Hibernate\project_web\WebContent\WEB-INF\jsp\SubjectManagement\demo_wait.gif"
				width="64" height="64" /><br />Loading...
		</div>

		<div style="float: right; margin-right: 50px;">
			<a href="../SubjectManagement/Upload.html" class="button1">Upload
				Material</a>
		</div>
		<div style="float: right; margin-right: 30px;">
			<a href="../SubjectManagement/Add.html" class="button1">Add
				Subject</a>
		</div>

		<div class="clear"></div>
		<div id="sresult"></div>
	</div>
</body>
</html>