package com.hairdresser.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table - für anderen Tabellennamen
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Long id;

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column(/*unique = true*/)
    private String email;
    @Column()
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)//.LAZY
    private List<Appointment> appointments = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstname(), user.getFirstname()) && Objects.equals(getLastname(), user.getLastname()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(appointments, user.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail(), getPhone(), appointments);
    }

    public void addAppointment(Appointment a){
        a.setUser(this); //Achtung: Hibernate Besonderheit, set muss ausgeführt werden
        // Zuordnung: Todo bekommz Person "zugewiesen"
        appointments.add(a);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public User(String firstname, String lastname, String email, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public User(){

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}