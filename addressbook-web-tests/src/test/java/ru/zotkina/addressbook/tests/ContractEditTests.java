package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContractEditTests extends TestBase{
    @BeforeMethod
    public void preconditions(){
        app.goTo().homePage();
        if(app.contract().list().size()==0) {
            app.goTo().groupPage();
            if(app.group().list().size()==0) {
                app.group().create(new GroupData().withGroupname("test1A").withHeader("test2A").withFooter("test3A"));
            }
            app.contract().create(new ContactData().withFirstname("Василий").withMiddlename("Иванович").withLastname("Пупкин")
                    .withNickname("Vasya").withTitle( "title")
                    .withCompany("company").withAddress("address").withEmail("222").withEmail2("333").withEmail3("444").withFax("555")
                    .withHomepage("3445").withHome("432434").withMobile("2423424").withWork("343543545").withGroup("test1A"), false);
            app.goTo().returnToHomePage();
        }
    }
    @Test
    public void testContactUpdate() {
        List<ContactData> before=app.contract().list();
        ContactData contact = new ContactData().withIdcontact(before.get(before.size()-1).getIdcontact())
                .withFirstname("Василий2").withMiddlename("Иванович").withLastname("Пупкин")
                .withNickname("Vasya").withTitle("title").withCompany("company")
                .withAddress("address").withEmail("222").withEmail2("333")
                .withEmail3("444").withFax("555").withHomepage("3445")
                .withHome("432434").withWork("2423424").withMobile("343543545");
        int index=before.size()-1;
        app.contract().edit(contact, index);
        app.goTo().returnToHomePage();
        List<ContactData> after = app.contract().list();
        Assert.assertEquals(after.size(),before.size());


        before.remove(index);
        before.add(contact);
        //сравнение множеств
        /*Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));*/

        //сравнение списков
        Comparator<? super ContactData> byId = (g1, g2)-> Integer.compare(g1.getIdcontact(),g2.getIdcontact());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }
}
