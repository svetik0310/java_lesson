package ru.zotkina.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;
import ru.zotkina.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContractDeleteTests extends TestBase{
    @BeforeMethod
    public void preconditions(){
        app.goTo().homePage();
        if(app.contact().all().size()==0) {
            app.goTo().groupPage();
            if(app.group().all().size()==0) {
                app.group().create(new GroupData().withGroupname("test1A").withHeader("test2A").withFooter("test3A"));
            }
            app.contact().create(new ContactData().withFirstname("Vasya")
                            .withMiddlename("Ivanov").withLastname("Pupkin")
                    .withNickname("Vasya").withTitle("title").withCompany("company")
                    .withAddress("address").withEmail("222").withEmail2("333").withEmail3("444")
                    .withFax("555").withWork("3445").withHomepage("432434").withHome("2423424")
                    .withMobile("343543545")
                    //.withGroup("test1A")
                    , false);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testContactDelete() {
        Contacts before=app.db().contacts();//app.contact().all();
        ContactData deleteContact=before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Contacts after=app.db().contacts();//app.contact().all();

        assertThat(after,equalTo(before.withOut(deleteContact)));
        verifyContactListInUi();
    }

}
