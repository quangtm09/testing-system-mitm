<%@ include file="/WEB-INF/jsp/include/lib.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../js/colour.css" type="text/css" media="screen"
	charset="utf-8" />
<link rel="stylesheet" href="../js/template.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" type="text/css" href="../js/jquery-ui.css" />

</head>
<body>
<div style="padding-top:0px; margin-top:0px;">
 <div id="search" />
	<c:choose>
		<c:when test="${not empty materialList}">
			<table>
				<thead>
					<tr style="height: 5px;">
						<th class="current"  ><fmt:message key="Material.Title"/></th>
						<th class="current"><fmt:message key="Material.Size"/></th>
						<th class="current"><fmt:message key="Material.Date"/></th>
						<th><fmt:message key="Material.View"/></th>
						<th><fmt:message key="Material.Download"/></th>					
						<th><fmt:message key="Material.Delete"/></th>
					</tr>
				</thead>
				<tbody>
				<c:set var="count" value="0"></c:set>
					<c:forEach items="${materialList}" var="materialList">
						<tr style="width: 120px;">
							<td><c:out value="${materialList.materialTitle}" /></td>
							<td><c:out value="${materialList.materialSize}" /></td>
							<td><c:out value="${materialList.dateModification}" /></td>
							<c:url value="../SubjectManagement/ViewMaterial.html" var="urlView">
								<c:param name="materialId" value="${materialList.materialId}"></c:param>
							</c:url>
							<td  ><a href='<c:out value="${urlView}"></c:out>' class="state">View</a>
							</td>
							<c:url value="../SubjectManagement/DownloadMaterial.html" var="urlDownload">
								<c:param name="materialId" value="${materialList.materialId}"></c:param>
							</c:url>
							<td  ><a href='<c:out value="${urlDownload}"></c:out>' class="state">Download</a>
							</td>
							<c:url value="../SubjectManagement/DeleteMaterial.html" var="urlDelete">
								<c:param name="materialId" value="${materialList.materialId}"></c:param>
							</c:url>
							<td  ><a href='<c:out value="${urlDelete}"></c:out>'
								class="state" onclick="return confirm('Delete this material, <c:out value="${materialList.materialTitle}"/> ?')">Delete</a>
							</td>						
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%-- Page --%>
		</c:when>
		<%--otherwise --%>
	</c:choose>
	</div>

</body>
</html>