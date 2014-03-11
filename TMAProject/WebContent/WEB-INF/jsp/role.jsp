<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="include/lib.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="tablecloth.css" rel="stylesheet" type="text/css"  />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href=" js/template.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" type="text/css" href=" js/jquery-ui.css" />
<script type="text/javascript" src=" js/jquery.js"></script>
<script type="text/javascript" src=" js/tooltipsy.min.js">
</script>
<script type="text/javascript" src=" js/jquery.form.js"></script>
<script type="text/javascript" src=" js/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src=" js/jquery-ui-1.8.16.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href=" js/jquery.autocomplete.css" />
<link rel="stylesheet" href=" css/header.css" type="text/css"
	media="screen" charset="utf-8" />


<link rel="stylesheet" type="text/css" href=" js/jquery-ui.css" />
<script type="text/javascript" src=" js/jquery.min.js"></script>
<script type="text/javascript" src=" js/jquery-ui.min.js"></script>

<meta charset="utf-8">  
    <link rel="icon" href=" resource/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href=" resource/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href=" resource/css/demo.css" type="text/css" media="all">  
    <script src=" resource/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src=" resource/js/superfish.js" type="text/javascript"></script>
    <script src=" resource/js/jcarousellite_1.0.1.js" type="text/javascript"></script>
    <script type="text/javascript" src=" resource/js/easyTooltip.js"></script>
    <script src=" resource/js/script.js" type="text/javascript"></script>
    <script type="text/javascript" async="" src=" resource/js/ga.js"></script>
    <script type="text/javascript" src=" resource/js/tms-0.3.js"></script>
    <script type="text/javascript" src=" resource/js/tms_presets.js"></script>
    <script type="text/javascript" src=" resource/js/demo.js"></script> 
    <script src=" resource/js/jquery.hoverIntent.js" type="text/javascript"></script>

<title>Registration Page</title>
	<script type="text/javascript">
	$(document).ready(function(){
		  $(".edit").click(function(){
		      var choosenId=$(this).next().val();
		      var link="editRole.html?choosenId="+choosenId;
		      $(this).attr("formaction",link);
		  });
		});
		function checkAll(master) {
			var array = document.getElementsByName("check");
			for ( var i = 0; i < array.length; i++) {
				var temp = array[i];
				temp.checked = master.checked;
			}
		}
	</script>
	<style type="text/css">
	.edit{
	  width: 50px;
	  height: 40px;
	  border: 0;
	  background: transparent url(edit-button.gif) no-repeat center center;
	  text-indent: -1000em;
	  cursor: pointer; /* hand-shaped cursor */
	  cursor: hand; /* for IE 5.x */
	  color:red;
	  }
 	 </style>
</head>
<body>
	<div class="wrapper p7">
	 <table>
    	<tr>
    		<td valign="middle"><h1>Manage Role</h1></td>
    		<td align="right"><a href="showRole.html">Manage Role</a></td>
    	</tr>
    </table>
    <hr>
    <form:form method="post" commandName="roleBean">
	<div>
    	<table>
    		<tr>
    			<td><b>RoleName</b></td>
    			<td><form:input path="roleName"/></td>
    		</tr>
    		<tr>
    			<td><b>Login Permission</b></td>
    			<td>
	    			<form:select path="loginPermission">
	    				<form:option value="true"></form:option>
	    				<form:option value="false"></form:option>
	    			</form:select> 
    			</td>
    		</tr>
    		<tr>
    			<td><b>View Permission</b></td>
    			<td>
	    			<form:select path="viewPermission">
	    				<form:option value="true"></form:option>
	    				<form:option value="false"></form:option>
	    			</form:select> 
    			</td>
    		</tr>
    		<tr>
    			<td><b>Edit Permission</b></td>
    			<td>
	    			<form:select path="editPermission">
	    				<form:option value="true"></form:option>
	    				<form:option value="false"></form:option>
	    			</form:select> 
    			</td>
    		</tr>
    		<tr>
    			<td><b>Manage Permission</b></td>
    			<td>
	    			<form:select path="managePermission">
	    				<form:option value="true"></form:option>
	    				<form:option value="false"></form:option>
	    			</form:select> 
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2"><span style="color:blue;font-weight:bold;">${message}</span></td>
    		</tr>
    		<tr>
    			<td colspan="2" align="right">
					<input type="submit" value="Add" class='button medium blue' formaction="addRole.html"/>
					<input type="submit" value="Update" class='button medium blue' formaction="updateRole.html"/>
					<input type="reset" value="Clear" class='button medium blue'/>
    			</td>
    		</tr>
    	</table>
	</div>
		
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th><input type="checkbox" onclick="checkAll(this)"/></th>
					<th>Edit</th>
					<th>Role ID</th>
					<th>Role Name</th>
					<th>Login Permission</th>
					<th>View Permission</th>
					<th>Edit Permission</th>
					<th>Manage Permission</th>
				</tr>
				<c:forEach items="${rList}" var="role">
					<tr>
						<td><input type="checkbox" name="check" value='${role.roleId}'  /></td>
						<td><input type="submit" class="edit" formaction=""/><input type="hidden" value="${role.roleId }"/></td>
						<td>${role.roleId }</td>
						<td>${role.roleName }</td>
						<td>${role.loginPermission }</td>
						<td>${role.viewPermission }</td>
						<td>${role.editPermission }</td>
						<td>${role.managePermission }</td>
					</tr>
				</c:forEach>
			</table>	
		</div>
	</div>
	</form:form>
	</div>
</body>
</html>