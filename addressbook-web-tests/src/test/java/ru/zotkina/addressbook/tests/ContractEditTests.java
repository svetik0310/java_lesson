package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;

public class ContractEditTests extends TestBase{
    @Test
    public void testContactUpdate() {
        if(!app.getContractHelper().isContactExist()) {
            app.getContractHelper().createContact(new ContactData("Василий", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "test1A"), false);
            app.getNavigationHelper().returnToHomePage();
        }
        app.getContractHelper().editSelectedContract();
        app.getContractHelper().fillContractForm(new ContactData("Василий", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", null), false);
        app.getContractHelper().submitContractUpdate();
        app.getNavigationHelper().returnToHomePage();
    }
}
