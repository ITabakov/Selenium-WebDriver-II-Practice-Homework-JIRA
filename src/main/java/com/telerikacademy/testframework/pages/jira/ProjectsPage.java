package com.telerikacademy.testframework.pages.jira;

import org.openqa.selenium.WebDriver;

public class ProjectsPage extends BaseJiraPage{
    public ProjectsPage(WebDriver driver) {
        super(driver, "jira.projectsPage");
    }

    public void clickOnProject(String projectName) {
        actions.waitForElementClickable("jira.projectsPage.viewProject", projectName);
        actions.clickElement("jira.projectsPage.viewProject", projectName);
    }
}
