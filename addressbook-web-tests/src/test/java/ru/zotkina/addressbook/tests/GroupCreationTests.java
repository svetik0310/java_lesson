package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;


import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before=app.getGroupHelper().getGroupList();
        GroupData group=new GroupData("1", "2", "3");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after=app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()+1);

        int max=0;
        for(GroupData g:after)
        {
            if(g.getIdgroup()> max) max=g.getIdgroup();
        }
        group.setIdgroup(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }

}
