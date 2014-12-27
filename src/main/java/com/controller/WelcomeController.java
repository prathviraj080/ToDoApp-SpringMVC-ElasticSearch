
package com.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TaskBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.ESDAO;


@Controller
public class WelcomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage(ModelMap model, HttpServletRequest request, HttpServletResponse response){
	    return "index";
	}
	
	@RequestMapping(value="/GetTasks", method = RequestMethod.GET)
	public ResponseEntity<String> getTasksResponse() throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return new ResponseEntity<String>(ESDAO.getTasks(), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error while fetching tasks", e);
		}
		return null;
	}
	
	@RequestMapping(value="/SaveTasks", method = RequestMethod.POST)
	public ResponseEntity<String> saveTasksResponse(@RequestParam(value = "task", required = true) String task,
													@RequestParam(value = "dateTime", required = true) String dateTime
													) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return new ResponseEntity<String>(saveTask(task,dateTime), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error while fetching tasks", e);
		}
		return null;
	}

	private String saveTask(String task, String dateTime) {
		TaskBean taskObj = new TaskBean();
		taskObj.setTask(task);
		taskObj.setDateTime(dateTime);
		return ESDAO.saveTask(taskObj);
	}
	
	@RequestMapping(value="/UpdateTask", method = RequestMethod.POST)
	public ResponseEntity<String> updateTasksResponse(@RequestParam(value = "taskID", required = true) String taskID,
													@RequestParam(value = "task", required = true) String task,
													@RequestParam(value = "dateTime", required = true) String dateTime
													) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return new ResponseEntity<String>(updateTask(taskID,task,dateTime), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error while fetching tasks", e);
		}
		return null;
	}

	private String updateTask(String taskID, String task, String dateTime) {
		TaskBean taskObj = new TaskBean();
		taskObj.setTaskID(taskID);
		taskObj.setTask(task);
		taskObj.setDateTime(dateTime);
		return ESDAO.updateTask(taskObj);
	}

	@RequestMapping(value="/DeleteTask", method = RequestMethod.POST)
	public ResponseEntity<String> deleteTasksResponse(@RequestParam(value = "taskID", required = true) String taskID
													) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			responseHeaders.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
			return new ResponseEntity<String>(ESDAO.deleteTask(taskID), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error while fetching tasks", e);
		}
		return null;
	}
	
}
