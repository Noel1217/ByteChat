<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page import="java.util.*" %>
     <%@ page import="com.Post.PostInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="shortcut icon" href="logo.png">   
    <link rel="stylesheet" href="css/style.css">
    <link href="css/styleSheet.css" rel="stylesheet" type="text/css">
 <script src="js/ByteChat.min.js"></script>
<title>Welcome</title>
</head>
<body >
<%@ include file="Navbar.jsp" %>  
<div id="sessionCheck"></div>
<div class="container ">
  <%
   String postTitle = null;
   int postId  = -1;
   String username = null;
   String postDate = null;
   String postImageOrVideo = null;
   List<List<PostInfo>> postUser = null;
   List<PostInfo> postData = null;
   PostInfo data = null;
   int totalLikes;
   postUser = (ArrayList<List<PostInfo>>) request.getAttribute("postInfo");
   if(!postUser.isEmpty() && postUser.get(0) !=null ){
	  for(int i = 0; i<postUser.size(); i++){
		  postData = postUser.get(i);
	  for(int j = 0; j<postData.size(); j++){
			data = postData.get(j);
			postTitle = data.getPostName();
			postId = data.getPostId();
			username = data.getUser();
			postDate = data.getPostDate();
			totalLikes = data.getPostLikes();
			postImageOrVideo = data.getPostImageOrVideo();
       %>
	   <div class="d-flex justify-content-center" >
		<div class="cardPost">
		<%
		if(postImageOrVideo.contains(".mp4")){
			%>
			  <video class="card-img-top" s >
	             <source src="<%=postImageOrVideo%>"  type="video/mp4">
	          </video>	
			<%			
		}else{
		    %>
		     <img class="card-img-top"  style="height:550px;" src="<%=postImageOrVideo%>" alt="Card image cap">
			<%
		}
		%>		 
		  <div class="card-body " >
		  <form action="AddLikeController" method="Post">
		    <button name="likeBtn" value="<%=postId %>" class="btn btn-secondary btn-sm">&#128077 Like : <%=totalLikes %>
	       </button>
		  </form>
		   <h5 class="card-title"><%=postTitle%></h5>
		   <a href="./ProfileController?user=<%=username%>&Page=false" class="card-link"><%=username%></a>
		   <p class="text-right"><small class="text-muted"><%= postDate %></small></p>		   
		  </div>
		</div>
	</div>
</div>
	<%}// end for j
	}//end for i
  }%>
  
  <script type="text/javascript">

const videos = document.querySelectorAll("video")

videos.forEach(video => {
  video.addEventListener("mouseover", function () {
    this.play()
  })
  
  video.addEventListener("mouseout", function () {
    this.pause()
  })
  
  video.addEventListener("touchstart", function () {
    this.play()
  })
  
  video.addEventListener("touchend", function () {
    this.pause()
  })
})

</script>
</body>
</html>