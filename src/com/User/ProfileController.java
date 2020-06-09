package com.User;

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

import org.apache.catalina.tribes.util.Arrays;

import com.Common.Commons;
import com.Connector.Dao;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare objects
		String user = request.getParameter("user");
		String requestOrProfilePage =request.getParameter("Page");
		Dao profileDao = new Dao();
		List<UserInfo> info = null;
		Commons common = new Commons();
		List<UserInfo> imagesOrVideo = null;
		List<UserInfo> userInfo = new ArrayList<UserInfo>();
		ArrayList<String> followerUserArray = null;
		List<UserInfo> followingUserArray = new ArrayList<UserInfo>();
		ArrayList<String> followingUserAr= null;

	
		switch(requestOrProfilePage) {
			case "Followers":
				info = profileDao.getProfile(user);
				followerUserArray = profileDao.getFollowersProfile(user);
				followingUserAr  = profileDao.getFollowingProfile(user);
				for(String username:followerUserArray ) {
					if(!username.isEmpty() && !username.equals(user)) {
						userInfo.add(profileDao.getRequestedInfo(username));
					}
				}
				
				break;
			case "Following":
				info = profileDao.getProfile(user);
				followingUserAr  = profileDao.getFollowingProfile(user);
				for(String users:followingUserAr) {
					System.out.println(users);
					if(!users.isEmpty() && !users.equals(user)) {
						System.out.println("Hi");
						followingUserArray.add(profileDao.getRequestedInfo(users));
					}
				}
			
				break;
			default:
				info = profileDao.getProfile(user);
				imagesOrVideo = profileDao.getProfileId(user);
				break;
	    }
		
		//Closing connections
		profileDao.closePstm();
		profileDao.closeRs();
		profileDao.closeCon();
		
		//Checking string and redirecting to specfic page
		switch(requestOrProfilePage) {
			case "Followers":
				request.setAttribute("Followers",userInfo);
				request.setAttribute("Following",followingUserAr);
			    request.setAttribute("profileInfo", info);
			    request.getRequestDispatcher("ProfileFollower.jsp").forward(request, response);
				break;
			case "Following":
				request.setAttribute("Following",followingUserArray);
			    request.setAttribute("profileInfo", info);
			    request.getRequestDispatcher("ProfileFollowing.jsp").forward(request, response);
				break;
			default:
				request.setAttribute("images/video", imagesOrVideo);
				request.setAttribute("profileInfo", info);
				request.getRequestDispatcher("Profile.jsp").forward(request, response);
				break;
		}
	}

	

}
