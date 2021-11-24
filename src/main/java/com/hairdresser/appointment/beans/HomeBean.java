package com.hairdresser.appointment.beans;


import com.hairdresser.appointment.model.Hairdresser;
import com.hairdresser.appointment.service.HairdresserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;

import javax.inject.Named;
import java.io.Serializable;

import java.util.List;

@SessionScope
@Named
public class HomeBean implements Serializable {
    Logger log = LogManager.getLogger(HomeBean.class);

    private Hairdresser selectedHairdresser;

    @Autowired
    HairdresserService hairdresserService;

    public  List<Hairdresser> getHairdressers(){
      //  log.info("!!List<Hairdresser>: " + hairdresserService);
        System.out.println(hairdresserService);
        return hairdresserService.list();
    }

    @PostConstruct
    public void init() {
   }

    public String tobook()  {
        log.debug("tobook()");
        return "Book.xhtml";
    }

    public Hairdresser getSelectedHairdresser() {
        return selectedHairdresser;
    }

    public void setSelectedHairdresser(Hairdresser selectedHairdresser) {
        this.selectedHairdresser = selectedHairdresser;
    }

    public void selectHairdresser(Hairdresser selectedHairdresser) {
        log.debug("selectedHairdresser {}", selectedHairdresser.getFullName());
        setSelectedHairdresser(selectedHairdresser);
    }
}

