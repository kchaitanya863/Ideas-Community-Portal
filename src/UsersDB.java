import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class UsersDB {
	
	public static boolean isRegistered(String id){
		id = id.toLowerCase();
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
				if(id.equals(s1.toLowerCase()))
					return true;
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return false;
		 
	}
	
	public static void deleteUser(String ID){
		 try{
			 Class.forName(DatabaseStrings.LoadString);
				Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
				String sql = "delete from users where id = '"+ID+"'";	
				PreparedStatement ps = con.prepareStatement(sql);
				 ps.execute();
				 ps.addBatch();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static ArrayList<String> getUsersNameList(){	
		ArrayList<String> myList = new ArrayList<String>();
		try{
			Class.forName(DatabaseStrings.LoadString);
			Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);
			String sql = "select * from users";	
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())			
				myList.add(rs.getString(1));	
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    	//System.out.println(e);
		    }
		return myList;		
		
	}
	public static void ExecuteSQL(String sql){
		 try{
			 System.out.println(sql);
			 Class.forName(DatabaseStrings.LoadString);
				Connection con=DriverManager.getConnection(DatabaseStrings.ConnectionString,DatabaseStrings.UserName,DatabaseStrings.Password);	
				PreparedStatement ps = con.prepareStatement(sql);
				 ps.execute();
				 ps.addBatch();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void CreateTable(){
		String sql = "CONNECT TO KRISHNA;";
		//ExecuteSQL(sql);
		String sql1 = "CREATE TABLE NKVAM.USERS1 ( ID VARCHAR (100)  NOT NULL , NAME VARCHAR (100)  NOT NULL , PASS VARCHAR (100)  NOT NULL  , CONSTRAINT CC1465261087430 PRIMARY KEY ( ID)  ) ;";
		ExecuteSQL(sql1);
		String sql2 = "CONNECT RESET;";
		//ExecuteSQL(sql2);
	}
	


}
