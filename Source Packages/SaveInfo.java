import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveInfo extends HttpServlet {
// override init method 
    private  Connection  con;
    private  PreparedStatement ps ; 
    public void init()
    {
      try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19?useSSL=false&allowPublicKeyRetrieval=TRUE","root","priyalgupta");
        String sql = "INSERT INTO covidinfo (idate, state, total, active, death, userid) VALUES(now(),?,?,?,?,?)";
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
       Cookie ck = new Cookie("info","1d/pass");
       ck.setMaxAge(60);//60 sec tak cookie jinda rahegi
       response.addCookie(ck);
               
       
       //how to read cookie
       Cookie ck1[] = request.getCookies();
       for(Cookie c:ck1)
       {
           String name = c.getName();
           if(name.equals("info"))
           {
               String cls = c.getValue();
           }
       }
       //-------------------------------
       PrintWriter out = response.getWriter();
       // read the request .......
       String userid    =  request.getParameter("Userid"); // 
       String state     =  request.getParameter("state"); // 
       int    total_cases   =  Integer.parseInt(request.getParameter("total")); // 
       int    Active_cases   =  Integer.parseInt(request.getParameter("active")); //
       int    Deaths  =  Integer.parseInt(request.getParameter("death")); // 
       //store the data in  DB....... jdbc
       try
       {  
        ps.setString(1,state);
        ps.setInt(2,total_cases);
        ps.setInt(3,Active_cases);
        ps.setInt(4,Deaths);
        ps.setString(5,userid);
        ps.executeUpdate();
       
        //response .....
       
        out.println(" <html>  <body> ");
        out.println("<h3>Information Store Successfully..!!</h3>");
        out.println("<h4> <a href=Stateadmindashboard.jsp>State_Admin_Dashboard</h4>");
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