package com.hairdresser.appointment.dao;

import com.hairdresser.appointment.model.Appointment;

import com.hairdresser.appointment.model.Hairdresser;
import com.hairdresser.appointment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, Long> { // Long Datentype des primären Schlüssels

    List<Appointment> findAppointmentByUser(User u);
    List<Appointment>  findAppointmentByHairdresser(Hairdresser h);
    List<Appointment>  findAppointmentByStartContains(LocalDateTime s);
    List<Appointment> findAppointmentByStartBetween(LocalDateTime start, LocalDateTime end);

    List<Appointment>  findAppointmentByHairdresserAndStartBetween(Hairdresser h, LocalDateTime start, LocalDateTime end);
}