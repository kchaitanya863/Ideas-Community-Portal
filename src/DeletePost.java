

import java.io.File;
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
 * Servlet implementation class DeletePost
 */
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePost() {
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
	public static void DeleteByTitle(String title){
		try{
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			String sql = "delete from ideas2 where title='"+title+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.addBatch();
			
			//delete related image also!!
			File dest = new File("C:\\ICPimages\\"+title+".jpg");
			if(dest.exists())
				dest.delete();
			
		}
		catch(Exception e){
			System.out.println(e);
			//response.getWriter().write(" not Deleted  <a href = \"DeletePost.html\">Try Again!</a><br>"  + e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title=request.getParameter("title");
		try{
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			
			String sql1 = "select * from ideas2 where title='"+title+"'";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs = ps1.executeQuery();
			boolean notFound = true;
			while(rs.next()){
				HttpSession ses=request.getSession();
				String email =  ses.getAttribute("uname").toString();
				if(email.equals(rs.getString(4))||email.equals("admin")){
					DeleteByTitle(title);
					
					response.setContentType("text/html");
					response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
					response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Deleted Successfully : "+title);
					response.getWriter().write("<p> <a href=\"LoadIedas2.jsp\">Go Back</a></p></div></div>");
				}			
				
				
			}
			if(notFound&&IdeasDB.HasTitle(title)){
				
				response.setContentType("text/html");
				response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
				response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\"> You are not authorised to delete this post " + title);
				response.getWriter().write("<p> <a href=\"LoadIedas2.jsp\">Go Back</a></p></div></div>");
				
			}
			else{
				
				response.setContentType("text/html");
				response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
				response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\"> No Such Post Found!" + title);
				response.getWriter().write("<p> <a href=\"LoadIedas2.jsp\">Go Back</a></p></div></div>");
				
				
			}
			
			
			
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	//response.getWriter().write(" not Deleted "+title+" <a href = \"DeletePost.html\">Try Again!</a><br>"  + e);
	    }
		
	}

}
