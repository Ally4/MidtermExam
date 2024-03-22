package com.auca.theservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auca.thedao.DaoOfAcademicUnit;
import com.auca.themodel.AcademicUnit;
import com.auca.themodel.Eacademicunit;

public class AcademicUnitServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	private DaoOfAcademicUnit daoOfAcademicUnit = new DaoOfAcademicUnit();
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<AcademicUnit> allAcademicUnits = daoOfAcademicUnit.getAllAcademicUnits();
        req.setAttribute("allAcademicUnits", allAcademicUnits);
        req.getRequestDispatcher("AcademicUnit.jsp").forward(req, res);
    }


	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        saveAcademicUnit(req, res);
    }

	
	public void saveAcademicUnit(HttpServletRequest req, HttpServletResponse res) {
		
		try {
		    String acacode = req.getParameter("code");
		    String acaname = req.getParameter("name");
		    String acatype = req.getParameter("type");
		    String acapcode = req.getParameter("parent");
		    AcademicUnit parentAcademicUnit = daoOfAcademicUnit.getAcademicUnitByCode(acapcode);

		    // Convertion of enum
		    Eacademicunit academicType = Eacademicunit.valueOf(acatype);

		    AcademicUnit academicUnit = new AcademicUnit();
		    academicUnit.setCode(acacode);
		    academicUnit.setName(acaname);
		    academicUnit.setAcademicType(academicType); 
		    academicUnit.setParentAcademic(parentAcademicUnit);

		    daoOfAcademicUnit.saveAcademicUnit(academicUnit);

		    req.getRequestDispatcher("AcademicUnit.jsp").forward(req, res);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
		
	
}
