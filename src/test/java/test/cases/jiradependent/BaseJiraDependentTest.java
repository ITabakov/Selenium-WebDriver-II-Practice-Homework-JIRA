package test.cases.jiradependent;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import test.cases.BaseJiraTest;

public class BaseJiraDependentTest extends BaseJiraTest {
    @BeforeClass
    public static void beforeTestSetUp() {
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

    @AfterClass
    public static void afterTestTearDown() {
        // Use it if you want to delete the project after the tests
        jiraApi.deleteProject(project.getId());
        UserActions.quitDriver();
    }

}
