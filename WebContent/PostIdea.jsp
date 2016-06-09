<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post Idea</title>
 <link rel="stylesheet" href="css/style.css">
 <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
 <script>tinymce.init({ selector:'textarea' });</script>
</head>
<body>
<%
HttpSession ses=request.getSession();      
if(ses.getAttribute("uname")==null)
	response.sendRedirect("Login.html");	
 %>

<div class="">
<form action="PostIdeaServlet"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1>Post Idea</h1>
			</div>
			<div class="login-form">
<div class="control-group">
 <input type="text" class="login-field" value="" placeholder="Title" name="title">
 <label class="login-field-icon fui-user" for="login-name"></label>
 </div>
 <div class="control-group">
 <textarea rows="20" cols="80" name="desc">Start describing your Idea by replacing this text!!</textarea> 
<%-- <input type="text" class="login-field" value="" placeholder="Description" name="desc" height="80" width="20" > --%>
 <label class="login-field-icon fui-lock" for="login-pass"></label>
	</div> </div><br>
	<input type="file" name="file" size="50" />
	<p>For best results crop images to 600 X 600 resolution</p>
 <input class="btn btn-primary btn-large btn-block" type="submit" value="Post">
  <center>
 <a class="login-link" href="LoadIedas2.jsp">View All Ideas</a>
</center>

<div align="right" class="login-link" > <%=ses.getAttribute("uname") %>, <a href="Logout.jsp">Logout</a> </div>
 </div>
</form>
</div>


</body>
</html>