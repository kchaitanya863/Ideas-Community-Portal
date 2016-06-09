<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	 HttpSession ses=request.getSession();      
	if(ses.getAttribute("uname")==null)
		response.sendRedirect("Login.html");
	String title = request.getParameter("title");
    %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>Delete Ideas here</title>
</head>
<body>
<center> 
<div class="login">
<form action="DeletePost"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1>Delete Ideas</h1>
			</div>

<div class="login-form">
<div class="control-group">
 <input type="text" class="login-field" value="<%= title %>" placeholder="Paste Title here" name="title">
 <label class="login-field-icon fui-user" for="login-name"></label>
 </div>
  </div><br>
 <input class="btn btn-primary btn-large btn-block" type="submit" value="Delete">
<a class="login-link" href="javascript:history.back()">Go Back!!</a>
 
<div align="right" class="login-link" > <%=ses.getAttribute("uname") %>, <a href="Logout.jsp">Logout</a> </div>
</form>
</div>
</center> 


</body>
</html>