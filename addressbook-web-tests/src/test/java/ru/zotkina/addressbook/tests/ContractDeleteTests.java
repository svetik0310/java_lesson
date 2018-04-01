package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.GroupData;

import java.util.List;

public class ContractDeleteTests extends TestBase{
    @BeforeMethod
    public void preconditions(){
        app.goTo().homePage();
        if(app.contract().list().size()==0) {
            app.goTo().groupPage();
            if(app.group().list().size()==0) {
                app.group().create(new GroupData().withGroupname("test1A").withHeader("test2A").withFooter("test3A"));
            }
            app.contract().create(new ContactData().withFirstname("Василий")
                    .withMiddlename("Иванович").withLastname("Пупкин")
                    .withNickname("Vasya").withTitle("title").withCompany("company")
                    .withAddress("address").withEmail("222").withEmail2("333").withEmail3("444")
                    .withFax("555").withWork("3445").withHomepage("432434").withHome("2423424")
                    .withMobile("343543545").withGroup("test1A"), false);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testContactDelete() {
        List<ContactData> before=app.contract().list();
        int index=0;
        app.contract().delete(index);
        app.goTo().homePage();
        List<ContactData> after=app.contract().list();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(index);
        Assert.assertEquals(before,after);
    }

}
