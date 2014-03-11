<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<title>Insert title here</title>
</head>
<body id="page1">
	<div class="main-bg">
		<div class="bg">
			<div class="bg1">
				<!--==============================header=================================-->
				<header>
				<div class="main">
					<div class="head-box" style="padding-bottom: 20px;">
						<h1>
							<a href="index.jsp">TTC Course Template</a>
						</h1>
						<div class="logo-light"></div>
						<div class="head-text">Training. Education. more.</div>
						<% if(session.getAttribute("username") == null) { %>
						<div class="login">
							<a href="/ProjectTMA01/login.html" class="button1">Login</a>
						</div>
						<% } else {%>
						<div class="login">
							Hello
							<%= session.getAttribute("username") %>!,<a href="/ProjectTMA01/logout.html"
								style="color: red">Logout</a>
						</div>

						<% }%>
						<div class="clear"></div>
					</div>
					<nav>
					<div class="shadow"></div>
					<ul class="sf-menu sf-js-enabled">
						<li class="first1"><a class="first active"
							href="/ProjectTMA01/index.html">Home</a></li>
						<li><a href="#">Management</a>
							<ul style="display: none; visibility: hidden;">
								<li><a href="/ProjectTMA01/CourseTemplate/Home.html">Course
										Template Management</a>
									<ul style="display: none; visibility: hidden;">
										<li><a href="/ProjectTMA01/CourseTemplate/Insert.html">Add</a>
										</li>
										<li><a href="/ProjectTMA01/CourseTemplate/PDF.pdf">ExportPDF</a>
										</li>
										<li class="last4"><a class="first1"
											href="/ProjectTMA01/CourseTemplate/Home.html">Search</a>
										</li>
									</ul></li>
								<li><a href="/ProjectTMA01/showPerson.html">User
										Management</a>
									<ul style="display: none; visibility: hidden;">
										<li><a href="/ProjectTMA01/showAccount.html">Account
												Management</a>
										</li>
										<li><a href="/ProjectTMA01/showPerson.html">Person
												Management</a>
										</li>
										<li class="last4"><a class="first1"
											href="/ProjectTMA01/showRole.html">Role Management</a>
										</li>
									</ul></li>

								<li><a href="/ProjectTMA01/CourseTemplate/Home.html">Course
										Management</a>
									<ul style="display: none; visibility: hidden;">
										<li><a href="/ProjectTMA01/CourseTemplate/Insert.html">Add
												Course Template</a>
										</li>
										<li><a href="/ProjectTMA01/CourseTemplate/PDF.pdf">ExportPDF
												Course Template</a>
										</li>
										<li class="last4"><a class="first1"
											href="/ProjectTMA01/CourseTemplate/Home.html">Search
												Course Template</a>
										</li>
									</ul></li>

								<li><a href="/ProjectTMA01/SubjectManagement/Home.html">Subject
										Management</a>
									<ul style="display: none; visibility: hidden;">
										<li><a href="/ProjectTMA01/SubjectManagement/Add.html">Add</a>
										</li>
										<li><a href="/ProjectTMA01/SubjectManagement/Home.html">Search</a>
										</li>
										<li class="last4"><a class="first1"
											href="/ProjectTMA01/SubjectManagement/Upload.html">Upload
												Material</a>
										</li>
									</ul></li>
								<li class="last4"><a class="first1" href="#">FAQs</a>
								</li>
							</ul></li>
						<li><a href="/ProjectTMA01/showPerson.html">News</a>
						</li>
						<li><a href="#">Picture</a>
						</li>
						<li><a href="/ProjectTMA01/SubjectManagement/Home.html">Contact</a>
						</li>
						<li><a href="/ProjectTMA01/SubjectManagement/Home.html">About</a>
						</li>

					</ul>
					<div class="clear"></div>
					</nav>
				</div>
				</header>
			</div>
		</div>
	</div>
</body>
</html>