package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connector.Dao;

/**
 * Servlet implementation class VerifyController
 */
@WebServlet("/VerifyController")
public class VerifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declaring variables
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String user = session.getAttribute("sessionUser").toString();
		int result = 0;
		Dao verifyDao = new Dao();
		
		//Updating state
		result = verifyDao.updateVerificationState(code, user);
		verifyDao.closePstm();
		verifyDao.closeRs();
		verifyDao.closeCon();
		
		
		//Checking result
		if(result > 0) {
			response.sendRedirect("./PostController");
		}else if(result == -1){
			request.setAttribute("codeError", "Error");
			request.getRequestDispatcher("Verification.jsp").forward(request, response);
		}else  {
			request.setAttribute("codeError", "Wrong Code");
			request.getRequestDispatcher("Verification.jsp").forward(request, response);
		}
		
		
	}

}
