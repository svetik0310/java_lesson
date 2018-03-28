package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.List;

public class GroupEditTests extends TestBase{
    @Test
    public void testGroupEditName() {
        app.getNavigationHelper().gotoGroupPage();
        if(!app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1A", "test2A", "test3A"));
        }
        List<GroupData> before=app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("testEdit", "test2A", "test3A"));
        app.getGroupHelper().updateSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after=app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
