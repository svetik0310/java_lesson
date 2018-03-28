package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase{

    @Test
    public void testGroupDeleteTests() {
        app.getNavigationHelper().gotoGroupPage();
        if(!app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1A", "test2A", "test3A"));
        }
        List<GroupData> before=app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after=app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
