package com.telerikacademy.testframework.pages.jira;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class IssuePage extends BaseJiraPage{
    public IssuePage(WebDriver driver) {
        super(driver, Utils.getConfigPropertyByKey("jira.issuePage"));
    }

    public void navigateToPage(String issueKey) {
        String issuePageUrl = getIssuePageUrl(issueKey);
        this.driver.get(issuePageUrl);

    }

    private static String getIssuePageUrl(String issueKey) {
        return String.format(Utils.getConfigPropertyByKey("jira.issuePage"), issueKey);
    }

    public void linkStoryToBug(String bugKey, String storyKey, String relation) throws InterruptedException {
        IssuePage issuePage = new IssuePage(driver);
        issuePage.navigateToPage(storyKey);
        actions.waitForElementClickable("jira.issuePage.linkButton");
        actions.clickElement("jira.issuePage.linkButton");
        actions.waitForElementClickable("jira.issuePage.selectRelationInput");
        actions.typeValueInField(relation, "jira.issuePage.selectRelationInput");
        actions.pressKey(Keys.ENTER);
        actions.waitForElementPresent("jira.issuePage.issueSearch");
        actions.typeValueInField(bugKey, "jira.issuePage.issueSearch");
        actions.pressKey(Keys.ENTER);
        actions.waitForElementPresent("jira.issuePage.linkSubmitButton");
        actions.clickElement("jira.issuePage.linkSubmitButton");
    }

}
