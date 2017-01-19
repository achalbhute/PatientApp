package com.psl.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psl.dao.PatientHelper;
import com.psl.model.Patient;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController.do")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SearchController() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname=request.getParameter("firstname"); 
		String lastname=request.getParameter("lastname");
		String facility=request.getParameter("facility"); 
		String dateofadmit=request.getParameter("dateofadmit"); 
		String dateofdischarge=request.getParameter("dateofdischarge"); 
		List<Patient> list=PatientHelper.SearchPatient(firstname, lastname, facility, dateofadmit, dateofdischarge);
	}

}
