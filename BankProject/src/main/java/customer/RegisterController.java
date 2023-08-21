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
@WebServlet("/register")
public class RegisterController extends HttpServlet 
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			String s1=request.getParameter("FIRSTNAME");
			String s2=request.getParameter("LASTNAME");
			String s3=request.getParameter("PHONE");
			String s4=request.getParameter("EMAIL");
			String s5=request.getParameter("PASSWORD");
			String s6=request.getParameter("GENDER");
			String s7=request.getParameter("ACCOUNT_TYPE");
			String s8=request.getParameter("SOURCE_OF_INCOME");
			String s9=request.getParameter("INCOME");
			String s10=request.getParameter("AGE");
			String s11=request.getParameter("ADDRESS");
			PreparedStatement ps=con.prepareStatement("INSERT INTO users (FIRSTNAME, LASTNAME, phone, email, password, gender, account_type, source_of_income, income, age, address,Bal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'0')");
					ps.setString(1, s1);
					ps.setString(2, s2);
					ps.setString(3, s3);
					ps.setString(4, s4);
					ps.setString(5, s5);
					ps.setString(6, s6);
					ps.setString(7, s7);
					ps.setString(8, s8);
					ps.setString(9, s9);
					ps.setString(10, s10);
					ps.setString(11, s11);
					ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.print("<html> <head> <title>Registration Successfully</title>");
			pw.print("</head><body>");
			pw.print("<h1>Registration Successful</h1>"+s1 +" "+s2);
			pw.print("<h2>Your Account Number is </h2>"+s3);
			pw.print("<p>Thank you for registering! Your account has been created successfully.</p>");
			pw.print("<a href=Login.html>Home Page</a>");
			pw.print("</body></html>");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

}

