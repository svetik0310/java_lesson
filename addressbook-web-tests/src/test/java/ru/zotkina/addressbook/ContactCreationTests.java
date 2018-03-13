package ru.zotkina.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContractForm(new ContactData("Вася", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545"));
        submitContractCreation();
        returnToHomePage();
    }
}
