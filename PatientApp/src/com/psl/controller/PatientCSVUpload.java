package com.psl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.psl.dao.PatientHelper;
import com.psl.model.Facility;
import com.psl.model.Patient;
import com.psl.model.UserBean;

@WebServlet("/upload.do")
@MultipartConfig
public class PatientCSVUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream;
		Part filePart = request.getPart("input7[]");
		inputStream = filePart.getInputStream();
		List<Patient> arr=new ArrayList<Patient>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	/*	try {
		Class<?> clazz = Class.forName("com.psl.dao.PatientHelper");
			Object obj = clazz.newInstance();
		*/
			UserBean userBean=(UserBean)request.getSession().getAttribute("user");
			Collection<Facility> facility =userBean.getFacilities();
			Iterator<Facility> itr=facility.iterator();
			List<String> fac=new ArrayList<String>();
			while(itr.hasNext())
			{
				fac.add(itr.next().getName());
			}
		//	Method method = clazz.getMethod("uploadData", reader.getClass(), fac.getClass());
		//	arr = (ArrayList<Patient>) method.invoke(obj, reader, fac);
			
			PatientHelper pp=new PatientHelper();
			arr=(ArrayList<Patient>)pp.uploadData(reader, fac);
/*		}
		
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.getCause();
			e.printStackTrace();
		} 
		
	*/	
		request.setAttribute("Reader", arr);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/uploadSuccess.jsp");
		requestDispatcher.forward(request, response);
	}

}
