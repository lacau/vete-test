package io.redspark.domain;

import io.redspark.domain.converter.PersonTypeConverter;
import io.redspark.domain.type.PersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by lacau on 17/06/17.
 */
@Entity
@Table(name = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @GeneratedValue
    @Id
    @Column(name = "person_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "type", nullable = false)
    @Convert(converter = PersonTypeConverter.class)
    private PersonType type;

    @OneToOne
    @JoinColumn(name = "fk_user", referencedColumnName = "user_id")
    private User user;
}
