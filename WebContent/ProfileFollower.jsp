<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.User.UserInfo" %>
    <%@ page import="com.Common.Commons" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link type="text/css" href="css/style.css" rel="stylesheet">
<link rel="shortcut icon" href="logo.png">
  <link href="css/styleSheet.css" rel="stylesheet" type="text/css">
 <script src="js/ByteChat.min.js"></script>
<title>Profile Requests</title>
</head>
<body >
<%@ include file="Navbar.jsp" %> 
<div id="sessionCheck"></div>
<div class="profileBody">
<%
String name = null;
int followerCount =  0;
UserInfo dataRequest = null;
String sessionUser = session.getAttribute("sessionUser").toString();
String status = "";
String labelOfflineOrOnline = null;
List<UserInfo> followerArray = null;
ArrayList<String> followingArray = null;
Boolean requestExist = null;
String bio = null;
List<UserInfo> info = (ArrayList<UserInfo>) request.getAttribute("profileInfo");
for(int i = 0; i< info.size(); i++){
	UserInfo data  = info.get(i);
	name = data.getName();
	bio = data.getBio();
	if(bio == null){
		bio="Hey there , I am using ByteChat";
	}
%>
<div class="container">
    <div class="row profile">
		<div class="col-md-3">
			<div class="profile-sidebar">
				<!-- SIDEBAR USERPIC -->
				<div class="profile-userpic">
					<img src="./getImage?User=<%=name%>" class="circle" alt="No Picture Uploaded">
				</div>
				<!-- END SIDEBAR USERPIC -->
				<!-- SIDEBAR USER TITLE -->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name">
						<%=name %>
					</div>
					<div class="profile-usertitle-bio">
						<%=bio %>
					</div>
				</div>
				<!-- END SIDEBAR USER TITLE -->
				<!-- SIDEBAR BUTTONS -->
				<div class="profile-userbuttons">
					
			        <form action="LogoutController" method="post"> 
			        <button class="btn btn-primary btn-md" type="submit">Logout</button>
			        </form>				
				</div>
				<!-- END SIDEBAR BUTTONS -->
				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
				<%
				   if(sessionUser.contains(name)){
					   %>
					     
					   <ul style="list-style: none;">
						<li>
						<a href="#">Account Settings </a>

						</li>				
						<li>
                            <a href="./ProfileController?user=<%=sessionUser%>&Page=Following">
							Following </a>
						</li>
						<li>
							<a href="./SuggestedUserController?user=<%=sessionUser%>">
							 Suggestions </a>
						</li>
						 <li>
							<a href="./ProfileController?user=<%=name%>&Page=false">
							<i class="glyphicon glyphicon-user"></i>
							Back</a>
						</li>
					</ul>
					   <%

				   }
				%>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		<div class="col-md-9" >
          <div class="profile-content" style=" height: 675px;">
          <table class="table table-striped table-space"  >
           <tbody>
           <%
           followerArray = (ArrayList<UserInfo>) request.getAttribute("Followers");
           followingArray = (ArrayList<String>) request.getAttribute("Following");
           if(followerArray.isEmpty()){
        	   requestExist = false;
        	   %>
        	   <h6>No Followers</h6>
        	   <%
           }else{
        	   requestExist = true;
           }//end if requestedArray
           
           //Iterating through array
           if( requestExist == true){
        	   for(int j = 0; j<followerArray.size(); j++){
        		   dataRequest = followerArray.get(j);
        		   status  = dataRequest.getStatus();
        		   followerCount = Commons.getFollowerCount(dataRequest.getName());
        		   if(status.contains("Online")){
        				labelOfflineOrOnline = "label success";
        			}else{
        				labelOfflineOrOnline = "label danger";
        			}//end if status
        		   
        		   if(followingArray.contains(dataRequest.getName())){
        			   System.out.println("Already folowing");
        			   %>
        			     <form action="UnFollowController" method="post">
			   			  <input type="hidden" name="Page" value="ProfileFollowing">
			   			    <tr>
			   			     <td>
			   			      <img class="img-circle img-align"src="./getImage?User=<%= dataRequest.getName() %>" >
			   			     </td>
			   			     <td><div class="text-alignn"><%=dataRequest.getName()  %></div></td>
			   			     <td><div class="text-align">Followers: <%=followerCount %></div></td>
			   			     <td><div class="text-align"><span class="<%=labelOfflineOrOnline %>"><%=status %></span></div></td>
			   			     <td><div class="text-right img-align"><button type="submit" name="unRequestName" value="<%=dataRequest.getName()%>" class="btn btn-primary" >Following</button></div></td>
			   			    </tr>
			   			  </form>
        			   <%
        			   
        		   }else{
        			   %>
          			  <form action="FollowController?page=Profile" method="post">
          			    <tr>
          			     <td>
          			      <img class="img-circle img-align"src="./getImage?User=<%= dataRequest.getName() %>" >
          			     </td>
          			     <td><div class="text-alignn"><%=dataRequest.getName()  %></div></td>
          			      <td><div class="text-align">Followers: <%=followerCount %></div></td>
          			     <td><div class="text-align"><span class="<%=labelOfflineOrOnline %>"><%=status %></span></div></td>
          			     <td><div class="text-right img-align"><button type="submit" name="followerName" value="<%=dataRequest.getName()%>" class="btn btn-primary" >Follow</button></div></td>
          			    </tr>
          			  </form>
          			   <%	   
        		   }//end for followingArray
        	   }//end for i
           }//end if requestedExist           
           %>
           </tbody>
          </table>           
          </div>
		</div>
	</div>
</div>
</div>
<%}//end for i %>
</body>
</html>