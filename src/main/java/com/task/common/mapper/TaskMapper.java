package com.task.common.mapper;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.task.common.dto.TaskDTO;
import com.task.common.entity.TaskEntity;
import com.task.common.response.Response;

@Component
public class TaskMapper {
	
	@Autowired
	private MessageSource msgSource;
	
	/**
	 * This method will convert DTO to entity for request.
	 * @param dto
	 * @param operation 
	 * @return
	 */
	public TaskEntity convertDTOToEntity(TaskDTO dto) {
		TaskEntity entity = new TaskEntity();
		if(dto != null){
			if(StringUtils.stripToNull(dto.getTaskName()) != null) {
				entity.setTaskName(dto.getTaskName());
			}
			if(StringUtils.stripToNull(dto.getTaskDescription()) != null) {
				entity.setTaskDescription(dto.getTaskDescription());
			}
			if(StringUtils.stripToNull(dto.getTaskCurrentStatus()) != null) {
				entity.setTaskStatus(dto.getTaskCurrentStatus());
			}
			if(StringUtils.stripToNull(dto.getTaskCreationDate()) != null) {
				entity.setTaskCreationDate(dto.getTaskCreationDate());
			}
			return entity;
		}
		return null;
		
	}
	
	/**
	 * This method will convert Entity class response to DTO .
	 * @param entity
	 * @return
	 * @throws ParseException
	 */
	public TaskDTO convertEntityToDTO(TaskEntity entity) throws ParseException {
		TaskDTO dto = new TaskDTO();
		if(entity != null){
			dto.setTaskId(entity.getId());
			if(StringUtils.stripToNull(entity.getTaskName()) != null) {
				dto.setTaskName(entity.getTaskName());
			}
			if(StringUtils.stripToNull(entity.getTaskDescription()) != null) {
				dto.setTaskDescription(entity.getTaskDescription());
			}
			if(StringUtils.stripToNull(entity.getTaskStatus()) != null) {
				dto.setTaskCurrentStatus(entity.getTaskStatus());
			}
			if(StringUtils.stripToNull(entity.getTaskCreationDate()) != null) {
				dto.setTaskCreationDate(entity.getTaskCreationDate());
			}
			return dto;
		}
		return null;
	}
	
	/**
	 * This method will convert Entity class response to DTO .
	 * @param entity
	 * @return
	 * @throws ParseException
	 */
	public List<TaskDTO> convertEntityListToDTOList(List<TaskEntity> entityList) throws ParseException {
		
		 List<TaskDTO> dtoList = new ArrayList<TaskDTO>();
		 for (TaskEntity entity : entityList) {
			TaskDTO dto = new TaskDTO();
			if(entity != null) {
				dto.setTaskId(entity.getId());
				if(StringUtils.stripToNull(entity.getTaskName()) != null) {
					dto.setTaskName(entity.getTaskName());
				}
				if(StringUtils.stripToNull(entity.getTaskDescription()) != null) {
					dto.setTaskDescription(entity.getTaskDescription());
				}
				if(StringUtils.stripToNull(entity.getTaskStatus()) != null) {
					dto.setTaskCurrentStatus(entity.getTaskStatus());
				}
				if(StringUtils.stripToNull(entity.getTaskCreationDate()) != null) {
					dto.setTaskCreationDate(entity.getTaskCreationDate());
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	
	/**
	 * This method will convert dto to response.
	 * @param dto
	 * @return
	 */
	public Response  convertCreateTaskDtoToResponse(TaskDTO dto) {
		Response response = new Response();
		if(dto != null){
			response.setStatus_codes(msgSource.getMessage("SUCCESS.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("CREATE.SUCCESS.MESSAGE",null,null));
			response.getTaskList().add(dto);
		} else {
			response.setStatus_codes(msgSource.getMessage("FAILURE.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("CREATE.FAILURE.MESSAGE",null,null));
		}
		return response;
	}
	
	
	/**
	 * This method will convert dto to response.
	 * @param dto
	 * @return
	 */
	public Response  convertSearchTaskDtoToResponse(List<TaskDTO> dtoList) {
		Response response = new Response();
		if(dtoList != null) {
			if(!dtoList.isEmpty()){
				response.setStatus_codes(msgSource.getMessage("SUCCESS.CODE",null,null));
				response.setStatus_msg(msgSource.getMessage("SEARCH.SUCCESS.MESSAGE",null,null));
				response.getTaskList().addAll(dtoList);
			} else {
				response.setStatus_codes(msgSource.getMessage("SUCCESS.CODE",null,null));
				response.setStatus_msg(msgSource.getMessage("SEARCH.SUCCESS.MESSAGE.RECORD.NOT.FOUND",null,null));
			}
		} else {
			response.setStatus_codes(msgSource.getMessage("FAILURE.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("SEARCH.FAILURE.MESSAGE",null,null));
		}
		return response;
	}
	
	/**
	 * This method will convert dto to response.
	 * @param dto
	 * @return
	 */
	public Response  convertDeletionTaskDtoToResponse(TaskDTO dto) {
		Response response = new Response();
		if(dto != null){
			response.setStatus_codes(msgSource.getMessage("SUCCESS.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("DELETE.SUCCESS.MESSAGE",null,null));
			response.getTaskList().add(dto);
		} else {
			response.setStatus_codes(msgSource.getMessage("FAILURE.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("DELETE.FAILURE.MESSAGE",null,null));
		}
		return response;
	}
	
	/**
	 * This method will convert dto to response.
	 * @param dto
	 * @return
	 */
	public Response  convertUpdateTaskDtoToResponse(TaskDTO dto) {
		Response response = new Response();
		if(dto != null){
			response.setStatus_codes(msgSource.getMessage("SUCCESS.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("UPDATE.SUCCESS.MESSAGE",null,null));
			response.getTaskList().add(dto);
		} else {
			response.setStatus_codes(msgSource.getMessage("SUCCESS.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("UPDATE.FAILURE.MESSAGE",null,null));
		}
		return response;
	}
	
	/**
	 * This method will convert dto to response.
	 * @param dto
	 * @return
	 */
	public Response  convertCompletedTaskDtoToResponse(TaskDTO dto) {
		Response response = new Response();
		if(dto != null){
			response.setStatus_codes(msgSource.getMessage("SUCCESS.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("UPDATE.SUCCESS.MESSAGE",null,null));
			response.getTaskList().add(dto);
		} else {
			response.setStatus_codes(msgSource.getMessage("FAILURE.CODE",null,null));
			response.setStatus_msg(msgSource.getMessage("UPDATE.FAILURE.MESSAGE",null,null));
		}
		return response;
	}
}
