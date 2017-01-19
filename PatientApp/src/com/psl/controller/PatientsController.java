package com.psl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.psl.dao.PatientHelper;
import com.psl.model.Facility;
import com.psl.model.Message;
import com.psl.model.Patient;
import com.psl.model.UserBean;

/**
 * Servlet implementation class Patients
 */
@WebServlet("/Patients.do")
public class PatientsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean userBean=(UserBean)request.getSession().getAttribute("user");
		Collection<Facility> facility =userBean.getFacilities();
		List<Patient> arrayList=PatientHelper.displayPatient(facility);
		request.setAttribute("patient", arrayList);
		if(request.getMethod().equals("POST"))
			request.setAttribute("name", new Message("successfully updated", true));
		 request.getRequestDispatcher("displaypatient.jsp").forward(request, response);
	}

}
