package io.redspark.controller.dto;

import io.redspark.domain.ScheduleVaccine;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ScheduleVaccineDTO {

    private Long id;
    @NotNull(message = "vaccine can not be null.")
    private VaccineDTO vaccine;
    @NotNull(message = "client can not be null.")
    private PersonDTO client;
    @NotNull(message = "animal can not be null.")
    private AnimalDTO animal;
    @NotBlank(message = "date can not be null or blank.")
    private String date;
    private Boolean notified;

    public ScheduleVaccineDTO(ScheduleVaccine scheduleVaccine) {
        this.id = scheduleVaccine.getId();
        this.vaccine = new VaccineDTO(scheduleVaccine.getVaccine());
        this.client = new PersonDTO(scheduleVaccine.getClient());
        this.animal = new AnimalDTO(scheduleVaccine.getAnimal());
        this.date = scheduleVaccine.getDate().toString();
        this.notified = scheduleVaccine.getNotified();
    }
}