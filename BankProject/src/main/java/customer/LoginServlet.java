package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{
		Connection con=null;
		String name;
		public void init(ServletConfig config)
		{
			try 
			{
				Class.forName("oracle.jdbc.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","orcl");
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			String s1=request.getParameter("phone");
			String s2=request.getParameter("password");
			PreparedStatement ps=con.prepareStatement("select * from users where phone=? and PASSWORD=?");
			ps.setString(1, s1);
			ps.setString(2, s2);
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=response.getWriter();
			
			if (rs.next()) {
			    Cookie cookie = new Cookie("phone", s1);
			    response.addCookie(cookie);
			    name=request.getParameter("phone");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		        // Forward the request to the target resource
		        dispatcher.forward(request, response);
			} else 
			{
				pw.println("<font color=red>");
				pw.println("User Name or Password</font>" +s1 +" "+s2);
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
				pw.print("</body> </html>");
			}
		
	}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	
	}

}
