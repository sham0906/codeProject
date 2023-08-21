<%@ page import="java.sql.*" %>
<%  Class.forName("oracle.jdbc.OracleDriver"); %>
<HTML>
       <HEAD>
       <TITLE>Show All Account Holder</TITLE>
       </HEAD>
       <%@ include file="header.html" %>
       <H1>Account holders </H1>
       <%
       Connection connection=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:orcl","System","orcl");
	
           Statement statement = connection.createStatement() ;
          ResultSet resultset = statement.executeQuery("select * from users") ;
       %>
      <TABLE BORDER="1">
      <TR>
      <TH>FIRSTNAME</TH>
      <TH>LASTNAME</TH>
      <TH>Mobile No.</TH>
      
      <TH>EMAIL</TH>
      <TH>PASSWORD</TH>
      <TH>GENDER</TH>
      <TH>ACCOUNT_TYPE</TH>
      <TH>SOURCE_OF_INCOME</TH>
      <TH>INCOME</TH>
      <TH>Age</TH>
      <TH>ADDRESS</TH>       
      </TR>
      <% while(resultset.next()){ %>
      <TR>
       <TD> <%= resultset.getString(1) %></td>
       <TD> <%= resultset.getString(2) %></TD>
       <TD> <%= resultset.getString(3) %></TD>
        <TD> <%= resultset.getString(4) %></TD>
         <TD> <%= resultset.getString(5) %></TD>
          <TD> <%= resultset.getString(6) %></TD>
           <TD> <%= resultset.getString(7) %></TD>
            <TD> <%= resultset.getString(8) %></TD>
             <TD> <%= resultset.getString(9) %></TD>
              <TD> <%= resultset.getString(10) %></TD>
               <TD> <%= resultset.getString(11) %></TD>
  
      </TR>
      <% } %>
     </TABLE>
     <%@ include file="footer.html" %>
     </BODY>
</HTML>