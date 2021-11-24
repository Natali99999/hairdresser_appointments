package com.hairdresser.appointment.service;


import com.hairdresser.appointment.dao.UserDAO;
import com.hairdresser.appointment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Injection -> Autowired
public class UserService {
    @Autowired // Dependency Injection
    private UserDAO dao;

    public User getById(long id){
        return dao.findById(id).get(); // Delegieren -> geerbt von JPA Repository
    }

    public List<User> list(){
        return dao.findAll(); // Delegieren -> geerbt von JPA Repository
    }

    public User save(User newUser){
        User user =  dao.save(newUser);
        return user;

    }

    public User update(User newUser){
        User user =  dao.save(newUser);
        return user;

    }

   /* public User findAllByEmail(String email){
        return dao.findAllByEmail();
    }*/
}