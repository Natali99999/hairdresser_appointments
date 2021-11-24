package com.hairdresser.appointment.dao;


import com.hairdresser.appointment.model.Hairdresser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairdresserDAO extends JpaRepository<Hairdresser, Long> { // Long Datentype des primären Schlüssels

}