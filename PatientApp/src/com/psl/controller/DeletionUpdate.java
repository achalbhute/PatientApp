package com.psl.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psl.dao.PatientHelper;
import com.psl.model.Message;

@WebServlet("/deleted.do")
public class DeletionUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletionUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str[]=request.getParameterValues("status[]");
		for(String s: str)
		{
			System.out.println("from deleted.do "+s);
		}
		Message message=PatientHelper.updatePatient(str);
		if(message.isSuccessful())
	      {
	    	 request.getSession().setAttribute("name", message);
	    	 request.getRequestDispatcher("/delete.do").forward(request, response);
	      }
	      else
	    	  response.sendRedirect("Delete.jsp");
	}

}
