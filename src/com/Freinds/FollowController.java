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
	private static final long serialVersionUID = 1L;
   
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
		}
		
		//Connection to database
		rslt2 = requestedDao.acceptFollower(user,FollowUser);
		System.out.println(rslt2);
		requestedDao.closePstm();
		requestedDao.closeCon();
		
		//Check result
		if(rslt2 >1) {
			response.sendRedirect(page);
		}else {
			response.sendRedirect("Error.jsp");
		}
	}

}
