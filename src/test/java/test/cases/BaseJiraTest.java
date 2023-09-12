package test.cases;

import api.JiraApi;
import api.ProjectModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.jira.*;

public class BaseJiraTest {

    protected static JiraApi jiraApi = new JiraApi();
    protected static ProjectModel project;
    protected static UserActions actions = new UserActions();

    protected static void login() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
    }

    protected static void logout() {
        BaseJiraPage.logout();
    }

    protected static void viewAllProjects() {
        YourWorkPage yourWorkPage = new YourWorkPage(actions.getDriver());
        yourWorkPage.viewAllProjects();
    }

    protected static void chooseProject(String name) {
        ProjectsPage projectsPage = new ProjectsPage(actions.getDriver());
        projectsPage.clickOnProject(name);
    }

    protected void createIssue(String projectKey, String issueType, String summary, String description, String environment, String... priority) throws InterruptedException {
        IssuesPage issuesPage = new IssuesPage(actions.getDriver(), projectKey);
        String issuePriority = "";
        if (priority.length == 1) {
            issuePriority = priority[0];
        }
        issuesPage.createIssue(issueType, summary, description, environment, issuePriority);
    }

    protected static void navigateToIssuesPage(String projectKey) {
        IssuesPage issuesPage = new IssuesPage(actions.getDriver(), projectKey);
        issuesPage.navigateToPage();
    }

    protected void clickOnStoryIssue() {
        actions.waitForElementPresent("jira.issuesPage.storyIcon");
        actions.clickElement("jira.issuesPage.storyIcon");
    }

    protected void linkStoryToBug(String bugKey, String storyKey, String relation) throws InterruptedException {
        IssuePage issuePage = new IssuePage(actions.getDriver());
        issuePage.linkStoryToBug(bugKey, storyKey, relation);
    }

}
