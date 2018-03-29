package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;


import java.util.Comparator;
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

      /*  int max=0;
        for(GroupData g:after)
        {
            if(g.getIdgroup()> max) max=g.getIdgroup();
        }*/

      //сравнение множеств
        group.setIdgroup(after.stream().max((o1,o2)-> Integer.compare(o1.getIdgroup(),o2.getIdgroup())).get().getIdgroup());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

        //сравнение списков
        Comparator<? super GroupData> byId = (g1, g2)-> Integer.compare(g1.getIdgroup(),g2.getIdgroup());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
