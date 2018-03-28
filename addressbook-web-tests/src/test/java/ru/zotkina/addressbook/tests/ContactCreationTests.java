package ru.zotkina.addressbook.tests;

import org.apache.http.annotation.Contract;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before=app.getContractHelper().getContractList();
        app.getContractHelper().initContactCreation();
        app.getContractHelper().fillContractForm(new ContactData("Вася", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "test1A"), true);
        app.getContractHelper().submitContractCreation();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after=app.getContractHelper().getContractList();
        Assert.assertEquals(after.size(), before.size()+1);
    }
}
