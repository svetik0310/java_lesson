package ru.zotkina.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;
import ru.zotkina.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.*;
import static org.testng.Assert.assertEquals;


public class GroupEditTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
     if (app.db().groups().size()==0) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
     }
        /*
        app.goTo().groupPage();
        if(app.group().all().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
        }*/
    }

    @Test
    public void testGroupEditName() {
        Groups before=app.db().groups();//group().all();
        GroupData editGroup=before.iterator().next();
        GroupData group = new GroupData()
                .withIdgroup(editGroup.getIdgroup()).withGroupname("testEdit").withHeader("test2A").withFooter("test3A");
        app.goTo().groupPage();
        app.group().edit(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after=app.db().groups();//group().all();

        assertThat(after,equalTo(before.withOut(editGroup).withAdded(group)));

        verifyGroupListInUi();
}

}
