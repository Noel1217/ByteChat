//Noel Gregory
//2020-04-18
//This servlet class to add new user
package com.Registration;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Common.Commons;
import com.Connector.Dao;
import com.Parameters.GetParameters;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")

public class RegistrationController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
	
	//This procedure takes in a request object and response object and add new user
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declaring Variables and objects
		Dao regDao = new Dao(); //Creating connection with database
		GetParameters data =  new GetParameters();
		int result = 0;
		String encyptedPassword = null;
		String password = null;
		String username = null;
		String email = null;
		InputStream image = null;
		Commons common = new Commons();

		//Retrieving information from jsp
		data = regDao.getInfo(request);
		
		//Assigning ArrayList values to Strings and InputStream
		username = data.getUsername();
		password = data.getPassword();
		email = data.getEmail();
		image =  data.getImage();
	
		//Checking user credentials
		if(password.length() > 6) {
			//Encrypting Password
			encyptedPassword = common.textEncoder(password);
			
			try {
				result  = regDao.createUser(username, encyptedPassword , email, image);
				regDao.closePstm();
				regDao.closeCon();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try catch
			
			if(result > 0 ) {
				request.setAttribute("username", username);
				request.getRequestDispatcher("BarLoader.jsp").forward(request, response);
			}else {
				//Setting attribute
				request.setAttribute("RegError", "Error or Username already taken");
				request.getRequestDispatcher("Registration.jsp").forward(request, response);
			}//end if result
	
	    }else {
		//Setting attribute
		request.setAttribute("RegError", "Password Incorrect");
		request.getRequestDispatcher("Registration.jsp").forward(request, response);
	    }//end if password			
		
	}//end doPost

}
