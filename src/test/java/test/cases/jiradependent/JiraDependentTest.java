package test.cases.jiradependent;

import com.telerikacademy.testframework.Utils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.telerikacademy.testframework.Constants.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JiraDependentTest extends BaseJiraDependentTest {

    private static String bugKey;
    private static String storyKey;

    @Test
    public void T1_createStory() throws InterruptedException {
        createIssue(project.getKey(), STORY_ISSUE_TYPE, STORY_SUMMARY, STORY_DESCRIPTION, TEST_ENVIRONMENT, PRIORITY_HIGH);
        // issue creation validation
        actions.waitForElementPresent("jira.issuesPage.successPopup");
        storyKey = actions.getElementAttribute(Utils.getUIMappingByKey("jira.issuesPage.successPopup"), "data-issue-key");
        // refresh
        actions.getDriver().navigate().refresh();
    }

    @Test
    public void T2_createBug() throws InterruptedException {
        createIssue(project.getKey(), BUG_ISSUE_TYPE, BUG_SUMMARY, BUG_DESCRIPTION, TEST_ENVIRONMENT, PRIORITY_HIGH);
        // issue creation validation
        actions.waitForElementPresent("jira.issuesPage.successPopup");
        bugKey = actions.getElementAttribute(Utils.getUIMappingByKey("jira.issuesPage.successPopup"), "data-issue-key");
        //refresh
        actions.getDriver().navigate().refresh();
    }

    @Test
    public void T3_linkIssues() throws InterruptedException {
        linkStoryToBug(bugKey, storyKey, IS_BLOCKED_BY_RELATION);
        // linking validation
        actions.waitForElementPresent("jira.issuePage.linkedIssuesHeader");
        actions.waitForElementPresent("jira.issuePage.linkedIssuesKey", bugKey);
    }

}
