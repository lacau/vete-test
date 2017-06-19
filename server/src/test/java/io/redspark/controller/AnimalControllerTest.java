package io.redspark.controller;

import io.redspark.ApplicationTest;
import io.redspark.controller.dto.AnimalDTO;
import io.redspark.domain.Animal;
import io.redspark.domain.type.AnimalType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AnimalControllerTest extends ApplicationTest {

    private static final String ANIMAL_NAME = "Dog test";

    private static final String ANIMAL_COMMENTS = "Very aggressive.";

    @Test
    public void testGetById() {
        final Animal animal = Animal.builder().name(ANIMAL_NAME).comments(ANIMAL_COMMENTS).type(AnimalType.DOG).build();
        saveall(animal);

        final ResponseEntity<AnimalDTO> animalDTO = get("/" + ControllerConstants.ANIMAL + "/" + animal.getId())
                .expectedStatus(HttpStatus.OK)
                .getResponse(AnimalDTO.class);

        Assert.assertEquals(animalDTO.getBody().getName(), animal.getName());
        Assert.assertEquals(animalDTO.getBody().getComments(), animal.getComments());
        Assert.assertEquals(animalDTO.getBody().getType(), animal.getType().name());
    }

    @Test
    public void testGetByIdWhenNotFound() {
        get("/" + ControllerConstants.ANIMAL + "/555").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    @Test
    public void testListAll() {
        addDogsAndRats();

        final ResponseEntity<List> animals = get("/" + ControllerConstants.ANIMAL + "/list")
                .expectedStatus(HttpStatus.OK)
                .getResponse(List.class);

        Assert.assertEquals(animals.getBody().size(), 6);
    }

    @Test
    public void testListAllWhenNotFound() {
        get("/" + ControllerConstants.ANIMAL + "/list").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    @Test
    public void testListByName() {
        addDogsAndRats();

        final ResponseEntity<List> animals = get("/" + ControllerConstants.ANIMAL + "/list/0")
                .expectedStatus(HttpStatus.OK)
                .getResponse(List.class);

        Assert.assertEquals(animals.getBody().size(), 2);
    }

    @Test
    public void testListByNameWhenNotFound() {
        get("/" + ControllerConstants.ANIMAL + "/list/ddd").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    private void addDogsAndRats() {
        // Add dogs
        for (int i = 0; i < 3; i++) {
            add(Animal.builder().name(ANIMAL_NAME + i).comments(ANIMAL_COMMENTS + i).type(AnimalType.DOG).build());
        }
        // Add rats
        for (int i = 0; i < 3; i++) {
            add(Animal.builder().name(ANIMAL_NAME + i).comments(ANIMAL_COMMENTS + i).type(AnimalType.RAT).build());
        }
        saveall();
    }
}