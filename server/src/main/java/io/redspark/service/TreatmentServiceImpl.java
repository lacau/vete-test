package io.redspark.service;

import io.redspark.controller.dto.TreatmentDTO;
import io.redspark.controller.dto.VaccineTreatmentDTO;
import io.redspark.domain.*;
import io.redspark.exception.NotFoundException;
import io.redspark.repository.AnimalRepository;
import io.redspark.repository.PersonRepository;
import io.redspark.repository.TreatmentRepository;
import io.redspark.repository.VaccineRepository;
import io.redspark.utils.DateUtils;
import io.redspark.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by lacau on 18/06/17.
 */
@Service
public class TreatmentServiceImpl implements TreatmentService {

    private static MapperUtils<Treatment, TreatmentDTO> treatmentConverter = new MapperUtils<>(Treatment.class, TreatmentDTO.class);

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private DateUtils dateUtils;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TreatmentDTO add(TreatmentDTO treatmentDTO) {
        final Date date = dateUtils.toDate(treatmentDTO.getDate());
        final Person doctor = personRepository.findOne(treatmentDTO.getDoctor().getId());
        Optional.ofNullable(doctor).orElseThrow(() -> new NotFoundException("doctor"));
        final Person client = personRepository.findOne(treatmentDTO.getClient().getId());
        Optional.ofNullable(client).orElseThrow(() -> new NotFoundException("client"));
        final Animal animal = animalRepository.findOne(treatmentDTO.getAnimal().getId());
        Optional.ofNullable(animal).orElseThrow(() -> new NotFoundException("animal"));

        final Treatment treatment = Treatment.builder()
                .doctor(doctor)
                .client(client)
                .animal(animal)
                .comments(treatmentDTO.getComments())
                .date(date)
                .build();

        final List<VaccineTreatment> vaccines = new ArrayList<>();

        for (VaccineTreatmentDTO vaccineTreatmentDTO : treatmentDTO.getVaccines()) {
            final Vaccine vaccine = vaccineRepository.findOne(vaccineTreatmentDTO.getId());
            Optional.ofNullable(vaccine).orElseThrow(() -> new NotFoundException("vaccine"));

            vaccines.add(VaccineTreatment.builder()
                    .treatment(treatment)
                    .vaccine(vaccine)
                    .quantity(vaccineTreatmentDTO.getQuantity())
                    .build());
        }

        treatment.setVaccineTreatmentList(vaccines);

        return treatmentConverter.toDTO(treatmentRepository.save(treatment));
    }
}
