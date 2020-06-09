<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
 <script src="js/ByteChat.min.js"></script>
  <link href="css/styleSheet.css" rel="stylesheet" type="text/css">
    <title>Post</title>
</head>

<body oncontextmenu="return false;">
   <%@ include file="Navbar.jsp" %> 
   <%@ include file="sessionRedirecting.jsp" %>  
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card"  style="width: 30rem;">
                    <div class="card-body">
                        <div class="card-title lead text-center my-4">Upload to&lt;Byte&gt;</div>
                        <form action="PostToStoryController"  enctype= multipart/form-data  method="Post"  >
                            <div class="grey-color">Title Comment</div>
                            <div class="form-group">
                                <input type="text"  name="title" class="form-control" placeholder="Enter Title Comment" required >
                            </div>
                            <div class="grey-color">Upload Picture</div>
                            <div class="custom-file">
                                <input type="file" name="ImageOrVideos" class="custom-file-input"   >
                                <label class="custom-file-label">Upload Image/Video </label>
                              </div>
                              <br>
                              <br>     
                              <div class="custom-control  mb-3">              
                                <label ><p>Uploading may take more time depending on the Size</label>
                              </div>
                            <p style="color:red;">${postError}</p>
                               <div class="d-flex justify-content-center">
                                <button type="submit" class="btnGreen btn-default btn-lg ">
                                    Next
                                </button>
                               </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>