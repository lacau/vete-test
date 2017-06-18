package io.redspark.domain;

import io.redspark.domain.converter.AnimalTypeConverter;
import io.redspark.domain.type.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by lacau on 17/06/17.
 */
@Entity
@Table(name = "animal")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @GeneratedValue
    @Id
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Convert(converter = AnimalTypeConverter.class)
    private AnimalType type;

    @Column(name = "comments")
    private String comments;
}
