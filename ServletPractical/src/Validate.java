import java.sql.*;

public class Validate {
	
	public static boolean checkUser(String id,String password) {
		boolean valid = false;
        try {
        	
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useSSL=false","root","1234");
            PreparedStatement stmt = con.prepareStatement("select * from student where id=? and password=?");
            stmt.setString(1, id);
            stmt.setString(2, password);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
        		valid=true;
        	}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return valid;     
    }   

}
