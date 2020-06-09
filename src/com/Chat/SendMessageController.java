package com.Chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connector.Dao;

/**
 * Servlet implementation class SendMessageController
 */
@WebServlet("/SendMessageController")
public class SendMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare variable
		HttpSession session = request.getSession();
		String user = session.getAttribute("sessionUser").toString();
		String chatId = request.getParameter("chatId");
		String message = request.getParameter("message");
		String file = null;
		Dao sendDao = new Dao();
		int result = 0;
		
		result = sendDao.addMessage(message, user, chatId, file);
		
		if(result > 0) {
			response.sendRedirect("./ChatInboxController?Chat="+chatId);
		}else {
			response.sendRedirect("error.jsp");
		}
	}

}
