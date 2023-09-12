package test.cases.jiraindependent;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.Constants.*;

public class JiraIndependentTest extends BaseJiraIndependentTest {

    private static String bugKey;
    private static String storyKey;

    @Test
    public void createStory() throws InterruptedException {
        createIssue(project.getKey(), STORY_ISSUE_TYPE, STORY_SUMMARY, STORY_DESCRIPTION, TEST_ENVIRONMENT, PRIORITY_HIGH);
        // issue creation validation
        actions.waitForElementPresent("jira.issuesPage.successPopup");
        storyKey = actions.getElementAttribute(Utils.getUIMappingByKey("jira.issuesPage.successPopup"), "data-issue-key");
        // refresh
        actions.getDriver().navigate().refresh();
    }

    @Test
    public void createBug() throws InterruptedException {
        createIssue(project.getKey(), BUG_ISSUE_TYPE, BUG_SUMMARY, BUG_DESCRIPTION, TEST_ENVIRONMENT, PRIORITY_HIGH);
        // issue creation validation
        actions.waitForElementPresent("jira.issuesPage.successPopup");
        bugKey = actions.getElementAttribute(Utils.getUIMappingByKey("jira.issuesPage.successPopup"), "data-issue-key");
        //refresh
        actions.getDriver().navigate().refresh();
    }

    @Test
    public void linkIssues() throws InterruptedException {
        // Create Story
        createIssue(project.getKey(), STORY_ISSUE_TYPE, STORY_SUMMARY, STORY_DESCRIPTION, TEST_ENVIRONMENT, PRIORITY_HIGH);
        // issue creation validation
        actions.waitForElementPresent("jira.issuesPage.successPopup");
        storyKey = actions.getElementAttribute(Utils.getUIMappingByKey("jira.issuesPage.successPopup"), "data-issue-key");
        // refresh
        actions.getDriver().navigate().refresh();

        // Create Bug
        createIssue(project.getKey(), BUG_ISSUE_TYPE, BUG_SUMMARY, BUG_DESCRIPTION, TEST_ENVIRONMENT, PRIORITY_HIGH);
        // issue creation validation
        actions.waitForElementPresent("jira.issuesPage.successPopup");
        bugKey = actions.getElementAttribute(Utils.getUIMappingByKey("jira.issuesPage.successPopup"), "data-issue-key");
        //refresh
        actions.getDriver().navigate().refresh();

        // Link
        linkStoryToBug(bugKey, storyKey, IS_BLOCKED_BY_RELATION);
        // linking validation
        actions.waitForElementPresent("jira.issuePage.linkedIssuesHeader");
        actions.waitForElementPresent("jira.issuePage.linkedIssuesKey", bugKey);
    }

}
