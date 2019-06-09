package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    private final static TaskDto taskDto = new TaskDto(1L,"Title1", "Content1");
    private final static Task task1 = new Task(1L,"Title1", "Content1");

    @Test
    public void getTasks() throws Exception {

        //Given
        List<TaskDto> taskListDto = new ArrayList<>(Arrays.asList(new TaskDto(1L,"Title1", "Content1")));
        when(taskMapper.mapToTaskDtoList(ArgumentMatchers.anyList())).thenReturn(taskListDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Title1")))
                .andExpect(jsonPath("$[0].content", is("Content1")));
    }

    @Test
    public void getTask() throws Exception {
        Optional<Task> task = Optional.of(new Task(1L, "Title1", "Content1"));
        when(service.getTask(ArgumentMatchers.anyLong())).thenReturn(task);

        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")));
                //.andExpect(jsonPath("$.title", is("Title1")))
                //.andExpect(jsonPath("$.content", is("Content1")));
    }

    @Test
    public void deleteTask() throws Exception {
        Optional<Task> task = Optional.of(new Task(1L, "Title1", "Content1"));
        when(service.deleteTask(ArgumentMatchers.anyLong())).thenReturn(task);

        mockMvc.perform(delete("v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTask() throws Exception {

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        //when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mockMvc.perform(put("v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)));
    }

    //@Test
    //public void createTask() throws Exception  {
      //  when(service.saveTask())

    //}
}