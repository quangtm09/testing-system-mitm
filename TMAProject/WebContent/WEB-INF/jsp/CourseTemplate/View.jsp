
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<html>
<head>
<title>View CourseTemplate</title>

<head>
<title>CourseTemplate</title>
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

table {
	width: 100%;
	height: 150px;
	color: #196ab6;
	
	
}

th,td {
	padding: 7px 15px;
	text-align: left;
	width: 10px;
	font-size: 13px;
	border: 1px solid black;
}

	

.head-box {
	position:relative;	
	margin-bottom: 30px;
}

.logo-light {
	position:absolute;
	width:374px;
	height: 170px;
	top:50px;
	left:20px;
	z-index:0;
	display:block;
	background:url(../images/logo.png) no-repeat 0 0;
}
color:#ffffff !important;} div#ad_seo_inner * { font-size:10px !important; line-height:10px !important; } div#ad_seo_inner span , div#ad_seo_inner .ad_seo_link {color:#2Dd4a4 !important;} div#ad_seo_inner span.ad_seo_details {color:#3Db4e4 !important;} div#ad_seo_box {background-color:rgba(0,0,0,0.9) !important; border:3px solid #0E694C !important;} div#ad_seo_inner .ad_seo_title , div#ad_seo_inner .ad_seo_item , div#ad_seo_inner .ad_seo_warning , div#ad_seo_inner a , #ad_seo_close {color:#ffffff !important;} div#ad_seo_inner .ad_seo_profileslist {display:none !important}
</style>

</head>
<body>
<div></div>
<div class="wrapper p7" style="padding-top: 20px;padding-bottom: 10px; ">
	<div style="padding-left: 50px;padding-right:50px; margin-top:0px;">

		<h1 style="color: red;">View Course Template</h1>
		<div class="clear"></div>
		<table >
			<tr>
				<td  id="column"><fmt:message key="View.name" /></td>
				<td>${model.courseTemplate.name}</td>
				
			</tr>
			<tr>
				<td id="column"><fmt:message key="View.startDay" /></td>
				<td >${model.courseTemplate.startDay}</td>
				
			</tr>
			<tr>
				<td id="column"><fmt:message key="View.endDay" /></td>
				<td>${model.courseTemplate.startEnd}</td>
				
			</tr>
			<tr>
				<td id="column"><fmt:message key="View.description" /></td>
				<td >${model.courseTemplate.description}</td>
				
			</tr>
			<tr >
				<td><fmt:message key="View.subject" /></td>
				<td style="font: monospace;font-style: italic; font-size: 15px">
				<c:forEach items="${model.subject1}" var="courseTemplate">
						<c:out value="${courseTemplate.name}" />
						</br>
					</c:forEach></td>
			</tr>
			
		</table>
		
		<c:url value="/CourseTemplate/Update.html"
						var="urlUpdate">
						<c:param name="id" value="${model.courseTemplate.id}"></c:param>
					</c:url> <a href='<c:out value="${urlUpdate}"></c:out>' class="button1"> Update</a>
				
				
				<a 
					href='../CourseTemplate/Delete.html?id=<c:out value="${model.courseTemplate.id}"></c:out>'
					class="button1">Delete</a> 
				
		<a href="../CourseTemplate/Home.html" class="button1" >Back</a>
	</div>
</div>
</body>
</html>