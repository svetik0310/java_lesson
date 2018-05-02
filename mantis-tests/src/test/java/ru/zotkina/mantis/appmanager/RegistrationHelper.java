package ru.zotkina.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager applicationManager) {
        this.app=applicationManager;
        wd=applicationManager.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
    }
}
