package ru.zotkina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before=app.contract().list();
        ContactData contact =new ContactData("Вася", "Иванович", "Пупкин", "Vasya", "title", "company", "address", "222", "333", "444", "555", "3445", "432434", "2423424", "343543545", "1");
        app.contract().create(contact);
        app.goTo().returnToHomePage();
        List<ContactData> after=app.contract().list();
        Assert.assertEquals(after.size(), before.size()+1);


/*        int max=0;
        for(ContactData g:after)
        {
            if(g.getIdcontact()> max) max=g.getIdcontact();
        }*/
        before.add(contact);

/*//Проверка множеств
        contact.setIdcontact(after.stream().max((o1,o2)-> Integer.compare(o1.getIdcontact(),o2.getIdcontact())).get().getIdcontact());
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));*/

        //проверка списков, предыдущее можно удалить
        Comparator<? super ContactData> byId = (g1, g2)-> Integer.compare(g1.getIdcontact(),g2.getIdcontact());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
