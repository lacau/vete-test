package io.redspark.controller;

import io.redspark.ApplicationTest;
import io.redspark.controller.dto.VaccineDTO;
import io.redspark.domain.Vaccine;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class VaccineControllerTest extends ApplicationTest {

    private static final String VACCINE_NAME = "Vaccine test";

    private static final String VACCINE_DESCRIPTION = "Vaccine test description";

    @Test
    public void testGetById() {
        final Vaccine vaccine = Vaccine.builder().name(VACCINE_NAME).description(VACCINE_DESCRIPTION).build();
        saveall(vaccine);

        final ResponseEntity<VaccineDTO> vaccineDTO = get("/" + ControllerConstants.VACCINE + "/" + vaccine.getId())
                .expectedStatus(HttpStatus.OK)
                .getResponse(VaccineDTO.class);

        Assert.assertEquals(vaccineDTO.getBody().getName(), vaccine.getName());
        Assert.assertEquals(vaccineDTO.getBody().getDescription(), vaccine.getDescription());
    }

    @Test
    public void testGetByIdWhenNotFound() {
        get("/" + ControllerConstants.VACCINE + "/555").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    @Test
    public void testListAll() {
        addVaccines();

        final ResponseEntity<List> animals = get("/" + ControllerConstants.VACCINE + "/list")
                .expectedStatus(HttpStatus.OK)
                .getResponse(List.class);

        Assert.assertEquals(animals.getBody().size(), 6);
    }

    @Test
    public void testListAllWhenNotFound() {
        get("/" + ControllerConstants.VACCINE + "/list").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    @Test
    public void testListByName() {
        addVaccines();

        final ResponseEntity<List> animals = get("/" + ControllerConstants.VACCINE + "/list/3")
                .expectedStatus(HttpStatus.OK)
                .getResponse(List.class);

        Assert.assertEquals(animals.getBody().size(), 1);
    }

    @Test
    public void testListByNameWhenNotFound() {
        get("/" + ControllerConstants.VACCINE + "/list/ddd").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    private void addVaccines() {
        for (int i = 0; i < 6; i++) {
            add(Vaccine.builder().name(VACCINE_NAME + i).description(VACCINE_DESCRIPTION + i).build());
        }
        saveall();
    }
}