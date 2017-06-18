package io.redspark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lacau on 17/06/17.
 */
@Entity
@Table(name = "treatment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @GeneratedValue
    @Id
    @Column(name = "treatment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_doctor", referencedColumnName = "person_id")
    private Person doctor;

    @ManyToOne
    @JoinColumn(name = "fk_client", referencedColumnName = "person_id")
    private Person client;

    @ManyToOne
    @JoinColumn(name = "fk_animal", referencedColumnName = "animal_id")
    private Animal animal;

    @OneToMany(mappedBy = "treatment")
    private List<VaccineTreatment> vaccineTreatmentList;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
