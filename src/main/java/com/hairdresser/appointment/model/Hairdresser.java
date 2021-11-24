package com.hairdresser.appointment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Hairdresser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private long id;
    @Column
    private String firstname;
    @Column
    private String lastname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hairdresser that = (Hairdresser) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(description, that.description) && Objects.equals(image, that.image) && Objects.equals(appointments, that.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, description, image, appointments);
    }

    @Column
    private String description;
    @Column
    private String image;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @JsonIgnore
    @OneToMany(mappedBy="hairdresser", cascade = CascadeType.ALL,fetch = FetchType.EAGER)//.LAZY
    private List<Appointment> appointments = new LinkedList<>();
    public void addAppointment(Appointment a){
        a.setHairdresser(this); //Achtung: Hibernate Besonderheit, set muss ausgeführt werden
        // Zuordnung: Todo bekommz Person "zugewiesen"
        appointments.add(a);
    }


    public Hairdresser(){}

    public Hairdresser(String firstname, String lastname, String description, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.image = image;
    }
    public String getFullName() {
        return firstname + " " + lastname;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Hairdresser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}
