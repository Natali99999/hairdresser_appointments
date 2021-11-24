package com.hairdresser.appointment.dao;

import com.hairdresser.appointment.model.HairdresserServ;
import com.hairdresser.appointment.model.SERVICE_CATEGORY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HairdresserServiceDAO extends JpaRepository<HairdresserServ, Long> { // Long Datentype des primären Schlüssels
    List<HairdresserServ> findAllByServiceCategory(SERVICE_CATEGORY category);


}