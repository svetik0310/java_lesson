package ru.zotkina.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper  extends HelperBase {
    public LoginHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void login(String username, String pass) {
        wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
        fillTextForm(By.name("username"),username);
        fillTextForm(By.name("password"),pass);
        click(By.cssSelector("input[value='Login'"));
    }

}
