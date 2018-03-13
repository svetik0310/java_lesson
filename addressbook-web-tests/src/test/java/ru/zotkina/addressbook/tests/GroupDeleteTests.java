package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTests extends TestBase{

    @Test
    public void testGroupDeleteTests() {
        app.gotoGroupPage();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }

}
