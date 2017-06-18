package io.redspark.controller.dto;

import io.redspark.domain.Animal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String name;
    private String type;
    private String comments;

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.name = animal.getName();
        this.type = animal.getType().name();
        this.comments = animal.getComments();
    }
}