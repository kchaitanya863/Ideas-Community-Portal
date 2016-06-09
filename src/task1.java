import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class task1
 */
public class task1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public task1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String id =request.getParameter("id");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		if(id==null||id==""||id.isEmpty()){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">ID Can not be Empty!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			

		}	
		else if(name==null||name==""||name.isEmpty()){
			
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Name Can not be Empty!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			

		}	
		else if(pass==null||pass==""||pass.isEmpty()){
			
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Password Can not be Empty!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			
		}	
		
		
		else if(UsersDB.isRegistered(id))
		{
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">The Username("+id+") is already taken");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			

			
		}
		else{
		try {
			pass = controller.DatabaseStrings.MD5(pass);
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);			
			String sql=	"insert into users values('"+id+"','"+name+"','"+pass+"')";			
			PreparedStatement ps=con.prepareStatement(sql);   
			ps.execute();
			ps.addBatch();	
			response.setContentType("text/html");
			response.getWriter().write("Successfully Added ("+id+") to the Database!!");
			response.getWriter().write("  <a href = \"AdminLoggedin.html\">Go Back</a>");
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
