<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Step 1</title>
<!-- BOOTSTRAP -->
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
					
					<p class="navbar-text pull-right">
           	  				<c:out value="${sessionScope.loggedInUser.firstName } ${sessionScope.loggedInUser.lastName }  "></c:out> <%--  <a href="${pageContext.servletContext.contextPath}/logout.htm" >Logout</a> --%>
           					 </p>
           					 
						<ul class="nav">


							<%-- <!-- User Home -->
							<li class="active"><a href="${pageContext.servletContext.contextPath}/processSignIn.htm">Home</a></li>
							<li ><a href="${pageContext.servletContext.contextPath}/editAccountDetails.htm">Account Settings</a></li> --%>
							<%-- <!-- create profile without webflow -->
							<li><a href="">Contacts</a></li>
								
							<!-- Account Settings -->
				
							<li ><a href="${pageContext.servletContext.contextPath}/logout.htm">Logout</a></li> --%>
							
							
							
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

<div class="info" > 
<h3></h3>
	<form:form id="step1" action="${flowExecutionUrl}" method="POST" commandName="user">
	<h3>Company Administrator Information</h3>
	<table>
		<tr>
			<td>First Name : </td>
			<td><form:input path="firstName" /><form:errors path="firstName" cssClass="error"></form:errors>  </td>
		</tr>
		<tr>
			<td>Last Name : </td>
			<td><form:input path="lastName"/><form:errors path="lastName" cssClass="error"></form:errors> </td>
		</tr>
		<tr>
			<td>Email : </td>
			<td><form:input path="email"/><form:errors path="email" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td>Password : </td>
			<td> <form:password path="password"/> <form:errors path="password" cssClass="error"></form:errors> </td>
		</tr>
		<tr>
			<td>Date of Birth : </td>
			<td> <form:input path="dob"/></td>
		</tr>
		<tr>
			<td>Phone : </td>
			<td><form:input path="phone"/></td>
		</tr>
	</table>
	
		<br/>
		
		<button id="cancel" type="submit" name="_eventId_cancel" class="btn btn-large btn-inverse">Cancel</button>
	<button id="next" type="submit" name="_eventId_next" class="btn btn-large btn-inverse">Submit &gt;&gt;</button>
		
		
	</form:form>
</div>
</center>
</body>
</html>