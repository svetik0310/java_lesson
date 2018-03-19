package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        fillTextForm(By.name("user"),username);
        fillTextForm(By.name("pass"),password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
