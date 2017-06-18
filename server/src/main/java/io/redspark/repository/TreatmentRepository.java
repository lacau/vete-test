package io.redspark.repository;

import io.redspark.domain.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    @Query("select t from Treatment t where t.doctor.id =:doctorId")
    List<Treatment> searchByDoctorId(@Param("doctorId") Long doctorId);
}
