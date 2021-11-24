package com.hairdresser.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@Entity
@Table
public class HairdresserServ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Long id;

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Duration duration = Duration.ofMinutes(30);
    @Column
    private int price;
    @Column
    private String image;
    @Column
    @Enumerated(EnumType.STRING)
    private SERVICE_CATEGORY serviceCategory = SERVICE_CATEGORY.DAMEN_HAARSCHNITT;


    // One HairdresserServ <-> List<Appointment>
    @JsonIgnore
    @OneToMany(mappedBy="serv", cascade = CascadeType.ALL,fetch = FetchType.EAGER)//.LAZY
    private List<Appointment> appointments = new LinkedList<>();
    public void addAppointment(Appointment appointment){
        appointment.setServ(this); //Achtung: Hibernate Besonderheit, set muss ausgeführt werden
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HairdresserServ)) return false;
        HairdresserServ that = (HairdresserServ) o;
        return getPrice() == that.getPrice() && Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDuration(), that.getDuration()) && Objects.equals(getImage(), that.getImage()) && getServiceCategory() == that.getServiceCategory() && Objects.equals(getAppointments(), that.getAppointments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getDuration(), getPrice(), getImage(), getServiceCategory(), getAppointments());
    }

    public HairdresserServ(){

    }

    public HairdresserServ(SERVICE_CATEGORY serviceCategory, String title, int duration,
                           String description, int price, String image) {
        this.serviceCategory = serviceCategory;
        this.duration = Duration.ofMinutes(duration);
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    @Override
    public String toString() {
        return "HairdresserServ{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", serviceCategory=" + serviceCategory +
                ", appointments=" + appointments +
                '}';
    }

    public SERVICE_CATEGORY getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(SERVICE_CATEGORY serviceCategory) {
        this.serviceCategory = serviceCategory;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public long getDuration() {
        return duration.toMinutes();
    }

    public void setDuration(long duration) {
        this.duration.ofMinutes(duration);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getDoublePrice() {
        return price/100.0;
    }

    public String getStringDuration() {
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        if (hours == 0){
            return minutes + " min";
        }
        else{

        }
        return hours + " h " + minutes + " min";
    }
}