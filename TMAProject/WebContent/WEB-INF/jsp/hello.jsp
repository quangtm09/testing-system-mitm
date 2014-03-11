<%@ include file="include/lib.jsp" %>

<html>
	<head>
		<title><fmt:message key="index.title"/></title>
	</head>
	<body>
		<h1><fmt:message key="index.heading"/></h1>
		<p><fmt:message key="index.greeting"/><c:out value="${model.now}"/></p>
	</body>
</html>
