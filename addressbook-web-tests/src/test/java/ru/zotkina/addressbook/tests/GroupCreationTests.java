package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1A", "test2A", "test3A"));
    }

}
