package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupEditTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.goTo().groupPage();
        if(app.group().list().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
        }
    }

    @Test
    public void testGroupEditName() {
        List<GroupData> before=app.group().list();
        int index=before.size()-1;
        GroupData group = new GroupData()
                .withIdgroup(before.get(index).getIdgroup()).withGroupname("testEdit").withHeader("test2A").withFooter("test3A");
        app.group().edit(index, group);
        List<GroupData> after=app.group().list();
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
