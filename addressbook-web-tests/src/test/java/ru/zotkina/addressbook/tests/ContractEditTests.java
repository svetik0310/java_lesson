package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.GroupData;

public class ContractEditTests extends TestBase{
    @Test
    public void testContactUpdate() {
        app.getNavigationHelper().goToHomePage();
        int before = app.getContractHelper().getContractCount();
        if(!app.getContractHelper().isContactExist()) {
            app.getNavigationHelper().gotoGroupPage();
            if(!app.getGroupHelper().isGroupExist()) {
                app.getGroupHelper().createGroup(new GroupData("test1A", "test2A", "test3A"));
            }
            app.getContractHelper().createContact(new ContactData("Василий", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "test1A"), false);
            app.getNavigationHelper().returnToHomePage();
        }
        app.getContractHelper().editSelectedContract(before-1);
        app.getContractHelper().fillContractForm(new ContactData("Василий", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", null), false);
        app.getContractHelper().submitContractUpdate();
        app.getNavigationHelper().returnToHomePage();
        int after = app.getContractHelper().getContractCount();
        Assert.assertEquals(after,before);
    }
}
