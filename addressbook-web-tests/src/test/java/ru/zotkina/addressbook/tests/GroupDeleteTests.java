package ru.zotkina.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;
import ru.zotkina.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.*;
import static org.testng.Assert.assertEquals;


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
        Groups before=app.db().groups();
        GroupData deletedGroup=before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(),equalTo(before.size()-1));
        Groups after=app.db().groups();
        assertThat(after,equalTo(before.withOut(deletedGroup)));
    }


}
