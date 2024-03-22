package com.auca.theservlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auca.thedao.daoOfAcademicUnit;
import com.auca.thedao.SemesterDao;
import com.auca.thedao.StudentDao;
import com.auca.thedao.StudentRegistrationDao;
import com.auca.themodel.AcademicUnit;
import com.auca.themodel.Eregistration;
import com.auca.themodel.Semester;
import com.auca.themodel.Student;
import com.auca.themodel.StudentRegistration;

public class StudentRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private StudentRegistrationDao studentRegistrationDao = new StudentRegistrationDao();

	private StudentDao studentDao = new StudentDao();

	private daoOfAcademicUnit daoOfAcademicUnit = new daoOfAcademicUnit();
	private SemesterDao SemesterDao = new SemesterDao();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req, res);		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		saveStudentRegistration(req, res);
	}
	
	public void saveStudentRegistration(HttpServletRequest req, HttpServletResponse res) {
		
		try {
		    String studentId = req.getParameter("code");

		    String registrationDate = req.getParameter("date");

		    String academicUnitCode = req.getParameter("academicunitcode");
		    Eregistration status = Eregistration.valueOf("PENDING");

		    
		    Long semesterCode = Long.parseLong(req.getParameter("semestercode"));

		    // Retrieve the student based on the studentId
		    Student student = studentDao.getStudentById(studentId);

		    if (student == null) {

				
				// discribe the error

		    } else {
		        StudentRegistration registration = new StudentRegistration();

		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		        java.util.Date startDate = dateFormat.parse(registrationDate);
		        Date sqlStartDate = new Date(startDate.getTime());

		        registration.setRegistrationDate(sqlStartDate);

		        registration.setRegno(student);
				
		        AcademicUnit academicUnit = daoOfAcademicUnit.getAcademicUnitByCode(academicUnitCode);

		        registration.setAcademicunit(academicUnit);
				
		        Semester semester = SemesterDao.getSemesterByCode(semesterCode);
		        registration.setSemester(semester);
				
		        registration.setStatus(status);

		        studentRegistrationDao.saveStudentRegistration(registration);
		        req.getRequestDispatcher("studentDashboard.jsp").forward(req, res);
		    }
		} catch (Exception e) {
		    e.printStackTrace();

		}

		
	}
}
