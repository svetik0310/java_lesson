package ru.zotkina.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> ValidContactsFromJson() throws IOException {
        try(BufferedReader buffer=new BufferedReader(new FileReader(new File("src/test/resources/contact.json")))) {
            String json = "";
            String line = buffer.readLine();
            while (line != null) {
                json += line;
                line = buffer.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());//List<GroupData>.class
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }





    @Test (dataProvider = "ValidContactsFromJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before=app.db().contacts();//app.contract().all();
        File photo=new File("src/test/resources/testPhoto.jpg");
        contact.withPhoto(photo);
        app.contract().create(contact);
        app.goTo().returnToHomePage();
        assertThat(app.contract().count(),equalTo(before.size()+1));
        Contacts after=app.db().contacts();//app.contract().all();

        assertThat(after,equalTo(before.withAdded(contact.withIdcontact(after.stream().mapToInt((g)->g.getIdcontact()).max().getAsInt()))));
    }
}
