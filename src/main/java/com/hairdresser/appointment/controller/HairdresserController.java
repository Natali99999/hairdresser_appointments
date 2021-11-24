package com.hairdresser.appointment.controller;

import com.hairdresser.appointment.model.Hairdresser;
import com.hairdresser.appointment.service.HairdresserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hairdressers")
public class HairdresserController {
    @Autowired
    HairdresserService hairdresserService;

    /**
     * GET
     * http://localhost:8080/hairdressers
     * @return
     */
    @RequestMapping
    public List<Hairdresser> getAll(){
        return hairdresserService.list();
    }
}
