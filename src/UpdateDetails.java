

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
 * Servlet implementation class UpdateDetails
 */
public class UpdateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDetails() {
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
		String name =request.getParameter("name");
		String pass = request.getParameter("pass");
		
		if(name==null&&pass==null||name.isEmpty()&&pass.isEmpty())
		 {

			 response.setContentType("text/html");
				response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
				response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Nothing Updated");
				response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
		
		 }
		
		//GetData.of(ID, ENAME);
		else if(UsersDB.isRegistered(id))
		{
		try {
			String pass1 = controller.DatabaseStrings.MD5(pass);
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			String sql=	"update users set id = '"+id+"',name = '" + name +  "',pass = '"+pass1+"' where id = '"+id+"'";
			if(pass.isEmpty()||pass==null||pass=="")
				sql="update users set name = '" + name +  "' where id = '"+id+"'";
			else if(name.isEmpty()||name==null||name=="")
				sql="update users set pass = '" + pass1 +  "' where id = '"+id+"'";
			else 
				sql=	"update users set id = '"+id+"',name = '" + name +  "',pass = '"+pass1+"' where id = '"+id+"'";
		
		PreparedStatement ps=con.prepareStatement(sql);   
		ps.executeUpdate();
		ps.addBatch();
		
		response.setContentType("text/html");
		response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
		response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Successfully updated to the Database!!");
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
		else{
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Update Failed ("+id+") not found in the Database!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
	
		}
		}
	
	

}
