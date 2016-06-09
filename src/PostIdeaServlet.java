

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PostIdeaServlet
 */
public class PostIdeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostIdeaServlet() {
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
		String title =request.getParameter("title");
		String desc =request.getParameter("desc");
		File folder = new File("C:\\ICPimages");
		
		if(!folder.exists())
			folder.mkdir();
		File src = new File(request.getParameter("file"));    
		if(src.canRead()){
			System.out.println("can read "+src.toPath().toString());
	
		File dest = new File("C:\\ICPimages\\"+title+".jpg");
		if(dest.exists())
			dest.delete();
		//dest.createNewFile();
		if(src.canWrite())
			System.out.println("can read "+dest.toPath().toString());
		Files.copy(src.toPath(), dest.toPath());
		}
		else{
			//File dest = new File("C:\\ICPimages\\"+title+".jpg");
			//if(dest.exists())
				//dest.delete();
		}
		
		//System.out.println(title+desc);
		//System.out.println();
		//response.getWriter().write(title+desc+cost);
		
		//add these items to database.
		HttpSession ses=request.getSession();		
		 //response.sendRedirect("AdminLoggedin.html");
		if(title==null||title==""||title.isEmpty()){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Title Can not be Empty!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
		}	
		else if(desc==null||desc==""||desc.isEmpty()){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Description Can not be Empty!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
		}	
		else if(desc.length()>1000){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Description Can not be more than 750 characters!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
			
		}	
		else if(title.length()>100){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">Title Can not be more than 100 characters!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");
		}	
		
		
		else if (IdeasDB.HasTitle(title)){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">This Title is already taken!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");		
		}
		
		else{
		try{
			response.getWriter().append((CharSequence) ses.getAttribute("uname"));
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			String timeStamp = new SimpleDateFormat("E, d-M-y 'at' h:m:s a z").format(Calendar.getInstance().getTime());
			String uname = ses.getAttribute("uname").toString();
			String sql = "insert into ideas2 values('"+title+"','"+desc+"',0,'"+uname+"','"+timeStamp+"')";	
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
		ps.addBatch();
		}
		catch(Exception e){
			System.out.println(e);
			response.getWriter().append(e.toString());
		}
		finally{
			response.sendRedirect("LoadIedas2.jsp");
		}
		}
		
	}

}
