package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private NavigationHelper navigationHelper;
    private ContractHelper contractHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    FirefoxDriver wd;

    public void init() {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        contractHelper = new ContractHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper= new SessionHelper(wd);
        sessionHelper.login("Admin", "secret");
    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public ContractHelper getContractHelper() {
        return contractHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
