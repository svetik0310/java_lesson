package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.zotkina.addressbook.model.ContactData;

import java.util.NoSuchElementException;

public class ContractHelper extends HelperBase{

    public ContractHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContractCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContractForm(ContactData contactData, boolean creation) {
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
        if (creation)
        {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else
        {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            click(By.name("selected[]"));
        }
    }

    public void deleteContract() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        allertAccept();
    }

    public void editSelectedContract()
    {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContractUpdate() {
        click(By.name("update"));
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contactData, boolean b) {
        initContactCreation();
        fillContractForm(new ContactData("Вася", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "test1A"), true);
        submitContractCreation();
    }

    public int getContractCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
