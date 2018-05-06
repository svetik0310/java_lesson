package ru.zotkina.rest.tests;

import org.testng.annotations.Test;
import ru.zotkina.rest.model.Issue;

import java.io.IOException;
import java.util.*;


import static org.testng.Assert.*;

public class RestTests extends TestBase{
    @Test
    public void testAddIssue() throws IOException {
        Set<Issue> old = getIssue();
        Issue newIssue=new Issue().withDescription("testZ").withSubject("testIssueZ");
        int idI=createIssue(newIssue);
        Set<Issue> newI = getIssue();
        old.add(newIssue.withId(idI));
        assertEquals(newI,old);
    }




    @Test
    public void testCloseIssue() throws IOException {
        Set<Issue> old = new HashSet<Issue>();
        old=getIssueClose();
        assertNotNull(old);
    }


    @Test
    public void testOpenIssue() throws IOException {
        Issue edit=getIssueOpen();
        skipIfNotFixed(edit.getId());
        assertEquals(edit.getState(),"0");
    }

}
