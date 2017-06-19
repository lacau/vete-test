package io.redspark.controller.dto;

import io.redspark.domain.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PersonDTO {

    @NotNull(message = "person id can not be null or blank.")
    @Min(0)
    @Max(Long.MAX_VALUE)
    private Long id;
    private String name;
    private String email;
    private String type;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.type = person.getType().name();
    }
}