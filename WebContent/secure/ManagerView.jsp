

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Home</title>
<meta charset="utf-8">
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css">
#background{
background: #f2f2f2
}
#title{
background: #99caff;
color: black;
}
#approve{
margin-left:20%;
float: left;

}
#deny{
margin-right: 20%;
float:right;

}
#link{
color: black;
float: right;
border-width: thin;
border-style: hidden;

}
#table{
background:#cce6ff;
}
#modalStyle{
text-align: center;
}

</style>

</head>
<body id = "background">
<div>
<%@ include file="NavBar.jsp" %>

</div>
<div>

<table class="table table-striped" id = "table" cellspacing="0" width="100%">
            <thead>
              <tr>
                <th>ID</th>
                <th>Employee Name</th>
                <th>Amount</th>
                <th>Date Submitted</th>
                <th>Date Resolved</th>
                <th>Resolved By</th>
                <th>Type</th>
                <th>Receipt</th>
                <th>Description</th>
                <th>status</th>
               
              </tr>
            </thead>
            
            <tbody>
              <c:forEach var = "list" items="${reimbursements }">
              <tr>
                <td> 
                
                <c:out value="${list.id}"></c:out>
            
                
                </td>
                
               <td> 
               <c:forEach var = "user" items="${users }">
               <c:if test="${user.id eq list.author }">
                <c:out value="${user.lname }, ${user.fname} "/>
                </c:if>
                </c:forEach>
                 <td> 
                <f:formatNumber type="currency"><c:out value="${list.amount}"></c:out></f:formatNumber></td>
                 <td> 
                <c:out value="${list.submitted}"/>
                 <td> 
                <c:out value="${list.resolved}"/>
                 <td> 
                 <c:forEach var = "user" items="${users }">
               	<c:if test="${user.id eq list.resolver }">
                <c:out value="${user.lname }, ${user.fname} "/>
                </c:if>
                </c:forEach>
                 <td> 
                 <c:forEach var = "type" items="${types}" >
                 <c:if test="${type.id eq list.type }">
               	 <c:out value="${type.name}"/>  
                 </c:if>
                 
                 </c:forEach>
               		<td>
               		<c:out value = "${list.receipt}"/>
               		
                  <td> 
                <c:out value="${list.description}"/>
                 <td> 
            
                 <c:choose>
                 <c:when test="${list.status eq 21}" >
                <button type="button" id="pending"  data-toggle="modal" data-target="#myModal">Pending</button>
               <!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Resolve Request</h4>
      </div>
      <div class="modal-body">
          		<div id= "modalStyle">
          		<div id="approve">
          		<form action="approve.do" method="post">
  				 <input type="hidden" value="${list.id}" name = "approve"/>
                 <button  class="btn btn-primary" type="submit"  >Approve</button>
                 </form>
				</div>
				<div id="deny" >
				<form action="deny.do" method="post">
				<input type="hidden" value="${list.id}" name = "deny"/>
                 <button class="btn btn-primary" type="submit" >Deny</button>
                 </form>  
                 </div>
                </div>
      </div>
      <div class="modal-footer">
        
      </div>
    </div>

  </div>
</div>
               
  				              
                
                 </c:when>
                 <c:when test="${list.status eq 22}">
                 <c:out value="approved"/>
                 </c:when>
                 <c:when test="${list.status eq 23}">
                 <c:out value="denied"/>
                 </c:when>
                 <c:otherwise>
                 <c:out value="error"/>
                 </c:otherwise>
                 </c:choose>
                
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
	$('#pending').click(function(){
	$('#myModal').clone(true);
	});

} 


);

</script>

</html>