package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.EmailType;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";
    private static final String MESSAGE_BEGINNING = "Currently in database you got: ";
    private static final String OTHERMAIL = "example@mail.com";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(EmailType.SCHEDULED_TRELLO_MAIL ,new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                messageContent(size),
                OTHERMAIL)
        );
    }

    protected String messageContent(long size) {
        return size==0 ? MESSAGE_BEGINNING + "no tasks" :
                size==1 ? MESSAGE_BEGINNING + size + " task" :
                        MESSAGE_BEGINNING + size + " tasks";
    }
}
