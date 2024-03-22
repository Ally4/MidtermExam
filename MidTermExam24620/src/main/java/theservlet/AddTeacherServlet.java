package com.auca.theservlet;

import com.auca.thedao.TeacherDao;
import com.auca.themodel.Teacher;
import com.auca.themodel.Equalification; 
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddTeacherServlet")
public class AddTeacherServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String code = request.getParameter("code");

        String firstName = request.getParameter("firstName");

        String lastName = request.getParameter("lastName");
        String qualificationString = request.getParameter("qualification");

        String courseIdString = request.getParameter("courseId");
        
        Equalification qualification = Equalification.valueOf(qualificationString.toUpperCase());
        
        UUID courseId = null;
        if (courseIdString != null && !courseIdString.isEmpty()) {

            courseId = UUID.fromString(courseIdString);
        }


        Teacher teacher = new Teacher(code, firstName + " " + lastName, qualification);

        TeacherDao dao = new TeacherDao();
        dao.addTeacher(teacher);


        // to the admin dashboard

        response.sendRedirect("adminDashboard.jsp"); 
    }
}
