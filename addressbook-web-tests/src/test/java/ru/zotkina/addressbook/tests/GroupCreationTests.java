package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before=app.group().list();
        GroupData group=new GroupData("1", "2", "3");
        app.group().create(group);
        List<GroupData> after=app.group().list();
        Assert.assertEquals(after.size(), before.size()+1);

      /*  int max=0;
        for(GroupData g:after)
        {
            if(g.getIdgroup()> max) max=g.getIdgroup();
        }*/

        before.add(group);
      /*//сравнение множеств
        group.setIdgroup(after.stream().max((o1,o2)-> Integer.compare(o1.getIdgroup(),o2.getIdgroup())).get().getIdgroup());
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));*/

        //сравнение списков
        Comparator<? super GroupData> byId = (g1, g2)-> Integer.compare(g1.getIdgroup(),g2.getIdgroup());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
