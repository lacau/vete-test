package io.redspark.controller.dto;

import io.redspark.domain.Animal;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AnimalDTO {

    @NotNull(message = "animal id can not be null or blank.")
    @Min(0)
    @Max(Long.MAX_VALUE)
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