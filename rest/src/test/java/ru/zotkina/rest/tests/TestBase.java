package ru.zotkina.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.SkipException;
import ru.zotkina.rest.model.Issue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class TestBase {
    public boolean isIssueOpen(int issueId) throws IOException {

        String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json",Integer.toString(issueId))))
                .returnContent().asString();
        JsonElement parsed=new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        ArrayList<Issue> arr= new Gson().fromJson(issues, new TypeToken<List<Issue>>() {
        }.getType());
        Set<Issue> temp=new HashSet<Issue>(arr);
        Issue iii=temp.iterator().next();
        return (issueId==iii.getId())&&(iii.getState().equals("1"));
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public Set<Issue> getIssueClose() throws IOException {
        String json= getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        JsonElement parsed=new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        ArrayList<Issue> arr= new Gson().fromJson(issues, new TypeToken<List<Issue>>() {
        }.getType());
        Set<Issue> i=new HashSet<Issue>(arr);
        Set<Issue> ii=i.stream().filter((m)->Integer.parseInt(m.getState())!=0).collect(Collectors.toSet());
        Issue edit=ii.iterator().next();
        skipIfNotFixed(edit.getId());
        return ii;
    }

    public Issue getIssueOpen() throws IOException {
        String json= getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        JsonElement parsed=new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        ArrayList<Issue> arr= new Gson().fromJson(issues, new TypeToken<List<Issue>>() {
        }.getType());
        Set<Issue> i=new HashSet<Issue>(arr);
        Set<Issue> ii=i.stream().filter((m)->Integer.parseInt(m.getState())==0).collect(Collectors.toSet());
        Issue edit=ii.iterator().next();
        return edit;
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json=getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed=new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Set<Issue> getIssue() throws IOException {
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
