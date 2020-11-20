import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class NewStudentForm extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String id=request.getParameter("id");  
	    String password=request.getParameter("password");
	    String name=request.getParameter("name");
	    String department=request.getParameter("department");
	    
	    try {
        	
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useSSL=false","root","1234");
            PreparedStatement stmt = con.prepareStatement("insert into student values(?,?,?,?)");
            stmt.setString(1,id);
			stmt.setString(2,password);
			stmt.setString(3,name);
			stmt.setString(4,department);
           
        	int row=stmt.executeUpdate();
			if(row>0){
				RequestDispatcher rd=request.getRequestDispatcher("Welcome");  
		        rd.forward(request,response);
			}else{
				out.println("Failed to insert result");
			}
		  
	    }catch(Exception e){
	    	out.println(e.getMessage());
	    }
	}
}
