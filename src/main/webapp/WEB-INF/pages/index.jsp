<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TODO Application</title>
	
	
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery.datetimepicker.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery-2.1.3.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
	<script src="<c:url value="/resources/js/moment.js" />"></script>
	
	
</head>
<body>
<h2 style="text-align:center">To Do List</h2>
<ul id="tasks">
</ul>
<hr/>
<div class='newTask' >
<h4> Add new task</h4>
<label>Enter the task : </label> <input type="text" id="task"> <br/>
<label>Enter the date and time : </label> 	<input type="text" id="datetimepicker1"/> <br/>
<div class="buttons"><button id="save">Save</button></div>
</div>








<script>
$('#datetimepicker1').datetimepicker({
dayOfWeekStart : 0,
lang:'en',
startDate:	moment(moment()).format('YYYY/MM/DD')
});
$('#datetimepicker1').datetimepicker({value: moment(moment()).format('YYYY/MM/DD HH:mm'),step:10});
</script>

</body>
</html>