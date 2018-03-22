package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private NavigationHelper navigationHelper;
    private ContractHelper contractHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    WebDriver wd;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser=browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        }
        else if (browser.equals(BrowserType.CHROME))
        {
            wd = new ChromeDriver();
        }
        else if (browser.equals(BrowserType.IE))
        {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
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
