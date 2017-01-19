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

import com.psl.model.*;
import com.psl.dao.*;
/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserBean userBean=(UserBean)request.getSession().getAttribute("user");
		Collection<Facility> facility =userBean.getFacilities();
		List<Patient> arrayList=PatientHelper.displayPatient(facility);
		request.setAttribute("patient", arrayList);
		if(request.getMethod().equals("GET"))
			request.setAttribute("name", new Message("successfully updated", true));
		 request.getRequestDispatcher("Delete.jsp").forward(request, response);
	}

}
