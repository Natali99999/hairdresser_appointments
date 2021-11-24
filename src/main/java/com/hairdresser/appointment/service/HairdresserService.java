package com.hairdresser.appointment.service;

import com.hairdresser.appointment.dao.HairdresserDAO;
import com.hairdresser.appointment.model.Hairdresser;

import com.hairdresser.appointment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HairdresserService {
    @Autowired // Dependency Injection
    private HairdresserDAO dao;

    public List<Hairdresser> list(){
        return dao.findAll(); // Delegieren -> geerbt von JPA Repository
    }

    public Hairdresser save(Hairdresser newHairdresser){
        Hairdresser hairdresser =  dao.save(newHairdresser);
        return hairdresser;
    }

    public Hairdresser update(Hairdresser newHairdresser){
        Hairdresser hairdresser =  dao.save(newHairdresser);
        return hairdresser;
    }

    public Hairdresser getById(long id){
        return dao.findById(id).get(); // Delegieren -> geerbt von JPA Repository
    }
}
