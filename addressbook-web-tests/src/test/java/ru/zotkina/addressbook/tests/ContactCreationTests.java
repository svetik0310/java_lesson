package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before=app.contract().all();
        File photo=new File("src/test/resources/testPhoto.jpg");
        ContactData contact =new ContactData().withFirstname("Вася").withMiddlename("Иванович").withLastname("Пупкин")
                .withNickname("Vasya").withTitle("title").withCompany("company")
                .withAddress("address").withHome("222").withMobile("333")
                .withWork("444").withFax("555").withEmail3("3445")
                .withEmail("432434").withEmail2("2423424")
                .withHomepage("343543545").withGroup("1")
                .withPhoto(photo);
        app.contract().create(contact);
        app.goTo().returnToHomePage();
        assertThat(app.contract().count(),equalTo(before.size()+1));
        Contacts after=app.contract().all();

        assertThat(after,equalTo(before.withAdded(contact.withIdcontact(after.stream().mapToInt((g)->g.getIdcontact()).max().getAsInt()))));
    }
}
