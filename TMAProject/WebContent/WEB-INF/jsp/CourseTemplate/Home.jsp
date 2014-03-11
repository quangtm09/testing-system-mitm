<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CourseTemplate</title>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="../js/jquery-ui-1.8.16.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../js/jquery.autocomplete.css" />
    
<link rel="stylesheet" type="text/css" href="../js/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>


</head>

<body>
	<script type="text/javascript">
		function doAjaxSearch(key, pageNo) {
			if (key == 'Search...')
				key = '';
			$("#sresult").load("../CourseTemplate/Search.html", {
				keyword : key,
				page : pageNo
			});
		}

		function clearText() {
			if ($("#searchfield").val() == '') {
				document.getElementById('searchfield').value = 'Search...';
			} else {
				if ($("#searchfield").val() == 'Search...') {
					document.getElementById('searchfield').value = '';

				}
			}
		}
		function doAjaxPage(key, pageNo) {
			$("#sresult").load("Search.html", {
				keyword : key,
				page : pageNo
			});
		}
		$(document).ready(function() {
			doAjaxPage("", 1);
			var url = "Ajax.html?name=searchfield1";
			$("#searchfield").autocomplete({
				source : url,
			});
		});

		$(function() {
			var hideDelay = 500;
			var currentID;
			var hideTimer = null;

			var container = $('<div id="personPopupContainer">'

			+ '  <div id="personPopupContent"></div>'

			+ '</div>');

			$('body').append(container);

			$('.personPopupTrigger').live('mouseover', function() {

				var settings = $(this).attr('rel');

				if (currentID == '')
					return;

				if (hideTimer)
					clearTimeout(hideTimer);

				var pos = $(this).offset();
				var width = $(this).width();
				container.css({
					left : (pos.left + width + 9) + 'px',
					top : pos.top + 5 + 'px'
				});

				$('#personPopupContent').html('&nbsp;');

				$.ajax({
					type : 'GET',
					url : '../AjaxTooltips',
					data : 'id=' + settings,
					success : function(data) {

						var text = $(data).find('.valid').html();
						$('#personPopupContent').html(data);
					},
				});

				container.css('display', 'block');
			});

			$('.personPopupTrigger').live('mouseout', function() {
				if (hideTimer)
					clearTimeout(hideTimer);
				hideTimer = setTimeout(function() {
					container.css('display', 'none');
				}, hideDelay);
			});

			$('#personPopupContainer').mouseover(function() {
				if (hideTimer)
					clearTimeout(hideTimer);
			});

			$('#personPopupContainer').mouseout(function() {
				if (hideTimer)
					clearTimeout(hideTimer);
				hideTimer = setTimeout(function() {
					container.css('display', 'none');
				}, hideDelay);
			});
		});
	</script>

	<style type="text/css">
.personPopupTrigger {
	color: #196ab6;
	text-indent: 9999;
}
#personPopupContainer {
	position: absolute;
	left: 0;
	top: 0;
	display: none;
	z-index: 20000;
	min-width: 175px;
	min-height: 50px;
	color: black;
	-moz-border-radius:6px;
     -webkit-border-radius:6px;
     padding-top: 3px;
     padding-bottom: 3px;
     padding-left: 3px;
     border-radius:6px;
}

.personPopupPopup {
	
}

#personPopupContent {
	background-color: yellow;
	min-width: 175px;
	min-height: 50px;
	-moz-border-radius:6px;
     -webkit-border-radius:6px;
     padding-top: 10px;
     padding-left: 20px;
     padding-bottom: 10px;
     border-radius:6px;
     border:1px solid;
}

</style>

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
		<div style="float: right; margin-right: 30px;">
			<a href="../CourseTemplate/PDF.pdf" class="button1">Export PDF </a>
		</div>
		<div style="float: right; margin-right: 50px;">
			<a href="../CourseTemplate/Insert.html" class="button1">Add Course Template </a>
		</div>

		<div class="clear"></div>
		<div id="sresult" style="margin-left: 10px;margin-right: 10px;"></div>
	</div>


</body>
</html>