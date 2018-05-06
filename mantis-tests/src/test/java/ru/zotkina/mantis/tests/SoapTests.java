package ru.zotkina.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zotkina.mantis.model.Issue;
import ru.zotkina.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{
    @Test
    public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {

        Set<Project> projects=app.soap().getProjects();
        System.out.println(projects.size());
        for (Project p:projects)
            System.out.println(p.getName());
    }

    @Test
    public void testCreateIssue()throws MalformedURLException, ServiceException, RemoteException
    {
        Set<Project> projects=app.soap().getProjects();
        Issue issue= new Issue().withSummery("s").withDescription("d").withProject(projects.iterator().next());
        Issue create=app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummery(),create.getSummery());
    }

}
