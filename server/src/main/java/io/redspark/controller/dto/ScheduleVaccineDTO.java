package io.redspark.controller.dto;

import io.redspark.domain.ScheduleVaccine;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleVaccineDTO {

    private Long id;
    private VaccineDTO vaccine;
    private PersonDTO client;
    private AnimalDTO animal;
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