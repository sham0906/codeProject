package customer;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/forget")
public class forgetpassword extends HttpServlet {
	Connection con;

	public void init(ServletConfig config) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:orcl", "system", "orcl");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String s1 = request.getParameter("phone");
			PreparedStatement ps = con.prepareStatement("select * from admin where phone=?");
			ps.setString(1, s1);
			ResultSet rs = ps.executeQuery();
			PrintWriter pw = response.getWriter();
			pw.print("<html><head></head><body>");
			{
				if (rs.next()) {
					pw.println("<h1> Welcome User ");
					pw.print(rs.getString(1)+" "+rs.getString(2));
					pw.println("<br> Password is " + rs.getString(3));
					pw.println("</h1> <br><a href=Login.html>Home Pages</a> </body> </html>");
				} else {
					pw.println("<font color=red>");
					pw.println("Invalid mobile </font>" + s1);
					RequestDispatcher rd = request.getRequestDispatcher("/ForgotPassword.html");
					rd.include(request, response);
					pw.print("</body> </html>");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
