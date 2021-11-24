package com.hairdresser.appointment.client;

import com.hairdresser.appointment.model.Appointment;
import com.hairdresser.appointment.model.Hairdresser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AppointmentClient {
   // http://localhost:8080/appointments/cmd?start=2021-11-01&end=2021-11-24
    private static final String  URL ="http://localhost:8080/appointments/cmd?";
    private static final String  URL_H ="http://localhost:8080/appointments/hcmd?";

    RestTemplate restTemplate = new RestTemplate();  // Client Template

    public List<Appointment> createAppointments(LocalDateTime startDate, LocalDateTime endDate){

        // getForEntity-> Request- <Apod>, Apod.class -> wandelt Json zu Apod-Objekt (HTTP-GET)
        String url = URL + "start=" + startDate + "&end=" + endDate;
        System.out.println(url);
        ResponseEntity<Appointment[]> re = restTemplate.getForEntity(url, Appointment[].class);
        List<Appointment> list = new ArrayList<Appointment>(Arrays.asList(re.getBody()));
            //    Arrays.asList(re.getBody());
        System.out.println("List<Appointment> size= " + list.size());
        return list;// List<Appointment>
    }
    public List<Appointment> createAppointmentsOfHairdresser(long hairdresserId, LocalDateTime startDate, LocalDateTime endDate){

        // getForEntity-> Request- <Apod>, Apod.class -> wandelt Json zu Apod-Objekt (HTTP-GET)
        String url = URL_H + "h=" + hairdresserId + "&start=" + startDate + "&end=" + endDate;
        System.out.println(url);
        ResponseEntity<Appointment[]> re = restTemplate.getForEntity(url, Appointment[].class);
        List<Appointment> list = new ArrayList<>(Arrays.asList(re.getBody()));
        //    Arrays.asList(re.getBody());
        System.out.println("List<Appointment> size= " + list.size());
        return list;// List<Appointment>
    }
}
