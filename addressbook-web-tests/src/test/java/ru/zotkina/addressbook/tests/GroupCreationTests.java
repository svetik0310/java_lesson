package ru.zotkina.addressbook.tests;

        import org.testng.annotations.Test;
        import ru.zotkina.addressbook.model.GroupData;
        import ru.zotkina.addressbook.model.Groups;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.junit.MatcherAssert.*;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group=new GroupData().withGroupname("1").withFooter("2").withHeader("3");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(),equalTo(before.size()+1));

        assertThat(after,equalTo(before.withAdded(group.withIdgroup(after.stream().mapToInt((g)->g.getIdgroup()).max().getAsInt()))));
    }

}
