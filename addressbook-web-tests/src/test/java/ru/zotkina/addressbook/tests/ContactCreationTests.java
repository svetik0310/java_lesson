package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before=app.contract().all();
        ContactData contact =new ContactData().withFirstname("Вася").withMiddlename("Иванович").withLastname("Пупкин")
                .withNickname("Vasya").withTitle("title").withCompany("company")
                .withAddress("address").withHome("222").withMobile("333")
                .withWork("444").withFax("555").withEmail3("3445")
                .withEmail("432434").withEmail2("2423424")
                .withHomepage("343543545").withGroup("1");
        app.contract().create(contact);
        app.goTo().returnToHomePage();
        Contacts after=app.contract().all();

        assertThat(after.size(),equalTo(before.size()+1));

        assertThat(after,equalTo(before.withAdded(contact.withIdcontact(after.stream().mapToInt((g)->g.getIdcontact()).max().getAsInt()))));
    }
}
