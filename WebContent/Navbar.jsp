<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<nav class="navbar navbar-expand-sm bg-my navbar-dark ">
  <!-- Brand/logo -->
   <a class="navbar-brand space-logo" >
    <img src="logo.png" alt="Logo" style="width:35px; class="d-inline-block align-top" >
    &lt;Byte&gt;
  </a>
 
  <%
	String user = null;
	if(session.getAttribute("sessionUser") != null){ 
		user = session.getAttribute("sessionUser").toString();
	}
  %>
 <ul class="navbar-nav space-items ">
    <li class="nav-item  ">
      <a class="nav-link active" href="./ChatInboxController?Chat=Null">Chat</a>
    </li>
     <li class="nav-item  ">
      <a class="nav-link active" href="./PostController">Feeds</a>
    </li>
    <li class="nav-item ">
      <a class="nav-link active" href="FindFollowers.jsp">Find Friends</a>
    </li>
    <li class="nav-item ">
      <a class="nav-link active" href="./ProfileController?user=<%=user%>&Page=false">Profile</a>
    </li>
  </ul>
	<div class="navbar-collapse ">
	     <ul class="navbar-nav ml-auto">
	         <li class="nav-item">
	           <form action="PostToStory.jsp" methods="post">
		         <button class="btn btn-primary btn-md" type="submit">Post</button>
		        </form>
	          </li>
	     </ul>
	  </div>
</nav>
