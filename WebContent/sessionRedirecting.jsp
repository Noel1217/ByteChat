
<%
if(session.getAttribute("sessionUser") == null){ 
	System.out.println("sessionDead");
	response.sendRedirect("Login.jsp?userRegistered=");
}else{
	System.out.println("sessionAlive");
}//end if session
%>
