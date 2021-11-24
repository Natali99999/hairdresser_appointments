package com.hairdresser.appointment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


/*
Annotation müssen über Klassen, Instanzvariablen oder get-Methoden
*/
@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Long id;

    @Column
    private LocalDateTime start = LocalDateTime.now();

    // Many Appointments <-> One HairdresserServ
    //@JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)//.LAZY
    @JoinColumn(foreignKey = @ForeignKey(name = "serv_id") )
    private HairdresserServ serv;

    // Many Appointments <-> One User
    //@JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)//.LAZY
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id") )
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return getId().equals(that.getId()) && getStart().equals(that.getStart()) && getServ().equals(that.getServ()) && getUser().equals(that.getUser()) && getHairdresser().equals(that.getHairdresser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStart(), getServ(), getUser(), getHairdresser());
    }

    // Many Appointments <-> One Hairdresser
    //@JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)//.LAZY
    @JoinColumn(foreignKey = @ForeignKey(name = "hairdresser_id") )
    private Hairdresser hairdresser;

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", start=" + start +
                 '}';
    }

    public Appointment(){

    }

    public Appointment(LocalDateTime start) {

        this.start = start;
     }
    public Appointment(LocalDateTime start, Hairdresser hairdresser, HairdresserServ hairdresserServ,
                  User user) {
        this.serv = hairdresserServ;
        this.user = user;
        this.hairdresser = hairdresser;
        this.start = start;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDateTime getStart() {
        return start;
    }
    public void setStart(LocalDateTime start) {
        System.out.println("setStartTime "+ start);
        this.start = start;
    }
    public LocalDateTime end() {
        if (getServ() != null)
         return start.plusMinutes(getServ().getDuration());
        return null;
    }


    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(Hairdresser hairdresser) {
        this.hairdresser = hairdresser;
    }

    public HairdresserServ getServ() {
        return serv;
    }

    public void setServ(HairdresserServ serv) {
        this.serv = serv;
    }
}

