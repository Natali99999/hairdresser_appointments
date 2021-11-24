package com.hairdresser.appointment.client;

import com.hairdresser.appointment.model.Appointment;
import com.hairdresser.appointment.model.Hairdresser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HairdresserClient {
   // http://localhost:8080/appointments/cmd?start=2021-11-01&end=2021-11-24
    private static final String  URL ="http://localhost:8080/hairdressers";

    RestTemplate restTemplate = new RestTemplate();  // Client Template

    public List<Hairdresser> createHairdressers(){
        ResponseEntity<Hairdresser[]> re = restTemplate.getForEntity(URL, Hairdresser[].class);
        List<Hairdresser> list = new ArrayList<Hairdresser>(Arrays.asList(re.getBody()));
        return list;// List<Appointment>
    }

}
