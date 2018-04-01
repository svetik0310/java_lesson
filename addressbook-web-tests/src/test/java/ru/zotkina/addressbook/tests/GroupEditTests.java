package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.Set;


public class GroupEditTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.goTo().groupPage();
        if(app.group().all().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
        }
    }

    @Test
    public void testGroupEditName() {
        Set<GroupData> before=app.group().all();
        GroupData editgroup=before.iterator().next();
        GroupData group = new GroupData()
                .withIdgroup(editgroup.getIdgroup()).withGroupname("testEdit").withHeader("test2A").withFooter("test3A");
        app.group().edit(group);
        Set<GroupData> after=app.group().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(editgroup);
        before.add(group);

        Assert.assertEquals(before,after);
}

}
