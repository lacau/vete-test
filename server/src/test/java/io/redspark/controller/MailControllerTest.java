package io.redspark.controller;

import io.redspark.ApplicationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.subethamail.wiser.Wiser;

public class MailControllerTest extends ApplicationTest {

    private Wiser wiser;

    @Before
    public void config() {
        wiser = new Wiser(10002);
        wiser.start();
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }

    @Test
    public void sendMailTest() {
        get("/send-mail").queryParam("to", "llacau@gmail.com").expectedStatus(HttpStatus.OK).getResponse();
    }
}