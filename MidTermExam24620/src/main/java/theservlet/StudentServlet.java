package com.auca.theservlet;

import com.auca.thedao.StudentDao;
import com.auca.themodel.Student;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {

                case "delete":
                    deleteStudent(request, response);
                    break;

                case "list":
                    listStudents(request, response);
                    break;
                case "edit":

                    showEditForm(request, response);
                    break;
                default:

                    listStudents(request, response);
                    break;
            }
        } else {
            listStudents(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":

                    addStudent(request, response);
                    break;
                case "update":
                    updateStudent(request, response);

                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        } else {
            listStudents(request, response);
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String regno = request.getParameter("regno");
        String fullname = request.getParameter("fullname");
        
        String dateofbirth = request.getParameter("dateofbirth"); 

        Student student = new Student();

        student.setRegno(regno);
        student.setFullname(fullname);


        student.setDateofbirth(dateofbirth); 

        studentDao.saveStudent(student);
        response.sendRedirect("StudentServlet?action=list"); 
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String regno = request.getParameter("regno");
        String fullname = request.getParameter("fullname");
        String dateofbirth = request.getParameter("dateofbirth"); 

        Student student = studentDao.getStudentById(regno);

        if (student != null) {
            student.setFullname(fullname);
            student.setDateofbirth(dateofbirth); 

            studentDao.updateStudent(student);
        }
        response.sendRedirect("StudentServlet?action=list"); 
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String regno = request.getParameter("regno");
        studentDao.deleteStudent(regno);
        response.sendRedirect("StudentServlet?action=list"); 
    }


    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDao.getAllStudents();

        request.setAttribute("students", students);

        request.getRequestDispatcher("studentList.jsp").forward(request, response); 
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String regno = request.getParameter("regno");
        Student existingStudent = studentDao.getStudentById(regno);

        request.setAttribute("student", existingStudent);

        request.getRequestDispatcher("editStudent.jsp").forward(request, response); 
    }

}
