package com.telerikacademy.testframework.pages.jira;

import org.openqa.selenium.WebDriver;

public class YourWorkPage extends BaseJiraPage{
    public YourWorkPage(WebDriver driver) {
        super(driver, "jira.yourWorkPage");
    }

    public void viewAllProjects() {
        actions.waitForElementClickable("jira.yourWorkPage.viewAllProjects");
        actions.clickElement("jira.yourWorkPage.viewAllProjects");
    }
}
