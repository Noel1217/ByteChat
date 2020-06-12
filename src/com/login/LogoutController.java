//Noel Gregory
//2020-03-15
//This servlet class to logout user
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
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
    

	//This procedure takes in a request object and response object and logout user
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare varibales
    	HttpSession session = request.getSession();
    	session.invalidate();
    	response.sendRedirect("Login.jsp?userRegistered=");
   
	}//end doPost
    

}
