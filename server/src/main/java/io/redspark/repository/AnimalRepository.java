package io.redspark.repository;

import io.redspark.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("select a from Animal a where UPPER(a.name) like CONCAT('%', UPPER(:name), '%')")
    List<Animal> search(@Param("name") String name);
}
