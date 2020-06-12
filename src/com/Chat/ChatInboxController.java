//Noel Gregory
//2020-06-1
//This servlet class gets all the chat that the session user participates in
package com.Chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connector.Dao;

/**
 * Servlet implementation class ChatInboxController
 */
@WebServlet("/ChatInboxController")
public class ChatInboxController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;
	
    //This procedure takes in a request object and response object and get all chat the user participates in
	//request:HttpServletRequest:containing request object from website
	//reponse:HttpServletResponse:containg response object to the website from server side
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare varibales
		HttpSession session = request.getSession();
		String chatName = request.getParameter("Chat");
		String sessionUser = session.getAttribute("sessionUser").toString();
		Dao inboxDao = new Dao();
		UserInbox userInbox = null;
		ArrayList<String> usersFollowing = new ArrayList<String>();
		List<UserInbox> inboxArray = null;
		
		//Getting Particpated Chat
		inboxArray = inboxDao.getUserInbox(sessionUser);
		usersFollowing = inboxDao.getFollowingProfile(sessionUser);
		inboxDao.closeRs();
		inboxDao.closePstm();
		inboxDao.closeCon();
		
		request.setAttribute("inboxData", inboxArray);
		request.setAttribute("Chat", chatName);
		session.setAttribute("Chat", chatName);
		request.setAttribute("following", usersFollowing);
		request.getRequestDispatcher("Chat.jsp").forward(request, response);
		
	}//end service


}
