<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<link rel="stylesheet" href="example.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#submit{
background:  #99caff;
color: black;
}
#background{
background: #f2f2f2
}
#title{
border-bottom-width: medium;
border-bottom-style: solid;
font-style: italic;
color:  #002266;

}

</style>
</head>

<body id = "background">

    <div class="container" >

      <form class="form-signin" action="login.do" method="post">
        <h2 class="form-signin-heading" id = "title">Please Sign In</h2>
        <label for="inputPassword" class="sr-only">User Name</label>
        <input type="text" name = "username"  class="form-control" placeholder="User Name" required="required">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name = "password" id="inputPassword" class="form-control" placeholder="Password" required="required">
        
        <br/>

        <button class="btn btn-lg btn-primary btn-block" id= "submit" type="submit" >Sign In</button>
      <c:if test="${not empty loginFail}">
		<c:out value="${loginFail}"/>
		</c:if>
      </form>

    </div> <!-- /container -->

  

</body>
</html>