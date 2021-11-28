package com.hairdresser.appointment.client;

import com.hairdresser.appointment.model.Hairdresser;
import com.hairdresser.appointment.model.Mail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailClient {
    private static final String  POST_URL ="http://localhost:8080/api/v1/mail/send";
    private static final String  POST_URL_ATACHMENT ="http://localhost:8080/api/v1/mail/atachment";

    RestTemplate restTemplate = new RestTemplate();  // Client Template

    public String sendMail(Mail mail){
        ResponseEntity<String>  result=  restTemplate.postForEntity(POST_URL, mail ,String.class);// String
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());
        return result.getBody();
    }

    public boolean sendAtachment(Mail mail){
        ResponseEntity<String>  result=  restTemplate.postForEntity(POST_URL_ATACHMENT, mail ,String.class);// String
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());
        return result.getStatusCode() == HttpStatus.OK;
    }

}
