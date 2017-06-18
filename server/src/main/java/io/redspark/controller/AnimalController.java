package io.redspark.controller;

import io.redspark.controller.dto.AnimalDTO;
import io.redspark.domain.Animal;
import io.redspark.exception.WebException;
import io.redspark.repository.AnimalRepository;
import io.redspark.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.redspark.controller.ControllerConstants.ANIMAL;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = ANIMAL)
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    private MapperUtils<Animal, AnimalDTO> animalConverter = new MapperUtils<>(Animal.class, AnimalDTO.class);

    @RequestMapping(value = "/{id}", method = GET)
    public AnimalDTO get(@PathVariable("id") final Long id) {
        final Animal animal = animalRepository.findOne(id);

        if (animal == null) {
            throw new WebException(HttpStatus.NOT_FOUND, "animal.not.found");
        }

        return new AnimalDTO(animal);
    }

    @RequestMapping(value = "/list", method = GET)
    public List<AnimalDTO> list() {
        final List<Animal> animalList = animalRepository.findAll();

        if (animalList == null || animalList.isEmpty()) {
            throw new WebException(HttpStatus.NOT_FOUND, "animal.not.found");
        }

        return animalConverter.toDTO(animalList);
    }

    @RequestMapping(value = "/list/{name}", method = GET)
    public List<AnimalDTO> listByName(@PathVariable("name") final String name) {
        final List<Animal> animalList = animalRepository.search(name);

        if (animalList == null || animalList.isEmpty()) {
            throw new WebException(HttpStatus.NOT_FOUND, "vaccine.not.found");
        }

        return animalConverter.toDTO(animalList);
    }
}
