package ru.zotkina.mantis.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.zotkina.mantis.model.Issue;
import ru.zotkina.mantis.model.Issues;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class SoapIssueTests extends TestBase {
    @Test
    public void testOpenIssue()throws MalformedURLException, ServiceException, RemoteException
    {
        short st=90;
        Issues before=app.dbI().issues();
        before.removeIf((b)->b.getStatus()==st);
        Issue is=before.iterator().next();
        skipIfNotFixed(is.getId());
        Issue read = app.soap().readIssue(is.getId());
        Assert.assertEquals(read.getId(), is.getId());
    }
    @Test//(expectedExceptions = SkipException.class)
    public void testCloseIssue()throws MalformedURLException, ServiceException, RemoteException
    {
        short st=10;
        Issues before=app.dbI().issues();
        before.removeIf((b)->b.getStatus()==st);
        Issue is=before.iterator().next();
        skipIfNotFixed(is.getId());
        Issue read = app.soap().readIssue(is.getId());
        Assert.assertEquals(read.getId(), is.getId());
    }
}
