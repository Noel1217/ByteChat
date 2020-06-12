//Noel Gregory
//2020-04-1
//This servlet class unfollow other users
package com.Freinds;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connector.Dao;

/**
 * Servlet implementation class UnFollowController
 */
@WebServlet("/UnFollowController")
public class UnFollowController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
  
	//This procedure takes in a request object and response object and unfollow other user from inputed user
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare variables
		HttpSession session = request.getSession();
		String  unFollowUser = request.getParameter("unRequestName");
		String  page = request.getParameter("Page");
		String user = session.getAttribute("sessionUser").toString();
		Dao unFollowDao = new Dao();
		int result = 0;
		
		  //connection database
		result = unFollowDao.unFollow(user, unFollowUser);
		unFollowDao.closePstm();
		unFollowDao.closeCon();
		
		
		if(page.equals("FindUser")) {
			//check result
			if(result > 1 ) {
				response.sendRedirect("FindFollowers.jsp");
			}else {
				response.sendRedirect("Error.jsp");
			}//end if result
		}else if(page.equals("ProfileFollowing")){

			//check result
			if(result > 1 ) {
				System.out.println("dd");
				response.sendRedirect("./ProfileController?user="+user+"&Page=Following");
			}else {
				response.sendRedirect("Error.jsp");
			}//end if result
		}//end if page
      
	}//end doPost

}
