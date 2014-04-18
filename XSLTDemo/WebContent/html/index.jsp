<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="/XSLTDemo/css/bootstrap.min.css">
	    <link rel="stylesheet" href="/XSLTDemo/css/bootstrap.vertical-tabs.min.css">
	    <link rel="stylesheet" href="/XSLTDemo/css/index.css">
	
	    <script src="/XSLTDemo/js/jquery.min.js"></script>
	    <script src="/XSLTDemo/js/bootstrap.min.js"></script>
		<title>XML Session 2 Demo - XSLT</title>
	</head>
	<body>
		<center>
			<h1>XML Session 2 Demo: XSL/XSLT</h1>
		</center>
		<div style="width: 100%;">
			<hr/>
			<ul class="nav nav-tabs" id="navigationTabs">
			  <li class="active"><a href="#XMLAndXSL" data-toggle="tab" style="font-size: 25px">XML/XSL</a></li>
			  <li><a href="#XMLAndXSD" data-toggle="tab" style="font-size: 25px">XML/XSD</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content" id="tabContents">
				<br>
				<div class="tab-pane active" id="XMLAndXSL">
					
				  	<div style="width: 50%; float: left;" >
				  		<button id="transformXmlButton">Transform XML File</button>
				  		<%@include file="/html/animalXML.jsp" %>
				  	</div>
					<div style="width: 50%; float: left;">
						<%@include file="/html/animalXSL.jsp" %>
					</div>
				</div>
				
				<div class="tab-pane" id="XMLAndXSD">
				  	<div style="width: 50%; float: left;" >
				  		<%@include file="/html/animalXML.jsp" %>
				  	</div>
					<div style="width: 50%; float: left;">
						<%@include file="/html/animalXSD.jsp" %>
					</div>
				</div>
				
			</div>
			
		</div>
	</body>
	
	<script type="text/javascript">
		$(function() {
			$('button#transformXmlButton').click(function() {
				$.ajax({
					  type: "POST",
						  url: "/XSLTDemo/XSLTDemoServlet",
						  data: {
							  
						  },
						  success: function(data){
							  $('#transformResultLI').remove();
							  $('#transformResult').remove();
							  
							  $('ul#navigationTabs').append('<li id="transformResultLI"><a href="#transformResult" data-toggle="tab" style="font-size: 25px">Transform Result</a></li>');
							  $('div#tabContents').append('<div class="tab-pane" id="transformResult">' + data + '</div>');
						  }
				}).done(function() {
					$('#transformResultLI').children().trigger('click');
				});
			});
		});
	</script>
</html>