package io.redspark.controller.dto;

import io.redspark.domain.VaccineTreatment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VaccineTreatmentDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private String description;

    public VaccineTreatmentDTO(VaccineTreatment vaccineTreatment) {
        this.id = vaccineTreatment.getId();
        this.name = vaccineTreatment.getVaccine().getName();
        this.quantity = vaccineTreatment.getQuantity();
        this.description = vaccineTreatment.getVaccine().getDescription();
    }
}