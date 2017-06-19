package io.redspark.repository;

import io.redspark.domain.ScheduleVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface ScheduleVaccineRepository extends JpaRepository<ScheduleVaccine, Long> {

    @Query("select sv from ScheduleVaccine sv where sv.notified =:notified and sv.date <=:to")
    List<ScheduleVaccine> searchBeforeDate(@Param("to") @Temporal(TemporalType.TIMESTAMP) Date to,
                                           @Param("notified") Boolean notified);
}
