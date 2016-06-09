import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class IdeasDB {
	public static boolean HasTitle(String title){
	try{
	Class.forName(DatabaseStrings.LoadString);
	Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
	String sql = "select title from ideas2 where title='"+title+"'";	
	PreparedStatement ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		if(title.equals(rs.getString(1)))
			return true;
	}
    }
catch(Exception e)
{    	}
	
	return false;
}
	
	public static void DeleteByTitle(String title){	
		//System.out.println("Reached DeleteByTitle");
		
	    try{
	    	Class.forName(DatabaseStrings.LoadString);
	    	Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
	    	String sql = "delete from ideas2 where title='"+title+"'";	
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			ps.addBatch();
			//System.out.println("DeleteByTitle Successful");
	    }
	    catch(Exception e)
	    {}
	}
	
	public static String getIdbyTitle(String title){
		//System.out.print("title is "+title);
		if(HasTitle(title)){
		try{
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			String sql = "select postedby from ideas2 where title='"+title+"'";	
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				//if(title.equals(rs.getString(1)))
				//System.out.println(HasTitle(title));
				//System.out.println(rs.getString(1));
				return rs.getString(1);
			}
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    	//System.out.println(e);
		    }
		 
		}
		return null;
	}
	
}