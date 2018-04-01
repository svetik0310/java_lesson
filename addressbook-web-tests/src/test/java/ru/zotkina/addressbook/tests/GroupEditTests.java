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
        app.goTo().groupPage();
        if(app.group().all().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
        }
    }

    @Test
    public void testGroupEditName() {
        Groups before=app.group().all();
        GroupData editGroup=before.iterator().next();
        GroupData group = new GroupData()
                .withIdgroup(editGroup.getIdgroup()).withGroupname("testEdit").withHeader("test2A").withFooter("test3A");
        app.group().edit(group);
        Groups after=app.group().all();
        assertEquals(after.size(), before.size());

        assertThat(after,equalTo(before.withOut(editGroup).withAdded(group)));
}

}
