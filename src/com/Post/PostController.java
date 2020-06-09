package com.Post;

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

/**
 * Servlet implementation class PostController
 */
@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare objects
		Dao postDao = new Dao();
		HttpSession session = request.getSession();
		String user = session.getAttribute("sessionUser").toString();
		List<List<PostInfo>> data = new ArrayList<List<PostInfo>>();
		ArrayList<String> userFollowings = null;
		ArrayList<String>  userFollowers = null;
		Commons common = new Commons();
         
		userFollowings = postDao.getFollowingProfile(user);
		userFollowers = postDao.getFollowersProfile(user);

		if(!userFollowings.isEmpty()  && !userFollowers.isEmpty()) {
			userFollowings.retainAll(userFollowers);
			
			for(int i = 0; i<userFollowings.size(); i++) {
				data.add(postDao.getPost(userFollowings.get(i)));
			}
			postDao.closePstm();
			postDao.closeRs();
			postDao.closeCon();
		}

		
		//Requesting attribute and redirecting
		request.setAttribute("postInfo", data);
		request.getRequestDispatcher("Feeds.jsp").forward(request, response);
	}

}
