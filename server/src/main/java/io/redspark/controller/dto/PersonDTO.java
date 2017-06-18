package io.redspark.controller.dto;

import io.redspark.domain.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {

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