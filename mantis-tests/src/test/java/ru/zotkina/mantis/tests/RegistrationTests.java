package ru.zotkina.mantis.tests;

import org.testng.annotations.Test;
import ru.zotkina.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{
    @Test
    public void testRegistration() throws IOException {
        HttpSession session=app.newSession();
        app.registration().start("user1","user1@localhost.localdomain");
    }
}
