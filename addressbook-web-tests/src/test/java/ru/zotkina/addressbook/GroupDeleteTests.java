package ru.zotkina.addressbook;

import org.testng.annotations.Test;
import org.openqa.selenium.*;

public class GroupDeleteTests extends TestBase{

    @Test
    public void testGroupDeleteTests() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}
