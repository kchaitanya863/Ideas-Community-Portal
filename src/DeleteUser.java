

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String id =request.getParameter("id");
		if(!UsersDB.isRegistered(id))
		{
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Record not found ("+id+") in the Database!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			
		
		}
		else{
		try {
			
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			String sql=	"delete from users where id = '"+id+"' ";
		PreparedStatement ps=con.prepareStatement(sql);   
		ps.execute();
		ps.addBatch();

		response.setContentType("text/html");
		response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
		response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Successfully Deleted "+id+" from the Database!!");
		response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
		
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
