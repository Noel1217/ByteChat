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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link type="text/css" href="css/style.css" rel="stylesheet">
<link rel="shortcut icon" href="logo.png">
 <script src="js/ByteChat.min.js"></script>
<link href="css/styleSheet.css" rel="stylesheet" type="text/css">
<link href="css/jquery.Jcrop.min.css" rel="stylesheet" type="text/css" />
</script>
</head>
<body >

   <%@ include file="Navbar.jsp" %> 
<div id="sessionCheck"></div>
    <div class="container">
    <%
    //Declare variables
    String data = (String) request.getAttribute("fileInput");
    String orginalFile = (String) request.getAttribute("orginalFile");
    %>
 
	<div class="justify-content-center" style="margin-top:25px;">
	<img src="<%=data%>"   >  
	</div>
	<ul>
	<li>
	<form action="ImageFilterController" method="get" >
    <input type="hidden" name="imageFilter" value="<%=data%>"/>
       <input type="hidden" name="orginalFile" value="<%=orginalFile%>"/>
    <input type="hidden" name="N" value="false"/>
    <input type="hidden" name="G" value="true"/>
     <button type="submit"  class="btn btn-danger ">
		      GreyScale
	 </button>
    </form>
	</li>
	<li>
	  <form action="ImageFilterController" method="get" >
     <input type="hidden" name="imageFilter" value="<%=data%>"/>
     <input type="hidden" name="orginalFile" value="<%=orginalFile%>"/>
     <input type="hidden" name="N" value="true"/>
     <input type="hidden" name="G" value="false"/>
      <button type="submit"  class="btn btn-danger ">
		      Negative
	  </button>
    </form>
	</li>
	</ul>
    
   
    <form action = "Cropper.jsp">
     <input type="hidden" name="imageFilter" value="<%=data%>"/>
    <input type="hidden" name="orginalFile" value="<%=orginalFile%>"/>
      <button type="submit"  style="margin:25px;" class="btn btn-danger ">
		      Crop
	  </button>
    </form>
    </div>
</body>
</html>