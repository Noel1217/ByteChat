<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <link rel="shortcut icon" href="logo.png">
    <script src="js/push.min.js"></script>
    <script src="js/serviceWorker.min.js"></script>

    <title>Login </title>
</head>
<body>
<%
String sessionUser  = request.getParameter("userRegistered");
if(sessionUser.isEmpty()){
}else{
%>
    <script>
    function start(){
   		Push.create("Byte Chat", {
   			body: " Welcome to Byte Chat <%=sessionUser%>",
   			icon: 'logo.png',
   			timeout: 5000,
   			onClick: function () {
   				window.focus();
   				this.close();
   			}
   		});
   	}
    window.onload =  start;
    </script>
<%
}%>
<nav class="navbar navbar-expand-sm bg-my navbar-dark ">
  <!-- Brand/logo -->
   <a class="navbar-brand space-logo" href="index.jsp">
    <img src="logo.png" alt="Logo" style="width:35px;" >
    &lt;Byte&gt;
  </a>
</nav>
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title lead text-center my-4">Sign In to&lt;Byte&gt;</div>
                        <form action="LoginController" method="GET">
                            <div class="form-group">
                                <div class="grey-color">Username </div>
                                <input type="text" class="form-control"  name="Username" placeholder="Enter Username" required >
                            </div>
                            <div class="form-group">
                                 <div class="grey-color">Password</div>
                                <input type="password" class="form-control"  name="Password" placeholder="Enter Password" required>
                            </div>
                            <div class="custom-control custom-checkbox mb-3">
                              <input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
                              <label class="custom-control-label" for="customCheck"><p>Remember Password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Need an Account? <a href="Registration.jsp"><code>Sign Up</code></a></p></label>
                            </div>
                              <p><a href="ForgotPassword.jsp"><code>Forgot Password?</code></a></p>
                              <p style="color:red;">${errorMessage}</p>
                            <div>
                               <div class="d-flex justify-content-center">
                                <button type = "submit" class="btnGreen btn-default btn-lg  ">
                                    Sign In
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