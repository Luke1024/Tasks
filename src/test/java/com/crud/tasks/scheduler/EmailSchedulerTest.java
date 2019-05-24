package com.crud.tasks.scheduler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSchedulerTest {

    @Autowired
    private EmailScheduler emailScheduler;

    @Test
    public void testMessageContent(){
        String content0 = emailScheduler.messageContent(0);
        String content1 = emailScheduler.messageContent(1);
        String content2 = emailScheduler.messageContent(2);
        String content3 = emailScheduler.messageContent(3);

        Assert.assertEquals("Currently in database you got: no tasks",content0);
        Assert.assertEquals("Currently in database you got: 1 task",content1);
        Assert.assertEquals("Currently in database you got: 2 tasks",content2);
        Assert.assertEquals("Currently in database you got: 3 tasks",content3);
    }
}