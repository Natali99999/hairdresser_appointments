package com.hairdresser.appointment.service;

import com.hairdresser.appointment.model.Mail;

import javax.mail.MessagingException;

public interface SendMailService {
    void sendMail(Mail mail);

    void sendMailWithAttachments(Mail mail) throws MessagingException;
}
