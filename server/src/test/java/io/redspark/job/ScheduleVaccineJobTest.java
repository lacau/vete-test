package io.redspark.job;

import io.redspark.domain.Person;
import io.redspark.domain.ScheduleVaccine;
import io.redspark.repository.ScheduleVaccineRepository;
import io.redspark.service.ScheduleVaccineService;
import io.redspark.service.SmtpMailSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.mail.MessagingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lacau on 19/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleVaccineJobTest {

    @InjectMocks
    ScheduleVaccineJob scheduleVaccineJob;

    @Mock
    private ScheduleVaccineService scheduleVaccineService;

    @Mock
    private ScheduleVaccineRepository scheduleVaccineRepository;

    @Mock
    private SmtpMailSender smtpMailSender;

    @Before
    public void init() throws MessagingException {
        ReflectionTestUtils.setField(scheduleVaccineJob, "daysBeforeNotify", 7);
        ReflectionTestUtils.setField(scheduleVaccineJob, "emailSubject", "");
        ReflectionTestUtils.setField(scheduleVaccineJob, "emailBody", "");
        final List<ScheduleVaccine> scheduleVaccines = new ArrayList<>();
        scheduleVaccines.add(buildScheduleVaccine(-1));
        scheduleVaccines.add(buildScheduleVaccine(3));

        Mockito.when(scheduleVaccineService.searchScheduledVaccine(Mockito.anyInt(), Mockito.anyBoolean())).thenReturn(scheduleVaccines);
        Mockito.doNothing().when(smtpMailSender).send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.when(scheduleVaccineRepository.saveAndFlush(Mockito.any(ScheduleVaccine.class))).thenReturn(null);
    }

    @Test
    public void testNotifyScheduleVaccineJob() throws MessagingException {
        scheduleVaccineJob.notifyScheduleVaccineJob();

        Mockito.verify(smtpMailSender, Mockito.times(2))
                .send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(scheduleVaccineRepository, Mockito.times(2))
                .saveAndFlush(Mockito.any(ScheduleVaccine.class));
    }

    @Test
    public void testNotifyScheduleVaccineJobNoNotification() throws MessagingException {
        Mockito.when(scheduleVaccineService.searchScheduledVaccine(Mockito.anyInt(), Mockito.anyBoolean())).thenReturn(new ArrayList<>());
        scheduleVaccineJob.notifyScheduleVaccineJob();

        Mockito.verify(smtpMailSender, Mockito.times(0))
                .send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testNotifyScheduleVaccineJobEmailFail() throws MessagingException {
        Mockito.doThrow(new MessagingException()).when(smtpMailSender).send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        scheduleVaccineJob.notifyScheduleVaccineJob();

        Mockito.verify(smtpMailSender, Mockito.times(2))
                .send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    private ScheduleVaccine buildScheduleVaccine(int plusDays) {
        final Instant date = Instant.now().plus(plusDays, ChronoUnit.DAYS);

        return ScheduleVaccine.builder()
                .client(Person.builder().email("").build())
                .date(Date.from(date))
                .notified(false)
                .build();
    }
}
