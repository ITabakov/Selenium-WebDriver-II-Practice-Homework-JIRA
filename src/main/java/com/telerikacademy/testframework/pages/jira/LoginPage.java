package com.telerikacademy.testframework.pages.jira;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseJiraPage{
    public LoginPage(WebDriver driver) {
        super(driver, "jira.loginPage");
    }

    public void loginUser() {
        String username = getConfigPropertyByKey("jira.username");
        String password = getConfigPropertyByKey("jira.password");

        actions.waitForElementVisible("jira.loginPage.username");
        actions.typeValueInField(username, "jira.loginPage.username");
        actions.waitForElementClickable("jira.loginPage.loginSubmit");
        actions.clickElement("jira.loginPage.loginSubmit");

        actions.waitForElementVisible("jira.loginPage.password");
        actions.typeValueInField(password, "jira.loginPage.password");
        actions.waitForElementClickable("jira.loginPage.loginSubmit");
        actions.clickElement("jira.loginPage.loginSubmit");
    }
}
