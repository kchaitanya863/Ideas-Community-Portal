

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
 * Servlet implementation class UpdateIdea
 */
public class UpdateIdea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateIdea() {
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
		
		doGet(request, response);
		
		
		//System.out.println(request.getParameter("title"));
		String title =request.getParameter("title1");
		String id = IdeasDB.getIdbyTitle(request.getParameter("title"));		
		//if(title.equals(request.getParameter("title")))
				//IdeasDB.DeleteByTitle(request.getParameter("title"));
		String desc =request.getParameter("desc");
		File folder = new File("C:\\ICPimages");
		
		if(!folder.exists())
			folder.mkdir();
		File src = new File(request.getParameter("file"));    
		if(src.canRead()){
			//System.out.println("can read "+src.toPath().toString());
	
		File dest = new File("C:\\ICPimages\\"+title+".jpg");
		if(dest.exists())
			dest.delete();
		//dest.createNewFile();
		if(src.canWrite())
			//System.out.println("can read "+dest.toPath().toString());
		Files.copy(src.toPath(), dest.toPath());
		}
		else{
			//File dest = new File("C:\\ICPimages\\"+title+".jpg");
			//if(dest.exists())
				//dest.delete();
		}
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
		
		
		else if (IdeasDB.HasTitle(title)&&!title.equals(request.getParameter("title"))){
			
			response.setContentType("text/html");
			response.getWriter().write("<link rel=\"stylesheet\" href=\"css/style.css\">");
			response.getWriter().write("<div class=\"login\"><div class=\"login-screen\"><div class=\"app-title\">This Title is already taken!!");
			response.getWriter().write("<p> <a href=\"javascript:history.back()\">Go Back</a></p></div></div>");		
		}
		
		else{
		try{
			
			//response.getWriter().append((CharSequence) ses.getAttribute("uname"));
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			String timeStamp = request.getParameter("PostedOn");
			if(timeStamp==null)
			 timeStamp = new SimpleDateFormat("E, d-M-y 'at' h:m:s a z").format(Calendar.getInstance().getTime());
			//String uname = ses.getAttribute("uname").toString();
			//System.out.println(id);
			String sql = "insert ideas2 values('"+title+"','"+desc+"',0,'"+id+"','"+timeStamp+"')";	
			sql=	"update ideas2 set title = '"+title+"',desc = '" + desc +  "',likes = 0,postedby = '"+id+"',postedon = '"+timeStamp+"' where title = '"+request.getParameter("title")+"'";
		
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
