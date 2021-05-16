<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <script type="text/javascipt" src="js/script.js"></script>
       <title>Create Staff Record</title>
    </head>
    <body>
      <%
          String emailError = (String) session.getAttribute("emailErr");
          String nameError = (String) session.getAttribute("nameErr");
          String existError = (String) session.getAttribute("existErr");
       %>
       <div align="center">
       <h1>Create Staff Record <span style="color:red;"><%=(existError != null ? existError : "")%></span></h1>
      <form action="AddStaffServlet" method="POST">
          <table>
              <tr>
                  <td style="width: 25%">Staff Name: <span>* </span></td>
                  <td><input type="text" placeholder="<%=(nameError != null ? nameError : "Enter name")%>" name="name"></td>
              </tr>
              <tr>
                  <td>Staff Email: <span>* </span></td>
                  <td><input type="text" placeholder="<%=(emailError != null ? emailError : "Enter email")%>" name="email"></td>
              </tr>
              <tr>
                <td>Occupation: <span>* </span></td>
                <td>
                   <select name="position">
                      <option value="manager">Manager</option>
                      <option value="salesperson">Salesperson</option>
                      <option value="accountant">Accountant</option>
                      <option value="staff">Professional Staff</option>
                      <option value="systemadmin">System Admin</option>
                   </select>
                </td>
            </tr>
            <tr>
                  <td>Staff Address: <span>* </span></td>
                  <td><input type="text" placeholder="Enter address" name="address"></td>
             </tr>
             <tr>
                  <td><input type="hidden" name="status" value="activate"></td>
                  <td><input type="submit" value="Add"></td>
              </tr>
              <tr>
                  <td></td>
                  <td><a href="staffInfoManagement.jsp">Cancel</a></td>
              </tr> 
          </table>
      </form>
       </div>
</body>




</html>