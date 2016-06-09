<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.sql.*" %>
    <%@page import = "controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>Welcome Admin</title>
<script type="text/javascript">
function Logout(){
	window.location="Logout.jsp"
}
function Manage(){
	window.location="LoadIedas2.jsp"
}
</script>
</head>
<body>
<%
	 HttpSession ses=request.getSession();      
	if(ses.getAttribute("uname")==null)
		response.sendRedirect("Login.html");
    %>
<div class="login">
<form action="task1"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1>Register User</h1>
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
</form>
</div>
  
  <!-- Add new user above -->
  
    <div class="login">
<form action="UpdateDetails"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1>Update User</h1>
			</div>

<div class="login-form">
<div class="control-group">
 <input type="text" class="login-field" value="" placeholder="Full Name"  name="name">
 <label class="login-field-icon fui-lock" for="login-pass"></label>
	</div>
<div class="control-group">
<!-- load list from users DB -->
  <select class="btn btn-primary btn-large btn-block" name="id">
<%
 //Class.forName("com.ibm.db2.jcc.DB2Driver");
    try{ 
    	
		//Connection  con = DriverManager.getConnection("jdbc:db2://localhost:50000/krishna","nkvam","nagarjuna");	    
		Class.forName(DatabaseStrings.LoadString);
		Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);		
		String sql = "select id from users";	
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int x = 0;
		while(rs.next())
		{
	    %>
	    
<option value="<%=rs.getString(1) %>"><%=rs.getString(1) %></option>
<%
		
    }}
    catch(Exception e)
    {
    	e.printStackTrace();
    	response.getWriter().write(e.toString());
    }
 %>
</select>
 <label class="login-field-icon fui-user" for="login-name"></label>
 </div>
 <div class="control-group">
 <input type="password" class="login-field" value="" placeholder="password"  name="pass">
 <label class="login-field-icon fui-lock" for="login-pass"></label>
	</div> </div><br>
 <input class="btn btn-primary btn-large btn-block" type="submit" value="Update">
 

</form>
</div>
  
  <!-- Update User above -->
  
     <div class="login">
<form action="DeleteUser"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1>Delete User</h1>
			</div>

<div class="login-form">

<div class="control-group">

 <!--  <input type="text" class="login-field" value="" placeholder="username" name="id">-->
 <select class="btn btn-primary btn-large btn-block" name="id">
<%
 //Class.forName("com.ibm.db2.jcc.DB2Driver");
    try{ 
    	
		//Connection  con = DriverManager.getConnection("jdbc:db2://localhost:50000/krishna","nkvam","nagarjuna");	    
		Class.forName(DatabaseStrings.LoadString);
		Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);		
		String sql = "select id from users";	
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int x = 0;
		while(rs.next())
		{
	    %>
	    
<option value="<%=rs.getString(1) %>"><%=rs.getString(1) %></option>
<%
		
    }}
    catch(Exception e)
    {
    	e.printStackTrace();
    	response.getWriter().write(e.toString());
    }
 %>
</select>
 
 <label class="login-field-icon fui-user" for="login-name"></label>
 </div>
  </div><br>
 <input class="btn btn-primary btn-large btn-block" type="submit" value="Delete">
 

</form>

</div>
  
  <!-- Delete User above -->
  
  <div class="login">
<div class="login-screen">
<div class="login-form">
 <Button class="btn btn-primary btn-large btn-block" onclick="Manage()" value="Manage Ideas">Manage Ideas</Button>
 
</div>
</div>
</div>

  
  <!--Manage Ideas above -->
  
<div class="login">
<div class="login-screen">
<div class="login-form">
 <Button class="btn btn-primary btn-large btn-block" onclick="Logout()" value="Logout">Logout</Button>
 
</div>
</div>
</div>

  
  <!--Logout above -->
  
  
  
</body>
</html>