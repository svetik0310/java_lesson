package ru.zotkina.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapTests {
    @Test
    public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType msPort = new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
        ProjectData[] projects=msPort.mc_projects_get_user_accessible("administrator","root");
        System.out.println(projects.length);
        for (ProjectData p:projects)
            System.out.println(p.getName());
    }

}
