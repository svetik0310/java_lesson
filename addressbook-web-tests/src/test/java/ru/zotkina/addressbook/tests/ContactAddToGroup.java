package ru.zotkina.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;
import ru.zotkina.addressbook.model.GroupData;
import ru.zotkina.addressbook.model.Groups;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;


public class ContactAddToGroup extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.goTo().groupPage();
        if(app.group().all().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A").withHeader("test2A").withFooter("test3A"));
        }
        app.goTo().homePage();
        if(app.contact().all().size()==0) {
            app.contact().create(new ContactData().withFirstname("Василий")
                    .withMiddlename("Иванович").withLastname("Пупкин")
                    .withNickname("Vasya").withTitle("title").withCompany("company")
                    .withAddress("address").withEmail("222").withEmail2("333").withEmail3("444")
                    .withFax("555").withWork("3445").withHomepage("432434").withHome("2423424")
                    .withMobile("343543545"), false);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testContactAddToGroup() {
        app.goTo().homePage();
        Contacts before=app.db().contacts();
        Groups allGr=app.db().groups();
        ContactData editContact=before.iterator().next();
        Set<GroupData> gr=new HashSet<>();
        GroupData group =new GroupData();
        gr=editContact.getGroups();
        if (gr.size()==allGr.size())
        {
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupname("test1A").withHeader("test2A").withFooter("test3A"));
            allGr=app.db().groups();
        }
        if (gr.size()<allGr.size()) {
            for (GroupData con : allGr) {
                if (!gr.contains(con)){group = con; break;}
            }
        }

        app.contact().addToGroup(editContact, group);
        Contacts after=app.db().contacts();
        ContactData afteredit=new ContactData();

        for(ContactData c:after)
        {
            if (c.getIdcontact()==editContact.getIdcontact())
            {afteredit=c;break;}
        }

        assertThat(afteredit.getGroups(),equalTo(editContact.getGroups().withAdded(group)));

    }
}
