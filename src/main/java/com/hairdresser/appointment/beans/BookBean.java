package com.hairdresser.appointment.beans;

import com.hairdresser.appointment.model.*;
import com.hairdresser.appointment.service.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Named
@ViewScoped
public class BookBean implements Serializable {
    Logger log = LogManager.getLogger(BookBean.class);

    @Autowired
    private SendMailService emailService;
    @Autowired
    private HairdresserServService hairdresserServService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    HairdresserService hairdresserService;
    @Autowired
    private HomeBean homeBean;

    private User user;
    private Appointment appointment;
    private HairdresserServ selectedHairdresserServ;
    private List<HairdresserServ> services;


    private Hairdresser selectedHairdresser;
    private LocalDateTime selectedDatetime = LocalDateTime.now();
    private List<Appointment> appointments;
    private SERVICE_CATEGORY[] categories;
    private SERVICE_CATEGORY selectedCategory;

    private boolean skip;
    private String minTime = "08:00:00";
    private String maxTime = "18:30:00";
    private String locale = "de";
    private String timeZone = "";
   // private ScheduleModel lazyModel;
    private ScheduleEvent<Appointment> event;
    private ScheduleModel model;
  /*  public ScheduleModel getLazyModel() {
        return lazyModel;
    }*/

    @PostConstruct
    public void init() {
        selectedHairdresser = homeBean.getSelectedHairdresser();
        log.info("onTabChange ");
        user = new User("Test user",
                "Test lastname",
                "natalifilatov@yahoo.de",
                "123456789");
        appointment = new Appointment();

        categories = SERVICE_CATEGORY.values();
        setSelectedCategory(SERVICE_CATEGORY.DAMEN_HAARSCHNITT);
        getHServices();
        if (services.size() > 0)
            setSelectedHairdresserService(services.get(0));

        model = new DefaultScheduleModel();
        event = new DefaultScheduleEvent<Appointment>();

        appointments = appointmentService.findAppointmentByHairdresser(
                selectedHairdresser);

        for (Appointment app: appointments) {
            DefaultScheduleEvent<?> currentEvent =   DefaultScheduleEvent.builder()
                    .title( app.getServ().getTitle())
                    .startDate(app.getStart())
                    .endDate(app.end())
                    .description(app.getServ().getDescription())
                    .borderColor("orange")
                    .data(app)
                    .overlapAllowed(false)
                    .editable(false)
                    .resizable(false)
                    .display(ScheduleDisplayMode.BACKGROUND)
                    .backgroundColor("green")
                    .build();

            model.addEvent(currentEvent);
        }

     /*   lazyModel = new LazyScheduleModel() {
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
        };*/

      }
    public ScheduleModel getModel() {
        return model;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Appointment getAppointment() {return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment;  }
    public String getUserFirstsname() {
        return user.getFirstname();
    }
    public void setUserFirstsname(String n) {
        user.setFirstname(n);
    }
    public String getUserLastsname() {
        return user.getLastname();
    }
    public void setUserLastsname(String n) {
        user.setLastname(n);
    }
    public String getUserEmail() {
        return user.getEmail();
    }
    public void setUserEmail(String email) {
        user.setEmail(email);
    }
    public String getUserPhone() {return user.getPhone();}
    public void setUserPhone(String phone) {
        user.setPhone(phone);
    }
    public SERVICE_CATEGORY[] getCategories(){
        return categories;
    }

    public Hairdresser getSelectedHairdresser() {
        return selectedHairdresser;
    }
    public void setSelectedHairdresser(Hairdresser selectedHairdresser) {
        this.selectedHairdresser = selectedHairdresser;
    }


    public HairdresserServ getSelectedHairdresserService(){
        return selectedHairdresserServ;
    }
    public void setSelectedHairdresserService(HairdresserServ hService) {
        System.out.println("setSelectedHairdresserService service " + hService != null ? hService.getTitle() : "null");
       // log.debug("selected service {}", hService.getTitle());
        this.selectedHairdresserServ = hService;
     }

    public void selectHairdresserService(HairdresserServ hService) {
        System.out.println("selectHairdresserService " +hService != null ? hService.getTitle() : "null");
        log.debug("select service {}", hService.getTitle());
        setSelectedHairdresserService(hService);
        System.out.println("add appointment to service {} " + hService.getTitle());
        log.debug("add appointment to service {}", hService.getTitle());
        this.selectedHairdresserServ.addAppointment(appointment);
    }

    public List<HairdresserServ> getHServices(){
        services = hairdresserServService.findAllByServiceCategory(getSelectedCategory());
     //   if (services.size() > 0)
            //setSelectedHairdresserService(services.get(0));
        return services;
      }

    public void save() {
        log.debug("save ");
        System.out.println("save ");
        if (appointment == null) {
            return;
        }
      //  appointment.setStart(selectedDatetime);

        Appointment newAppointment = appointmentService.save(appointment);
        log.debug("newAppointment {}", newAppointment);
        System.out.println("save newAppointment " + newAppointment);

        if (selectedHairdresserServ != null && user != null) {
            User newUser = userService.save(user);
            user.addAppointment(appointment);
            userService.update(user);
            System.out.println("update user " + user);

            selectedHairdresserServ.addAppointment(appointment);
            hairdresserServService.update(selectedHairdresserServ);

            Mail mail = new Mail(user.getEmail(), selectedHairdresserServ.getTitle(), "Termin gebucht");
            emailService.sendMail(mail);
            System.out.println("sendMail ");
            log.debug("sendMail");
        }

        if (getSelectedHairdresser() != null){
            System.out.println(" hairdresser addAppointment" );
            getSelectedHairdresser().addAppointment(appointment);
            System.out.println(" hairdresser update" );
            hairdresserService.update(getSelectedHairdresser());
            System.out.println("update hairdresser " + getSelectedHairdresser());

        }

         // reload
     /*   appointments = appointmentService.findAppointmentByHairdresser(  getSelectedHairdresser());
        System.out.println("reload appointments " + appointments.size());*/

      //  FacesMessage msg = new FacesMessage("Successful", "Appointment at :" + appointment + "is created");
     //   FacesContext.getCurrentInstance().addMessage(null, msg);

        System.out.println("end save ");

    }

    public void onTabChange(TabChangeEvent event) {
        System.out.println("onTabChange " + event.getTab().getTitle());
        log.debug("onTabChange {} ", event.getTab().getTitle());
        for(SERVICE_CATEGORY cat: categories){
            if (cat.getTitle().equals(event.getTab().getTitle())){
                setSelectedCategory(cat);
                getHServices();
                break;
            }
        }

        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onTabClose(TabCloseEvent event) {
        log.debug("onTabClose " + event.getTab().getTitle());
        System.out.println("onTabClose");
       //  setSelectedCategory(null);
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public SERVICE_CATEGORY getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(SERVICE_CATEGORY selectedCategory) {
        log.debug("setSelectedCategory {}", selectedCategory);
        System.out.println("setSelectedCategory " + selectedCategory);
        this.selectedCategory = selectedCategory;
        //selectedCategory.getHairdresserServices();
    }

    public boolean isSkip() {
        return skip;
    }
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        log.debug("onFlowProcess {}", event.getNewStep());
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public LocalDateTime getSelectedDatetime() {
        return selectedDatetime;
    }

    public String getSelectedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return selectedDatetime.format(formatter);
    }

    public String getSelectedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return selectedDatetime.format(formatter);
    }

    public String toHome()  {
        save();
        log.debug("toHome()");
        return "Home.xhtml";
    }

    public ScheduleEvent<Appointment> getEvent() {
        return event;
    }
    public void setEvent(ScheduleEvent<Appointment> event) {
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
            System.out.println("add addEvent zum model");

            model.addEvent(event);

            FacesMessage msg = new FacesMessage("Successful", "Die Terminzeit ist " + event.getStartDate());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            System.out.println("update addEvent");
            model.updateEvent(event);
            FacesMessage msg = new FacesMessage("Successful",
                    "Die Terminzeit wurde zu " + event.getStartDate() + "geändert");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

      //  System.out.println("new addEvent");
        // KEINEN NEUEN Event erzeugen
         // event = new DefaultScheduleEvent<Appointment>();
    }
    public void onEventSelect(SelectEvent<ScheduleEvent<Appointment>> selectEvent) {
        System.out.println("onEventSelect");
        event = selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        System.out.println("onDateSelect " + selectEvent.getObject());
        if (getSelectedHairdresserService() == null) {
            System.out.println("getSelectedHairdresserService() == null ");
            return;
        }
        System.out.println("getSelectedHairdresserService()  " + getSelectedHairdresserService());

        appointment.setStart(selectEvent.getObject());

        if (event != null) {
            event.setStartDate(selectEvent.getObject());
            event.setEndDate(selectEvent.getObject());

            DefaultScheduleEvent<Appointment> dEvent = (DefaultScheduleEvent<Appointment>) event;
            dEvent.setTitle(getSelectedHairdresserService().getTitle());
            dEvent.setDescription(getSelectedHairdresserService().getDescription());
            dEvent.setOverlapAllowed(false);
            dEvent.setResizable(false);
            dEvent.setDraggable(false);
            dEvent.setData(appointment);
            dEvent.setEditable(false);
        }
    }
  /*  public Validator getAppointmentValidator() {
        System.out.println("getAppointmentValidator");
        appointmentEndTime();
        List<Appointment> apps = appointmentService.findAppointmentByHairdresserAndStartBetween(
                homeBean.getSelectedHairdresser(), event.getStartDate(), event.getEndDate());
        System.out.println("List<Appointment>" + apps);

        return (context, component, value) -> {
            System.out.println("getAppointmentValidator event ");
            //   System.out.println("getFooValidator value" + event.getStartDate());
            // System.out.println("getFooValidator duration" + duration);

            boolean valueIsInvalid = false;
            if (valueIsInvalid) {
                throw new ValidatorException(new FacesMessage("Value is invalid!"));
            }
        };
    }*/



    public void onEventDelete() {
        String eventId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId");
        if (event != null) {
            ScheduleEvent<?> event = model.getEvent(eventId);
            model.deleteEvent(event);
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

    public void appointmentEndTime()
    {
        if (selectedHairdresserServ != null) {
            System.out.println("duration: " + selectedHairdresserServ.getDuration());
            String terminArt = event.getTitle();

            event.setEndDate(event.getStartDate().plusMinutes(selectedHairdresserServ.getDuration()));
            System.out.println("endtime: " + event.getEndDate());
        }
    }


    public List<HairdresserServ> getServices() {
        return services;
    }

    public void setServices(List<HairdresserServ> services) {
        this.services = services;
    }
}
