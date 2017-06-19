package io.redspark.controller;

import io.redspark.controller.dto.ScheduleVaccineDTO;
import io.redspark.domain.ScheduleVaccine;
import io.redspark.exception.WebException;
import io.redspark.repository.ScheduleVaccineRepository;
import io.redspark.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.redspark.controller.ControllerConstants.SCHEDULE;
import static io.redspark.controller.ControllerConstants.VACCINE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = VACCINE + "/" + SCHEDULE)
public class ScheduleVaccineController {

    @Autowired
    private ScheduleVaccineRepository scheduleVaccineRepository;

    private MapperUtils<ScheduleVaccine, ScheduleVaccineDTO> vaccineConverter = new MapperUtils<>(ScheduleVaccine.class, ScheduleVaccineDTO.class);

    @RequestMapping(value = "/{id}", method = GET)
    public ScheduleVaccineDTO get(@PathVariable("id") final Long id) {
        final ScheduleVaccine vaccine = scheduleVaccineRepository.findOne(id);

        if (vaccine == null) {
            throw new WebException(HttpStatus.NOT_FOUND, "schedule.vaccine.not.found");
        }

        return new ScheduleVaccineDTO(vaccine);
    }

    @RequestMapping(value = "/list", method = GET)
    public List<ScheduleVaccineDTO> list() {
        final List<ScheduleVaccine> vaccineList = scheduleVaccineRepository.findAll();

        if (vaccineList == null || vaccineList.isEmpty()) {
            throw new WebException(HttpStatus.NOT_FOUND, "schedule.vaccine.not.found");
        }

        return vaccineConverter.toDTO(vaccineList);
    }
}
