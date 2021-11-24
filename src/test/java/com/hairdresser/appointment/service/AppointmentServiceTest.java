package com.hairdresser.appointment.service;

import com.hairdresser.appointment.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Testen sollen als SpringBootTest laufen
class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;
     @Autowired
    private HairdresserServService hairderesserServService;
    @Autowired
    HairdresserService hairdresserService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void list() {

        List<Appointment> list =  appointmentService.list();
        assertEquals(8, list.size());
    }

    @Test
    void save() {

        Appointment a1 = new Appointment(LocalDateTime.now().plusDays(1));
        User user1 = new User("Max", "Mustermann", "max@web.de", "0123456789");
        HairdresserServ serv1 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_HAARSCHNITT, "Waschen & Schneiden",
                        30,
                        "Ob kurz oder lang - wir scneiden die deine Wunschfrisur!",
                        4000, "resources/images/schneiden.jpg");

        Appointment a2 = new Appointment(LocalDateTime.now().plusDays(2));
        User user2 = new User("Maja", "Mueller", "maja@web.de", "23452357888");
        HairdresserServ serv2 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_HAARSCHNITT, "Fönen, Legen & Stylen",
                        30,
                        "Angesagte Styles und Looks - jetzt umstylen lassen!",
                        4000, "resources/images/FoenenLegenStylen.jpg");

        Hairdresser h1 = new Hairdresser(
                "Lena", "Müller", "Master", "resources/images/fris1.jpg");

       assertNotNull(appointmentService.save(a1));
       assertNotNull(appointmentService.save(a2));

       assertNotNull(hairdresserService.save(h1));

       assertNotNull(userService.save(user1));
       assertNotNull(userService.save(user2));

       hairderesserServService.save(serv1);
       hairderesserServService.save(serv2);

        user1.addAppointment(a1);
        user1.addAppointment(a2);
        serv1.addAppointment(a1);
        serv2.addAppointment(a2);
        h1.addAppointment(a1);
        h1.addAppointment(a2);

        hairdresserService.update(h1);
        userService.update(user1);
        userService.update(user2);
        hairderesserServService.update(serv1);
        hairderesserServService.update(serv2);
    }

    @Test
    void saveAll() {

        HairdresserServ serv1_1 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_HAARSCHNITT, "Waschen & Schneiden",
                        30,
                        "Ob kurz oder lang - wir scneiden die deine Wunschfrisur!",
                        4000, "resources/images/schneiden.jpg");

        HairdresserServ serv1_2 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_HAARSCHNITT,"Fönen, Legen & Stylen",
                        30,
                        "Angesagte Styles und Looks - jetzt umstylen lassen!",
                        4000, "resources/images/FoenenLegenStylen.jpg");
        HairdresserServ serv1_3 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_HAARSCHNITT,"Locken machen",
                        30,
                        "Typveränderung gefällig? Unsere Locken halten in jedem Haar",
                        2500, "resources/images/locken.jpg");

        // Serv speichern
        assertNotNull(hairderesserServService.save(serv1_1));
        assertNotNull(hairderesserServService.save(serv1_2));
        assertNotNull(hairderesserServService.save(serv1_3));

        HairdresserServ serv2_1 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_COLORATION,"Ansatzfärbung",
                        60,
                        "Ansatz rausgewachsen? Jetzt schnell nachfärben lassen!",
                        3500, "resources/images/faerben.jpg");

        HairdresserServ serv2_2 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_COLORATION,"Foliensträhnen",
                        135,
                        "Egal ob langes oder kurzes, glattes oder welliges Haar -Balayage ist im Trendund steht jedem!",
                        5500, "resources/images/Straenchen.jpg");
        HairdresserServ serv2_3 =
                new HairdresserServ(SERVICE_CATEGORY.DAMEN_COLORATION,"Farbaufrischung",
                        130,
                        "Wir frischen deine Haarfarbe wieder auf - schnell &unkompliziert",
                        2500, "resources/images/Farbaufrischung.jpg");

        // Serv speichern
        hairderesserServService.saveAll(Arrays.asList(serv2_1, serv2_2, serv2_3));

        // Category 3

        HairdresserServ serv3_1 =
                new HairdresserServ(SERVICE_CATEGORY.HERREN_HAARSCHNITT, "Langhaarschnitt",
                        30,
                        "Lass dich Inspirieren. Wir haben den passenden Look für deinen Typ!",
                        2400, "resources/images/herrenschnitt_lang.jpg");

        HairdresserServ serv3_2 =
                new HairdresserServ(SERVICE_CATEGORY.HERREN_HAARSCHNITT,"Maschinenhaarschnitt",
                        30,
                        "Coole Looks für kurze Haare - unsere Maschinenhaarschnitte sind immer voll im Trend!",
                        1500, "resources/images/Maschinenhaarschnitt.jpg");
        HairdresserServ serv3_3 =
                new HairdresserServ(SERVICE_CATEGORY.HERREN_HAARSCHNITT,"Kopfmassage mit individueller Haartonik",
                        30,
                        "Typveränderung gefällig? Unsere Locken halten in jedem Haar",
                        550, "resources/images/Kopfmassage.jpg");

        hairderesserServService.saveAll(Arrays.asList(serv3_1, serv3_2, serv3_3));


        // Category 4

        HairdresserServ serv4_1 =
                new HairdresserServ(SERVICE_CATEGORY.HOCHZEIT,"Beratung - Frisur/Makeup",
                        30,
                        "Beratungstermin für deinen Look zu besonderen Anlässen.",
                        2000, "resources/images/beratung.jpg");

        HairdresserServ serv4_2 =
                new HairdresserServ(SERVICE_CATEGORY.HOCHZEIT,"Hochzeitfrisur",
                        60,
                        "Termin zum Stylen deiner Hochzeitfrisur.",
                        4000, "resources/images/hochzeit.jpg");
        HairdresserServ serv4_3 =
                new HairdresserServ(SERVICE_CATEGORY.HOCHZEIT,"Hochzeitpacket",
                        90,
                        "Wir nehmen dir den Hochzeitstress - mit unserem vollumfänglichem Hochzeitstyling",
                        7000, "resources/images/hochzeitspacket.jpg");

        // Serv speichern
        hairderesserServService.saveAll(Arrays.asList(serv4_1, serv4_2, serv4_3));

        // !! Hairdresser
        Hairdresser h1 = new Hairdresser(
                "Maria", "Majer", "Master", "resources/images/fris2.jpg");
        Hairdresser h2 = new Hairdresser(
                "Ina", "Stein", "Azubi", "resources/images/fris3.jpg");
        Hairdresser h3 = new Hairdresser(
                "Lena", "Müller", "Master", "resources/images/fris1.jpg");

        assertNotNull(hairdresserService.save(h1));
        assertNotNull(hairdresserService.save(h2));
        assertNotNull(hairdresserService.save(h3));

        // User
        User user1 = new User("Max", "Mustermann", "max@web.de", "0123456789");
        User user2 = new User("Maja", "Müller", "maja@web.de", "23452357888");
        User newUser1 = userService.save(user1);
        User newUser2 = userService.save(user2);


        // Appointment
        LocalDateTime d1 = LocalDateTime.parse("2021-11-23T08:30");
        LocalDateTime d2 = LocalDateTime.parse("2021-11-24T09:30");
        LocalDateTime d3 = LocalDateTime.parse("2021-11-22T11:30");
        LocalDateTime d4 = LocalDateTime.parse("2021-11-25T12:00");

        Appointment a1 = new Appointment(d1);
        Appointment a2 = new Appointment(d2);
        Appointment a3 = new Appointment(d3);
        Appointment a4 = new Appointment(d4);
        Appointment a5 = new Appointment(LocalDateTime.now().plusDays(1));
        Appointment a6 = new Appointment(LocalDateTime.now().plusDays(2));
        Appointment a7 = new Appointment(LocalDateTime.now().minusDays(1));
        Appointment a8 = new Appointment(LocalDateTime.now().minusDays(2));

        assertNotNull(appointmentService.save(a1));
        assertNotNull(appointmentService.save(a2));
        assertNotNull(appointmentService.save(a3));
        assertNotNull(appointmentService.save(a4));
        appointmentService.saveAll(Arrays.asList(a5, a6, a7, a8));

        // Many Appointments <-> one User verlinken
        user1.addAppointment(a1);
        user2.addAppointment(a2);
        user1.addAppointment(a3);
        user2.addAppointment(a4);
        user1.addAppointment(a5);
        user2.addAppointment(a6);
        user1.addAppointment(a7);
        user2.addAppointment(a8);

        // update
        userService.update(user1);
        userService.update(user2);

        // Many Appointments <-> One Hairdresser verlinken
        h1.addAppointment(a1);
        h1.addAppointment(a2);
        h1.addAppointment(a3);
        h1.addAppointment(a4);
        h2.addAppointment(a5);
        h2.addAppointment(a6);
        h3.addAppointment(a7);
        h3.addAppointment(a8);
        // update
        hairdresserService.update(h1);

        // One Appointment <-> Many HairdresserServ verlinken
        serv1_1.addAppointment(a1);
        serv2_1.addAppointment(a2);
        serv1_1.addAppointment(a3);
        serv2_1.addAppointment(a4);
        serv2_2.addAppointment(a5);
        serv2_3.addAppointment(a6);
        serv3_1.addAppointment(a7);
        serv4_2.addAppointment(a8);

        // update
        assertNotNull(hairderesserServService.updateAll(
                Arrays.asList(serv1_1, serv2_1, serv2_2, serv2_3, serv3_1, serv4_2)));

    }
}