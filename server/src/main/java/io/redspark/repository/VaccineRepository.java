package io.redspark.repository;

import io.redspark.domain.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    @Query("select v from Vaccine v where UPPER(v.name) like CONCAT('%', UPPER(:name), '%')")
    List<Vaccine> search(@Param("name") String name);
}
