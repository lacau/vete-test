package io.redspark.controller;

import io.redspark.ApplicationTest;
import io.redspark.controller.dto.TreatmentDTO;
import io.redspark.domain.*;
import io.redspark.domain.type.AnimalType;
import io.redspark.domain.type.PersonType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreatmentControllerTest extends ApplicationTest {

    @Test
    public void testGetById() {
        final User user1 = User.builder().login("login_doc").password("password").admin(false).build();
        final User user2 = User.builder().login("login_cli").password("password").admin(false).build();
        final Person doctor = Person.builder().name("Doctor").email("Doctor email").type(PersonType.DOCTOR).user(user1).build();
        final Person client = Person.builder().name("Client").email("Client email").type(PersonType.CLIENT).user(user2).build();
        final Animal animal = Animal.builder().name("Animal").type(AnimalType.DOG).build();
        final Vaccine vaccine1 = Vaccine.builder().name("Vaccine 1").description("Vaccine description 1").build();
        final Vaccine vaccine2 = Vaccine.builder().name("Vaccine 2").description("Vaccine description 2").build();
        final Treatment treatment = Treatment.builder().doctor(doctor).client(client).animal(animal).date(new Date()).build();
        final List<VaccineTreatment> vaccineTreatmentList = new ArrayList<>();
        vaccineTreatmentList.add(VaccineTreatment.builder().vaccine(vaccine1).quantity(2).treatment(treatment).build());
        vaccineTreatmentList.add(VaccineTreatment.builder().vaccine(vaccine2).quantity(3).treatment(treatment).build());
        treatment.setVaccineTreatmentList(vaccineTreatmentList);

        saveall(user1, user2, doctor, client, animal, vaccine1, vaccine2, treatment);

        final TreatmentDTO treatmentDTO = get("/" + ControllerConstants.TREATMENT + "/" + treatment.getId())
                .expectedStatus(HttpStatus.OK)
                .getResponse(TreatmentDTO.class).getBody();

        Assert.assertEquals(treatmentDTO.getDoctor().getId(), doctor.getId());
        Assert.assertEquals(treatmentDTO.getClient().getId(), client.getId());
        Assert.assertEquals(treatmentDTO.getAnimal().getId(), animal.getId());
        Assert.assertEquals(treatmentDTO.getVaccines().size(), 2);
    }

    @Test
    public void testGetByIdWhenNotFound() {
        get("/" + ControllerConstants.TREATMENT + "/555").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    @Test
    public void testListAll() {
        addTreatments();

        final ResponseEntity<List> animals = get("/" + ControllerConstants.TREATMENT + "/list")
                .expectedStatus(HttpStatus.OK)
                .getResponse(List.class);

        Assert.assertEquals(animals.getBody().size(), 3);
    }

    @Test
    public void testListAllWhenNotFound() {
        get("/" + ControllerConstants.TREATMENT + "/list").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    @Test
    public void testListByDoctorId() {
        final long doctorId = addTreatments();

        final ResponseEntity<List> animals = get("/" + ControllerConstants.TREATMENT + "/list/doctor/" + doctorId)
                .expectedStatus(HttpStatus.OK)
                .getResponse(List.class);

        Assert.assertEquals(animals.getBody().size(), 2);
    }

    @Test
    public void testListByDoctorIdWhenNotFound() {
        get("/" + ControllerConstants.TREATMENT + "/list/doctor/555").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }

    private long addTreatments() {
        final User user1 = User.builder().login("login_doc1").password("password").admin(false).build();
        final User user2 = User.builder().login("login_cli").password("password").admin(false).build();
        final User user3 = User.builder().login("login_doc2").password("password").admin(false).build();
        final Person doctor1 = Person.builder().name("Doctor 1").email("Doctor email").type(PersonType.DOCTOR).user(user1).build();
        final Person client = Person.builder().name("Client").email("Client email").type(PersonType.CLIENT).user(user2).build();
        final Person doctor2 = Person.builder().name("Doctor 2").email("Doctor email").type(PersonType.DOCTOR).user(user3).build();
        final Animal animal = Animal.builder().name("Animal").type(AnimalType.DOG).build();
        final Vaccine vaccine1 = Vaccine.builder().name("Vaccine 1").description("Vaccine description 1").build();
        final Vaccine vaccine2 = Vaccine.builder().name("Vaccine 2").description("Vaccine description 2").build();
        final Treatment treatment1 = Treatment.builder().doctor(doctor1).client(client).animal(animal).date(new Date()).build();
        final List<VaccineTreatment> vaccineTreatmentList1 = new ArrayList<>();
        vaccineTreatmentList1.add(VaccineTreatment.builder().vaccine(vaccine1).quantity(2).treatment(treatment1).build());
        treatment1.setVaccineTreatmentList(vaccineTreatmentList1);
        final Treatment treatment2 = Treatment.builder().doctor(doctor1).client(client).animal(animal).date(new Date()).build();
        final List<VaccineTreatment> vaccineTreatmentList2 = new ArrayList<>();
        vaccineTreatmentList2.add(VaccineTreatment.builder().vaccine(vaccine2).quantity(3).treatment(treatment2).build());
        treatment2.setVaccineTreatmentList(vaccineTreatmentList2);
        final Treatment treatment3 = Treatment.builder().doctor(doctor2).client(client).animal(animal).date(new Date()).build();
        final List<VaccineTreatment> vaccineTreatmentList3 = new ArrayList<>();
        vaccineTreatmentList3.add(VaccineTreatment.builder().vaccine(vaccine1).quantity(5).treatment(treatment3).build());
        vaccineTreatmentList3.add(VaccineTreatment.builder().vaccine(vaccine2).quantity(7).treatment(treatment3).build());
        treatment3.setVaccineTreatmentList(vaccineTreatmentList3);

        saveall(user1, user2, user3, doctor1, doctor2, client, animal, vaccine1, vaccine2, treatment1, treatment2, treatment3);
        return doctor1.getId();
    }
}