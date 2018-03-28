package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

public class GroupDeleteTests extends TestBase{

    @Test
    public void testGroupDeleteTests() {
        app.getNavigationHelper().gotoGroupPage();
        if(!app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1A", "test2A", "test3A"));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
    }
}
