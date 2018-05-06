package ru.zotkina.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.zotkina.mantis.model.Issue;
import ru.zotkina.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app=app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType msPort = getMantisConnectPortType();
        ProjectData[] projects=msPort.mc_projects_get_user_accessible("administrator","root");
        return Arrays.asList(projects).stream()
                .map((p)->new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnectPortType() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL(app.getProperty("mantis.url")));//"http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType msPort = getMantisConnectPortType();
        String categories[]=msPort.mc_project_get_categories("administrator","root",BigInteger.valueOf(issue.getProject().getId()));
        IssueData idata = new IssueData();
        idata.setSummary(issue.getSummery());
        idata.setDescription(issue.getDescription());
        idata.setCategory(categories[0]);
        idata.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),issue.getProject().getName()));
        BigInteger id=msPort.mc_issue_add("administrator","root",idata);
        IssueData created=msPort.mc_issue_get("administrator","root",id);
        return new Issue().withId(created.getId().intValue())
                .withDescription(created.getDescription())
                .withSummery(created.getSummary())
                .withProject(new Project().withId(created.getId().intValue()).withName(created.getProject().getName()));
    }

    public Issue readIssue(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType msPort = getMantisConnectPortType();
        IssueData data = msPort.mc_issue_get("administrator","root",BigInteger.valueOf(id));
        return new Issue().withProject(new Project().withName(data.getProject().getName()).withId(data.getId().intValue()))
                .withSummery(data.getSummary()).withDescription(data.getDescription()).withId(data.getId().intValue());
    }

}
