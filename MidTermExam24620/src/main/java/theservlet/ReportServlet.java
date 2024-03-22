package com.auca.theservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auca.thedao.ReportDao;
import com.auca.themodel.AcademicUnit;
import com.auca.themodel.Course;
import com.auca.themodel.CourseDefinition;
import com.auca.themodel.Semester;
import com.auca.themodel.Student;
import com.auca.themodel.StudentCourse;
import com.auca.themodel.StudentRegistration;
import com.auca.themodel.Teacher;


public class ReportServlet extends HttpServlet {

	

	private static final long serialVersionUID = 1L;
	private ReportDao reportDao = new ReportDao();
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
	    String action = req.getParameter("action");
	    
	    if ("fetchData".equals(action)) {
	        fetchAllData(req, response);
	    }
	}

	public void fetchAllData(HttpServletRequest req, HttpServletResponse response)throws ServletException, IOException {
	    List<Student> listStudents = reportDao.students();
	    List<Semester> listSemester = reportDao.semesters();
	    List<Teacher> listTeacher = reportDao.teachers();

	    List<AcademicUnit> listAcademicUnit = reportDao.academicUnits();
	    List<CourseDefinition> listCourseDefinition = reportDao.courseDefinitions();

	    List<Course> listCourse = reportDao.course();
	    List<StudentCourse> liststudentcourse = reportDao.studentcourse();
	    List<StudentRegistration> listStudentRegistration = reportDao.studentRegistrations();
	    

	    
	    req.setAttribute("listStudents", listStudents);

	    req.setAttribute("listSemester", listSemester);
	    req.setAttribute("listTeacher", listTeacher);

	    req.setAttribute("listAcademicUnit", listAcademicUnit);
	    req.setAttribute("listCourseDefinition", listCourseDefinition);

	    req.setAttribute("listCourse", listCourse);

	    req.setAttribute("liststudentcourse", liststudentcourse);

	    req.setAttribute("listStudentRegistration", listStudentRegistration);
	   
	    

	    RequestDispatcher dispatcher = req.getRequestDispatcher("Report.jsp");
	    dispatcher.forward(req, response);
	}
}