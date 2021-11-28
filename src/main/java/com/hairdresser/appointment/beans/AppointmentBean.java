package com.hairdresser.appointment.beans;

import com.hairdresser.appointment.client.AppointmentClient;
import com.hairdresser.appointment.client.EmailClient;
import com.hairdresser.appointment.client.HairdresserClient;
import com.hairdresser.appointment.model.Appointment;

import com.hairdresser.appointment.model.Hairdresser;
import com.hairdresser.appointment.model.Mail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;


@ViewScoped
@Named
public class AppointmentBean {

    Logger log = LogManager.getLogger(AppointmentBean.class);

    @Autowired
    private AppointmentClient clientAppointments;
    @Autowired
    private HairdresserClient clientHairdressers;
    @Autowired
    private EmailClient emailClient;

    private LocalDate startDate =  LocalDate.now().minusDays(2);
    private LocalDate endDate =  LocalDate.now().plusDays(2);

    private String minTime = "08:00:00";
    private String maxTime = "18:30:00";
    private String locale = "de";
    private String timeZone = "";

    private ScheduleEvent<?> event;
    List<Appointment> appointments;
    List<Hairdresser> hairdressers;

    private ScheduleModel lazyModel;
    private Hairdresser selectedHairdresser;

    public  List<Hairdresser> getHairdressers(){
        return hairdressers;
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

        FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Der Kalender von", selectedHairdresser.getFullName() + " wurde ausgewählt.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void sendEmailToHairdresser(Hairdresser selectedHairdresser) {
        log.debug("sendEmailToHairdresser {}", selectedHairdresser.getFullName());
        String mailText =  String.format("Hi %s", selectedHairdresser.getFullName());
        String title =  String.format("Email an %s", selectedHairdresser.getFullName());
        String emailAddress = "natalifilatov@yahoo.de";
        Mail mail = new Mail(emailAddress, title, mailText);

        String result = emailClient.sendMail(mail);

        FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Send email", "result");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

  /*  public void onSelect(AjaxBehaviorEvent e) {
        setSelectedHairdresser(selectedHairdresser);
        String label = ((Chip) e.getSource()).getLabel();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Select Event", label + " selected.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }*/



    public ScheduleModel getLazyModel() {
        return lazyModel;
    }

    @PostConstruct
    public void init() {
        hairdressers = clientHairdressers.createHairdressers();

        event = new DefaultScheduleEvent<>();
        if (getHairdressers().size() > 0) {
            selectedHairdresser = getHairdressers().get(0);
        }

        lazyModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(LocalDateTime start, LocalDateTime end) {

                appointments = clientAppointments.createAppointmentsOfHairdresser(getSelectedHairdresser().getId(), start, end);
                System.out.println("appointments.size(): " + appointments.size());

                for (Appointment appointment: appointments) {
                    if (appointment.getServ() != null) {
                        String title = appointment.getServ().getTitle();
                        System.out.println("title: " + title);
                        System.out.println("start: " + appointment.getStart());
                        System.out.println("end: " + appointment.end());

                        addEvent(DefaultScheduleEvent.builder()
                                .title(appointment.getServ().getTitle())
                                .startDate(appointment.getStart())
                                .endDate(appointment.end())
                                .description(appointment.getServ().getDescription())
                                .borderColor("orange")
                                .data(appointment)
                                .overlapAllowed(false)
                                .editable(false)
                                .resizable(false)
                                .display(ScheduleDisplayMode.BACKGROUND)
                                .backgroundColor("green")
                                .build());
                    }

                }
            }
        };
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }


  /*  public void selectAppointments(){
        appointments = client.createAppointments(startDate,endDate);

       // log.debug("appointments {} - {}: {}", startDate, endDate, appointments);

        for (Appointment appointment: appointments) {

            DefaultScheduleEvent<?> event =   DefaultScheduleEvent.builder()
                    .title( appointment.getServ().getTitle())
                    .startDate(appointment.getStart())
                    .endDate(appointment.end())
                    .description(appointment.getServ().getDescription())
                    .borderColor("orange")
                    .data(appointment)
                    .overlapAllowed(false)
                    .editable(false)
                    .resizable(false)
                    .display(ScheduleDisplayMode.BACKGROUND)
                    .backgroundColor("green")
                    .build();

            model.addEvent(event);
        }
    }*/
}
