package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.GroupData;

import java.util.List;

public class ContractDeleteTests extends TestBase{
    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToHomePage();
        if(!app.getContractHelper().isContactExist()) {
            app.getNavigationHelper().gotoGroupPage();
            if(!app.getGroupHelper().isGroupExist()) {
                app.getGroupHelper().createGroup(new GroupData("test1A", "test2A", "test3A"));
            }
            app.getContractHelper().createContact(new ContactData("Василий", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "test1A"), false);
            app.getNavigationHelper().returnToHomePage();
        }
        List<ContactData> before=app.getContractHelper().getContractList();
        app.getContractHelper().selectContact(0);
        app.getContractHelper().deleteContract();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after=app.getContractHelper().getContractList();
        Assert.assertEquals(after.size(),before.size()-1);
    }
}
