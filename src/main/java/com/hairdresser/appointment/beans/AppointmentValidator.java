package com.hairdresser.appointment.beans;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDateTime;

@FacesValidator("appointmentValidator")
public class AppointmentValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {
        System.out.println("AppointmentValidator validate");
        System.out.println(value.toString());
        LocalDateTime date = LocalDateTime.parse(value.toString());

        boolean valueIsInvalid = true;
        if (valueIsInvalid) {
            FacesMessage msg =
                    new FacesMessage("Invalid time",
                            String.format("invalid time: %s",  value));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
