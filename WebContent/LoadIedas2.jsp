<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.sql.*" %>
    <%@page import = "java.io.*" %>
   <%@page import = "controller.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Load Ideas</title>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<link href="css/simpleGridTemplate.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/style1.css">
<script type="text/javascript">
function PostIdeaClick(){
	window.location="PostIdea.jsp"		
}
function DeleteIdea(){
	window.location="DeleteByID.jsp"		
}
</script>
</head>
<body background="#3498DB" bgcolor="#3498DB">
<%
	 HttpSession ses=request.getSession();      
	if(ses.getAttribute("uname")==null)
		response.sendRedirect("Login.html");
    %>
<div class="container"> 
  <!-- Header -->
  <header class="header">
    <div align="left">
    <h4 class="logo">Ideas Community Portal</h4>
    </div>
  </header>
   <nav>
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#defaultNavbar1" aria-expanded="false"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
        <a class="navbar-brand" href="LoadIedas2.jsp">Home</a></div>
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="defaultNavbar1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="PostIdea.jsp">Post Idea<span class="sr-only">(current)</span></a></li>
          <li><a href="DeleteByID.jsp">Delete Idea</a></li>   
          <%if(ses.getAttribute("uname").equals("admin")){ %>
          <li><a href="AdminLoggedin.jsp">Admin Panel</a></li> 
          <%} %>
        </ul>
        <form class="navbar-form navbar-left" role="search" action="SearchServlet" method="post">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search" name="search">
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
    	<%if(!ses.getAttribute("uname").equals("admin")){ %>
        <ul class="nav navbar-nav navbar-right">        
          <li></li>
           <ul class="nav navbar-nav">
          <li><a href="mailto:kchaitanya863@gmail.com">Contact Us!</a></li>   
        </ul>
              <%} %>    
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>
  
  <!-- Hero Section -->

  <!-- Stats Gallery Section -->
  <div class="gallery">
   <%    
   
    //Class.forName("com.ibm.db2.jcc.DB2Driver");
    try{
    	
		//Connection  con = DriverManager.getConnection("jdbc:db2://localhost:50000/krishna","nkvam","nagarjuna");
	    	Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			
		String sql = "select * from ideas2 order by title";
	
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int number = 0;
		while(rs.next())
		{
			String imgsrc = "C:\\ICPimages\\"+ rs.getString(1)+".jpg";
			File f1 = new File(imgsrc);
			if(!f1.exists())
				imgsrc="images/bkg_06.jpg";
			
	    %>
  
    <div class="thumbnail"> <a href="ViewIdea.jsp?title=<%= rs.getString(1) %>"><img src="<%=imgsrc %>" alt="" width="2000" class="cards"/></a>
      <h4> <a href="ViewIdea.jsp?title=<%= rs.getString(1) %>"> <%=rs.getString(1) %></a></h4>
      <p class="tag">By : <a href=SearchResults.jsp?search=<%=rs.getString(4) %>><%=rs.getString(4) %></a><br> On : <%=rs.getString(5) %></p>
      <%
    		  String desc;
      if(rs.getString(2).length()>100){
    	   desc = rs.getString(2).substring(0,100)+".....";}
      else{
    	  desc = rs.getString(2);}
    	  %>
      <p class="text_column"><%= desc%></p><br>
      <%if(rs.getString(4).equals(ses.getAttribute("uname"))||ses.getAttribute("uname").equals("admin")){ %>
      <p><a href="DeleteByID2.jsp?title=<%= rs.getString(1) %>">Delete</a></p>
      <p><a href="EditIdea.jsp?title=<%= rs.getString(1) %>&desc=<%= rs.getString(2) %>&PostedOn=<%= rs.getString(5) %>">Edit</a></p>
      <%} %>
    </div>
     <%
     	number++;
     	if(number%4==0){     		
     		%>
     			</div>
     		  <div class="gallery">     		
     		<%     		
     	}
		}
	    }
	catch(Exception e)
	{
	   	response.getWriter().write("This is from Catch block "+e.toString());
	    	
	}
		%>
		
    
</div>
<div align="right" > <%=ses.getAttribute("uname") %> <a href="Logout.jsp">Logout</a> 
</div>

  
  </section>
  <!-- Copyrights Section -->
  <div class="copyright">&copy;2016 - <strong>Krishna Chaitanya</strong></div>
</div>


<!-- Main Container Ends -->

</div>
</body>
</html>