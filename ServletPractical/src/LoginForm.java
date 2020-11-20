import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginForm extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String id=request.getParameter("id");  
	    String password=request.getParameter("password");  
	          
	    if(Validate.checkUser(id,password)){  
	        RequestDispatcher rd=request.getRequestDispatcher("Welcome");  
	        rd.forward(request,response);  
	    }  
	    else{  
	        out.print("Id or Password incorrect");  
	        RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
	        rd.include(request,response);  
	    }
	    out.close();
	}

}
