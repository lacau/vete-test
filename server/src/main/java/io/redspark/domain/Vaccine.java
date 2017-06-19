package io.redspark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by lacau on 17/06/17.
 */
@Entity
@Table(name = "vaccine")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {

    @GeneratedValue
    @Id
    @Column(name = "vaccine_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;
}
