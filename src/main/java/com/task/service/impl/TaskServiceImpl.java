/**
 * 
 */
package com.task.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.common.dto.TaskDTO;
import com.task.common.entity.TaskEntity;
import com.task.common.mapper.TaskMapper;
import com.task.repository.TaskJpaRepository;
import com.task.service.TaskService;

/**
 * @author manchanda.a
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskJpaRepository repository;

	@Autowired
	private TaskMapper mapper;

	/**
	 * This method will create new task.
	 */
	@Override
	public TaskDTO createTask(TaskDTO task) throws ParseException {
		task.setTaskCurrentStatus("Pending");
		TaskEntity entity = mapper.convertDTOToEntity(task);
		entity = repository.save(entity);
		return mapper.convertEntityToDTO(entity);
	}

	/**
	 * This method will retrieve task based on search parameter entered by user.
	 */
	@Override
	public List<TaskDTO> getTask(String status) throws ParseException {
		List<TaskEntity> entityList;
		if (StringUtils.stripToNull(status) != null) {
			entityList = repository.findBasedOnStatus(status);
		} else {
			entityList = repository.findAll();
		}
		return mapper.convertEntityListToDTOList(entityList);
	}

	/**
	 * This method is use to delete old task.
	 */
	@Override
	public TaskDTO deleteTask(Integer taskId) throws Exception {
		TaskEntity entity = repository.getOne(taskId);
		repository.delete(taskId);
		return mapper.convertEntityToDTO(entity);
	}

	/**
	 * This method will change pending task to complete.
	 */
	@Override
	public TaskDTO completeTask(Integer taskId) throws ParseException {
		TaskEntity entity = repository.findOne(taskId);
		entity.setTaskStatus("Complete");
		entity = repository.save(entity);
		return mapper.convertEntityToDTO(entity);
	}
}
