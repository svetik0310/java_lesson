package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getContractHelper().initContactCreation();
        app.getContractHelper().fillContractForm(new ContactData("Вася", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "testEdit"), true);
        app.getContractHelper().submitContractCreation();
        app.getNavigationHelper().returnToHomePage();
    }
}
