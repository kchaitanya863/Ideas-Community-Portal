<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "controller.LoginServlet2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Idea</title>
 <link rel="stylesheet" href="css/style.css">
 <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
 <script>tinymce.init({ selector:'textarea' });</script>
</head>
<body>
<%
HttpSession ses=request.getSession();      
if(ses.getAttribute("uname")==null)
	response.sendRedirect("Login.html");	
String title = request.getParameter("title");
String desc = request.getParameter("desc");
 %>

<div class="">
<form action="UpdateIdea"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1>Edit Idea</h1>
			</div>
			<div class="login-form">
<div class="control-group">
 <input type="text" class="login-field" value="<%= title %>" placeholder="Title" name="title1">
 <label class="login-field-icon fui-user" for="login-name"></label>
 </div>
 <input type="text" name= "title" value="<%= title %>"  style="visibility:hidden;">

 
 <div class="control-group">
 <textarea rows="20" cols="80" name="desc"><%=desc %></textarea> 
<%-- <input type="text" class="login-field" value="" placeholder="Description" name="desc" height="80" width="20" > --%>
 <label class="login-field-icon fui-lock" for="login-pass"></label>
	</div> </div><br>  <center>
	 <%if(ses.getAttribute("uname").equals("admin")) {%>
 <input type="text" name= "Posted On" value="<%= request.getParameter("PostedOn") %>" >
 
 <%} %><br></center>
	
	<input type="file" name="file" size="50" />
	<p>For best results crop images to 600 X 600 resolution</p>
 <input class="btn btn-primary btn-large btn-block" type="submit" value="Update">
  <center>
  <br><br><br>
 <a class="login-link" href="javascript:history.back()">Go Back!!</a>
</center>

<div align="right" class="login-link" > <%=ses.getAttribute("uname") %>, <a href="Logout.jsp">Logout</a> </div>
 </div>
</form>
</div>


</body>
</html>