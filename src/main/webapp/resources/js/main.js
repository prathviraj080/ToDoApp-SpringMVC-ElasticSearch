$(function() {
	
	
	$.ajax({
		type: 'GET',
		url: '/ToDoApp-SpringMVC-ElasticSearch/GetTasks',
		dataType : 'json',
		success: function(tasks) {
			console.log(tasks);
			$.each(tasks, function(i,task){
				$('#tasks').append('<li><p>Task: ' + task.task + '----------> When to do: ' + task.dateTime + '</p></li>');
			});
			
		},
		error: function(tasks){
			console.log('Errror while fetching '+tasks);
		}
	
		
	});
	
	$('#save').on('click', function() {
		var task = $('#task');
		var dateTime = $('#datetimepicker');
		
		$.ajax({
			type: 'POST',
			url: '/ToDoApp-SpringMVC-ElasticSearch/SaveTasks',
			data: {"task" : $('#task').val(), "dateTime" : $('#datetimepicker').val()},
			success: function(newTodo) {
				console.log("todo " + newTodo);
				$('#tasks').append('<li><p>Task: ' + newTodo.task + '----------> When to do: ' + newTodo.dateTime + '</p></li>');
			},
			error: function(newTodo){
				console.log('Errror while fetching '+newTodo);
			}
		});
	});
	
});