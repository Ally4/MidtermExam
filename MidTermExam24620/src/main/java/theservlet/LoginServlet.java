package com.auca.theservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auca.thedao.UsersDao;
import com.auca.themodel.Users;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UsersDao usersDao = new UsersDao();

 // Login servlet 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Users user = usersDao.getUserByEmailAndPassword(email, password);

        if (user != null) {
            // Logged in successfully


            HttpSession session = request.getSession();
            session.setAttribute("user", user); 

            session.setAttribute("role", user.getRole()); 
            
            // Here is the session time(the time required for the user to stay logged in)
            session.setMaxInactiveInterval(30 * 60); 

            // Redirection
            dispatchUser(request, response, user.getRole());
        } else {
            // Login failed
            request.setAttribute("error", "Check well your email and password");


            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }


    private void dispatchUser(HttpServletRequest request, HttpServletResponse response, String role)
            throws IOException {
        if ("admin".equals(role)) {

            response.sendRedirect("adminDashboard.jsp");
        } else if ("teacher".equals(role)) {
            response.sendRedirect("teacherDashboard.jsp");

        } else if ("student".equals(role)) {
            response.sendRedirect("studentDashboard.jsp");
        } else {
            
            response.sendRedirect("loginFailure.jsp");
        }
    }
}