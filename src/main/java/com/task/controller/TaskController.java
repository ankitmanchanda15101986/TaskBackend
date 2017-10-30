/**
 * 
 */
package com.task.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.common.dto.TaskDTO;
import com.task.common.mapper.TaskMapper;
import com.task.common.response.Response;
import com.task.service.impl.TaskServiceImpl;

/**
 * @author manchanda.a
 *
 */
@RestController
public class TaskController {

	private final Logger log = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskServiceImpl service;
	
	@Autowired
	private TaskMapper mapper;
	
	/**
	 * Controller will be called when from front end user create new task.
	 * @param dto
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/createTask",produces = { "application/json" },
			consumes = { "application/json" },method=RequestMethod.POST)
	public Response createNewTask(@Validated @RequestBody TaskDTO task) throws ParseException {
		log.debug("Calling create webservice");
		TaskDTO response = service.createTask(task);
		log.debug("returned response from create webservice "+response);
		return mapper.convertCreateTaskDtoToResponse(response);
		
	}
	
	/**
	 * Controller will be called when from front end user retrieve task based on search parameters.
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/retrieveTask" ,method=RequestMethod.GET)
	public Response searchTask(@RequestParam(value="status", required=false) String status) throws ParseException {
		log.debug("Calling retrieve webservice");
		List<TaskDTO> response = service.getTask(status);
		log.debug("returned response from search webservice "+response);
		return mapper.convertSearchTaskDtoToResponse(response);
	}
	
	/**
	 * Controller will be called when from front end user delete task.
	 * @param taskId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteTask", method=RequestMethod.GET)
	public Response deleteTask(@RequestParam("id") Integer taskId) throws Exception {
		log.debug("Calling delete webservice");
		TaskDTO response = service.deleteTask(taskId);
		log.debug("returned response from delete webservice "+response);
		return mapper.convertDeletionTaskDtoToResponse(response);			
	}
	
	/**
	 * Controller will be called when from front end user update task status to complete.
	 * @param taskId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/markComplete",method=RequestMethod.GET)
	public Response completeTask(@RequestParam("id") Integer taskId) throws ParseException {
		log.debug("Calling delete webservice");
		TaskDTO response = service.completeTask(taskId);
		log.debug("returned response from complete task webservice "+response);
		return mapper.convertCompletedTaskDtoToResponse(response);			
	}
}
