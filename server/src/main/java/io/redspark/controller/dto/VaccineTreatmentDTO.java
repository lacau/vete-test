package io.redspark.controller.dto;

import io.redspark.domain.VaccineTreatment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccineTreatmentDTO {

    @NotNull(message = "vaccine id can not be null.")
    @Min(0)
    @Max(Long.MAX_VALUE)
    private Long id;
    private String name;
    @NotNull(message = "vaccine quantity can not be null.")
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private Integer quantity;

    public VaccineTreatmentDTO(VaccineTreatment vaccineTreatment) {
        this.id = vaccineTreatment.getId();
        this.name = vaccineTreatment.getVaccine().getName();
        this.quantity = vaccineTreatment.getQuantity();
    }
}