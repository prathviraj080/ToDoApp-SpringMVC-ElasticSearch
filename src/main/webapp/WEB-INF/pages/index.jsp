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
<h1>To Do List</h1>
<ul id="tasks">
</ul>
<hr/>

<label>Enter the task : </label> <input type="text" id="task"> <br/>
<label>Enter the date and time : </label> 	<input type="text" value="" id="datetimepicker"/> <br/>
<div class="buttons"><button id="save">Save</button><button id="cancel">Cancel</button></div>









<script>
$('#datetimepicker').datetimepicker({
dayOfWeekStart : 1,
lang:'en',
startDate:	moment(moment()).format('YYYY/MM/DD')
});
$('#datetimepicker').datetimepicker({value: moment(moment()).format('YYYY/MM/DD HH:mm'),step:10});
</script>

</body>
</html>