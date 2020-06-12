<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.Parameters.GetParameters" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link type="text/css" href="css/style.css" rel="stylesheet">
<link rel="shortcut icon" href="logo.png">
  <link href="css/styleSheet.css" rel="stylesheet" type="text/css">
 <link href="css/jquery.Jcrop.min.css" rel="stylesheet" type="text/css" />

 <!-- add scripts -->
 <script src="js/ByteChat.min.js"></script>
 <script src="js/jquery.min.js"></script>
 <script src="js/jquery.Jcrop.min.js"></script>
    <title> Image Filters</title>
    <script language="Javascript">
	jQuery(function($) {
		$('#image').Jcrop({
			onSelect : setCoordinates
		});
	});
	function setCoordinates(c) {
		document.myForm.x.value = c.x;
		document.myForm.y.value = c.y;
		document.myForm.w.value = c.w;
		document.myForm.h.value = c.h;
	};

	</script>
</script>
</head>
<body >
<%@ include file="Navbar.jsp" %> 
<div id="sessionCheck"></div>
 <div class="container">
 <%
 String imageOrVideo = request.getParameter("imageFilter");
 String OrginalimageOrVideo = request.getParameter("orginalFile");
 %>
   <form action="CropperController" method="post" name="myForm">
  	<input type="hidden" name="x" value=""/>
<input type="hidden" name="y" value=""/>
<input type="hidden" name="w" value=""/>
<input type="hidden" name="h" value=""/>
<input type="hidden" name="imageFilter" value="<%=imageOrVideo%>"/>
 

<div class="justify-content-center" style="margin-top:25px;">
	<%
	if(imageOrVideo.contains(".mp4")){
	 %>
	   <input type="hidden" name="video/image" value="video">
	   <video class="card-img-top" alt="could not load video" style="margin:20px;"width="1000px" height="650px" controls  >
	    <source src="<%=imageOrVideo%>"  type="video/mp4">
	   </video>	
	 <%
	}else{
	 %>
	   <input type="hidden" name="video/image" value="image">
	   <input type="hidden" name="imageFilter" value="<%=imageOrVideo%>"/>
	    <input type="hidden" name="orginalFile" value="<%=OrginalimageOrVideo%>"/>
	   <img src="<%=imageOrVideo%>" id="image"  >
	 <%
	}//end if imageOrVideo
	%>		   
</div>
 <button type="submit" style="margin:25px;" class="btn btn-danger " >
     Cancel 
 </button>
   <button type="submit"  class="btn btn-danger ">
     Upload
</button>
 </form>
 </div>
</body>
</html>