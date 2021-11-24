package com.hairdresser.appointment.service;

import com.hairdresser.appointment.dao.HairdresserServiceDAO;
import com.hairdresser.appointment.model.HairdresserServ;
import com.hairdresser.appointment.model.SERVICE_CATEGORY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service // Injection -> Autowired
public class HairdresserServService {
    @Autowired
    private HairdresserServiceDAO dao;

    public List<HairdresserServ> list() {
        return dao.findAll();
    }

    public HairdresserServ save(HairdresserServ serv){
        return dao.save(serv);
    }

    public HairdresserServ update(HairdresserServ serv){
        return dao.save(serv);
    }
    public List<HairdresserServ>  updateAll(List<HairdresserServ> asList){
        return dao.saveAll(asList);
    }

    public List<HairdresserServ>  saveAll(List<HairdresserServ> asList) {
        return dao.saveAll(asList);
    }

    public List<HairdresserServ> findAllByServiceCategory(SERVICE_CATEGORY category){
        return  dao.findAllByServiceCategory(category);
    }
}