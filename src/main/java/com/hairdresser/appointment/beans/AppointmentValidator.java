package com.hairdresser.appointment.beans;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.time.LocalDateTime;

@Named
@FacesValidator("appointmentValidator")
public class AppointmentValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {
        System.out.println("AppointmentValidator validate");
        System.out.println(value.toString()); //For some reason this prints Wed Jul 23 02:00:00 CEST 2014 when inputting 23-07-2014
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

/*
@FacesValidator("dateRangeValidator")
public class DateRangeValidator implements Validator {

   @Override
   public void validate(FacesContext facesContext, UIComponent component,
           Object value) throws ValidatorException {

       UIInput dateIniComponent = (UIInput) component.getAttributes().get("dateIniComponent");
       UIInput dateFinComponent = (UIInput) component.getAttributes().get("dateFinComponent");
       String range = ((String) component.getAttributes().get("range")).toLowerCase();
       String reference = ((String) component.getAttributes().get("reference")).toLowerCase();

       if (value == null) {
           return;
       } else if (value instanceof Date) {
           Date dateIni = null;
           Date dateFin = null;
           if ((dateIniComponent == null) && (dateFinComponent != null)) {
               if (!dateFinComponent.isValid()) {
                   return; //No hay datos contra quien comparar
               }
               dateFin = (Date) dateFinComponent.getValue();
               dateIni = (Date) value;
           }

           if ((dateFinComponent == null) && (dateIniComponent != null)) {
               if (!dateIniComponent.isValid()) {
                   return; //No hay datos contra quien comparar
               }
               dateIni = (Date) dateIniComponent.getValue();
               dateFin = (Date) value;
           }

           if ((dateIni != null) && (dateFin != null)) {
               Calendar cal = Calendar.getInstance();

               cal.setTime(dateIni);
               Integer yearIni = cal.get(Calendar.YEAR);
               Integer monthIni = cal.get(Calendar.MONTH);
               Long daysMonthIni = (long) YearMonth.of(yearIni, monthIni + 1).lengthOfMonth();
               Long daysYearIni = (long) cal.getActualMaximum(Calendar.DAY_OF_YEAR);

               cal.setTime(dateFin);
               Integer yearFin = cal.get(Calendar.YEAR);
               Integer monthFin = cal.get(Calendar.MONTH);
               Long daysMonthFin = (long) YearMonth.of(yearFin, monthFin + 1).lengthOfMonth();
               Long daysYearFin = (long) cal.getActualMaximum(Calendar.DAY_OF_YEAR);

               Long daysAllowed =
                       ("year".equals(range) ? ("ini".equals(reference)?daysYearIni:("fin".equals(reference)?daysYearFin:null)) :
                       ("month".equals(range) ? ("ini".equals(reference)?daysMonthIni:("fin".equals(reference)?daysMonthFin:null)) :
                       ("week".equals(range) ? 7 : null)));

               Long daysBetweenDates = TimeUnit.DAYS.convert(dateFin.getTime() - dateIni.getTime(), TimeUnit.MILLISECONDS);

               if (daysAllowed == null) {
                   FacesMessage facesMessage
                           = new FacesMessage(
                                   FacesMessage.SEVERITY_ERROR,
                                   "Rango de fechas mal expresado en el facelet (vista) ",
                                   "Rango de fechas mal expresado en el facelet (vista) ");
                   throw new ValidatorException(facesMessage);
               }

               if (dateFin.before(dateIni)) {
                   FacesMessage facesMessage
                           = new FacesMessage(
                                   FacesMessage.SEVERITY_ERROR,
                                   "Fecha Final No es posterior a Fecha Inicial ",
                                   "La Fecha Final debe ser posterior a Fecha Inicial");
                   throw new ValidatorException(facesMessage);
               }

               if (daysBetweenDates > daysAllowed) {
                   FacesMessage facesMessage
                           = new FacesMessage(
                                   FacesMessage.SEVERITY_ERROR,
                                   "Se ha excedido los dias permitidos " + daysAllowed + " entre fechas seleccionadas, entre las fechas hay " + daysBetweenDates + " dias",
                                   "entre las fechas hay " + daysBetweenDates + " dias");
                   throw new ValidatorException(facesMessage);
               }
           }

       }
   }

    <p:outputLabel value="Date Initial:" for="itHeadDateInitial" />
    <p:calendar id="itHeadDateInitial"
                navigator="true"
                binding="#{bindingDateIniComponent}"
                value="#{theBean.DateIni}"
                pattern="dd-MM-yyyy" mask="true" >
        <f:validator validatorId="dateRangeValidator" />
        <f:attribute name="dateFinComponent" value="#{bindingDateFinComponent}" />
        <f:attribute name="range" value="year" />
        <f:attribute name="reference" value="ini" />
    </p:calendar>
 */
