package com.task.service;

import java.text.ParseException;
import java.util.List;

import com.task.common.dto.TaskDTO;

/**
 * Crud Operations.
 * @author manchanda.a
 *
 */
public interface TaskService {

	public TaskDTO createTask(TaskDTO task) throws ParseException;
			
	public TaskDTO deleteTask(Integer taskId) throws Exception;

	public List<TaskDTO> getTask(String status) throws ParseException;
	
	public TaskDTO completeTask(Integer taskId) throws ParseException;
	
}
