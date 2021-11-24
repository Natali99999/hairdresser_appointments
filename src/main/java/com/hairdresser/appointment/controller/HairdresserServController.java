package com.hairdresser.appointment.controller;

import com.hairdresser.appointment.model.Appointment;
import com.hairdresser.appointment.model.HairdresserServ;
import com.hairdresser.appointment.service.AppointmentService;
import com.hairdresser.appointment.service.HairdresserServService;
import com.hairdresser.appointment.service.HairdresserService;
import com.hairdresser.appointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class HairdresserServController {
    @Autowired
    HairdresserServService hairdresserServService;

    /**
     * GET
     * http://localhost:8080/services
     * @return
     */
    @RequestMapping
    public List<HairdresserServ> getAll(){ //

        return hairdresserServService.list();

    }
}
