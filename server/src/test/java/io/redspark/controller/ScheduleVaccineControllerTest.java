package io.redspark.controller;

import io.redspark.ApplicationTest;
import io.redspark.controller.dto.ScheduleVaccineDTO;
import io.redspark.domain.*;
import io.redspark.domain.type.AnimalType;
import io.redspark.domain.type.PersonType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public class ScheduleVaccineControllerTest extends ApplicationTest {

    private static final String USER_LOGIN = "Login test";

    private static final String USER_PASSWORD = "Password test";

    private static final String PERSON_NAME = "Person name test";

    private static final String PERSON_EMAIL = "Person email test";

    private static final String ANIMAL_NAME = "Animal name test";

    private static final String VACCINE_NAME = "Vaccine name test";

    private static final String VACCINE_DESCRIPTION = "Vaccine description test";

    @Test
    public void testGetById() {
        final User user = User.builder().login(USER_LOGIN).password(USER_PASSWORD).admin(false).build();
        final Person client = Person.builder().name(PERSON_NAME).email(PERSON_EMAIL).type(PersonType.DOCTOR).user(user).build();
        final Animal animal = Animal.builder().name(ANIMAL_NAME).type(AnimalType.RAT).build();
        final Vaccine vaccine = Vaccine.builder().name(VACCINE_NAME).description(VACCINE_DESCRIPTION).build();
        final ScheduleVaccine scheduleVaccine = ScheduleVaccine.builder().
                client(client)
                .animal(animal)
                .vaccine(vaccine)
                .date(new Date())
                .notified(false)
                .build();

        saveall(user, client, animal, vaccine, scheduleVaccine);

        final ScheduleVaccineDTO scheduleVaccineDTO = get("/" + ControllerConstants.VACCINE_SCHEDULE + "/" + scheduleVaccine.getId())
                .expectedStatus(HttpStatus.OK)
                .getResponse(ScheduleVaccineDTO.class)
                .getBody();

        Assert.assertFalse(scheduleVaccineDTO.getNotified());
        Assert.assertEquals(scheduleVaccineDTO.getAnimal().getType(), AnimalType.RAT.name());
        Assert.assertEquals(scheduleVaccineDTO.getAnimal().getName(), animal.getName());
        Assert.assertEquals(scheduleVaccineDTO.getVaccine().getName(), vaccine.getName());
        Assert.assertEquals(scheduleVaccineDTO.getVaccine().getDescription(), vaccine.getDescription());
        Assert.assertEquals(scheduleVaccineDTO.getClient().getType(), client.getType().name());
        Assert.assertEquals(scheduleVaccineDTO.getClient().getName(), client.getName());
        Assert.assertEquals(scheduleVaccineDTO.getClient().getEmail(), client.getEmail());
    }

    @Test
    public void testGetByIdWhenNotFound() {
        get("/" + ControllerConstants.VACCINE_SCHEDULE + "/555").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    @Test
    public void testListAll() {
        addScheduleVaccines();

        final ResponseEntity<List> animals = get("/" + ControllerConstants.VACCINE_SCHEDULE + "/list")
                .expectedStatus(HttpStatus.OK)
                .getResponse(List.class);

        Assert.assertEquals(animals.getBody().size(), 6);
    }

    @Test
    public void testListAllWhenNotFound() {
        get("/" + ControllerConstants.VACCINE_SCHEDULE + "/list").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    private void addScheduleVaccines() {
        final User user = User.builder().login(USER_LOGIN).password(USER_PASSWORD).admin(false).build();
        final Person client = Person.builder().name(PERSON_NAME).email(PERSON_EMAIL).type(PersonType.DOCTOR).user(user).build();
        final Animal animal = Animal.builder().name(ANIMAL_NAME).type(AnimalType.RAT).build();
        final Vaccine vaccine = Vaccine.builder().name(VACCINE_NAME).description(VACCINE_DESCRIPTION).build();
        add(user, client, animal, vaccine);
        for (int i = 0; i < 6; i++) {
            final ScheduleVaccine scheduleVaccine = ScheduleVaccine.builder().
                    client(client)
                    .animal(animal)
                    .vaccine(vaccine)
                    .date(new Date())
                    .notified(false)
                    .build();

            add(scheduleVaccine);
        }
        saveall();
    }
}