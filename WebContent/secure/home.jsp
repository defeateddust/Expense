<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<c:if test="${loggedIn.role eq 21 }" >
<%@ include file="/secure/Employee.jsp" %>
</c:if>
<c:if test="${loggedIn.role eq 22 }" >
<%@ include file= "/secure/ManagerView.jsp" %>
</c:if>


</body>
</html>