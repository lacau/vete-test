package io.redspark.job;

import io.redspark.domain.ScheduleVaccine;
import io.redspark.repository.ScheduleVaccineRepository;
import io.redspark.service.ScheduleVaccineService;
import io.redspark.service.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lacau on 18/06/17.
 */
@Component
public class ScheduleVaccineJob {

    @Value("${mail.schedule.vaccine.days.before.notify}")
    private Integer daysBeforeNotify;

    @Value("${mail.schedule.vaccine.subject}")
    private String emailSubject;

    @Value("${mail.schedule.vaccine.body}")
    private String emailBody;

    @Autowired
    private ScheduleVaccineService scheduleVaccineService;

    @Autowired
    private ScheduleVaccineRepository scheduleVaccineRepository;

    @Autowired
    private SmtpMailSender smtpMailSender;

    //@Scheduled(cron = "0 0 12 1/1 * ? *") // 12 hours interval forever
    @Scheduled(fixedDelay = 60000) // every 30 seconds
    public void notifyScheduleVaccineJob() {
        System.out.println("ScheduleVaccineJob - START");
        final List<ScheduleVaccine> scheduleVaccines = scheduleVaccineService.searchScheduledVaccine(daysBeforeNotify, false);

        if (!scheduleVaccines.isEmpty()) {
            for (ScheduleVaccine scheduleVaccine : scheduleVaccines) {
                final long diff = new Date().getTime() - scheduleVaccine.getDate().getTime();
                long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                final String email = scheduleVaccine.getClient().getEmail();

                try {
                    smtpMailSender.send(email, emailSubject, emailBody.replace("$1", String.valueOf(days)));

                    scheduleVaccine.setNotified(true);
                    scheduleVaccineRepository.saveAndFlush(scheduleVaccine);
                } catch (MessagingException e) {
                    // Log something preferable with slf4j
                }
            }
            System.out.println("ScheduleVaccineJob - All schedule vaccines notified!");
        } else {
            System.out.println("ScheduleVaccineJob - Nothing to notify!");
        }
        System.out.println("ScheduleVaccineJob - END");
    }
}
