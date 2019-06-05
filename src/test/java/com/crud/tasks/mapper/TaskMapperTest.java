package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    private static final Task task1 = new Task(1L, "task1", "description1");
    private static final Task task2 = new Task(2L, "task2", "description2");

    private static final TaskDto taskDto1 = new TaskDto(1L, "task1", "description1");
    private static final TaskDto taskDto2 = new TaskDto(2L, "task2", "description2");

    private static final List<TaskDto> taskDtos = new ArrayList<>(Arrays.asList(taskDto1, taskDto2));
    private static final List<Task> tasks = new ArrayList<>(Arrays.asList(task1, task2));

    @Test
    public void mapToTaskTest() {
        assertThat(task1, sameBeanAs(taskMapper.mapToTask(taskDto1)));
    }

    @Test
    public void mapToTaskDtoTest() {
        assertThat(taskDto1, sameBeanAs(taskMapper.mapToTaskDto(task1)));
    }

    @Test
    public void mapToTaskDtoListTest() {
        assertThat(taskDtos, sameBeanAs(taskMapper.mapToTaskDtoList(tasks)));
    }
}