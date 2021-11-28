package com.hairdresser.appointment.controller;

import com.hairdresser.appointment.model.Mail;
import com.hairdresser.appointment.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;

@RestController
@RequestMapping("/api/v1/mail/")
public class EmailController {
    @Autowired
    SendMailService service;

    public EmailController(SendMailService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody Mail mail) {
        service.sendMail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail) throws MessagingException {
        service.sendMailWithAttachments(mail);
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}
