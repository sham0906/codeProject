package customer;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/Adminregister")
public class AdminRegister extends HttpServlet 
{	Connection con;
	public void init(ServletConfig config)
	{
		try 
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:orcl","system","orcl");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			String s1=request.getParameter("name");
			String s2=request.getParameter("phone");
			String s3=request.getParameter("password");
			PreparedStatement ps=con.prepareStatement("INSERT INTO admin (name, phone, password) VALUES (?, ?, ?)");
					ps.setString(1, s1);
					ps.setString(2, s2);
					ps.setString(3, s3);
					ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.print("<html> <head> <title>Registration Successfully</title>");
			pw.print("</head><body>");
			pw.print("<h1>Registration Successful</h1>"+s1 +" "+s2);
			pw.print("<h2>Your Account Number is </h2>"+s3);
			pw.print("<p>Thank you for registering! Your account has been created successfully.</p>");
			pw.print("<a href=Adminuser.html>Home Page</a>");
			pw.print("</body></html>");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

}

