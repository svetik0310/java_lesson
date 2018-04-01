package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.zotkina.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
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

    public void edit(ContactData contact, int index) {
        editSelectedContract(index);
        fillContractForm(contact, false);
        submitContractUpdate();
    }

    public void delete(int index) {
        selectContact(index);
        deleteContract();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContractForm(contact, true);
        submitContractCreation();
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContract() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        allertAccept();
    }

    public void editSelectedContract(int index)
    {
        wd.findElements(By.name("entry")).get(index).findElement(By.xpath("./td[8]")).click();
    }

    public void submitContractUpdate() {
        click(By.name("update"));
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contactData, boolean b) {
        initContactCreation();
        fillContractForm(contactData, true);
        submitContractCreation();
    }

    public int getContractCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contracts= new ArrayList<ContactData>();
        List<WebElement> elements= wd.findElements(By.name("entry"));

        for(WebElement element: elements)
        {
            WebElement elementsTdid= element.findElement(By.xpath("./td[1]"));
            int id = Integer.parseInt(elementsTdid.findElement(By.tagName("input")).getAttribute("value"));

            WebElement elementsTd= element.findElement(By.xpath("./td[2]"));
            String lastname= elementsTd.getText();

            WebElement elementsTd1= element.findElement(By.xpath("./td[3]"));
            String firstname= elementsTd1.getText();

            ContactData contract = new ContactData(id, firstname,null,lastname,null,null,null,null,null,null,null,null,null,null,null,null,null);
            contracts.add(contract);
        }
        return  contracts;
    }
}
