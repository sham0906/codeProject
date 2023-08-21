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

/**
 * Servlet implementation class Tranfer
 */
@WebServlet("/deposit")
public class Deposit extends HttpServlet {	Connection con;
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
		String s1=request.getParameter("account_number");
		String s2=request.getParameter("amount");
		String s3=request.getParameter("date");
		PreparedStatement ps=con.prepareStatement("INSERT INTO bal (ACCOUNTFRIST,  balance,tdate) VALUES (?, ?,?)");
				ps.setString(1, s1);
				ps.setString(2, s2);
				ps.setString(3, s3);
				ps.executeUpdate();
		PrintWriter pw=response.getWriter();
		pw.print("<h1>Deposit successfully "+s1);
		pw.print("<br>amount : "+s2+"</h1>");
		RequestDispatcher rd = request.getRequestDispatcher("Deposit.jsp");
		rd.include(request, response);
	} 
	catch (Exception e) 
	{
		System.out.println(e);
	}
}

}

