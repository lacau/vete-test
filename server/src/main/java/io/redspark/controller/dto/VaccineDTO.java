package io.redspark.controller.dto;

import io.redspark.domain.Vaccine;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VaccineDTO {

    private Long id;
    private String name;
    private String description;

    public VaccineDTO(Vaccine vaccine) {
        this.id = vaccine.getId();
        this.name = vaccine.getName();
        this.description = vaccine.getDescription();
    }
}