package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before=app.contract().all();
        ContactData contact =new ContactData().withFirstname("Вася").withMiddlename("Иванович").withLastname("Пупкин")
                .withNickname("Vasya").withTitle("title").withCompany("company")
                .withAddress("address").withHome("222").withMobile("333")
                .withWork("444").withFax("555").withEmail3("3445")
                .withEmail("432434").withEmail2("2423424")
                .withHomepage("343543545").withGroup("1");
        app.contract().create(contact);
        app.goTo().returnToHomePage();
        Set<ContactData> after=app.contract().all();
        Assert.assertEquals(after.size(), before.size()+1);

        contact.withIdcontact(after.stream().mapToInt((g)->g.getIdcontact()).max().getAsInt());
        before.add(contact);

        Assert.assertEquals(before,after);
    }
}
