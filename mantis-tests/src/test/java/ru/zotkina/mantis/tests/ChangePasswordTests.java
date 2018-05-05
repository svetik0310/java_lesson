package ru.zotkina.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.zotkina.mantis.model.MailMessage;
import ru.zotkina.mantis.model.UserData;
import ru.zotkina.mantis.model.Users;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String password = "000";
        Users u = app.db().users();
        u.removeIf(userData -> userData.getUsername().equals("administrator"));
        UserData change= u.iterator().next();
        app.login().login("administrator","root");

        app.registration().changePass(change);
            List<MailMessage> mailM = app.mail().waitForMail(1, 10000);
            String confirmLink = findConfirmationLink(mailM, change.getEmail());
            app.registration().finish(confirmLink, password);
            assertTrue(app.newSession().login(change.getUsername(), password));
    }

        private String findConfirmationLink(List<MailMessage> mailM, String email)
        {
        MailMessage mail = mailM.stream().filter((m)->m.to.equals(email)).findFirst().get();
        VerbalExpression v= VerbalExpression.regex().find("http").nonSpace().oneOrMore().build();
        return v.getText(mail.text);
    }



    @AfterMethod(alwaysRun = true)
    public void stopMailServer()
    {
        app.mail().stop();
    }
}