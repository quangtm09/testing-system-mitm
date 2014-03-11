<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link href="/ProjectTMA01/css/reset.css" rel="stylesheet"
	type="text/css">
	
   <meta charset="utf-8">
    <link rel="stylesheet" href="resource/css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="resource/css/grid.css" type="text/css" media="screen">   
    <link rel="icon" href="resource/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="resource/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="resource/css/demo.css" type="text/css" media="all">  
    <script src="resource/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="resource/js/superfish.js" type="text/javascript"></script>
    <script src="resource/js/jcarousellite_1.0.1.js" type="text/javascript"></script>
    <script type="text/javascript" src="resource/js/easyTooltip.js"></script>
    <script src="resource/js/script.js" type="text/javascript"></script>
    <script type="text/javascript" async="" src="resource/js/ga.js"></script>
    <script type="text/javascript" src="resource/js/tms-0.3.js"></script>
    <script type="text/javascript" src="resource/js/tms_presets.js"></script>
    <script type="text/javascript" src="resource/js/demo.js"></script> 
    <script src="resource/js/jquery.hoverIntent.js" type="text/javascript"></script>
    
<style type="text/css">div#ad_seo_inner { color:#ffffff !important;} div#ad_seo_inner * { font-size:10px !important; line-height:10px !important; } div#ad_seo_inner span , div#ad_seo_inner .ad_seo_link {color:#2Dd4a4 !important;} div#ad_seo_inner span.ad_seo_details {color:#3Db4e4 !important;} div#ad_seo_box {background-color:rgba(0,0,0,0.9) !important; border:3px solid #0E694C !important;} div#ad_seo_inner .ad_seo_title , div#ad_seo_inner .ad_seo_item , div#ad_seo_inner .ad_seo_warning , div#ad_seo_inner a , #ad_seo_close {color:#ffffff !important;} div#ad_seo_inner .ad_seo_profileslist {display:none !important}</style></head>
</head>
<body id="page1">
	<div class="main-bg">
		<div class="bg">
			<div class="bg1">

				<div id="header">
					<tiles:insertAttribute name="header"></tiles:insertAttribute>
				</div>
				<section id="content">
				<div class="main">
					<tiles:insertAttribute name="body"></tiles:insertAttribute>
				</div>
				</section>

				<div id="footer">
					<tiles:insertAttribute name="footer"></tiles:insertAttribute>
				</div>
			</div>
		</div>
	</div>
</body>


</html>