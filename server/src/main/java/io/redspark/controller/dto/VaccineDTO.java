package io.redspark.controller.dto;

import io.redspark.domain.Vaccine;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class VaccineDTO {

    @NotNull(message = "vaccine id can not be null.")
    @Min(0)
    @Max(Long.MAX_VALUE)
    private Long id;
    private String name;
    private String description;

    public VaccineDTO(Vaccine vaccine) {
        this.id = vaccine.getId();
        this.name = vaccine.getName();
        this.description = vaccine.getDescription();
    }
}