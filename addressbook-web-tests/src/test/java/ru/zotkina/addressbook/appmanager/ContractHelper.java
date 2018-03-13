package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.zotkina.addressbook.model.ContactData;

public class ContractHelper extends HelperBase{

    public ContractHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContractCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContractForm(ContactData contactData) {
        fillTextForm(By.name("firstname"),contactData.getFirstname());
        fillTextForm(By.name("middlename"),contactData.getMiddlename());
        fillTextForm(By.name("lastname"),contactData.getLastname());
        fillTextForm(By.name("nickname"),contactData.getNickname());
        fillTextForm(By.name("title"),contactData.getTitle());
        fillTextForm(By.name("company"),contactData.getCompany());
        fillTextForm(By.name("address"),contactData.getAddress());
        fillTextForm(By.name("home"),contactData.getHome());
        fillTextForm(By.name("mobile"),contactData.getMobile());
        fillTextForm(By.name("work"),contactData.getWork());
        fillTextForm(By.name("fax"),contactData.getFax());
        fillTextForm(By.name("email"),contactData.getEmail());
        fillTextForm(By.name("email2"),contactData.getEmail2());
        fillTextForm(By.name("email3"),contactData.getEmail3());
        fillTextForm(By.name("homepage"),contactData.getHomepage());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }
}
