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

    public void changePass(String username, String pass) {
        wd.get(app.getProperty("web.baseUrl")+"/manage_user_page.php");
        //fillTextForm(By.name("username"),username);
        //fillTextForm(By.name("password"),pass);
        //click(By.cssSelector("input[value='Login'"));

        //выбрать из бд всех пользователей, из них любого не админа
        //перейти на страницу пользователя http://localhost/mantisbt-1.2.19/manage_user_edit_page.php?user_id=2
        //и нажать резет пасс, перехват почты , перейти по ссылке, сменить пароль, проверить что можем зайти
    }

}
