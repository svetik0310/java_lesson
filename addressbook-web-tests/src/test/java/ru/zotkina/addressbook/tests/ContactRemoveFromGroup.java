package ru.zotkina.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;
import ru.zotkina.addressbook.model.GroupData;
import ru.zotkina.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {
    private SessionFactory sessionFactory;

    @BeforeMethod
    public void preconditions(){
        app.goTo().groupPage();
        if(app.group().all().size()==0) {
            app.group().create(new GroupData().withGroupname("test1A6").withFooter("test2A").withHeader("test3A"));
        }
       Groups beforeGr=app.db().groups();
        app.goTo().homePage();
        if(app.contact().all().size()==0) {
            app.contact().create(new ContactData().withFirstname("Vasya")
                    .withMiddlename("Ivanov").withLastname("Pupkin")
                    .withNickname("Vasya").withTitle("title").withCompany("company")
                    .withAddress("address").withEmail("222").withEmail2("333").withEmail3("444")
                    .withFax("555").withWork("3445").withHomepage("432434").withHome("2423424")
                    .withMobile("343543545").withGroups(beforeGr)
                     , false);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        app.goTo().homePage();
        Contacts before=app.db().contacts();
        ContactData editcontact = new ContactData();
        for (ContactData con : before) {
            if (con.getGroups().size()!=0) {
                editcontact = con;
                break;
            }
        }

        GroupData editGroup=editcontact.getGroups().iterator().next();

        app.contact().removeFromGroup(editcontact, editGroup);
        Contacts after=app.db().contacts();

        ContactData afteredit=new ContactData();

        for(ContactData c:after)
        {
            if (c.getIdcontact()==editcontact.getIdcontact())
            {afteredit=c;break;}
        }

        assertThat(afteredit.getGroups(),equalTo(editcontact.getGroups().withOut(editGroup)));
    }
}
