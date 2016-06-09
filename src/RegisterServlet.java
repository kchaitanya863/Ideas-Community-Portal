

import java.io.IOException;
import java.security.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id =request.getParameter("id");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		pass = controller.DatabaseStrings.MD5(pass);
		
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
		else if(id.toLowerCase().equals("admin")){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">The Username("+id+") is already taken");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			
		}
		
		//check if database is created
		
		
		else if(UsersDB.isRegistered(id))
		{
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">The Username("+id+") is already taken");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");	
		}
		else{
		try {
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);			
			String sql=	"insert into users values('"+id+"','"+name+"','"+pass+"')";			
			PreparedStatement ps=con.prepareStatement(sql);   
			ps.execute();
			ps.addBatch();	
			response.sendRedirect("Login.html");
		
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
