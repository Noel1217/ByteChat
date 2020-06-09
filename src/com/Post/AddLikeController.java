package com.Post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connector.Dao;

/**
 * Servlet implementation class AddLikeController
 */
@WebServlet("/AddLikeController")
public class AddLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declaring variables and objects
		HttpSession session = request.getSession();
		String user =  session.getAttribute("sessionUser").toString();
		int postId = Integer.parseInt(request.getParameter("likeBtn"));
		Dao likeDao = new Dao();
		int result;
		
		//connection with database
		result = likeDao.addLike(user, postId);
		likeDao.closePstm();
		likeDao.closeCon();
		
		//Checking result
		if(result > 0) {
			response.sendRedirect("./PostController");
		}else {
			response.sendRedirect("./PostController");
		}
	}

}
