
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create Team</title>
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
	margin-top: 80px;  overflow: auto; 
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

<script type="text/javascript">
	$(#loadStaff).click(function(){
		//some code to load static html saying team is created and add staff to team 
		$("#show").load('ajax/teamCreated.html');
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
	<!-- Home page functionalities -->
	<center>
		<div class="info">

			<!-- company name -->
			<h1>${sessionScope.companyName}</h1>
			<!-- logged in user -->
			<br/><br/>

			<!-- This form is used to Add teams to a Company -->
<br/><br/>
			<div class="team">
				<h3>Select the staff you want to add to </h3>
				<form action="addStaff.htm" method="POST">
			
			<%-- <select  name="user">
							<c:forEach items="${requestScope.tempList }" var="tempList">
							<option value="${tempList.email}">${resultUserList.firstName} ${ tempList.lastName} </option>
							</c:forEach>
						</select></br>
			 --%>
				
					<h3>Select User:</h3>
						<select  name="user">
							<c:forEach items="${requestScope.resultUserList }" var="resultUserList">
							<option value="${resultUserList.email}">${resultUserList.firstName} ${ resultUserList.lastName} </option>
							</c:forEach>
						</select></br>
					<h3>Select Team:</h3>
						
							<select  name="team">
							<c:forEach items="${requestScope.resultTeamList }" var="resultTeamList">
							<option value="${resultTeamList.teamName}">${resultTeamList.teamName }</option>
							</c:forEach>
						</select></br>
					<br/>
					<input value="${requestScope.team}" type="hidden" name="createdTeam"/>
					<button type="submit" class="btn btn-large btn-inverse" id="loadStaff">Add staff to Team</button>
					
					
				</form>
				

			</div>
		</div>
	</center>
</body>
</html>