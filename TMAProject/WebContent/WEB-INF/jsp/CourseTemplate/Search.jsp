
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<html>
<head>
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

html{
		
}
body {
	font-family:Arial, Helvetica, sans-serif;
	font-size:100%; 
	color:black;
	min-width:980px;
}
ul{
	float: right;	
}
ul li{
	float: left:
	list-style: none;	
}

.select{
	width: 250px;
	padding-left: 10px;
	padding-top: 5px;
	max-height:100px;
	height: 100px;
	
}
.description{
	text-align: 10px;
}
h1,h2,h3,h4,h5,h6 {
	color: white;
	margin-top:-70px;
	font-family: 'Boogaloo', cursive;
	font-size:35px;
	color:#fff;
	line-height:1.2em;
	text-shadow:0 2px 2px #1d7eaf;
	font-weight:normal;
}

	
.button1 {
	display:inline-block;
	position:relative;
	color:#000;
	font-family:Arial, Helvetica, sans-serif;
	font-weight:bold;
	font-size:12px;
	line-height:21px;
	padding:10px 22px 9px;
	border-radius:6px;
     -moz-border-radius:6px;
     -webkit-border-radius:6px;
	 background:#d3eaf5;
	 text-shadow:none;
	 margin-left:2px;
}
.button1:hover {
	background:#196ab6;
	color:#fff;
}

#search{
	margin-top: 20px;	
}
a:hover {
	text-decoration: none;
}

#column{
	width: 1px;	
}

a {
	
	padding-left: 25px;
	background-repeat: no-repeat;
	background-position: left center;
}

Error{
		font-size: 13px;
		color: red;
		font-family:Arial, Helvetica, sans-serif;
}

/* Table styling */
table {
	width: 100%;
	height: 150px;
	color: #196ab6;
}

#tableupdate{
	width: 100%;
	height: 150px;
	color:  #196ab6;
}

th,td {
	padding: 7px 15px;
	text-align: left;
	width: 10px;
}



.butons {
	float: right;
}

.butons a {
	margin: 0 10px 0 0;
	float: left;
	margin-left: 4px; color : black;
	padding: 1px 10px;
	text-decoration: none;
	border: 1px solid #ebe8e8;
	background: #fbfbfc;
	color: black;
}

.butons p {
	float: right;
	margin-left: 15px;
}

.state a:hover {
	border: 1px solid #d9f0ff;
	color: red;
	text-decoration: none;
}

.state{
	border: 1px solid #d9f0ff;
	color: red;
	text-decoration: none;
	text-align: center;
}

.butons a.active {
	border: 1px solid #ebe8e8;
	background: #78bbe6;
	color: #fff;
	text-decoration: none;
}
.butons a.active {
	border: 1px solid #ebe8e8;
	background: #78bbe6;
	color: #fff;
	text-decoration: none;
}


a {
	color: white;
}

th.sort a {
	text-decoration: none;
	display: inline;
	background: url(../images/sort-icons.png) no-repeat 0 3px;
	padding-left: 20px;
}

th.sorted-desc a {
	background-position: 0 -47px;
}

th.sorted-asc a {
	background-position: 0 -97px;
}

th.sort a:hover {
	color: red;
}

tr:hover {
	background-color: #78bbe6;
	color: white;
}

th.sort a:focus {
	color: white;
}
ltipsy {
	padding: 10px;
	max-width: 200px;
	color: #303030;
	background-color: #f5f5b5;
	border: 1px solid #deca7e;
}
</style>
<script type="text/javascript">



$(document).ready(function() {
var $table1 = $('#t-1');
var $headers = $table1.find('thead th').slice(0);
$headers
  .each(function() {
    var keyType = this.className.replace(/^sort-/,'');
    $(this).data('keyType', keyType);
  })
  .wrapInner('<a href="#" class="hastip" title="Sort"></a>')
  .addClass('sort');

var sortKeys = {
  alpha: function($cell) {
    var key = $cell.find('span.sort-key').text() + ' ';
    key += $.trim($cell.text()).toUpperCase();
    return key;
  },
  numeric: function($cell) {
    var num = $cell.text().replace(/^[^\d.]*/, '');
    var key = parseFloat(num);
    if (isNaN(key)) {
      key = 0;
    }
    return key;
  },
  date: function($cell) {
    var key = Date.parse('1 ' + $cell.text());
    return key;
  }
};

$headers.bind('click', function(event) {
  event.preventDefault();
  var $header = $(this),
      column = $header.index(),
      keyType = $header.data('keyType');

  if ( !$.isFunction(sortKeys[keyType]) ) {
    return;
  }

  var rows = $table1.find('tbody > tr').each(function() {
    var $cell = $(this).children('td').eq(column);
    $(this).data('sortKey', sortKeys[keyType]($cell));
  }).get();

  rows.sort(function(a, b) {
    var keyA = $(a).data('sortKey');
    var keyB = $(b).data('sortKey');
    if (keyA < keyB) return -1;
    if (keyA > keyB) return 1;
    return 0;
  });

  $.each(rows, function(index, row) {
    $table1.children('tbody').append(row);
  });
});
});

