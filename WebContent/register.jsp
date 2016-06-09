<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>Register here</title>
</head>
<body>

  
  <div class="login">
<form action="RegisterServlet"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1>Register</h1>
			</div>

<div class="login-form">
<div class="control-group">
 <input type="text" class="login-field" value="" placeholder="Full Name"  name="name">
 <label class="login-field-icon fui-lock" for="login-pass"></label>
	</div>
<div class="control-group">

 <input type="text" class="login-field" value="" placeholder="username" name="id">
 <label class="login-field-icon fui-user" for="login-name"></label>
 </div>
 <div class="control-group">
 <input type="password" class="login-field" value="" placeholder="password"  name="pass">
 <label class="login-field-icon fui-lock" for="login-pass"></label>
	</div> </div><br>
 <input class="btn btn-primary btn-large btn-block" type="submit" value="Register">
 <center>
 <a class="login-link" href="Login.html">Already a member?</a>
</center>
 

</form>
</div>
  
  
</body>
</html>