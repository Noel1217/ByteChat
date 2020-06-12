<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
     <%@ page import="com.User.UserInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link type="text/css" href="css/style.css" rel="stylesheet">
 <script src="js/ByteChat.min.js"></script>
<link rel="shortcut icon" href="logo.png">
 <link href="css/styleSheet.css" rel="stylesheet" type="text/css">
<title>Profile</title>
</head>

<body >
<%@ include file="Navbar.jsp" %> 
<div id="sessionCheck"></div>
<div class="profileBody">
<%
//Declare varibales
String name = null;
String postId = null;
String imageOrVideo=null;
String sessionUser = session.getAttribute("sessionUser").toString();
String bio = null;
List<UserInfo> info = (ArrayList<UserInfo>) request.getAttribute("profileInfo");
for(int i = 0; i< info.size(); i++){
	UserInfo data  = info.get(i);
	name = data.getName();
	bio = data.getBio();
	if(bio == null){
		bio="Hey there , I am using ByteChat";
	}//end if bio
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
				<%
				 if(sessionUser.contains(name)){
					 %>
					   <form action="LogoutController" method="post"> 
			           <button class="btn btn-primary btn-md" type="submit">Logout</button>
			           </form>		
					 <% 
				 }//end if sessionUser
				%>
				</div>
				<!-- END SIDEBAR BUTTONS -->
				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
				<%
				   if(sessionUser.contains(name)){
					   %>
					   <ul style="list-style: none;">			
						<li>
							<a href="./ProfileController?user=<%=sessionUser%>&Page=Followers">
							Followers </a>
						</li>
						<li>
                            <a href="./ProfileController?user=<%=sessionUser%>&Page=Following">
							Following </a>
						</li>
						<li>
							<a href="./SuggestedUserController?user=<%=sessionUser%>">
							Suggestions </a>
						</li>
					</ul>
					   <%
				   }//end if sessionUser
				%>
					
				</div>
				<!-- END MENU -->
			</div>
		</div>
		<div class="col-md-9" >
            <div class="profile-content" style=" height: 675px;">
            <div class="row ">
            <% 
            List<UserInfo> images = (ArrayList<UserInfo>) request.getAttribute("images/video");
            for(int j = 0; j<images.size(); j++){
            	UserInfo profileImages = images.get(j);
            	postId = profileImages.getPostId();
            	imageOrVideo = profileImages.getImageOrVideo();
            	%>       
            	<%
				if(imageOrVideo.contains(".mp4")){
					%>
					 <div class="column">                
                     <video controls  width="250">
			             <source   src="<%=imageOrVideo %>" type="video/mp4">
			          </video>           
                   </div>				
					<%
					
				}else {
				 %>
				   <div class="column">
                    <a href="<%=imageOrVideo %>">
                     <img src="<%=imageOrVideo %>"  height="142px" alt="error displaying photos" style="width:100%">
                   </a>
                   </div>
				
					<%
				}//end if imageOrVideo				
              
            }//end for j
            %>
             </div>
            </div>
		</div>
	</div>
</div>
</div>
<%}//end for i %>
</body>
</html>