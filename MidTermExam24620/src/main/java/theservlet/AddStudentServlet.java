package com.auca.theservlet;

import com.auca.themodel.Student;
import com.auca.theutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object roleObj = session.getAttribute("role"); 
        
        String role = null;
        if (roleObj != null) {
            role = roleObj.toString();
        }
        
        String regno = request.getParameter("regno");

        String fullname = request.getParameter("fullname");

        String dateofbirth = request.getParameter("dateofbirth");

        Student student = new Student(regno, fullname, dateofbirth);

        Transaction transaction = null;
        try (Session hibernateSession = HibernateUtil.getSession().openSession()) {

            // Beginning of transaction
            transaction = hibernateSession.beginTransaction();

            // Student save
            hibernateSession.save(student);

            // Commit the save
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        // Redirection based on the role
        if ("admin".equalsIgnoreCase(role)) {
            
            response.sendRedirect("ListStudentsServlet"); 

        } else {

            response.sendRedirect("studentDashboard.jsp"); 
        }
    }
}
