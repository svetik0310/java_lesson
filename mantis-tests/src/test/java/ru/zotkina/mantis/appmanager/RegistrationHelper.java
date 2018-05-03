package ru.zotkina.mantis.appmanager;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.zotkina.mantis.model.UserData;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void start(String username, String email) {
       wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
        fillTextForm(By.name("username"),username);
        fillTextForm(By.name("email"),email);
        click(By.cssSelector("input[value='Signup'"));
    }

    public void finish(String confirmLink, String s) {
        wd.get(confirmLink);
        fillTextForm(By.name("password"),s);
        fillTextForm(By.name("password_confirm"),s);
        click(By.cssSelector("input[value='Update User'"));
    }

    public void changePass(UserData user) {
        wd.get(app.getProperty("web.baseUrl")+String.format("/manage_user_edit_page.php?user_id=%s",user.getId()));
        click(By.cssSelector("input[value='Reset Password'"));
    }
}
