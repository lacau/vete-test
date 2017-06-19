package io.redspark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lacau on 17/06/17.
 */
@Entity
@Table(name = "schedule_vaccine")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVaccine {

    @GeneratedValue
    @Id
    @Column(name = "schedule_vaccine_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_vaccine", referencedColumnName = "vaccine_id")
    private Vaccine vaccine;

    @ManyToOne
    @JoinColumn(name = "fk_client", referencedColumnName = "person_id")
    private Person client;

    @ManyToOne
    @JoinColumn(name = "fk_animal", referencedColumnName = "animal_id")
    private Animal animal;

    @Column(name = "date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "notified")
    private Boolean notified;
}
