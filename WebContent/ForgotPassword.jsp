<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1">
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <meta http-equiv="X-UA-Compatible" content="ie=edge">
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 <link rel="stylesheet" href="css/style.css">
 <link rel="shortcut icon" href="logo.png">
 <title>Forgot Password</title>
</head>

<body oncontextmenu="return false;">
<nav class="navbar navbar-expand-sm bg-my navbar-dark ">
  <!-- Brand/logo -->
   <a class="navbar-brand space-logo" href="index.html">
    <img src="logo.png" alt="Logo" style="width:35px;"  >
    &lt;Byte&gt;
  </a>
</nav>
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title lead text-center my-4">Forgot Password</div>
                        <form action="ForgotPasswordController" method="post">
                            <div class="grey-color">Email</div>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" placeholder="Email Address" required >
                            </div>
                            <a href="Login.jsp?userRegistered="><code>Sign In</code></a>
                            <div>
                                <div class="d-flex justify-content-center">
                                <button type="submit" class="btnGreen btn-default btn-lg ">
                                    Submit
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