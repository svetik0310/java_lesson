package ru.zotkina.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1A", "test2A", "test3A"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
