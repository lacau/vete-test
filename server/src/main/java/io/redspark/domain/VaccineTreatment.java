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
@Table(name = "vaccine_treatment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccineTreatment {

    @GeneratedValue
    @Id
    @Column(name = "vaccine_treatment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_vaccine", referencedColumnName = "vaccine_id")
    private Vaccine vaccine;

    @ManyToOne
    @JoinColumn(name = "fk_treatment", referencedColumnName = "treatment_id")
    private Treatment treatment;

    @Column(name = "quantity")
    private Integer quantity;
}
