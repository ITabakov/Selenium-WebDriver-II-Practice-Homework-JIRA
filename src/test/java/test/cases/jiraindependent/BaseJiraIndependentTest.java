package test.cases.jiraindependent;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import test.cases.BaseJiraTest;

public class BaseJiraIndependentTest extends BaseJiraTest {

    @BeforeEach
    public void setUp() {
        UserActions.loadBrowser("jira.homePage");
        String projectKey = null;
        while (projectKey == null) {
            project = jiraApi.createProject();
            projectKey = project.getKey();
        }
        String projectId = project.getId();
        Assertions.assertNotNull(projectKey, "The project was not created.");
        Assertions.assertNotNull(projectId, "The project was not created.");

        login();
        viewAllProjects();
        chooseProject(project.getName());
        navigateToIssuesPage(project.getKey());
    }

    @AfterEach
    public void tearDown() {
        logout();
        // Use it if you want to delete the project after the tests
        jiraApi.deleteProject(project.getId());
    }

    @AfterAll
    public static void afterTests() {
        UserActions.quitDriver();
    }

}
