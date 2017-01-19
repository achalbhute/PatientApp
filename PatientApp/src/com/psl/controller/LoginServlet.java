package com.psl.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.psl.dao.Login;
import com.psl.model.Facility;
import com.psl.model.Message;
import com.psl.model.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = new UserBean();		   
	    user.setEmail(request.getParameter("email"));
	    user.setPassword(request.getParameter("password"));
	    UserBean bean = Login.login(user);
	    HttpSession session=request.getSession(false); 
	    if(bean.isValid()){
	    	System.out.println("Successfully Logged in");
	    	for(Facility facility : bean.getFacilities()){
	    		System.out.println(facility.getName());
	    	}
	    	if(!bean.getStatus().equals("Active"))	 {
	    		request.getRequestDispatcher("notactive.html").forward(request, response);
	    		return;
	    	}
	    	 
	          request.setAttribute("Uname", bean.getFirstName());
	          if(bean.getRole().equals("Admin"))
	          {		          
	        	  request.setAttribute("name", new Message("successfully logged in", true));
	        	  session.setAttribute("user", bean);
		    	//  request.getRequestDispatcher("admin").include(request, response);
		      }
		      else			    	  
		      {
		    	  session.setAttribute("user", bean);
		    	  if(bean.isFirstLogin()){
		    		  request.setAttribute("firstlogin", bean.isFirstLogin());		    		  
		    		  request.getRequestDispatcher("changepassword.jsp").forward(request, response);
		    		  return;
		    	  }
		    	
		    	   request.setAttribute("name", new Message("successfully logged in", true));
		    	  request.getRequestDispatcher("clerk.jsp").include(request, response);
		    	  
		      }
	     }
		        
	     else {
	    	 request.setAttribute("name", new Message("Invalid login details", true));
	    	request.getRequestDispatcher("/login.jsp").include(request, response);
	     }
	}

}
