$(function() {
	
	
	$.ajax({
		type: 'GET',
		url: '/ToDoApp-SpringMVC-ElasticSearch/GetTasks',
		dataType : 'json',
		success: function(tasks) {
			$.each(tasks, function(i,task){
				$('#tasks').append('<li taskID='+ task.taskID +'><p>Task: ' + 
						'<span class="noEdit task"> '+ task.task + '</span>' + 
						'<input class="edit task"/>' +
						'----------> When to do: ' + 
						'<span class="noEdit dateTime"> '+ task.dateTime + '</span> ' +
						'<input class="edit dateTime" id="datetimepicker"/></p>' +
						'<button class="remove"> X </button>' +
						'<button id="edit" class="noEdit"> Edit </button>'+
						'<button id="saveEdit" class="edit"> Save </button>' +
						'<button id="cancelEdit" class="edit"> Cancel </button></li>');
			});
			
		},
		error: function(tasks){
			console.log('Errror while fetching '+tasks);
		}
	
		
	});
	
	$('#save').on('click', function() {
		
		$.ajax({
			type: 'POST',
			url: '/ToDoApp-SpringMVC-ElasticSearch/SaveTasks',
			data: {"task" : $('#task').val(), "dateTime" : $('#datetimepicker').val()},
			success: function(newTodo) {
				$('#tasks').append('<li taskID='+ newTodo.taskID +' ><p>Task: ' + 
						'<span class="noEdit task"> '+ newTodo.task + '</span>' + 
						'<input class="edit task"/>' +
						'----------> When to do: ' + 
						'<span class="noEdit dateTime"> '+ newTodo.dateTime + '</span> ' +
						'<input class="edit dateTime" id="datetimepicker"/></p>' +
						'<button class="remove"> X </button>' +
						'<button id="edit" class="noEdit"> Edit </button>'+
						'<button id="saveEdit" class="edit"> Save </button>' +
						'<button id="cancelEdit" class="edit"> Cancel </button></li>');
			},
			error: function(newTodo){
				console.log('Errror while fetching '+newTodo);
			}
		});
	});
	
	$('#tasks').delegate('.remove','click', function() {
		var $li = $(this).closest('li');
		var taskID = $li.attr('taskID');
		$.ajax({
			type: 'POST',
			url: '/ToDoApp-SpringMVC-ElasticSearch/DeleteTask',
			data: {"taskID" : taskID},
			success: function(deletedTaskID) {
				console.log("deleted " + deletedTaskID);
				$li.remove();
			},
			error: function(deletedTaskID){
				console.log('Errror while deleting '+deletedTaskID);
			}
		});
	});
	
	
	$('#tasks').delegate('#edit','click', function() {
		var $li = $(this).closest('li');
		$li.find('input.task').val($li.find('span.task').html());
		
		$('#datetimepicker').datetimepicker({
			dayOfWeekStart : 1,
			lang:'en',
			startDate:	$li.find('span.dateTime').html()
		});
		$li.find('#datetimepicker').datetimepicker({value: $li.find('span.dateTime').html(),step:10});
		
		$li.addClass('edit');
	});
	
	$('#tasks').delegate('#cancelEdit','click', function() {
		$(this).closest('li').removeClass('edit');
	});
	
	
	$('#tasks').delegate('#saveEdit','click', function() {
		var $li = $(this).closest('li');
		var $task = $li.find('input.task').val();
		var $dateTime = $li.find('input.dateTime').val();
		var $taskID = $li.attr('taskID');
		console.log($taskID);
		$.ajax({
			url: '/ToDoApp-SpringMVC-ElasticSearch/UpdateTask',
			data: {"taskID" : $taskID, "task" : $task, "dateTime" : $dateTime},
			success: function(UpdatedTodo) {
				$li.find('span.task').html(UpdatedTodo.task);
				$li.find('span.dateTime').html(UpdatedTodo.dateTime);
				$li.removeClass('edit');
				
			},
			error: function(newTodo){
				console.log('Errror while updating '+newTodo);
			}
		});
	});
	
	
	
});