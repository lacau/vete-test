package io.redspark.controller;

import io.redspark.controller.converter.VaccineConverter;
import io.redspark.controller.dto.VaccineDTO;
import io.redspark.domain.Vaccine;
import io.redspark.exception.WebException;
import io.redspark.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.redspark.controller.ControllerConstants.VACCINE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = VACCINE)
public class VaccineController {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private VaccineConverter vaccineConverter;

    @RequestMapping(value = "/{id}", method = GET)
    public VaccineDTO get(@PathVariable("id") final Long id) {
        final Vaccine vaccine = vaccineRepository.findOne(id);

        if (vaccine == null) {
            throw new WebException(HttpStatus.NOT_FOUND, "vaccine.not.found");
        }

        return new VaccineDTO(vaccine);
    }

    @RequestMapping(value = "/list", method = GET)
    public List<VaccineDTO> list() {
        final List<Vaccine> vaccineList = vaccineRepository.findAll();

        if (vaccineList == null) {
            throw new WebException(HttpStatus.NOT_FOUND, "vaccine.not.found");
        }

        return vaccineConverter.convert(vaccineList);
    }

    @RequestMapping(value = "/list/{name}", method = GET)
    public List<VaccineDTO> listByName(@PathVariable("name") final String name) {
        final List<Vaccine> vaccineList = vaccineRepository.search(name);

        if (vaccineList == null) {
            throw new WebException(HttpStatus.NOT_FOUND, "vaccine.not.found");
        }

        return vaccineConverter.convert(vaccineList);
    }
}
