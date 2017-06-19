package io.redspark.service;

import io.redspark.domain.ScheduleVaccine;
import io.redspark.repository.ScheduleVaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by lacau on 18/06/17.
 */
@Service
public class ScheduleVaccineServiceImpl implements ScheduleVaccineService {

    @Autowired
    private ScheduleVaccineRepository scheduleVaccineRepository;

    @Override
    public List<ScheduleVaccine> searchScheduledVaccine(Integer daysTillVaccine, Boolean notified) {
        final Instant to = Instant.now().plus(daysTillVaccine, ChronoUnit.DAYS);

        return scheduleVaccineRepository.searchBeforeDate(Date.from(to), notified);
    }
}
