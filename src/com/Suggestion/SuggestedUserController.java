package com.Suggestion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Common.Commons;
import com.Connector.Dao;
import com.User.UserInfo;

/**
 * Servlet implementation class SuggestedUserController
 */
@WebServlet("/SuggestedUserController")
public class SuggestedUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare variables
		Dao suggestionDao = new Dao();
		Commons common = new Commons();
		String sessionUser = request.getParameter("user");
		ArrayList<String> allUsers = null;
		double distanceToUser = 0;
		HashMap<Double,String> similarUsers = new HashMap<Double,String>(); 
		List<UserInfo> likedUserPost = new ArrayList<UserInfo>();
		UserInfo session = null;
		List<UserInfo> topSuggestions = new ArrayList<UserInfo>();
		List<UserInfo> info = null;
		List<String> followingUsers = null;
		
		session = suggestionDao.getLikedPostUsers(sessionUser);
		if(session.getUserLikedPost() == null) {
			System.out.println("Empty");

		}else {

			allUsers = suggestionDao.getAllUsers();
			followingUsers = suggestionDao.getFollowingProfile(sessionUser);
			
			if(allUsers.isEmpty()) {
				System.out.println("No Users");
			}else {
				for(String user :allUsers) {
					if(user.equals(sessionUser)) {
						System.out.println("sessionUser");
					}else {
						likedUserPost.add(suggestionDao.getLikedPostUsers(user));
					}//end if user
				}

				
				for(UserInfo userPostLiked : likedUserPost) {
					if(userPostLiked.getName() != null) {
						distanceToUser = common.Similarity(userPostLiked.getUserLikedPost(), session.getUserLikedPost());
						similarUsers.put(distanceToUser, userPostLiked.getName());
					}
				}
				similarUsers = common.haspMapSort(similarUsers);

				if(similarUsers.isEmpty()) {
					System.out.println("No Suggestions");
				}else {
					for(Double key : similarUsers.keySet()) {
						if(!followingUsers.contains(similarUsers.get(key))) {
							topSuggestions.add(suggestionDao.getRequestedInfo(similarUsers.get(key)));
						}
					}
					
				}

			}
			
		}


		info = suggestionDao.getProfile(sessionUser);
		request.setAttribute("profileInfo", info);
		request.setAttribute("suggestions",topSuggestions );
		request.getRequestDispatcher("ProfileSuggestions.jsp").forward(request, response);
		
	}

	

}
