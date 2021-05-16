<%-- 
    Document   : SRF
    Created on : 2021-4-11, 15:04:45
    Author     : Yangyulin Ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Staff Registration Page</title>
</head>
<body>
    <h1>Staff Registration Form</h1>
    <a href="CancelServlet">Cancel</a>
    <form method="post" action="RegisterServlet">
        <table class="register_table">
            <tr>
                <td style="width: 20%">Staff Name: <span style="color: red">* </span></td>
                <td><input type="text" placeholder="Enter Name" name="name" required></td>
            </tr>
            <tr>
                <td style="width: 20%">Email: <span style="color: red">* </span></td>
                <td><input type="text" placeholder="Enter Email" name="email" required></td>
            </tr>
            <tr>
           
                <td style="width: 20%;">Password: <span style="color: red">* </span></td>
                <td><input type="password" placeholder="Enter Password" name="password" min="6" max="12" required><small style="color: green">(password should be between 6 and 12 characters)</small></td>
            </tr>
            <tr>
                <td style="width: 20%">Phone: <span style="color: red">* </span></td>
                <td><input type="tel" name="phone" placeholder="Enter Phone Number" required></td>
            </tr>
            <tr>
                <td style="width: 20%">Occupation: <span style="color: red">* </span></td>
                <td>
                <select name="occupation">
                <option value="manager">Manager</option>
                <option value="purchasing staff">Purchasing Staff</option>
                <option value="marketing staff">Marketing Staff</option>
                <option value="it staff">IT Staff</option>
                </select>
                </td>
            </tr>
            <tr>
                <td style="width: 20%">Gender: </td>
                <td><input type="radio" name="gender" value="female">female
                    <input type="radio" name="gender" value="male">male</td>
            </tr>
            <tr>
                <td style="width: 20%">Date of Birth: </td>
                <td><input type="date" name="dob" placeholder="Enter date of birth"></td>
            </tr>
            <tr>
                <td><input type="hidden" name="type" value="Staff"></td> 
            </tr>
            <tr>   
                 <td><input type="submit" value="Sign Up"></td>
            </tr>
    </table>
    </form>
    
</body>
</html>

