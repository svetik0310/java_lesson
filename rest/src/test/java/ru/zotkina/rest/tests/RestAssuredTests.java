package ru.zotkina.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.zotkina.rest.model.Issue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {
    @BeforeClass
    public void init(){
        RestAssured.authentication=RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed","");
    }


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

        String json = RestAssured.given().parameter("subject",newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed=new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private Set<Issue> getIssue() throws IOException {
       String json= RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed=new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        ArrayList<Issue> arr= new Gson().fromJson(issues, new TypeToken<List<Issue>>() {
        }.getType());
        Set<Issue> i=new HashSet<Issue>(arr);
        return i;
    }

    /*private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
    }*/
}
