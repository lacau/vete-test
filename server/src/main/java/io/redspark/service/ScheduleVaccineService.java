package io.redspark.service;

import io.redspark.controller.dto.ScheduleVaccineDTO;
import io.redspark.domain.ScheduleVaccine;

import java.util.List;

/**
 * Created by lacau on 18/06/17.
 */
public interface ScheduleVaccineService {

    List<ScheduleVaccine> searchScheduledVaccine(Integer daysTillVaccine, Boolean notified);

    ScheduleVaccineDTO scheduleVaccine(ScheduleVaccineDTO scheduleVaccineDTO);
}
