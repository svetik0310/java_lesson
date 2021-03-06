package ru.zotkina.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;
import ru.zotkina.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContractEditTests extends TestBase{
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
                    .withNickname("Vasya").withTitle( "title")
                    .withCompany("company").withAddress("address").withEmail("222").withEmail2("333").withEmail3("444").withFax("555")
                    .withHomepage("3445").withHome("432434").withMobile("2423424").withWork("343543545")
                    //.withGroup("test1A")
                    , false);
            app.goTo().returnToHomePage();
        }
    }
    @Test
    public void testContactUpdate() {
        Contacts before=app.db().contacts();//app.contact().all();
        ContactData editContact=before.iterator().next();
        File photo=new File("src/test/resources/testPhoto.jpg");
        ContactData contact = new ContactData().withIdcontact(editContact.getIdcontact())
                .withFirstname("Vasya500").withMiddlename("Ivanov").withLastname("Pupkin")
                .withNickname("Vasya").withTitle("title").withCompany("company")
                .withAddress("address").withEmail("222").withEmail2("333")
                .withEmail3("444").withFax("555").withHomepage("3445")
                .withHome("432434").withWork("2423424").withMobile("343543545").withPhoto(photo);

        app.contact().edit(contact);
        app.goTo().returnToHomePage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after,equalTo(before.withOut(editContact).withAdded(contact)));
        verifyContactListInUi();
    }

    //-DverifyUi=true

}
