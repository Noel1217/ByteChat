//Noel Gregory
//2020-06-1
//This servlet class find all user from the inputed information
package com.Freinds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Common.Commons;
import com.Connector.Dao;
import com.User.UserInfo;

/**
 * Servlet implementation class FindUsersController
 */
@WebServlet("/FindUsersController")
public class FindUsersController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
       
	//This procedure takes in a request object and response object and find all user from inputed information
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare Variable and objects
		Dao friendDao = new Dao();
		HttpSession session = request.getSession();
		String sessionUser = session.getAttribute("sessionUser").toString();
		List<UserInfo> users = null;
		ArrayList<String> following = null;
		String user  ="";
		Commons common = new Commons();
		
		if(request.getParameter("user").isEmpty()) {
			response.sendRedirect("FindFollowers.jsp");
		}else {
			user = request.getParameter("user");
			try {
				users = friendDao.getUsers(user);
				following = friendDao.getFollowingProfile(sessionUser);
				System.out.println();
				friendDao.closeRs();
				friendDao.closePstm();
				friendDao.closeSt();
				friendDao.closeCon();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try catch
			System.out.println(users);
			if(users.size() > 0) {
				//RequestAttribute
				request.setAttribute("userInfo", users);
				request.setAttribute("Following",following);
			}else {
				//RequestAttribute
				request.setAttribute("result","No result found for " + user);
			}//end if users
			
			request.getRequestDispatcher("FindFollowers.jsp").forward(request, response);
		}//end if request
	
	}//end service

}
