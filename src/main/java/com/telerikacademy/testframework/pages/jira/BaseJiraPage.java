package com.telerikacademy.testframework.pages.jira;

import com.telerikacademy.testframework.*;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseJiraPage extends BasePage {

    public BaseJiraPage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }

    public static void logout() {
        UserActions actions = new UserActions();
        actions.waitForElementClickable("jira.accountButton");
        actions.clickElement("jira.accountButton");
        actions.waitForElementClickable("jira.logoutButton");
        actions.clickElement("jira.logoutButton");
        actions.waitForElementClickable("jira.logoutSubmitButton");
        actions.clickElement("jira.logoutSubmitButton");
    }
}
