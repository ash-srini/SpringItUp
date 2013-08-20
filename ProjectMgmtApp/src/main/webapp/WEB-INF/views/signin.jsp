<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Sign In</title>

<link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css"
	rel="stylesheet">
<link
	href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/carousel.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/navbar.css">

<style type="text/css">
body{
	background-color: #E8E8E8  ;
}
.info {
	margin-top: 80px;
	background-color: #FFF0F5;
	padding-top: 30px;
	border-width: thin;
	width: 940px;
	height: 500px;
}

.error {
color: #ff0000;
font-style: italic;
text-align: center;
}

</style>
</head>
<body>

	<!-- MENU BAR -->
	<!-- This is the bootstrap navbar for menu -->

	<div class="navbar-wrapper">
		<!-- Wrap the .navbar in .container to center it within the absolutely positioned parent. -->
		<div class="container">

			<div class="navbar navbar-inverse">
				<div class="navbar-inner">
					<!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
					<button type="button" class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="brand" href="#">SpringItUp!</a>
					<!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
					<div class="nav-collapse collapse">
						<ul class="nav">


							<!-- Create a new profile web flow link -->
							<li><a
								href="${pageContext.servletContext.contextPath}/flows/register.htm">Create
									Profile</a></li>

						<%-- 	<!-- create profile without webflow -->
							<li><a
								href="${pageContext.servletContext.contextPath}/register.htm">Profile
									Creation</a></li>
 --%>
							<li class="active"><a
								href="${pageContext.servletContext.contextPath}/signin.htm">Sign
									In</a></li>
					</div>
					<!--/.nav-collapse -->
				</div>
				<!-- /.navbar-inner -->
			</div>
			<!-- /.navbar -->

		</div>
		<!-- /.container -->
	</div>
	<!-- /.navbar-wrapper -->

	<center>
		<div class="info">
			
			
			<form action="/controller/j_spring_security_check" method="post" >
			<div align="center" style="padding-top: 60px;">
			<h3>Sign In</h3>
			<div class="error">${error}</div>
			<table>
				<tr>
					<td><i class="icon-envelope"></i><input type="text" name="j_username" placeholder="Email" class="input-large "/></td>
					
				</tr>
				<tr>
					<td><i class="icon-lock"></i><input type="password" name="j_password" placeholder="Password" class="input-large "//></td>
				</tr>
			</table>
				
				<input class="btn btn-large btn-inverse" type="submit" value="Submit"/>
				</div>
			</form>
			
			
<%-- 			<form:form commandName="user" action="/controller/j_spring_security_check" method="GET"> --%>
<%-- 			<h3><c:out value="${requestScope.error }"></c:out></h3> --%>
<!-- 				<table> -->
<!-- 				<tr> -->
<!-- 					<th>Email :</th> -->
<!-- 					<td> -->
<%-- 					<form:input path="email" placeholder="Email" id="j_username" name="j_username" ></form:input> <form:errors path="email" cssClass="error"></form:errors> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<th>Password: </th> -->
<%-- 					<td><form:password path="password" placeholder="Password" id="j_password" name="j_password"></form:password><form:errors path="password" cssClass="error"></form:errors> --%>
<!-- 					</td>  -->
<!-- 				</tr> -->

<!-- 				</table> -->
				
<!-- 				<button type="submit" class="btn btn-default btn-lg">Submit</button> -->
<%-- 			</form:form> --%>
		</div>
	</center>

</body>
</html>