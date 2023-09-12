package com.telerikacademy.testframework.pages.jira;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.telerikacademy.testframework.Constants.*;

public class IssuesPage extends BaseJiraPage {

    private static String issuesPageUrl;
    private static Actions action;

    public IssuesPage(WebDriver driver, String projectKey) {
        super(driver, getPageUrl(projectKey));
        action = new Actions(driver);
    }

    private static String getPageUrl(String projectKey) {
        issuesPageUrl = String.format(Utils.getConfigPropertyByKey("jira.issuesPage"), projectKey);
        return issuesPageUrl;
    }

    public void navigateToPage() {
        driver.get(issuesPageUrl);
    }

    public void createIssue(String issueType, String summary, String description, String environment, String priority) throws InterruptedException {
        actions.waitForElementClickable("jira.issuesPage.createIssueButton");
        Thread.sleep(1000);
        actions.clickElement("jira.issuesPage.createIssueButton");
        actions.waitForElementPresent("jira.issuesPage.issueTypeMenu");
        Thread.sleep(1000);
        actions.typeValueInField(issueType, "jira.issuesPage.issueTypeMenu");
        Thread.sleep(1000);
        actions.pressKey(Keys.ENTER);
        actions.waitForElementPresent("jira.issuesPage.issueNameInput");
        actions.typeValueInField(summary, "jira.issuesPage.issueNameInput");
        WebElement descriptionField = driver.findElement(By.xpath(Utils.getUIMappingByKey("jira.issuesPage.description")));
        action.scrollToElement(descriptionField).build().perform();
        actions.waitForElementPresent("jira.issuesPage.description");
        actions.typeValueInField(description, "jira.issuesPage.description");
        if (issueType.equals("Bug")) {
            WebElement environmentField = driver.findElement(By.xpath(Utils.getUIMappingByKey("jira.issuesPage.description")));
            action.scrollToElement(environmentField).build().perform();
            actions.waitForElementPresent("jira.issuesPage.environment");
            actions.typeValueInField(environment, "jira.issuesPage.environment");
        }
        if (!priority.equals(PRIORITY_MEDIUM)) {
            WebElement priorityField = driver.findElement(By.xpath(Utils.getUIMappingByKey("jira.issuesPage.priorityMenu")));
            action.scrollToElement(priorityField).build().perform();
            Thread.sleep(1000);
            actions.waitForElementPresent("jira.issuesPage.priorityMenu");
            actions.typeValueInField(priority, "jira.issuesPage.priorityMenu");
            if (priority.equals(PRIORITY_HIGH) || priority.equals(PRIORITY_LOWEST)) {
                action.sendKeys(driver.findElement(By.xpath(Utils.getUIMappingByKey("jira.issuesPage.priorityMenu"))),
                        Keys.ARROW_DOWN).build().perform();
            }
            Thread.sleep(1000);
            actions.pressKey(Keys.ENTER);
        }
        actions.clickElement("jira.issuesPage.createButton");
        Thread.sleep(1000);
    }

}
