



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.auca.themodel.Users" %>
<%
HttpSession existingSession = request.getSession(false);
    Users user = null;
    if (session != null) {
        user = (Users) session.getAttribute("user");
    }
    if (user == null || ! "admin".equals(user.getRole())) {
        // User is not logged in or not an admin, redirect to login page
        response.sendRedirect("Login.jsp");
        return;
    }
%>
  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>The management of student on the board</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"> 
</head>
<body>
    <div class="main"> 
        <nav>
            
        </nav>

        <section class="section main" style="padding-top: 100px;"> 
            <h1>The management of student on the board</h1>
            <div class="dashboard-links">

                <a href="add-student.jsp" class="dashboard-link">Add New Student</a>
                <a href="ListStudentsServlet" class="dashboard-link">View , Edit or Delete the student</a>
            </div>
        </section>
    </div>
    <footer>
        <p>Copyright (C) - 2024, with love By Ally</p>
     </footer>
</body>
</html>
