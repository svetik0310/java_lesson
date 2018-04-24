package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;
import ru.zotkina.addressbook.model.GroupData;

import java.util.*;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
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
        attachFile(By.name("photo"),contactData.getPhoto());
        if (creation)
        {
            if (contactData.getGroups().size()>0) {
                Assert.assertTrue(contactData.getGroups().size()==1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getGroupname()
                );
            }
        } else
        {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void edit(ContactData contact) {
        editSelectedContractById(contact.getIdcontact());
        fillContractForm(contact, false);
        contractCash=null;
        submitContractUpdate();
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getIdcontact());
        WebElement select = wd.findElement(By.name("to_group"));
        Select selectall=new Select(select);
        selectall.selectByValue(Integer.toString(group.getIdgroup()));
        wd.findElement(By.name("add")).click();
        contractCash=null;
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        selectGroupByName(group.getIdgroup());

        selectContactById(contact.getIdcontact());
        wd.findElement(By.name("remove")).click();
        contractCash=null;
    }

    private void selectGroupByName(int id) {
        WebElement select = wd.findElement(By.name("group"));
        Select selectall=new Select(select);
        selectall.selectByValue(Integer.toString(id));
        String s=wd.findElement(By.name("remove")).getAttribute("value");
    }

    private void editSelectedContractById(int idcontact) {
        wd.findElement(By.xpath("//input[@id='"+idcontact+"']/../../td[8]")).click();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getIdcontact());
        deleteContract();
        contractCash=null;
    }

    private void selectContactById(int idcontact) {
        wd.findElement(By.cssSelector("input[id='"+idcontact+"']")).click();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContractForm(contact, true);
        submitContractCreation();
        contractCash=null;
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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    private Contacts contractCash=null;

    public Contacts all() {
        if(contractCash!=null){
            return new Contacts(contractCash);
        }
        contractCash= new Contacts();
        List<WebElement> elements= wd.findElements(By.name("entry"));

       for(WebElement element: elements)
        {
           /* WebElement elementsTdid= element.findElement(By.xpath("./td[1]"));
            int id = Integer.parseInt(elementsTdid.findElement(By.tagName("input")).getAttribute("value"));

            WebElement elementsTd= element.findElement(By.xpath("./td[2]"));
            String lastname= elementsTd.getText();

            WebElement elementsTd1= element.findElement(By.xpath("./td[3]"));
            String firstname= elementsTd1.getText();

            ContactData contact = new ContactData().withIdcontact(id).withFirstname(firstname).withLastname(lastname);
            contractCash.add(contact);*/
           List<WebElement> cells = element.findElements(By.tagName("td"));
           int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
           String lastname=cells.get(1).getText();
           String firstname = cells.get(2).getText();
           String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String allAddress = cells.get(3).getText();
           contractCash.add(new ContactData().withLastname(lastname).withFirstname(firstname).withIdcontact(id)
           .withAllPhones(allPhones).withAllEmails(allEmails).withAllAddress(allAddress));//.withMobile(phones[1]).withWork(phones[2]));

        }
        return new Contacts(contractCash);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editSelectedContractById(contact.getIdcontact());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withFirstname(firstname).withLastname(lastname).withMobile(mobile)
                .withWork(work).withHome(home).withIdcontact(contact.getIdcontact())
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }
}
