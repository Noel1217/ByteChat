//Noel Gregory
//2020-04-18
//This servlet class getImage using username
package com.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Connector.Dao;

/**
 * Servlet implementation class getImage
 */
@WebServlet("/getImage")
public class getImage extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
       
	//This procedure takes in a request object and response object and gets Image from username
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declaring variables and objects
		String user = request.getParameter("User");
		ServletOutputStream out = null;
		Dao imageDao = new Dao();
		byte[] image = null;
		

		try {
			image = imageDao.getImage(user);
			imageDao.closePstm();
			imageDao.closeRs();
			imageDao.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		out = response.getOutputStream();
		out.write(image);
		out.close();
	}//end for service

}
