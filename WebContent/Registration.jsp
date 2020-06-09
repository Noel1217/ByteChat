<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="shortcut icon" href="logo.png">
    <title>Registration</title>
</head>

<body oncontextmenu="return false;">
    <nav class="navbar navbar-expand-sm bg-my navbar-dark ">
        <!-- Brand/logo -->
         <a class="navbar-brand space-logo" href="index.html">
          <img src="logo.png" alt="Logo" style="width:35px;" >
          &lt;Byte&gt;
        </a>
      </nav>
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card"  style="width: 30rem;">
                    <div class="card-body">
                        <div class="card-title lead text-center my-4">Sign Up to&lt;Byte&gt;</div>
                        <form action="RegistrationController"  enctype= multipart/form-data  method="Post"  >
                            <div class="grey-color">Username</div>
                            <div class="form-group">
                                <input type="text"  name="username" class="form-control" placeholder="Enter Username" required >
                            </div>
                            <div class="grey-color">Email</div>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" placeholder="Email Address" required >
                            </div>
                            <div class="grey-color">Password</div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control" placeholder="Enter Password" required >
                            </div>
                            <div class="grey-color">Upload Profile Picture</div>
                            <div class="custom-file">
                                <input type="file" name="image" class="custom-file-input" accept="image/*"  >
                                <label class="custom-file-label">Upload Image </label>
                              </div>
                              <br>
                              <br>
                            <div class="custom-control custom-checkbox mb-3">
                                <input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
                                <label class="custom-control-label" for="customCheck"><p>Do you agree with Terms &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Need an Account? <a href="Login.jsp?userRegistered="><code>Sign In</code></a></p></label>
                              </div>
                            <div>
                            <p style="color:red;">${RegError}</p>
                               <div class="d-flex justify-content-center">
                                <button type="submit" class="btnGreen btn-default btn-lg ">
                                    Register
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