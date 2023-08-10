import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyUser extends HttpServlet 
{
    // override init method 
    private  Connection  con;
    private  PreparedStatement ps1,ps2 ; 
    public void init()
    {
      try
      {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19?useSSL=false&allowPublicKeyRetrieval=TRUE","root","priyalgupta");
        String sql1 = "SELECT *FROM userdetail where email=? AND password=?";
        String sql2 = "SELECT *FROM stateadmin where userid=? AND password=?";
        ps1 = con.prepareStatement(sql1);
        ps2 = con.prepareStatement(sql2);
        ps1.executeQuery();
        ps2.executeQuery();

      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
   }
    
   //destroy  method override........
    public void destroy()
    {
      try
      { 
          con.close();
      }
      catch(Exception e)
      { 
          e.printStackTrace();
      }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       PrintWriter out = response.getWriter();
       // read the request .......
        String email       = request.getParameter("UserId");
        String password = request.getParameter("pass");
        String utype    = request.getParameter("UserType");
        
        if(utype.equals("Super-Admin"))
        {
            if(email.equals("sadmin") && password.equals("eternal"))
            {
               // out.println("Welcome Super Admin Account..!!");
                //request forward to superadmin dashboard
                response.sendRedirect("Superadmindashboard.jsp");
            }
            else
            {
                //out.println("Invalid Super-Admin Account..!!");
                out.println("<html><body>");
                out.println("<h3>Invalid Super Admin Account..!!</h3>");
                out.println("<h4><a href=index.html>Try_Again</h4>");
                out.println("</html></body>");
            }
        }
        else if(utype.equals("State-Admin"))
        {
           // out.println("Welcome State Admin Account..!!");
           // response.sendRedirect("Stateadmindashboard.jsp");
            ///===================================================
              try
            {
               //apn ne id or pass me esa kuch dala jo database me he hi nahi 
               ps2.setString(1,email);      //aaa
               ps2.setString(2,password);//112
               ResultSet rs = ps2.executeQuery();//database se data leke aygi or resultset ke obj me lake store kar degi 
               //result set se data read karenge 
               boolean found = rs.next();//cursosr bydefult before first pe hota he 
               if(found)
               {
                   String status = rs.getString("status");
                   if(status.equals("disabled"))
                   {
                       out.println("<html><body bgcolor = gray>");
                       out.println("<h3>Profile - Complete - Form..!!</h3>");
                       out.println("<form action = Updatestateadminprofile>");
                       out.println("<pre>");
                       out.println("User Id :       <input type = text name = UserId value = "+email+">");
                       out.println("User Password : <input type = text name = pass value = "+password+">");
                       out.println("User Name :     <input type = text name = uname>");
                       out.println("User Email :    <input type = text name = email>");
                       out.println("User Mobile :   <input type = text name = mobile>");
                       out.println("User Address :  <input type = text name = address>");
                       out.println("User State :    <input type = text name = state>");
                       out.println("<br>            <input type = submit value=Submit> <input type=submit value= Reset>");
                       out.println("</pre>");
                       out.println("</html></body>");

                   }
                   else
                   {
                       response.sendRedirect("Stateadmindashboard.jsp");
                   }
                   //out.println("Welcome User..!!");
                   //ye kam sabme Requestdispatcher se bhi ho jyga obj banake or forward kar denge lakin in dono me bhi diff he 
                   /*RequestDispatcher rd = request.getRequestDispatcher("Userdashboard.jsp");
                   rd.forward(request,response);*///ye faster hota he as comapres to sendRedirect ke 
                   
               }
               else
               {
                   //out.println("Invalid User Account..!!");
                   out.println("<html><body>");
                   out.println("<h3>Invalid User And Password..!!</h3>");
                   //out.println("<h4><a href=index.html>Try_Again</h4>");
                   out.println("</html></body>");
               }
            }
            catch(Exception e)
            {
                out.println(e);
            }
            //==================================================
            
        }
        else if(utype.equals("End-User"))
        {
            //abb end user me verify karna he ki apka user jo enter kiya he vo database me he ya nahi
            try
            {
                //apn ne id or pass me esa kuch dala jo database me he hi nahi 
               ps1.setString(1,email);      //aaa
               ps1.setString(2,password);//112
               ResultSet rs = ps1.executeQuery();//database se data leke aygi or resultset ke obj me lake store kar degi 
               //result set se data read karenge 
               boolean found = rs.next();//cursosr bydefult before first pe hota he 
               if(found)
               {
                   //out.println("Welcome User..!!");
                   response.sendRedirect("Userdashboard.jsp");
                   //ye kam sabme Requestdispatcher se bhi ho jyga obj banake or forward kar denge lakin in dono me bhi diff he 
                   /*RequestDispatcher rd = request.getRequestDispatcher("Userdashboard.jsp");
                   rd.forward(request,response);*///ye faster hota he as comapres to sendRedirect ke 
                   
               }
               else
               {
                   //out.println("Invalid User Account..!!");
                   out.println("<html><body>");
                   out.println("<h3>Invalid User Account..!!</h3>");
                   out.println("<h4><a href=index.html>Try_Again</h4>");
                   out.println("</html></body>");
               }
            }
            catch(Exception e)
            {
                out.println(e);
            }
        }
      
   }
       protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    public String getServletInfo() {
        return "Short description";
    }
}