package com.telerikacademy.testframework;

import java.sql.Date;

public class Constants {

    public static final String PROJECT_NAME = "SELENIUM WEBDRIVER II ";
    public static final String PROJECT_TEMPLATE = "com.pyxis.greenhopper.jira:gh-scrum-template";
    public static final String PROJECT_TYPE = "software";
    public static final String PROJECT_DESCRIPTION = "Project for Selenium Webdriver II Homework";
    public static final String PROJECT_URL = "http://atlassian.com";
    public static final String PROJECT_ASSIGNEE_TYPE = "PROJECT_LEAD";
    public static final String PROJECT_AVATAR_ID = "10200";
    public static final String STORY_SUMMARY = "Upload image with maximum size of 4 MB";
    public static final String STORY_DESCRIPTION = "As an end user, that is successfully registered and logged in, " +
            "I want to be able to upload an image with maximum size of 4 MB to add real " +
            "value and insight into my post and to ensure that there will not be quality loss" +
            " or slow down of the website.";
    public static final String TEST_ENVIRONMENT = "OS: Windows 11 Pro -> Version 22H2;\n" +
            "Browser: Google Chrome -> Version 116.0.5845.142 (Official Build) (64-bit)";
    public static final String BUG_SUMMARY = "Uploaded image size is bigger than required";
    public static final String BUG_DESCRIPTION = "*Description:*\nIn order to upload image.\n*Preconditions:*\n" +
            "Staging forum:\nAn existing user that is successfully registered and logged in." +
            "\nThe user has access to the certain topic where they can create a new comment and" +
            " upload image.\n*Test Steps:*\nClick on the title of an existing topic\n" +
            "Click on the “Reply” button\nClick on the “Upload” button\nSelect picture" +
            " from computer bigger than 4 MB\nClick “Open” button\nClick “Reply” button\n" +
            "*Expected result:*\nA pop-up with error message appears “Sorry, that file is too big " +
            "(maximum size is 4 MB). Why not upload your large file to a cloud sharing service, then " +
            "paste the link?“\n*Actual result:*\nThe window with text box disappeared\n" +
            "The picture in the comment appears below the topic\n*Severity:*\nHigh";
    public static final String BUG_ISSUE_TYPE = "Bug";
    public static final String STORY_ISSUE_TYPE = "Story";
    public static final String PRIORITY_HIGHEST = "Highest";
    public static final String PRIORITY_HIGH = "High";
    public static final String PRIORITY_MEDIUM = "Medium";
    public static final String PRIORITY_LOW = "Low";
    public static final String PRIORITY_LOWEST = "Lowest";
    public static final String IS_BLOCKED_BY_RELATION = "is blocked by";
}
