package io.redspark.service;

import io.redspark.controller.dto.AnimalDTO;
import io.redspark.controller.dto.PersonDTO;
import io.redspark.controller.dto.TreatmentDTO;
import io.redspark.controller.dto.VaccineTreatmentDTO;
import io.redspark.domain.*;
import io.redspark.domain.type.AnimalType;
import io.redspark.domain.type.PersonType;
import io.redspark.exception.NotFoundException;
import io.redspark.repository.AnimalRepository;
import io.redspark.repository.PersonRepository;
import io.redspark.repository.TreatmentRepository;
import io.redspark.repository.VaccineRepository;
import io.redspark.utils.DateUtils;
import io.redspark.utils.MapperUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lacau on 18/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TreatmentServiceImplTest {

    @InjectMocks
    TreatmentServiceImpl treatmentService;

    @Mock
    PersonRepository personRepository;

    @Mock
    AnimalRepository animalRepository;

    @Mock
    TreatmentRepository treatmentRepository;

    @Mock
    VaccineRepository vaccineRepository;

    @Mock
    DateUtils dateUtils;

    @Spy
    MapperUtils treatmentConverter = new MapperUtils(Treatment.class, TreatmentDTO.class);

    @Before
    public void init() {
        Person doctor = Person.builder().id(1L).type(PersonType.DOCTOR).build();
        Person client = Person.builder().id(2L).type(PersonType.CLIENT).build();
        Animal animal = Animal.builder().id(1L).name("Name").type(AnimalType.DOG).build();
        Vaccine vaccine1 = Vaccine.builder().id(1L).name("Vaccine 1").description("Vaccine description 1").build();
        Vaccine vaccine2 = Vaccine.builder().id(2L).name("Vaccine 2").description("Vaccine description 2").build();
        Treatment treatment = Treatment.builder().doctor(doctor).client(client).animal(animal).date(new Date()).build();
        List<VaccineTreatment> vaccineTreatmentList = new ArrayList<>();
        vaccineTreatmentList.add(VaccineTreatment.builder().id(1L).quantity(2).vaccine(vaccine1).build());
        vaccineTreatmentList.add(VaccineTreatment.builder().id(2L).quantity(3).vaccine(vaccine2).build());
        treatment.setVaccineTreatmentList(vaccineTreatmentList);

        Mockito.when(dateUtils.toDate(Mockito.anyString())).thenReturn(new Date());
        Mockito.when(personRepository.findOne(1L)).thenReturn(doctor);
        Mockito.when(personRepository.findOne(2L)).thenReturn(client);
        Mockito.when(animalRepository.findOne(1L)).thenReturn(animal);
        Mockito.when(vaccineRepository.findOne(1L)).thenReturn(vaccine1);
        Mockito.when(vaccineRepository.findOne(2L)).thenReturn(vaccine2);
        Mockito.when(treatmentRepository.save(Mockito.any(Treatment.class))).thenReturn(treatment);
    }

    @Test
    public void testAdd() {
        treatmentService.add(buildTreatment());
    }

    @Test(expected = NotFoundException.class)
    public void testAddWhenDoctorNotFound() {
        Mockito.when(personRepository.findOne(1L)).thenReturn(null);
        treatmentService.add(buildTreatment());
    }

    @Test(expected = NotFoundException.class)
    public void testAddWhenClientNotFound() {
        Mockito.when(personRepository.findOne(2L)).thenReturn(null);
        treatmentService.add(buildTreatment());
    }

    @Test(expected = NotFoundException.class)
    public void testAddWhenAnimalNotFound() {
        Mockito.when(animalRepository.findOne(1L)).thenReturn(null);
        treatmentService.add(buildTreatment());
    }

    @Test(expected = NotFoundException.class)
    public void testAddWhenVaccineNotFound() {
        Mockito.when(vaccineRepository.findOne(1L)).thenReturn(null);
        treatmentService.add(buildTreatment());
    }

    private TreatmentDTO buildTreatment() {
        final TreatmentDTO treatmentDTO = new TreatmentDTO();
        treatmentDTO.setDoctor(PersonDTO.builder().id(1L).build());
        treatmentDTO.setClient(PersonDTO.builder().id(2L).build());
        treatmentDTO.setAnimal(AnimalDTO.builder().id(1L).build());
        treatmentDTO.setDate("2017-06-19 20:00:00");

        List<VaccineTreatmentDTO> vaccines = new ArrayList<>();
        vaccines.add(VaccineTreatmentDTO.builder().id(1L).quantity(2).build());
        vaccines.add(VaccineTreatmentDTO.builder().id(2L).quantity(3).build());

        treatmentDTO.setVaccines(vaccines);

        return treatmentDTO;
    }
}
