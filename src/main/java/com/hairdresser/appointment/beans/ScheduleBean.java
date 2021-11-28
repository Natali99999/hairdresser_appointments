package com.hairdresser.appointment.beans;

import com.hairdresser.appointment.model.Appointment;

import com.hairdresser.appointment.model.HairdresserServ;
import com.hairdresser.appointment.service.AppointmentService;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class ScheduleBean {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    private HomeBean homeBean;

    private String minTime = "08:00:00";
    private String maxTime = "18:30:00";
    private String locale = "de";
    private String timeZone = "";

    private ScheduleModel lazyModel;
    private ScheduleEvent<?> event;
    List<Appointment> appointments;

    public ScheduleModel getLazyModel() {
        return lazyModel;
    }
    @PostConstruct
    public void init() {
        event = new DefaultScheduleEvent<>();

        lazyModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(LocalDateTime start, LocalDateTime end) {
                appointments = appointmentService.findAppointmentByHairdresserAndStartBetween(
                        homeBean.getSelectedHairdresser(), start, end);

                for (Appointment appointment: appointments) {
                    String title = appointment.getServ().getTitle();
                    System.out.println("title: " + title);
                    System.out.println("start: " + appointment.getStart());
                    System.out.println("end: " + appointment.end());

                    addEvent( DefaultScheduleEvent.builder()
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
                            .build());

                }
            }
        };

    }

     public ScheduleEvent<?> getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent<?> event) {
        this.event = event;
    }

    public void addEvent() {

        if (event.isAllDay()) {
            // see https://github.com/primefaces/primefaces/issues/1164
            if (event.getStartDate().toLocalDate().equals(event.getEndDate().toLocalDate())) {
                event.setEndDate(event.getEndDate().plusDays(1));
            }
        }

        if (event.getId() == null) {
            System.out.println("add addEvent");

            lazyModel.addEvent(event);
        }
        else {
            System.out.println("update addEvent");
            lazyModel.updateEvent(event);
        }

        System.out.println("new addEvent");

        event = new DefaultScheduleEvent<>();

    }
    public void onEventSelect(SelectEvent<ScheduleEvent<Appointment>> selectEvent) {
        System.out.println("onEventSelect");
        event = selectEvent.getObject();
    }

    public void terminEndTime()
    {
        System.out.println("terminEndTime");
      }


    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        System.out.println("onDateSelect");

    }

    public void onEventDelete() {
        String eventId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId");
        if (event != null) {
            ScheduleEvent<?> event = lazyModel.getEvent(eventId);
            lazyModel.deleteEvent(event);
        }
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
 /*   public Validator getAppointmentValidator() {
        System.out.println("getAppointmentValidator");
        return (context, component, value) -> {
            System.out.println("getAppointmentValidator event ");
            //   System.out.println("getFooValidator value" + event.getStartDate());
            // System.out.println("getFooValidator duration" + duration);

            System.out.println("appointmentEndTime");
            appointmentEndTime();
            System.out.println("after appointmentEndTime");
            List<Appointment> apps = appointmentService.findAppointmentByHairdresserAndStartBetween(
                    homeBean.getSelectedHairdresser(), event.getStartDate(), event.getEndDate());
            System.out.println("List<Appointment>" + apps);
            boolean valueIsInvalid = false;
            if (valueIsInvalid) {
                throw new ValidatorException(new FacesMessage("Value is invalid!"));
            }
        };
    }
       <!--    <f:validator binding="#{userWizard.appointmentValidator}"
                                             process="@this" update="@form"/> -->
    */

}