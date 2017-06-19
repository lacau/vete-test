package io.redspark.service;

import io.redspark.controller.dto.ScheduleVaccineDTO;
import io.redspark.domain.Animal;
import io.redspark.domain.Person;
import io.redspark.domain.ScheduleVaccine;
import io.redspark.domain.Vaccine;
import io.redspark.exception.NotFoundException;
import io.redspark.repository.AnimalRepository;
import io.redspark.repository.PersonRepository;
import io.redspark.repository.ScheduleVaccineRepository;
import io.redspark.repository.VaccineRepository;
import io.redspark.utils.DateUtils;
import io.redspark.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by lacau on 18/06/17.
 */
@Service
public class ScheduleVaccineServiceImpl implements ScheduleVaccineService {

    private static MapperUtils<ScheduleVaccine, ScheduleVaccineDTO> scheduleVaccineConverter = new MapperUtils<>(ScheduleVaccine.class, ScheduleVaccineDTO.class);

    @Autowired
    private ScheduleVaccineRepository scheduleVaccineRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private DateUtils dateUtils;

    @Override
    public List<ScheduleVaccine> searchScheduledVaccine(Integer daysTillVaccine, Boolean notified) {
        final Instant to = Instant.now().plus(daysTillVaccine, ChronoUnit.DAYS);

        return scheduleVaccineRepository.searchBeforeDate(java.sql.Date.from(to), notified);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ScheduleVaccineDTO scheduleVaccine(ScheduleVaccineDTO scheduleVaccineDTO) {
        final Date date = dateUtils.toDate(scheduleVaccineDTO.getDate());
        final Vaccine vaccine = vaccineRepository.findOne(scheduleVaccineDTO.getVaccine().getId());
        Optional.ofNullable(vaccine).orElseThrow(() -> new NotFoundException("vaccine"));
        final Person client = personRepository.findOne(scheduleVaccineDTO.getClient().getId());
        Optional.ofNullable(client).orElseThrow(() -> new NotFoundException("client"));
        final Animal animal = animalRepository.findOne(scheduleVaccineDTO.getAnimal().getId());
        Optional.ofNullable(animal).orElseThrow(() -> new NotFoundException("animal"));

        final ScheduleVaccine scheduleVaccine = ScheduleVaccine.builder()
                .vaccine(vaccine)
                .client(client)
                .animal(animal)
                .date(date)
                .notified(scheduleVaccineDTO.getNotified() == null ? false : scheduleVaccineDTO.getNotified())
                .build();

        return scheduleVaccineConverter.toDTO(scheduleVaccineRepository.save(scheduleVaccine));
    }
}
