package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

public class GroupEditTests extends TestBase{
    @Test
    public void testGroupEditName() {
        app.getNavigationHelper().gotoGroupPage();
        if(!app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1A", "test2A", "test3A"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("testEdit", "test2A", "test3A"));
        app.getGroupHelper().updateSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
