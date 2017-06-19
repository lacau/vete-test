package io.redspark.service;

import io.redspark.controller.dto.AnimalDTO;
import io.redspark.controller.dto.PersonDTO;
import io.redspark.controller.dto.ScheduleVaccineDTO;
import io.redspark.controller.dto.VaccineDTO;
import io.redspark.domain.Animal;
import io.redspark.domain.Person;
import io.redspark.domain.ScheduleVaccine;
import io.redspark.domain.Vaccine;
import io.redspark.domain.type.AnimalType;
import io.redspark.domain.type.PersonType;
import io.redspark.exception.NotFoundException;
import io.redspark.repository.AnimalRepository;
import io.redspark.repository.PersonRepository;
import io.redspark.repository.ScheduleVaccineRepository;
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

import java.util.Date;

/**
 * Created by lacau on 18/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleVaccineServiceImplTest {

    @InjectMocks
    ScheduleVaccineServiceImpl scheduleVaccineService;

    @Mock
    ScheduleVaccineRepository scheduleVaccineRepository;

    @Mock
    VaccineRepository vaccineRepository;

    @Mock
    PersonRepository personRepository;

    @Mock
    AnimalRepository animalRepository;

    @Mock
    DateUtils dateUtils;

    @Spy
    MapperUtils scheduleVaccineConverter = new MapperUtils(ScheduleVaccine.class, ScheduleVaccineDTO.class);

    @Before
    public void init() {
        Vaccine vaccine1 = Vaccine.builder().id(1L).name("Vaccine 1").description("Vaccine description 1").build();
        Person client = Person.builder().id(1L).type(PersonType.CLIENT).build();
        Animal animal = Animal.builder().id(1L).name("Name").type(AnimalType.DOG).build();
        ScheduleVaccine scheduleVaccine = ScheduleVaccine.builder().vaccine(vaccine1).client(client).animal(animal).date(new Date()).build();

        Mockito.when(dateUtils.toDate(Mockito.anyString())).thenReturn(new Date());
        Mockito.when(vaccineRepository.findOne(1L)).thenReturn(vaccine1);
        Mockito.when(personRepository.findOne(1L)).thenReturn(client);
        Mockito.when(animalRepository.findOne(1L)).thenReturn(animal);
        Mockito.when(scheduleVaccineRepository.save(Mockito.any(ScheduleVaccine.class))).thenReturn(scheduleVaccine);
    }

    @Test
    public void testScheduleVaccine() {
        scheduleVaccineService.scheduleVaccine(buildScheduleVaccine());
    }

    @Test(expected = NotFoundException.class)
    public void testAddWhenVaccineNotFound() {
        Mockito.when(vaccineRepository.findOne(1L)).thenReturn(null);
        scheduleVaccineService.scheduleVaccine(buildScheduleVaccine());
    }

    @Test(expected = NotFoundException.class)
    public void testAddWhenClientNotFound() {
        Mockito.when(personRepository.findOne(1L)).thenReturn(null);
        scheduleVaccineService.scheduleVaccine(buildScheduleVaccine());
    }

    @Test(expected = NotFoundException.class)
    public void testAddWhenAnimalNotFound() {
        Mockito.when(animalRepository.findOne(1L)).thenReturn(null);
        scheduleVaccineService.scheduleVaccine(buildScheduleVaccine());
    }

    private ScheduleVaccineDTO buildScheduleVaccine() {
        final ScheduleVaccineDTO scheduleVaccineDTO = new ScheduleVaccineDTO();
        scheduleVaccineDTO.setVaccine(VaccineDTO.builder().id(1L).name("Vaccine 1").description("Vaccine description 1").build());
        scheduleVaccineDTO.setClient(PersonDTO.builder().id(1L).type(PersonType.CLIENT.name()).build());
        scheduleVaccineDTO.setAnimal(AnimalDTO.builder().id(1L).name("Name").type(AnimalType.DOG.name()).build());
        scheduleVaccineDTO.setDate("2017-06-19 20:00:00");

        return scheduleVaccineDTO;
    }
}
