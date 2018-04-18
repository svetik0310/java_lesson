package ru.zotkina.addressbook.tests;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;
        import ru.zotkina.addressbook.model.GroupData;
        import ru.zotkina.addressbook.model.Groups;

        import java.io.*;
        import java.util.Iterator;
        import java.util.List;
        import java.util.stream.Collectors;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.junit.MatcherAssert.*;


public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> ValidGroupsFromJson() throws IOException {
       try(BufferedReader buffer=new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
           String json = "";
           String line = buffer.readLine();
           while (line != null) {
               json += line;
               line = buffer.readLine();
           }
           Gson gson = new Gson();
           List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
           }.getType());//List<GroupData>.class
           return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
       }
    }


    @Test (dataProvider = "ValidGroupsFromJson")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().count(),equalTo(before.size()+1));
        Groups after = app.db().groups();

        assertThat(after,equalTo(before.withAdded(group.withIdgroup(after.stream().mapToInt((g)->g.getIdgroup()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData group=new GroupData().withGroupname("1'").withFooter("2").withHeader("3");
        app.group().create(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.db().groups();

        assertThat(after,equalTo(before));
    }

}
