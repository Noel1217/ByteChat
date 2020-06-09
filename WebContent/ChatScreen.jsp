<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="java.util.*" %>
    <%@ page import="com.Chat.UserInbox" %>
    <%@ page import="com.Connector.Dao" %>
    <%@ page import="com.Chat.Message" %>
		<% 
		 String sessionUser =session.getAttribute("sessionUser").toString().trim();
		 String chatId  = null;
		  if(!session.getAttribute("Chat").toString().equals("Null")){
			  chatId  = session.getAttribute("Chat").toString();
			  Message mesg = null;
			  Dao sendDao = new Dao();
			  List<Message> mesgArray = null;
			  
			  mesgArray = sendDao.getMessage(chatId);
			  if(!mesgArray.isEmpty()){
				  for(int i = 0; i<mesgArray.size(); i++){
					  mesg = mesgArray.get(i);
					  if(mesg.getUserSend().equals(sessionUser)){
						  %>
								<div class="outgoing_msg">
								  <div class="sent_msg">
									<p><%=mesg.getMessage() %></p>
									<span class="time_date"><%=mesg.getTime() %></span> </div>
								</div>
						  <%
					  }else{
						  %>
							<div class="incoming_msg">
							  <div class="incoming_msg_img"> <img  src="./getImage?User=<%=mesg.getUserSend().trim() %>" alt="error"> </div>
							  <div class="received_msg">
								<div class="received_withd_msg">
								  <p><%=mesg.getMessage() %></p>
								  <span class="time_date"> <%=mesg.getTime() %></span></div>
							  </div>
							</div>
						  <%
					  }
				  }
			  }
			  
			 
		  }
		%>
		