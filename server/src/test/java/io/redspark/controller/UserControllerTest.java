package io.redspark.controller;

import io.redspark.ApplicationTest;
import io.redspark.controller.dto.PersonDTO;
import io.redspark.controller.dto.UserDTO;
import io.redspark.domain.Person;
import io.redspark.domain.User;
import io.redspark.domain.type.PersonType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest extends ApplicationTest {

    private static final String USER_LOGIN = "Login test";

    private static final String USER_PASSWORD = "Password test";

    private static final String PERSON_NAME = "Person name test";

    private static final String PERSON_EMAIL = "Person email test";

    @Test
    public void testGetById() {
        final User user = User.builder().login(USER_LOGIN).password(USER_PASSWORD).admin(false).build();
        final Person person = Person.builder().name(PERSON_NAME).email(PERSON_EMAIL).type(PersonType.DOCTOR).user(user).build();
        saveall(person, user);

        final ResponseEntity<UserDTO> userDTO = get("/" + ControllerConstants.USER + "/" + user.getId())
                .expectedStatus(HttpStatus.OK)
                .getResponse(UserDTO.class);

        final PersonDTO personDTO = userDTO.getBody().getPerson();

        Assert.assertFalse(userDTO.getBody().getAdmin());
        Assert.assertEquals(personDTO.getName(), PERSON_NAME);
        Assert.assertEquals(personDTO.getEmail(), PERSON_EMAIL);
        Assert.assertEquals(personDTO.getType(), PersonType.DOCTOR.name());
    }

    @Test
    public void testGetByIdWhenNotFound() {
        get("/" + ControllerConstants.USER + "/555").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
    }
}