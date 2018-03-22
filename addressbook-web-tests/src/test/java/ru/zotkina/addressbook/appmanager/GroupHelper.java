package ru.zotkina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.zotkina.addressbook.model.GroupData;

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


    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            click(By.name("selected[]"));
        }
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

    public void createGroup(GroupData data) {
        initGroupCreation();
        fillGroupForm(new GroupData("test1A", "test2A", "test3A"));
        submitGroupCreation();
        returnToGroupPage();
    }
}
