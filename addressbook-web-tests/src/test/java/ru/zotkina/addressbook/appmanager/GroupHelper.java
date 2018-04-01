package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.zotkina.addressbook.model.GroupData;
import ru.zotkina.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        fillTextForm(By.name("group_name"),groupData.getGroupname());
        fillTextForm(By.name("group_header"),groupData.getHeader());
        fillTextForm(By.name("group_footer"),groupData.getFooter());
    }

    public void edit(GroupData group) {
        selectGroupById(group.getIdgroup());
        editSelectedGroup();
        fillGroupForm(group);
        updateSelectedGroup();
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getIdgroup());
        deleteSelectedGroups();
        returnToGroupPage();
    }

    private void selectGroupById(int idgroup) {
        wd.findElement(By.cssSelector("input[value='"+idgroup+"']")).click();
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void editSelectedGroup() {
        click(By.name("edit"));
    }
    public void updateSelectedGroup() {
        click(By.name("update"));
    }

    public boolean isGroupExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(GroupData data) {
        initGroupCreation();
        fillGroupForm(data);
        submitGroupCreation();
        returnToGroupPage();
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups= new ArrayList<GroupData>();
        List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
        for(WebElement element: elements)
        {
            String name=element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withIdgroup(id).withGroupname(name));
        }
        return  groups;
    }

    public Groups all() {
        Groups groups= new Groups();
        List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
        for(WebElement element: elements)
        {
            String name=element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withIdgroup(id).withGroupname(name));
        }
        return  groups;
    }

}
