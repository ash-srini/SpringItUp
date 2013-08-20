<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/carousel.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/navbar.css">
	<title>CRM Home</title>
	
	
</head>
<body>
<!-- This is the bootstrap navbar for menu -->

<div class="navbar-wrapper">
      <!-- Wrap the .navbar in .container to center it within the absolutely positioned parent. -->
      <div class="container">

        <div class="navbar navbar-inverse">
          <div class="navbar-inner">
            <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="${pageContext.servletContext.contextPath}/" class="active" st>SpringItUp!</a>
            <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
            <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              <a href="${pageContext.servletContext.contextPath}/signin.htm">Sign In</a>
            </p>
              <ul class="nav">
               
                
                <!-- Create a new profile web flow link -->
                <li><a href="${pageContext.servletContext.contextPath}/flows/register.htm">Create Profile</a></li>
                
                <!-- create profile without webflow -->
                <%-- <li><a href="${pageContext.servletContext.contextPath}/register.htm">Profile Creation</a></li> --%>
                
                 <li ></li>
               
            </div><!--/.nav-collapse -->
          </div><!-- /.navbar-inner -->
        </div><!-- /.navbar -->

      </div> <!-- /.container -->
    </div><!-- /.navbar-wrapper -->

		 <!-- =================================================================================================== -->
    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide">
      <div class="carousel-inner">
        <div class="item active">
          <img src="${pageContext.servletContext.contextPath}/resources/grey.jpeg" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>Welcome to SpringItUp!</h1>
              <h2>Project Management and bug Tracking made Easy</h2>
              <p class="lead">We help you manage your company projects in the most intuitive way with a very friendly and appealing User Interface</p>
              <p></p>
              <br/>
              <a style="height: 25px; " class="btn btn-large btn-inverse" href="${pageContext.servletContext.contextPath}/flows/register.htm">Sign up today</a>
            </div>
            
          </div>
        </div>
       </div>
      </div>
        <!-- /.carousel -->

      	 <!-- =================================================================================================== -->
      
     <!-- FOOTER -->
      <footer>
      
        <p>&copy; 2013 SpringItUp!, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
      </footer>




<script src="http://twitter.github.io/bootstrap/assets/js/holder/holder.js"></script>


<!-- loading the js files in the end so page loads faster -->
<script src="http://twitter.github.io/bootstrap/assets/js/jquery.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/bootstrap-transition.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-alert.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-modal.js"></script>
    <script src=".http://twitter.github.io/bootstrap/assets/js/bootstrap-dropdown.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-scrollspy.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-tab.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-tooltip.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-popover.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-button.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-collapse.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-carousel.js"></script>
    <script src="http://twitter.github.io/bootstrap/assets/js/bootstrap-typeahead.js"></script>
    
    <script>
      !function ($) {
        $(function(){
          // carousel demo
          $('#myCarousel').carousel()
        })
      }(window.jQuery)
    </script>
 <script src="http://twitter.github.io/bootstrap/assets/js/holder/holder.js"></script>
</body>

</html>
