<%-- 
    Document   : SRF_Check
    Created on : 2021-4-12, 15:45:17
    Author     : Yangyulin Ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Staff Authentication</title>
</head>
<body>

    <h1>Staff Authentication</h1>
    <a href="RegisterPage.jsp">Cancel</a>
    <form method="post" action="StaffServlet">
        <table>
            <tr>
                <td style="width: 20%;">Password: <span style="color: red">* </span></td>
                <td><input type="password" placeholder="Enter Staff Code" name="code" min="6" max="8" required></td>
            </tr>
            <tr>
              
                 <td><input type="submit" value="Confirm"></td>
            </tr>
        </table>
    </form>
            
</body>
</html>
