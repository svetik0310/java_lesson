package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.goTo().groupPage();
        if(app.group().list().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
        }
    }

    @Test
    public void testGroupDeleteTests() {
        List<GroupData> before=app.group().list();
        int index=0;
        app.group().delete(index);
        List<GroupData> after=app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before,after);
    }


}
