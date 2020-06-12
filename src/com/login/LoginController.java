//Noel Gregory
//2020-04-19
//This servlet class to check credentials and login
package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connector.Dao;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
       
	//This procedure takes in a request object and response object and check credentials and login
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declaring variables and objects
		String user = request.getParameter("Username");
		String pass = request.getParameter("Password");
		String error= null;;
		String status = "Online";
		HttpSession session = request.getSession();
		int result = 0;
		Dao loginDao = new Dao(); //initiating connection with database
		
		//Check User Credentials
		result = loginDao.checkCredentials(user, pass);
		loginDao.closePstm();
		loginDao.closeRs();
		loginDao.closeCon();
		
		//Checking result
		if(result == 1) {
			session.setAttribute("sessionUser", user);
			loginDao.userStatus(user, status);
			response.sendRedirect("./PostController");
		}else if(result == 2) {
			request.setAttribute("errorMessage", "Invalid user ");
			request.getRequestDispatcher("Login.jsp?userRegistered=").forward(request, response);
		}else if(result == 3) {
			request.setAttribute("errorMessage", "Invalid password");
			request.getRequestDispatcher("Login.jsp?userRegistered=").forward(request, response);
		}else if(result == 4) {
			request.setAttribute("errorMessage", "Cannot Connect to Database");
			request.getRequestDispatcher("Login.jsp?userRegistered=").forward(request, response);
		}else if(result == 5) {
			session.setAttribute("sessionUser", user);
			response.sendRedirect("Verification.jsp");
		}else {
			error ="Error";
		}//end if result

	}//end doGet

	

}
