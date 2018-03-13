package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1A", "test2A", "test3A"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
