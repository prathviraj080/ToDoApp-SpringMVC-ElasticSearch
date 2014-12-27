package com.dao;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.TaskBean;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;

import com.util.JSONUtil;







public class ESDAO {

	//private static final Logger LOGGER = Logger.getLogger(ESDAO.class);
	
	static Node node = nodeBuilder().clusterName("elasticsearch").client(true).node();
	static Client client =  node.client();
	
	
	

	public static String getTasks() {
		SearchResponse response = client.prepareSearch("todo").setTypes("tasks").setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(QueryBuilders.matchAllQuery())
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();

		SearchHit[] results = response.getHits().getHits();
		List<TaskBean> tasks = new ArrayList<TaskBean>();
		for (SearchHit hit : results) {
			TaskBean task = new TaskBean();
			task.setTask((String)hit.getSource().get("task"));
			task.setDateTime((String)hit.getSource().get("dateTime"));
			task.setTaskID((String)hit.getId());
			tasks.add(task);
		}
		return JSONUtil.toJson(tasks);
	}

	public static void main(String[] args) {
		System.out.println(getTasks());

	}
	
	public static String saveTask(TaskBean newTask) {
		
		Map<String,Object> task = new HashMap<String,Object>();
		task.put("task", newTask.getTask());
		task.put("dateTime", newTask.getDateTime());

		IndexResponse ir =  client.prepareIndex("todo", "tasks").setSource(task).execute().actionGet();
		
		
		GetResponse response = client.prepareGet("todo", "tasks", ir.getId()).execute().actionGet();
		
		response.getSource().put("taskID", ir.getId());
		
		System.out.println(JSONUtil.toJson(response.getSource()));
		
		return JSONUtil.toJson(response.getSource());
		
		

	}

	public static String updateTask(TaskBean taskObj) {
		Map<String,Object> task = new HashMap<String,Object>();
		task.put("task", taskObj.getTask());
		task.put("dateTime", taskObj.getDateTime());
		
		UpdateResponse ur = client.prepareUpdate("todo", "tasks", taskObj.getTaskID())
		.setDoc(task)
		.execute()
		.actionGet();
		
		GetResponse response = client.prepareGet("todo", "tasks", ur.getId()).execute().actionGet();

		System.out.println(JSONUtil.toJson(response.getSource()));
		
		return JSONUtil.toJson(response.getSource());
	}

	public static String deleteTask(String taskID) {
		DeleteResponse dr = client.prepareDelete("todo", "tasks", taskID).execute().actionGet();
		
		return dr.getId();
	}

}
