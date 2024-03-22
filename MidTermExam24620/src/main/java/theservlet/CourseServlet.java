package com.auca.theservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auca.thedao.daoOfAcademicUnit;
import com.auca.thedao.CourseDao;
import com.auca.thedao.CourseDefinitionDao;
import com.auca.thedao.SemesterDao;
import com.auca.thedao.TeacherDao;
import com.auca.themodel.AcademicUnit;
import com.auca.themodel.Course;
import com.auca.themodel.CourseDefinition;
import com.auca.themodel.Semester;
import com.auca.themodel.Teacher;

public class CourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CourseDao courseDao = new CourseDao();
	private CourseDefinitionDao courseDefinitionDao = new CourseDefinitionDao();
	private TeacherDao teacherDao = new TeacherDao();
	private SemesterDao semesterDao = new SemesterDao();
	private daoOfAcademicUnit daoOfAcademicUnit = new daoOfAcademicUnit();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		saveCourse(req, res);
	}
	
	public void saveCourse(HttpServletRequest req, HttpServletResponse res) {

	    try {
	        String crcoursedefinition = req.getParameter("coursecode");

	        String crteacher = req.getParameter("teachercode");
	        String crsemester = req.getParameter("semestercode");
	        long crsemesterCode = Long.parseLong(crsemester);

	        String cracademicunit = req.getParameter("academicunitcode");
	        	        
	        // Fetching base on code
	        CourseDefinition courseDefinition = courseDefinitionDao.getCourseDefinitionByCode(crcoursedefinition);

	        Teacher teacher = teacherDao.getTeacherByCode(crteacher);
	        
	        Semester semester = semesterDao.getSemesterByCode(crsemesterCode);
	        
	        AcademicUnit academicUnit = daoOfAcademicUnit.getAcademicUnitByCode(cracademicunit);

	        Course course = new Course();

	        course.setCousedefinition(courseDefinition);

	        course.setTeacher(teacher);
	        course.setSemester(semester);

	        course.setAcademicunit(academicUnit);

	        courseDao.saveCourse(course);

	        req.getRequestDispatcher("adminDashboard.jsp").forward(req, res);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}
}


