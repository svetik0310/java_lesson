package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupEditTests extends TestBase{
    @Test
    public void testGroupEditName() {
        app.getNavigationHelper().gotoGroupPage();
        if(!app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1A6", "test2A", "test3A"));
        }
        List<GroupData> before=app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().editSelectedGroup();
        GroupData group = new GroupData(before.get(before.size()-1).getIdgroup(),"testEdit", "test2A", "test3A");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().updateSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after=app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }
}
