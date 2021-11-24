package com.hairdresser.appointment.service;

import com.hairdresser.appointment.dao.AppointmentDAO;
import com.hairdresser.appointment.model.Appointment;

import com.hairdresser.appointment.model.Hairdresser;
import com.hairdresser.appointment.model.HairdresserServ;
import com.hairdresser.appointment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service // Injection -> Autowired
public class AppointmentService {
    @Autowired // Dependency Injection
    private AppointmentDAO dao;

    public List<Appointment> list(){
        return dao.findAll(); // Delegieren -> geerbt von JPA Repository
    }

    public Appointment save(Appointment appointment) {
        Appointment newAppointment = dao.save(appointment);
        return newAppointment;
    }
    public Appointment update(Appointment appointment) {
        Appointment newAppointment = dao.save(appointment);
        return newAppointment;
    }
    public List<Appointment>  saveAll(List<Appointment> asList) {
        return dao.saveAll(asList);
    }


    public void remove(long id){
        dao.deleteById(id);
    }
    public List<Appointment> findAppointmentByUser(User u){
        return dao.findAppointmentByUser(u);
    }
    public List<Appointment> findAppointmentByHairdresser(Hairdresser h){
        return dao.findAppointmentByHairdresser(h);
    }
    public List<Appointment> findAppointmentByStartContains(LocalDateTime s){
        return dao.findAppointmentByStartContains(s);
    }

    public List<Appointment> findAppointmentByStartBetween(String start, String end){
        return dao.findAppointmentByStartBetween(LocalDateTime.parse(start),
                LocalDateTime.parse(end));
    }
    public List<Appointment> findAppointmentByHairdresserAndStartBetween(Hairdresser h, LocalDateTime start, LocalDateTime end){
        return dao.findAppointmentByHairdresserAndStartBetween(h, start, end);
    }




}