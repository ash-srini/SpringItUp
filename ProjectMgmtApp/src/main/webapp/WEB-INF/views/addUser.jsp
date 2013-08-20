
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	height: 600px;
}

.error {
color: #ff0000;
font-style: italic;
text-align: center;
}

</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- JQuery AJAX -->
<script type="text/javascript" >
$(document).ready(function(){
	$("#email").change(function(){
		
	 	$("#exists").hide();
		var x = $("#email").val();
		if(x!=""){
			$.ajax({
				url : "/controller/companyadmin/checkUserName?x="+x,
				type : 'GET',
				
				success : function(result){
					if(result == "yes"){
						$("#exists").show();
						alert("User with this Email already exists in the Company! User another email address!");
					}
				}
			});
		} 
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
<div class="info">
	
	<!-- company name -->
	<h1>${sessionScope.companyName}</h1>
	<!-- logged in user -->
	<br/><br/>

<!-- This form is used to add user to a company by the company admin -->
	<h4>Add User to Company</h4>
	<form:form commandName="companyUser" action="createCompanyUser.htm" method="POST">
	<table>
	<tr>
	 	<td>First Name:</td>
	 	
	 	<td><form:input path="firstName"/> <form:errors path="firstName" cssClass="error"></form:errors> </td>
	 	</tr>
	 	
		<tr>
		<td>Last Name:</td>
		<td> <form:input path="lastName"/><form:errors path="lastName" cssClass="error"></form:errors> </td>
		</tr>
		
		<!-- <tr id="exists" style="visibility: hidden">
			<td></td>
			<td class="error">User With this email already Exists. Enter another email address. </td>
		</tr> -->
		
		<tr>
		<td>Email:</td>
		
		<td> <form:input path="email" id="email" class="email"/><form:errors path="email" cssClass="error"></form:errors></td>
		
		</tr>
		
		<tr>
		<td>Role:</td>
		 <td><form:select path="role">
		 	<form:option value=""></form:option>
			<form:option value="Manager"></form:option>
			<form:option value="Employee"></form:option>
		</form:select> <form:errors path="role" cssClass="error"></form:errors> </td>
		</tr>
		<tr>
		<td>Job Title:</td>
		<td><form:input path="jobTitle"/></td>
		</tr>
		<tr>
		<td>Date of Birth:</td>
		<td> <form:input path="dob"/></td>
		</tr>
		<tr>
		<td>Phone:</td>
		<td> <form:input path="phone"/></td>
		</tr>
		</table>
		<br/>
		<button type="submit" class="btn btn-large btn-inverse ">Next &gt;&gt;</button>
	</form:form>


</div>
</center>
</body>
</html>