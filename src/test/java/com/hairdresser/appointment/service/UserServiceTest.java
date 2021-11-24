package com.hairdresser.appointment.service;

import com.hairdresser.appointment.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void list() {
    }

    @Test
    void save() {
        User user = new User("Max", "Mustermann", "max@web.de", "0123456789");
        User newUser = service.save(user);
        assertNotNull(newUser);

        User user2 = new User("Maja", "Müller", "majy@web.de", "23452357888");
        User newUser2 = service.save(user2);
        assertNotNull(newUser2);

    }
}