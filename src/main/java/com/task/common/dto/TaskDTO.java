package com.task.common.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TaskDTO {
	
	@JsonProperty(value="Id")
	private Integer taskId;
	
	@JsonProperty(value="Name")
	@NotNull(message="Task name is mandatory field")
	@Size(max=20)
	private String taskName;
	
	@JsonProperty(value="Description")
	@NotNull(message="Task description is mandatory field")
	@Size(max=100)
	private String taskDescription;
	
	@JsonProperty(value="CreationDate")
	@NotNull(message="Creation Date is mandatory field")
	private String taskCreationDate;

	
	@JsonProperty(value="Status")
	private String taskCurrentStatus;
	
	/**
	 * @return the taskCreationDate
	 */
	public String getTaskCreationDate() {
		return taskCreationDate;
	}
	/**
	 * @param taskCreationDate the taskCreationDate to set
	 */
	public void setTaskCreationDate(String taskCreationDate) {
		this.taskCreationDate = taskCreationDate;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getTaskCurrentStatus() {
		return taskCurrentStatus;
	}
	public void setTaskCurrentStatus(String taskCurrentStatus) {
		this.taskCurrentStatus = taskCurrentStatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskCurrentStatus == null) ? 0 : taskCurrentStatus.hashCode());
		result = prime * result + ((taskDescription == null) ? 0 : taskDescription.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDTO other = (TaskDTO) obj;
		if (taskCurrentStatus == null) {
			if (other.taskCurrentStatus != null)
				return false;
		} else if (!taskCurrentStatus.equals(other.taskCurrentStatus))
			return false;
		if (taskDescription == null) {
			if (other.taskDescription != null)
				return false;
		} else if (!taskDescription.equals(other.taskDescription))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}
	
	
}
