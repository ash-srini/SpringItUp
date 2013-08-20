
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit User Acc</title>

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
.myul{
	list-style: none;
	font-size: large;
}
</style>


<script type="text/javascript">
$(document).ready(function(){
$("#reset").click(function(){
	$("#password").css("visibility","visible");
});
});
</script>
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
           	  				<c:out value="${sessionScope.loggedInUser.firstName } ${sessionScope.loggedInUser.lastName }  "></c:out>  <a href="${pageContext.servletContext.contextPath}/logout.htm" >Logout</a>
           					 </p>
           					 
						<ul class="nav">


							<!-- User Home -->
							<li class="active"><a href="${pageContext.servletContext.contextPath}/processSignIn.htm">Home</a></li>
							<li ><a href="${pageContext.servletContext.contextPath}/editAccountDetails.htm">Account Settings</a></li>
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
<!-- Home page functionalities -->
<center>
<div class="info">
	
	<!-- company name -->
	<h1>${sessionScope.companyName}</h1>
	<!-- logged in user -->
	
</br></br>
<!-- Password -->
<ul class="myul">
<li><c:out value="${sessionScope.loggedInUser.firstName } ${sessionScope.loggedInUser.lastName }"></c:out></li></br>
<li>Job Title : <c:out value="${sessionScope.loggedInUser.jobTitle }"></c:out></li></br>
<li>Date of Birth : <c:out value="${sessionScope.loggedInUser.dob } "></c:out></li></br>
<li>Phone : <c:out value="${sessionScope.loggedInUser.phone } "></c:out></li></br>
</ul>



<div id="password">
	<form:form commandName="user" method="post" action="editAccountDetails.htm">
	<table style="font-size: medium; text-align: right;">
		<tr>
			<td>Old Password : </td>
			<td> <input type="password" name="oldpassword"/> </td>
		</tr>
			<tr>
			<td>New Password : </td>
			<td> <input type="password" name="newpassword"/> </td>
		</tr>
			<tr>
			<td>Confirm Password : </td>
			<td> <input type="password" name="confpassword"/> </td>
		</tr>
	</table>
	  <button type="submit" class="btn btn-large btn-inverse">Submit</button>
	</form:form>
</div>


</div>
</center>
</body>
</html>