$('.hastip').tooltipsy();

</script>
</head>
<body>
	<div style="padding-top: 0px; margin-top: 0px;">
		<div id="search">
			<c:set var="count" scope="page"></c:set>
			<c:choose>
				<c:when test="${not empty model.Beans}">
					<table id="t-1">
						<thead>
							<tr style="height: 5px;">
								<th class="sort-alpha"><fmt:message key="CourseTemplate.no" />
								</th>
								<th class="sort-alpha"><fmt:message
										key="CourseTemplate.name" />
								</th>
								<th style="text-align: left; width: 100px;" class="sort-alpha"><fmt:message
										key="CourseTemplate.startDay" />
								</th>
								<th style="text-align: left; width: 120px;" class="sort-alpha"><fmt:message
										key="CourseTemplate.endDay" />
								</th>
								<th class="sort-alpha" style="width: 400px;"><fmt:message
										key="CourseTemplate.description" />
								</th>
								<th><fmt:message key="CourseTemplate.view" />
								</th>
								<th><fmt:message key="CourseTemplate.update" />
								</th>
								<th><fmt:message key="CourseTemplate.delete" />
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${model.Beans}" var="CourseTemplate">
								<tr style="width: 50px;">
									<c:set var="count" value="${count + 1}"></c:set>
									<td><span class="sort-key">${count}.</span>
									</td>
									<td><a href="#"
										rel='<c:out value="${CourseTemplate.id}"/>'
										class="personPopupTrigger"><span class="sort-key"
											style="text-align: left;"><c:out
													value="${CourseTemplate.name}" /> </span>
									</a>
									</td>
									<td><span class="sort-key"><c:out
												value="${CourseTemplate.startDay}" /> </span>
									</td>
									<td><span class="sort-key"><c:out
												value="${CourseTemplate.startEnd}" /> </span>
									</td>
									<td><a href="#"
										rel='<c:out value="${CourseTemplate.id}"/>'
										class="personPopupTrigger"><span class="sort-key"><c:out
													value="${CourseTemplate.description}" /> </span>
									</a>
									</td>

									<c:url value="../CourseTemplate/View.html" var="urlView">
										<c:param name="id" value="${CourseTemplate.id}"></c:param>
									</c:url>
									<td><a href='<c:out value="${urlView}"></c:out>'
										class="state">Detail</a></td>
									<c:url value="../CourseTemplate/Update.html" var="urlUpdate">
										<c:param name="id" value="${CourseTemplate.id}"></c:param>
									</c:url>
									<td><a href='<c:out value="${urlUpdate}"></c:out>'
										class="state">Update</a></td>
									<c:url value="../CourseTemplate/Delete.html" var="urlDelete">
										<c:param name="id" value="${CourseTemplate.id}"></c:param>
									</c:url>
									<td><a href='<c:out value="${urlDelete}"></c:out>'
										class="state"
										onclick="return confirm('Do you want to delete user - <c:out value="${CourseTemplate.name}"/> ?')">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div>
						<p>
							<strong
								style="font-family: 'Boogaloo', cursive; font-size: 14px; padding-left: 10px;">Page
								${model.pageNo} of ${model.Totalpages}</strong> <span class="butons">
								<c:forEach items="${model.pages}" var="page">
									<c:choose>
										<c:when test='${page != model.pageNo}'>
											<a href="javascript:void(0)"
												onclick="doAjaxSearch('<c:out value="${model.keyword1.searchname}"/>',${page})">${page}</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)"
												onclick="doAjaxSearch('<c:out value="${model.keyword1.searchname}"/>',${page})"
												class="active">${page}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach> </span>
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div style="clear: both;" />
					<div style="padding-top: 50px; float: right;">
						<h2 style="margin-left: 10px;">
							<span style="color: red; display: inline;">No
								CourseTemplate found !!!</span>
						</h2>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>


</body>
</html>