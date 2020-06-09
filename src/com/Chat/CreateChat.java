package com.Chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import com.Connector.Dao;
import com.Parameters.GetParameters;

/**
 * Servlet implementation class CreateChat
 */
@WebServlet("/CreateChat")
public class CreateChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare varibales
		Dao chatDao = new Dao();
		String groupOrMessage = request.getParameter("type");
		String[] messageUser = request.getParameterValues("userSelected");
		GetParameters info = null;
		int result = 0;
		String chatName = null;
		String imageUrl = null;
		String members =null;
		
		if(groupOrMessage.equals("message" ) && messageUser.length == 2) {
			String temp = Arrays.toString(messageUser).replace("{", "").replace("}","");
			result = chatDao.createChat(temp);
			chatDao.closePstm();
			chatDao.closeCon();
		}else if(groupOrMessage.equals("group")) {
			info = chatDao.getChatInfo(request);
			
			chatName = info.getChatName();
			members = info.getMembers();
			imageUrl = info.getImageUrl();
			
			result = chatDao.createChat(members, chatName, imageUrl);
			chatDao.closePstm();
			chatDao.closeCon();
		}else {
			System.out.println("Error");
		}
		
		if(result > 0) {
			response.sendRedirect("./ChatInboxController?Chat=Null");
		}else {
			response.sendRedirect("Error.jsp");
		}
	}

}
