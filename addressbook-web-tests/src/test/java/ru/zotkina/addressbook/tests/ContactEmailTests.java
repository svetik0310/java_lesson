package ru.zotkina.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;
import ru.zotkina.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{
    @BeforeMethod
    public void preconditions(){
        app.goTo().homePage();
        if(app.contract().all().size()==0) {
            app.goTo().groupPage();
            if(app.group().all().size()==0) {
                app.group().create(new GroupData().withGroupname("test1A").withHeader("test2A").withFooter("test3A"));
            }
            app.contract().create(new ContactData().withFirstname("Василий")
                    .withMiddlename("Иванович").withLastname("Пупкин")
                    .withNickname("Vasya").withTitle("title").withCompany("company")
                    .withAddress("address").withEmail("222").withEmail2("333").withEmail3("444")
                    .withFax("555").withWork("3445").withHomepage("432434").withHome("2423424")
                    .withMobile("343543545").withGroup("test1A"), false);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testContactEmails(){

        app.goTo().homePage();
        ContactData contact=app.contract().all().iterator().next();
        ContactData contactInfoFromEditData = app.contract().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(),equalTo(merdgeEmails(contactInfoFromEditData)));
    }

    private String merdgeEmails(ContactData contact) {

        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s)->!s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email)
    {
        return email.replaceAll(" ","");
    }
}
