package ru.zotkina.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.zotkina.mantis.model.MailMessage;
import ru.zotkina.mantis.model.UserData;
import ru.zotkina.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        Long now=System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain",now);
        String username = "user"+now;
        String password = "000";
        app.login().login("administrator","root");

        UserData userChange=new UserData().withId(Integer.parseInt("2")).withUsername("user1");
         app.registration().changePass(userChange);
            List<MailMessage> mailM = app.mail().waitForMail(2, 10000);
            String confirmLink = findConfirmationLink(mailM, email);
            app.registration().finish(confirmLink, password);
            assertTrue(app.newSession().login(userChange.getUsername(), "000"));

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