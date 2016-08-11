<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>NavBar</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<link rel="stylesheet" href="example.css">
<style type="text/css">
#background{
background: #f2f2f2
}
#title{
background: #99caff;
color: black;




}
#link{
color: black;
float: right;
border-width: thin;
border-style: hidden;

}



</style>

</head>
<body id = "background">
<nav class="navbar navbar-default"id= "title">
        <div class="container" >
          <div class="navbar-header">

            <a class="navbar-brand" id = "title">Invoice History</a>
          </div>
          <div class="navbar-collapse collapse"id = "link" >
            <ul class="nav navbar-nav">
            <c:if test="${loggedIn.role eq 21 }">
              <li><a href="/ExpenseReimbursement/secure/employeeTools/CreateReimbursement.jsp"id = "link" >Create New Reimbursement Request</a></li>
				</c:if>
				<li><a href="logout.do"id = "link" >Logout</a></li>
      
                </ul>
          </div><!--/.nav-collapse -->
        </div>
      </nav>
<div>

</body>
</html>