<%@ include file="/WEB-INF/jsp/include/lib.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../js/colour.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" href="../js/template.css" type="text/css"
	media="screen" charset="utf-8" />
<link rel="stylesheet" type="text/css" href="../js/jquery-ui.css" />
</head>
<body>
	<div style="padding-top: 0px; margin-top: 0px;">
		<div id="search" />
		<c:choose>
			<c:when test="${not empty subjectBeanList}">
				<table>
					<thead>
						<tr style="height: 5px;">
							<th><fmt:message key="Number" /></th>
							<th><fmt:message key="Subject.ID" /></th>
							<th class="current"><fmt:message key="Subject.Name" /></th>
							<th><fmt:message key="Subject.View" /></th>
							<th><fmt:message key="Subject.Modify" /></th>
							<th><fmt:message key="Subject.Delete" /></th>
							<th><fmt:message key="Subject.Upload" /></th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="0"></c:set>
						<c:forEach items="${subjectBeanList}" var="SubjectManagement">
							<tr style="width: 120px;">
								<c:set var="count" value="${count + 1}"></c:set>
								<td>${count}.</td>
								<td><c:out value="${SubjectManagement.subjectId}" /></td>
								<td><c:out value="${SubjectManagement.subjectName}" /></td>

								<c:url value="../SubjectManagement/ViewSubject.html"
									var="urlView">
									<c:param name="subjectId"
										value="${SubjectManagement.subjectId}"></c:param>
								</c:url>
								<%-- --%>
								<td title="${SubjectManagement.subjectId}"><a
									href='<c:out value="${urlView}"/>' class="state">Details</a>
								</td>
								<c:url value="../SubjectManagement/Modify.html" var="urlModify">
									<c:param name="subjectId"
										value="${SubjectManagement.subjectId}"></c:param>
								</c:url>
								<td><a href='<c:out value="${urlModify}"></c:out>'
									class="state">Modify</a>
								</td>
								<c:url value="../SubjectManagement/DeleteSubject.html"
									var="urlDelete">
									<c:param name="subjectId"
										value="${SubjectManagement.subjectId}"></c:param>
								</c:url>
								<td><a href='<c:out value="${urlDelete}"></c:out>'
									class="state"
									onclick="return confirm('Delete this subject, <c:out value="${SubjectManagement.subjectName}"/> ?')">Delete</a>
								</td>
								<c:url value="../SubjectManagement/Upload.html" var="urlUpload">
									<c:param name="subjectId"
										value="${SubjectManagement.subjectId}"></c:param>
								</c:url>
								<td><a href='<c:out value="${urlUpload}"></c:out>'
									class="state">Upload</a>
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