package com.crud.tasks.service;

import com.crud.tasks.domain.EmailType;
import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(EmailType emailType, final Mail mail) {

        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(emailType ,mail));

            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    protected MimeMessagePreparator createMimeMessage(EmailType emailType ,final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if(emailType==EmailType.TRELLO_CARD_CREATION_MAIL) {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            }
            if(emailType==EmailType.SCHEDULED_TRELLO_MAIL) {
                messageHelper.setText(mailCreatorService.buildScheduledTrelloEmail(mail.getMessage()),true);
            }
        };
    }
}
