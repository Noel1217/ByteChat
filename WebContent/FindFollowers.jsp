<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page import="java.util.*" %>
     <%@ page import="com.User.UserInfo" %>
     <%@ page import="com.Common.Commons" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Friends</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link type="text/css" href="css/style.css" rel="stylesheet">
 <script src="js/ByteChat.min.js"></script>
<link rel="shortcut icon" href="logo.png">
  <link href="css/styleSheet.css" rel="stylesheet" type="text/css">
</head>
<body oncontextmenu="return false;">
<%@ include file="Navbar.jsp" %> 


<div id="sessionCheck"></div>

<div class="container ">
  <div class="table-wrapper-scroll-y my-custom-scrollbar">
   <form action="FindUsersController" class="search" method="get"  >
    <div class="input-group mb-2 ">
      <input type="text" class="form-control" name="user" placeholder="Search Username" aria-label="Recipient's username" aria-describedby="basic-addon2">
     <div class="input-group-append">
      <button class="btn btn-danger" type="submit">Search</button>
     </div>
    </div>
  </form>
<table class="table table-striped table-space"  >
 <tbody>
  <%
   String name = null;
   int followerCount =0;
   String result ="";
   String status = null;
   String labelOfflineOrOnline = null;
   String sessionUser = session.getAttribute("sessionUser").toString();
   System.out.println(sessionUser);
   List<UserInfo> info = null;
   ArrayList<String> requestedFollowers = null;
   if(request.getAttribute("Following") != null){
	   requestedFollowers  = (ArrayList<String>) request.getAttribute("Following");
   }
   if(request.getAttribute("userInfo") !=null){
	  info = (ArrayList<UserInfo>)request.getAttribute("userInfo");
	  for(int i = 0; i<info.size(); i++){
		UserInfo data = info.get(i);
		name = data.getName();
		followerCount = Commons.getFollowerCount(name);
		status = data.getStatus();
		if(status.contains("Online")){
			labelOfflineOrOnline = "label success";
		}else{
			labelOfflineOrOnline = "label danger";
		}
		if(requestedFollowers.contains(name) && !requestedFollowers.contains(sessionUser)){
			%>
			 <form action="UnFollowController" method="post">
			   <input type="hidden" name="Page" value="FindUser">
			    <tr>
			     <td>
			      <img class="img-circle img-align"src="./getImage?User=<%=name %>" >
			     </td>
			     <td><div class="text-alignn"> <a href="./ProfileController?user=<%=name%>&Page=false" class="card-link"><%=name%></a></div></td>
			     <td><div class="text-align">Followers: <%=followerCount %></div></td>
			    			     <td><div class="text-align"><span class="<%=labelOfflineOrOnline %>"><%=status %></span></div></td>
			     <td><div class="text-right img-align"><button type="submit" name="unRequestName" value="<%=name %>" class="btn btn-success" >UnFollow</button></div></td>
			    </tr>
			  </form>
			<%
		}else{
			  %>
			  <form action="FollowController?page=findUser" method="post">
			    <tr>
			     <td>
			      <img class="img-circle img-align"src="./getImage?User=<%=name %>" >
			     </td>
			     <td><div class="text-alignn"><a href="./ProfileController?user=<%=name%>&Page=false" class="card-link"><%=name%></a></div></td>
			     <td><div class="text-align">Followers: <%=followerCount %></div></td>
			     <td><div class="text-align"><span class="<%=labelOfflineOrOnline %>"><%=status %></span></div></td>
			     <td><div class="text-right img-align"><button type="submit" name="followerName" value="<%=name %>" class="btn btn-primary" >Follow</button></div></td>
			    </tr>
			  </form>
			   <%
		}//end if requested.contains(name)
	   }// end for i 
    }else{
    	if(request.getAttribute("result") !=null){
    		result = request.getAttribute("result").toString();
    	}
    	%>
    	<h6><%=result %></h6>
    	<%
    }%>
  </tbody>
    </table>
  </div>
 </div>
</body>
</html>