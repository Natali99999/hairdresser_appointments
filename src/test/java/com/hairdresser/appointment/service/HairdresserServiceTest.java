package com.hairdresser.appointment.service;

import com.hairdresser.appointment.model.Hairdresser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HairdresserServiceTest {
    @Autowired
    HairdresserService haidresserService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void list() {

       // List<Hairdresser> list =  haidresserService.list();
       // assertEquals(3, list.size());
    }

    @Test
    void save() {

        Hairdresser h1 = new Hairdresser(
                "Maria", "Majer", "Master", "resources/images/frisörin2.jpg");
        Hairdresser h2 = new Hairdresser(
                "Ina", "Stein", "Azubi", "resources/images/frisörin3.jpg");
        Hairdresser h3 = new Hairdresser(
                "Lena", "Müller", "Master", "resources/images/frisörin1.jpg");

        assertNotNull(haidresserService.save(h1));
        assertNotNull(haidresserService.save(h2));
        assertNotNull(haidresserService.save(h3));
    }
}
