package io.redspark.controller.dto;

import io.redspark.domain.Treatment;
import io.redspark.domain.VaccineTreatment;
import io.redspark.utils.MapperUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class TreatmentDTO {

    private static MapperUtils<VaccineTreatment, VaccineTreatmentDTO> vaccineTreatmentConverter = new MapperUtils<>(VaccineTreatment.class, VaccineTreatmentDTO.class);

    private Long id;
    @NotNull(message = "doctor can not be null.")
    private PersonDTO doctor;
    @NotNull(message = "client can not be null.")
    private PersonDTO client;
    @NotNull(message = "animal can not be null.")
    private AnimalDTO animal;
    @Valid
    @NotEmpty(message = "vaccines can not be null or empty.")
    private List<VaccineTreatmentDTO> vaccines;
    private String comments;
    @NotBlank(message = "date can not be null or blank.")
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