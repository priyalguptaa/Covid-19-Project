<html>
    <head>
        <title>Entry_page</title>
    </head>
    <body bgcolor="pink">
        <h1>Entry_page Form</h1>
        <h2>Welcome to COVID information portal..!!</h2>
        <hr>
        
        <form action="SaveInfo" method="get">
            <table>
                <tr>
                    <td><b>State : </b></td> <td><input type="text" name="state"></td>
                </tr>
                
                <tr>
                    <td><b>User_id : </b></td> <td><input type="password" name="userid"></td>
                </tr>
                
                <tr>
                    <td><b>Total Cases : </b></td> <td><input type="text" name="total"></td>
                </tr>
                
                <tr>
                    <td><b>Active Cases : </b></td> <td><input type="text" name="active"></td>
                </tr>
                
                <tr>
                    <td><b>Deaths : </b></td> <td><input type="text" name="death"></td>
                </tr>
                
                <tr>
                <td><input type="submit" value="Submit"/></td>
                <td><input type="submit" value="Reset"/></td>
                </tr>
            </table>
             <hr>
            <a href="index.html">User_Login
        </form>
    </body>
</html>
