package com.hairdresser.appointment.controller;

import com.hairdresser.appointment.model.Appointment;
import com.hairdresser.appointment.model.Hairdresser;
import com.hairdresser.appointment.model.User;
import com.hairdresser.appointment.service.AppointmentService;
import com.hairdresser.appointment.service.HairdresserService;
import com.hairdresser.appointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    UserService userService;
    @Autowired
    HairdresserService hairdresserService;
    /**
     * GET
     * http://localhost/appointments
     * @return
     */
    @RequestMapping
    public List<Appointment> getAll(){ //

        return appointmentService.list();

    }

    @PostMapping("/new-appointment")
    public Appointment newTodo( @RequestBody Appointment appointment){

        return appointmentService.save(appointment);
    }


    /**
     *
     * @param id
     */

    @DeleteMapping("/del")// DELETE del?1
    public void delete(@RequestParam("id") long id){

        appointmentService.remove(id);
    }
    @PutMapping("/update")  //PUT
    public Appointment update(@RequestBody Appointment appointment){

        return  appointmentService.update(appointment);
    }

    @RequestMapping("/user") //  /appointments/user?1
    List<Appointment> getByUser(long userId){
        User user = userService.getById(userId);
        return  appointmentService.findAppointmentByUser(user);
    }

    @RequestMapping("/hairdresser") //  /appointments/hairdresser?1
    List<Appointment> getByHairdresser(long hairdresserId){
        Hairdresser hairdresser = hairdresserService.getById(hairdresserId);
        return  appointmentService.findAppointmentByHairdresser(hairdresser);
    }

    @RequestMapping("/cmd") //  /appointments/cmd?start=2021-11-01T08:00&end=2021-11-22T18:00
    List<Appointment> getByDates(@RequestParam("start") String start, @RequestParam("end") String end){

        return  appointmentService.findAppointmentByStartBetween(start, end);
    }
    @RequestMapping("/hcmd") //  /appointments/hcmd?h=1&start=2021-11-01T08:00&end=2021-11-22T18:00
    List<Appointment> findAppointmentByHairdresserAndStartBetween(@RequestParam("h") long hairdresserId, @RequestParam("start") String start, @RequestParam("end") String end){
        Hairdresser hairdresser = hairdresserService.getById(hairdresserId);
        return  appointmentService.findAppointmentByHairdresserAndStartBetween(hairdresser,
                LocalDateTime.parse(start), LocalDateTime.parse(end));
    }
}
