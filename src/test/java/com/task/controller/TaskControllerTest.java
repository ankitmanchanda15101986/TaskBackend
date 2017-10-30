package com.task.controller;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.task.common.dto.TaskDTO;
import com.task.common.mapper.TaskMapper;
import com.task.common.response.Response;
import com.task.service.impl.TaskServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TaskControllerTest.class)
public class TaskControllerTest {

	@InjectMocks
	private TaskController mockController;

	@Mock
	private TaskServiceImpl service;
	
	@Mock TaskMapper mapper;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(mockController, "service", service);
	}

	@Test
	public final void testCreateRecord_Success() {
		try {
			Mockito.when(service.createTask(getTaskDto())).thenReturn(getTaskDto());
			Mockito.when(mapper.convertCreateTaskDtoToResponse(getTaskDto())).thenReturn(getResponseSuccess());
			Response response = mockController.createNewTask(getTaskDto());
			assertEquals("200", response.getStatus_codes());
			assertEquals("SUCCESS", response.getStatus_msg());
			assertEquals(getTaskDto().getTaskCurrentStatus(), response.getTaskList().get(0).getTaskCurrentStatus());
			assertEquals(getTaskDto().getTaskDescription(), response.getTaskList().get(0).getTaskDescription());
			assertEquals(getTaskDto().getTaskName(), response.getTaskList().get(0).getTaskName());
			assertEquals(getTaskDto().getTaskId(), response.getTaskList().get(0).getTaskId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void testCreateRecord_Failure() {
		try {
			Mockito.when(service.createTask(getTaskDto())).thenReturn(getTaskDto());
			Mockito.when(mapper.convertCreateTaskDtoToResponse(getTaskDto())).thenReturn(getResponseFailure());
			Response response = mockController.createNewTask(getTaskDto());
			assertEquals("400", response.getStatus_codes());
			assertEquals("FAILURE", response.getStatus_msg());
			assertEquals(getTaskDto().getTaskCurrentStatus(), response.getTaskList().get(0).getTaskCurrentStatus());
			assertEquals(getTaskDto().getTaskDescription(), response.getTaskList().get(0).getTaskDescription());
			assertEquals(getTaskDto().getTaskName(), response.getTaskList().get(0).getTaskName());
			assertEquals(getTaskDto().getTaskId(), response.getTaskList().get(0).getTaskId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void testRetrieveTask() {
		try {
			Mockito.when(service.getTask("")).thenReturn(getTaskDTOList());
			Mockito.when(mapper.convertSearchTaskDtoToResponse(getTaskDTOList())).thenReturn(getResponseSuccess());
			Response response = mockController.searchTask("");
			assertEquals("200", response.getStatus_codes());
			assertEquals("SUCCESS", response.getStatus_msg());
			assertEquals(getTaskDto().getTaskCurrentStatus(), response.getTaskList().get(0).getTaskCurrentStatus());
			assertEquals(getTaskDto().getTaskDescription(), response.getTaskList().get(0).getTaskDescription());
			assertEquals(getTaskDto().getTaskName(), response.getTaskList().get(0).getTaskName());
			assertEquals(getTaskDto().getTaskId(), response.getTaskList().get(0).getTaskId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public final void testDeleteTask() {
		try {
			Mockito.when(service.deleteTask(1)).thenReturn(getTaskDto());
			Mockito.when(mapper.convertUpdateTaskDtoToResponse(getTaskDto())).thenReturn(getResponseSuccess());
			Response response = mockController.deleteTask(1);
			assertEquals("200", response.getStatus_codes());
			assertEquals("SUCCESS", response.getStatus_msg());
			assertEquals(getTaskDto().getTaskCurrentStatus(), response.getTaskList().get(0).getTaskCurrentStatus());
			assertEquals(getTaskDto().getTaskDescription(), response.getTaskList().get(0).getTaskDescription());
			assertEquals(getTaskDto().getTaskName(), response.getTaskList().get(0).getTaskName());
			assertEquals(getTaskDto().getTaskId(), response.getTaskList().get(0).getTaskId());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private TaskDTO getTaskDto() {
		TaskDTO dto = new TaskDTO();
		dto.setTaskId(1);
		dto.setTaskName("TEST");
		dto.setTaskDescription("DESC");
		dto.setTaskCurrentStatus("Pending");
		return dto;
	}
	
	private List<TaskDTO> getTaskDTOList() {
		List<TaskDTO> list = new ArrayList<>();
		list.add(getTaskDto());
		return list;
	}
	
	private Response getResponseSuccess() {
		Response resp  = new Response();
		resp.setStatus_codes("200");
		resp.setStatus_msg("SUCCESS");
		resp.getTaskList().add(getTaskDto());
		return resp;
	}
	
	private Response getResponseFailure() {
		Response resp  = new Response();
		resp.setStatus_codes("400");
		resp.setStatus_msg("FAILURE");
		resp.getTaskList().add(getTaskDto());
		return resp;
	}
}
