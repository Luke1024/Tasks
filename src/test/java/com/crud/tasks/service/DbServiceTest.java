package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    public void getAllTasksTest() {
        Task task = new Task("Task", "Content");
        int allTasksCount = dbService.getAllTasks().size();
        dbService.saveTask(task);
        int tasksCountAfter = dbService.getAllTasks().size();
        Assert.assertTrue(allTasksCount < tasksCountAfter);
        //clean
        System.out.println();
        dbService.deleteTask(task.getId());
    }

    @Test
    public void findTaskByIdTest() {
        Task task = new Task("Task", "Content");
        dbService.saveTask(task);
        assertThat(task, sameBeanAs(dbService.findTaskById(task.getId()).get()));
        dbService.deleteTask(task.getId());
    }

    @Test
    public void saveTaskTest() {
        Task task = new Task("Task", "Content");
        dbService.saveTask(task);
        Optional<Task> readTask = dbService.findTaskById(task.getId());
        Assert.assertTrue(dbService.getTask(task.getId()).isPresent());
        dbService.deleteTask(task.getId());
    }

    @Test
    public void deleteTaskTest() {
        Task task = new Task("Task", "Content");
        dbService.saveTask(task);
        Long taskId = task.getId();
        Optional<Task> readTask = dbService.findTaskById(taskId);
        Assert.assertTrue(readTask.isPresent());
        dbService.deleteTask(taskId);
        readTask = dbService.findTaskById(taskId);
        Assert.assertFalse(readTask.isPresent());
    }
}