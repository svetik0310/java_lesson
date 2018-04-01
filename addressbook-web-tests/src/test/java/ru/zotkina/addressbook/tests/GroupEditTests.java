package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupEditTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.getNavigationHelper().gotoGroupPage();
        if(!app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1A6", "test2A", "test3A"));
        }
    }

    @Test
    public void testGroupEditName() {
        List<GroupData> before=app.getGroupHelper().getGroupList();
        int index=before.size()-1;
        GroupData group = new GroupData(before.get(index).getIdgroup(),"testEdit", "test2A", "test3A");
        app.getGroupHelper().editGroup(index, group);
        List<GroupData> after=app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(index);
        before.add(group);
        //сравнение множеств
        /*Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));*/

        //сравнение списков
        Comparator<? super GroupData> byId = (g1,g2)-> Integer.compare(g1.getIdgroup(),g2.getIdgroup());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
}

}
