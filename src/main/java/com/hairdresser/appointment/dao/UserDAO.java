package com.hairdresser.appointment.dao;


import com.hairdresser.appointment.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> { // Long Datentype des primären Schlüssels
   // User findAllByEmail();
}