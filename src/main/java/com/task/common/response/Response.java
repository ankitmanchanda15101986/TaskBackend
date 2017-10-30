package com.task.common.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.common.dto.TaskDTO;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value="StatusCode")
	private String status_codes;

	@JsonProperty(value="StatusMsg")
	private String status_msg;

	@JsonProperty(value="Task")
	private List<TaskDTO> taskList = new ArrayList<TaskDTO>();

	/**
	 * @return the status_codes
	 */
	public String getStatus_codes() {
		return status_codes;
	}

	/**
	 * @param status_codes the status_codes to set
	 */
	public void setStatus_codes(String status_codes) {
		this.status_codes = status_codes;
	}

	/**
	 * @return the status_msg
	 */
	public String getStatus_msg() {
		return status_msg;
	}

	/**
	 * @param status_msg the status_msg to set
	 */
	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}

	/**
	 * @return the taskList
	 */
	public List<TaskDTO> getTaskList() {
		return taskList;
	}	
}
