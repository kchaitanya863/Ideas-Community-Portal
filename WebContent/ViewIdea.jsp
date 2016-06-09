<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.sql.*" %>
     <%@page import = "controller.*" %> 
     <%@page import = "java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<%
		HttpSession ses=request.getSession();      
		if(ses.getAttribute("uname")==null)
			response.sendRedirect("Login.html");
    	String title= request.getParameter("title").toString();
    	//System.out.println(title);
 %>
 
 
<title><%=title %> </title>
</head>
<body>
<%
 //Class.forName("com.ibm.db2.jcc.DB2Driver");
    try{ 
    	
		//Connection  con = DriverManager.getConnection("jdbc:db2://localhost:50000/krishna","nkvam","nagarjuna");	    
		Class.forName(DatabaseStrings.LoadString);
		Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);		
		String sql = "select * from ideas2 where title='"+title+"'";	
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int x = 0;
		while(rs.next())
		{
	    %>
<div class="">
<form action="PostIdeaServlet"  method="post">
<div class="login-screen">
			<div class="app-title">
				<h1><%=rs.getString(1)%></h1>
			</div>
			<div class="login-form">
<div class="control-group">
 <label class="login-field-icon fui-user" for="login-name"></label>
 </div>
 <hr>
 <%
 String imgsrc = "C:\\ICPimages\\"+ rs.getString(1)+".jpg";
	File f1 = new File(imgsrc);
	if(f1.exists()){
		
 %>
 <a href="<%=imgsrc%>"><img  alt="" src="<%= imgsrc %>" height="100%" width="100%"></a>
 <%} %>
 <div class="control-group">
 	<%=rs.getString(2) %>
 <label class="login-field-icon fui-lock" for="login-pass"></label>
	</div><hr><br><br>
	Posted By : <b><a href=SearchResults.jsp?search=<%=rs.getString(4) %>><%=rs.getString(4) %></a></b> On : <%=rs.getString(5) %>
	 </div><br>

  <center>
 <p><a class="login-link" href="javascript:history.back()"><-- Go Back</a></p>
</center>

<div align="right" class="login-link" > <%=ses.getAttribute("uname") %>, <a href="Logout.jsp">Logout</a> </div>
 </div>
</form>
</div>




				
			
		
</body>

<%
		
    }}
    catch(Exception e)
    {
    	e.printStackTrace();
    	response.getWriter().write(e.toString());
    }
 %>
</html>