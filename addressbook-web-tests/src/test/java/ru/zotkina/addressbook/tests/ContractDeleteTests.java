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
                app.group().create(new GroupData("test1A", "test2A", "test3A"));
            }
            app.contract().create(new ContactData("Василий", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "test1A"), false);
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
