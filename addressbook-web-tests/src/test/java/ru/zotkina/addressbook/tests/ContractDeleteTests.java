package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;

public class ContractDeleteTests extends TestBase{
    @Test
    public void testContactDelete() {
        app.getContractHelper().selectContact();
        app.getContractHelper().deleteContract();
        app.getNavigationHelper().goToHomePage();
    }
}
