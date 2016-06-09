package controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet2
 */
public class LoginServlet2 extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			
			HttpSession ses=request.getSession(false);//Make HttpSession Object  by default true; 
 			ses.invalidate();    //Terminate the session.
			 //response.sendRedirect("Login.jsp");
			 request.setAttribute("msg"," Logged Out Successfully");//Why you have not used session.requestAttribute
             getServletContext().getRequestDispatcher("/Login.html").forward(request, response);
		}	 
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			//1st Approach
			String email=request.getParameter("mail");
			 String password=request.getParameter("pass"); 
			 String password1 = DatabaseStrings.MD5(password);
			 
			 //2nd Approach
			 boolean databaseHas = false;			 
				 try{
					 Class.forName(DatabaseStrings.LoadString);
					Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
					String sql = "select * from users";	
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next())
				
				{
					
					String s1 = rs.getString(1);
					String s2 = rs.getString(3);
					//System.out.println(s1+"  "+s2);
					if(email.equals(s1)&&password1.equals(s2))
					databaseHas = true;
				}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			 //3rd Approach
			 
			 //Difference between null and ""
			 if(email.equals("")||email==null||password.equals("")||password==null)
			 {

				 response.setContentType("text/html");
					response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
					response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Fill In all Details");
					response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			
			 }
			 else if(email.equals("admin")&&password.equals("password"))
				 
			 {
				 HttpSession ses=request.getSession();
				 ses.setAttribute("uname", email);
				 response.sendRedirect("AdminLoggedin.jsp");
			 }
			 //check from database
			 
			 else if(databaseHas){
				 HttpSession ses=request.getSession();
				 ses.setAttribute("uname", email);
				 //response.sendRedirect("AdminLoggedin.html");
				 response.sendRedirect("LoadIedas2.jsp");
			 }
			 
			 else
			 {
				 
				 response.setContentType("text/html");
					response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
					response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">We did not find a match for the credentials you entered");
					response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
				 
			
			 }
		

		 
		 

		}}

