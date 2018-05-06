package ru.zotkina.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static org.testng.Assert.*;

public class RestTests {
    @Test
    public void testAddIssue() throws IOException {
        Set<Issue> old = getIssue();
        Issue newIssue=new Issue().withDescription("testZ").withSubject("testIssueZ");
        int idI=createIssue(newIssue);
        Set<Issue> newI = getIssue();
        old.add(newIssue.withId(idI));
        assertEquals(newI,old);
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json=getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
        .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject()),
                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed=new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private Set<Issue> getIssue() throws IOException {
        String json= getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        JsonElement parsed=new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        ArrayList<Issue> arr= new Gson().fromJson(issues, new TypeToken<List<Issue>>() {
        }.getType());
        Set<Issue> i=new HashSet<Issue>(arr);
        return i;
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
    }
}
