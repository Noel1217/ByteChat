package com.Registration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.Common.Commons;
import com.Connector.Dao;
import com.Parameters.GetParameters;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
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
			}
			
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
	    }//end if password.lenght();				
		
	}

}
