package ru.zotkina.addressbook.tests;

        import org.testng.Assert;
        import org.testng.annotations.Test;
        import ru.zotkina.addressbook.model.GroupData;

        import java.util.Set;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before=app.group().all();
        GroupData group=new GroupData().withGroupname("1").withFooter("2").withHeader("3");
        app.group().create(group);
        Set<GroupData> after=app.group().all();
        Assert.assertEquals(after.size(), before.size()+1);

        group.withIdgroup(after.stream().mapToInt((g)->g.getIdgroup()).max().getAsInt());
        before.add(group);

        Assert.assertEquals(before,after);
    }

}
