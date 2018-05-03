package ru.zotkina.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.zotkina.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        //Закоментировала чтобы каждый раз не менять конфиг
       // app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        //Закоментировала чтобы каждый раз не менять конфиг
        //app.ftp().restore("config_inc.php.bak","config_inc.php");
        app.stop();
    }

}