package ru.zotkina.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.zotkina.addressbook.appmanager.ApplicationManager;
import ru.zotkina.addressbook.model.ContactData;
import ru.zotkina.addressbook.model.Contacts;
import ru.zotkina.addressbook.model.GroupData;
import ru.zotkina.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object p[])
    {
        logger.info("Start test "+m.getName()+" with parametrs "+ Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop()
    {
        logger.info("Stop test testBadGroupCreation");
    }


    public void verifyGroupListInUi() {
       if(Boolean.getBoolean("verifyUi")) {
           Groups dbgroups = app.db().groups();//group().all();
           Groups uigroups = app.group().all();
           assertThat(uigroups, equalTo(dbgroups.stream()
                   .map((g) -> new GroupData().withIdgroup(g.getIdgroup()).withGroupname(g.getGroupname()))
                   .collect(Collectors.toSet())));
       }
    }

    public void verifyContactListInUi() {
        if(Boolean.getBoolean("verifyUi")) {
            Contacts dbcontacts = app.db().contacts();
            Contacts uicontacts = app.contact().all();
            assertThat(uicontacts, equalTo(dbcontacts.stream()
                    .map((g) -> new ContactData().withFirstname(g.getFirstname()).withLastname(g.getLastname())
                   // .withMiddlename(g.getMiddlename()).withHome(g.getHome())
                   // .withWork(g.getWork()).withMobile(g.getMobile())
                    //.withEmail(g.getEmail()).withEmail2(g.getEmail2())
                    //.withEmail3(g.getEmail3()).withAddress(g.getAddress())
                    //.withCompany(g.getCompany()).withFax(g.getFax()).withHomepage(g.getHomepage())
                    //.withTitle(g.getTitle()).withNickname(g.getNickname())
                    //.withPhoto(g.getPhoto())
                            .withIdcontact(g.getIdcontact())
                    .withAllAddress(g.getAllAddress()).withAllEmails(g.getAllEmails()).withAllPhones(g.getAllPhones()))
                    .collect(Collectors.toSet())));
        }
    }

}
