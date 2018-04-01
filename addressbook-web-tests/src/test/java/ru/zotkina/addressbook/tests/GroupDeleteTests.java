package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeleteTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.goTo().groupPage();
        if(app.group().all().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
        }
    }

    @Test
    public void testGroupDeleteTests() {
        Set<GroupData> before=app.group().all();
        GroupData deletedgroup=before.iterator().next();
        app.group().delete(deletedgroup);
        Set<GroupData> after=app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedgroup);
        Assert.assertEquals(before,after);
    }


}
