<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home</title>
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

#table{
background:#cce6ff;
}


</style>

</head>
<body id = "background">
<div>
<%@ include file="NavBar.jsp" %>
</div>
<div>

<table class="table table-striped table-bordered" id = "table"cellspacing="0" width="100%">
            <thead>
            
              <tr>
                <th>ID</th>
                <th>Status</th>
                <th>Amount</th>
                <th>Date Submitted</th>
                <th>Date Resolved</th>
                <th>Resolved By</th>
                <th>Type</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var = "list" items="${reimbursements }">
              <tr>
                <td>
                <c:out value="${list.id}"/>
               <td> 
                 <c:forEach var = "status" items="${statuses}" >
                 <c:if test="${status.id eq list.status }">
               	 <c:out value="${status.name}"/>  
                 </c:if>
                 
                 </c:forEach>
                 <td>
                 <div id="amountId">
                <f:formatNumber type="currency"><c:out value="${list.amount}" ></c:out></f:formatNumber>
                </div>
               	</td>
                 <td> 
                 
                <c:out value="${list.submitted}"/>
               <td>
               <c:out value="${list.resolved }"/>
                   <td>
                   
                 <c:forEach var = "user" items="${users }">
                 <c:choose> 
               <c:when test="${user.id eq list.resolver }">
                <c:out value="${user.lname }, ${user.fname} "/>
                </c:when>
                 <c:otherwise>
                <c:out value=""/>
                </c:otherwise>
                </c:choose>
                </c:forEach>
                
               
                
             
                 <td> 
                 <c:forEach var = "type" items="${types}" >
                 <c:if test="${type.id eq list.type }">
               	 <c:out value="${type.name}"/>  
                 </c:if>
                 
                 </c:forEach>
              </tr>
              </c:forEach>

            </tbody>
          </table>
</div>
      
</body>
<script src="//code.jquery.com/jquery-1.12.3.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function() {
    $('#table').DataTable({
    "info":false
    });
 
	$('#amountId').number(true,2);
}
);
</script>
<script type="text/javascript">
document.getElementbyId("amountId").toFixed(2);
</script>
</html>