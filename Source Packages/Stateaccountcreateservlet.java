import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stateaccountcreateservlet extends HttpServlet {
// override init method 
    private  Connection  con;
    private  PreparedStatement ps ; 
    public void init()
    {
      try{
        Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19?useSSL=false&allowPublicKeyRetrieval=TRUE","root","priyalgupta");
        String sql = "INSERT INTO stateadmin VALUES(?,?,null,null,null,null,null,'disabled')";
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
       String email    =  request.getParameter("UserId"); // 
       String password  =  request.getParameter("pass"); // 
       //store the data in  DB....... jdbc
       try
       {  
        ps.setString(1,email);
        ps.setString(2,password);
        ps.executeUpdate();
       
        //response .....
       
        out.println(" <html>  <body> ");
        out.println("<h3>Create Account For Statdmin..!!</h3>");
        out.println("<h4> <a href=Superadmindashboard.jsp>Go_To_SuperAdminDashboard</h4>");
        out.println("</html>  </body> ");
        
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