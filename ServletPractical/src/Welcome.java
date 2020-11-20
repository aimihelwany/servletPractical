import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Welcome extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    out.println("Welcome user!");
	    
	  
	    boolean login=false;
	    Connection con=null;
	    Statement statement=null;
	    
	    String id=request.getParameter("id");  
	    String password=request.getParameter("password");
	      
	    try {
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useSSL=false","root","1234");
            PreparedStatement stmt = con.prepareStatement("select * from student where id=? and password=?");
            stmt.setString(1, id);
            stmt.setString(2, password);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
        		login=true;
        	}
      
	    	} catch(Exception e){
	    	  out.println(e.getMessage());
	     	}
    
    
	    if(login){
	    	try{ 
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery("select id, science, english, math from marks");
            out.println("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n"
            		  + "<html>\n" + "<head><title></title></head>\n" + "<body bgcolor = \"#f0f0f0\">\n" 
                      + "<h2>" + "Student Marks" + "</h2>");
            
            out.println("<table border=\"1\">"
                      + "<tr>"
                      + "<th>ID</th>"
                      + "<th>Science</th>"
                      + "<th>English</th>"
                      + "<th>Math</th>"
                      + "<th>Average</th>"
                      + "</tr>");
            
            while(rs.next()){
          	  int average=(rs.getInt("science")+ rs.getInt("english")+ rs.getInt("math"))/3;
          	  
          	out.println("<tr>"
          			  + "<td>" + rs.getString("id") + "</td>"
          			  + "<td>" + rs.getString("science") + "</td>"
          			  + "<td>" + rs.getString("english") + "</td>"
          			  + "<td>" + rs.getString("math") + "</td>"
          			  + "<td>" + average + "</td>"
          			  + "</tr>");
            }
            out.println("</table>" + "</body>" + "</html>");
	    }
        catch(Exception e) {
            e.printStackTrace();
        	}
	    }
	}
}
