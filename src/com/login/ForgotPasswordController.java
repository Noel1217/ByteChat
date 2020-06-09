package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Connector.Dao;

/**
 * Servlet implementation class ForgotPasswordController
 */
@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declaring variables and objects
		String email = request.getParameter("email");
		Dao forgotDao = new Dao();
		int result = 0;
		
		//Sending ForogtPassword email
		result =  forgotDao.forgotPassword(email);
		forgotDao.closePstm();
		forgotDao.closeRs();
		forgotDao.closeCon();
		
		//Checking result
		if(result > 0 ) {
			request.setAttribute("username", "Null");
			request.getRequestDispatcher("BarLoader.jsp").forward(request, response);
		}else {
			response.sendRedirect("ForgotPassword.jsp");
		}//end if result
	}

}
