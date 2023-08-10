<html>
    <head>
        <title>Registration Page</title>
    </head>
    <body bgcolor="pink">
        <h1>Registration Page..!!</h1>
        <h2>Welcome to COVID information portal..!!</h2>
        <hr>
        
        <form action="RegistrationServlet" method="get">
            <table>
                <tr>
                    <td><b>User/Mail : </b></td> <td><input type="text" name="UserId"></td>
                </tr>
                
                <tr>
                    <td><b>Password : </b></td> <td><input type="password" name="pass"></td>
                </tr>
                
                <tr>
                    <td><b>Name : </b></td> <td><input type="text" name="name"></td>
                </tr>
                
                <tr>
                    <td><b>Address : </b></td> <td><input type="text" name="address"></td>
                </tr>
                
                <tr>
                    <td><b>Mobile : </b></td> <td><input type="text" name="mobile"></td>
                </tr>
                
                <tr>
                <td><input type="submit" value="Register"/></td>
                <td><input type="submit" value="Reset"/></td>
                </tr>
            </table>
             <hr>
            <a href="index.html">User_Login
        </form>
    </body>
</html>
