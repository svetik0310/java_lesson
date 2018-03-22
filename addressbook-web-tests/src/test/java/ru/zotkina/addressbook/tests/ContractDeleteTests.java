package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;

public class ContractDeleteTests extends TestBase{
    @Test
    public void testContactDelete() {
        if(!app.getContractHelper().isContactExist()) {
            app.getContractHelper().createContact(new ContactData("Василий", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "test1A"), false);
            app.getNavigationHelper().returnToHomePage();
        }
        app.getContractHelper().selectContact();
        app.getContractHelper().deleteContract();
        app.getNavigationHelper().goToHomePage();
    }
}
