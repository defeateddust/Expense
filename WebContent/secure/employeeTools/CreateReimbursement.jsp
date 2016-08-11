<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reimbursement form</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<link rel="stylesheet" href="example.css">
<style type="text/css">
#background{
background-color: #f2f2f2
}
.rForm{
text-align: left;
margin: 2ex;
margin-right: 40%;
padding-left: 5%;
padding-right: 5%;
border: medium;
border-style: solid;
border-color: black;
background: white;
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
<div>
<nav class="navbar navbar-default"id= "title">
        <div class="container" >
          <div class="navbar-header">

            <a class="navbar-brand" id = "title">Reimbursement Request</a>
          </div>
          <div class="navbar-collapse collapse"id = "link" >
            <ul class="nav navbar-nav">
              <li><a href="/ExpenseReimbursement/secure/home.jsp"id = "link" >Reimbursement History </a></li>
				<li><a href="/ExpenseReimbursement/secure/logout.do"id = "link" >Logout</a></li>
      
                </ul>
          </div><!--/.nav-collapse -->
        </div>
      </nav>
</div>

<div class = "rForm">
<form action="create.do" method="post" id="form">
<label>Amount Requested</label><br/>
<input type = "text" name="amount" placeholder="0.00" required="required" id = "amount"/><br/><span id = "amountId"></span>
<br/>
<label>Type of Request</label><br/>
<jsp:include page="/secure/typeList.jsp"/>

	<br/>
<label>Description</label><br/>
<textarea columns = "15" rows="5" name = "description"id = "description"></textarea>
<br/>
<span id = "des"></span><br/>
<label>Receipt</label><br/>
<input type = "file" name = "file" ><br/>
<input type = "submit" id= "submit" value = "Submit" disabled="disabled" >


</form>

</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
$(document).ready($("#amount").keyup(function(){
	var amount = $("#amount").val();
	if(isNaN(amount)){
		$("#amountId").text("Please enter a valid amount");
		$("#amountId").css("color","red");
		$("#submit").attr("disabled","disabled")
	}else{
		if(amount.toString().split(".")[1].length>2){
			$("#amountId").text("Please use 2 decimal notation");
			$("#amountId").css("color","red");
			$("#submit").attr("disabled","disabled")
		}else{
			$("#amountId").text("Ok");
			$("#amountId").css("color","green");
			$("#submit").attr("disabled",false)
		}
		
	}
	}))
$(document).ready($("#description").keyup(function(){
	
	var desc = $("#description").val();
	if(desc.length>250){
	$("#des").text("description too long please shorten");
	$("#des").css("color","red");
	$("#submit").attr("disabled","disabled")
	}else{
			$("#des").text("Ok");
			$("#des").css("color","green");
			$("#submit").attr("disabled",false)
		}

})

)
$(document).ready($("#submit").click("#form").submit)
</script>
</html>