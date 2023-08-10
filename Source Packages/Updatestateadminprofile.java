 import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Updatestateadminprofile extends HttpServlet {
// override init method 
    private  Connection  con;
    private  PreparedStatement ps ; 
    public void init()
    {
      try
      {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19?useSSL=false&allowPublicKeyRetrieval=TRUE","root","priyalgupta");
        String sql = "UPDATE stateadmin set password = ?, uname = ?, email = ?, mobile = ?, address = ?, state = ?, status='enabled' where userid = ?";
        ps= con.prepareStatement(sql);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
   }
    
   //destroy  method override........
    public void destroy()
    {
      try{  con.close(); }
      catch(Exception e) { e.printStackTrace(); }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       PrintWriter out = response.getWriter();
       // read the request .......
       String userid    =   request.getParameter("UserId");
       String password  =  request.getParameter("pass"); // 
       String uname     =  request.getParameter("uname"); // 
       String address   =  request.getParameter("address"); //  
       String mobile    =  request.getParameter("mobile"); //  
       String email     =  request.getParameter("email"); //  
       String state     =  request.getParameter("state"); //  
       //store the data in  DB....... jdbc
       try
       {
        ps.setString(1,password);
        ps.setString(2,uname);
        ps.setString(3,email);
        ps.setString(4,mobile);
        ps.setString(5,address);
        ps.setString(6,state);
        ps.setString(7,userid);
        //response .....
        
        int x = ps.executeUpdate();       
        if(x>0)
        {
          out.println("Update Successfully..!!");
        }
        else
        {
          out.println("Faild..!!");
        }
        //response.sendRedirect("Stateadmindashboard.jsp");
        /*out.println(" <html>  <body> ");
        out.println("<h3>Profile is successfully completed..!!</h3>");
        out.println("<h4> <a href=index.jsp>Login-Now</h4>");
        out.println("</html>  </body> ");*/
        
       }
       catch(Exception e)
       {
        out.println(e);
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