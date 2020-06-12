//Noel Gregory
//2020-04-1
//This servlet class follow other users
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
 * Servlet implementation class FollowController
 */
@WebServlet("/FollowController")
public class FollowController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
	
	//This procedure takes in a request object and response object and follow other user from inputed user
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare variables
		HttpSession session = request.getSession();
		String user = session.getAttribute("sessionUser").toString();
		String page = request.getParameter("page");
		String FollowUser = request.getParameter("followerName");
		Dao requestedDao = new Dao();
		int rslt2 = 0;
		
		if(page.equals("Profile")) {
			page = "./ProfileController?user="+user+"&Page=Followers";
		}else {
			page = "FindFollowers.jsp";
		}//end if page
		
		//Connection to database
		rslt2 = requestedDao.acceptFollower(user,FollowUser);
		requestedDao.closePstm();
		requestedDao.closeCon();
		
		//Check result
		if(rslt2 >1) {
			response.sendRedirect(page);
		}else {
			response.sendRedirect("Error.jsp");
		}//end if rslt2
	}//end do Post

}
