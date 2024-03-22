package com.auca.theservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auca.thedao.CourseDao;
import com.auca.thedao.StudentCourseDao;
import com.auca.thedao.StudentRegistrationDao;
import com.auca.themodel.Course;
import com.auca.themodel.StudentCourse;
import com.auca.themodel.StudentRegistration;

public class StudentCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private StudentCourseDao studentCourseDao = new StudentCourseDao();
	private CourseDao courseDao = new CourseDao();
	private StudentRegistrationDao studentRegistrationDao = new StudentRegistrationDao();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		saveStudentCourse(req, res);
	}
	
	public void saveStudentCourse(HttpServletRequest req, HttpServletResponse res) {
		
		try {
		    String credits = req.getParameter("credits"); 
		    String results = req.getParameter("results"); 
		    String studentregistration = req.getParameter("regig").trim(); 
		    Integer courseCode = Integer.parseInt(req.getParameter("course")); 

		    StudentCourse studentCourse = new StudentCourse();

		    studentCourse.setCredits(Integer.parseInt(credits)); 
		    studentCourse.setResults(results);


			

		    StudentRegistration studentRegistration = studentRegistrationDao.getStudentRegistrationByCode(Integer.parseInt(studentregistration));

		    Course course = courseDao.getCourseByCode(courseCode);


		    studentCourse.setStudentregistration(studentRegistration);

		    studentCourse.setCourse(course);

		    studentCourseDao.saveStudentCourse(studentCourse);

		    req.getRequestDispatcher("adminDashboard.jsp").forward(req, res);
			
		} catch (Exception e) {
		    e.printStackTrace();
		}


		
	}
}
