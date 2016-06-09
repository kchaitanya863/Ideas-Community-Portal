package controller;

public class DatabaseStrings {
	//update these values when needed to access on any system
	public static String LoadString = "com.ibm.db2.jcc.DB2Driver";
	public static String ConnectionString = "jdbc:db2://localhost:50000/krishna";
	public static String UserName = "nkvam";
	public static String Password= "nagarjuna";
	
	/* *
	  Create 2 tables,
	  users(id , name , pass) all varchar
	  ideas(Title (*) , desc , likes(int) , postedby(varchar), postedon(varchar))
	  dir C:\ICPimages
	 * */
	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}

}
