package io.redspark.controller.dto;

import io.redspark.domain.Treatment;
import io.redspark.domain.VaccineTreatment;
import io.redspark.utils.MapperUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TreatmentDTO {

    private static MapperUtils<VaccineTreatment, VaccineTreatmentDTO> vaccineTreatmentConverter = new MapperUtils<>(VaccineTreatment.class, VaccineTreatmentDTO.class);

    private Long id;
    private PersonDTO doctor;
    private PersonDTO client;
    private AnimalDTO animal;
    private List<VaccineTreatmentDTO> vaccines;
    private String comments;
    private String date;

    public TreatmentDTO(Treatment treatment) {
        this.id = treatment.getId();
        this.doctor = new PersonDTO(treatment.getDoctor());
        this.client = new PersonDTO(treatment.getClient());
        this.animal = new AnimalDTO(treatment.getAnimal());
        this.vaccines = vaccineTreatmentConverter.toDTO(treatment.getVaccineTreatmentList());
        this.comments = treatment.getComments();
        this.date = treatment.getDate().toString();
    }
}