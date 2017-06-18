package io.redspark.controller.dto;

import io.redspark.domain.Treatment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TreatmentDTO {

    private Long id;
    private PersonDTO doctor;
    private PersonDTO client;
    private AnimalDTO animal;
    private String comments;
    private String date;

    public TreatmentDTO(Treatment treatment) {
        this.id = treatment.getId();
        this.doctor = new PersonDTO(treatment.getDoctor());
        this.client = new PersonDTO(treatment.getClient());
        this.animal = new AnimalDTO(treatment.getAnimal());
        this.comments = treatment.getComments();
        this.date = treatment.getDate().toString();
    }
}