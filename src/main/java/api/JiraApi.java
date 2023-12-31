package api;

import com.google.gson.Gson;
import com.telerikacademy.testframework.PropertiesManager;
import com.telerikacademy.testframework.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.telerikacademy.testframework.Constants.*;

public class JiraApi {

    private static String getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
        return sdf.format(date);
    }

    private static String randomAlphanumericString(int length) {
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(alphanumericCharacters.length());
            char randomChar = alphanumericCharacters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    protected RequestSpecification getRestAssured() {
        Gson deserializer = new Gson();
        String baseUri = PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties()
                .getProperty("jira.api.baseUrl") + PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties()
                .getProperty("jira.api.version");
        String username = Utils.getConfigPropertyByKey("jira.username");
        String token = Utils.getConfigPropertyByKey("jira.apiToken");
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .baseUri(baseUri)
                .auth()
                .preemptive()
                .basic(username, token);
    }

    public ProjectModel createProject() {

        String projectKey = randomAlphanumericString(4);
        String projectName = PROJECT_NAME + getCurrentDateTime();

        String projectRequestBody = "{\n" +
                "\n" +
                "    \"key\": \"" + projectKey + "\",\n" +
                "\n" +
                "    \"name\": \"" + projectName + "\",\n" +
                "\n" +
                "    \"projectTypeKey\": \"" + PROJECT_TYPE + "\",\n" +
                "\n" +
                "    \"projectTemplateKey\": \"" + PROJECT_TEMPLATE + "\",\n" +
                "\n" +
                "    \"description\": \"" + PROJECT_DESCRIPTION + "\",\n" +
                "\n" +
                "    \"leadAccountId\": \"" + "%s" + "\",\n" +
                "\n" +
                "    \"url\": \"" + PROJECT_URL + "\",\n" +
                "\n" +
                "    \"assigneeType\": \"" + PROJECT_ASSIGNEE_TYPE + "\",\n" +
                "\n" +
                "    \"avatarId\": " + PROJECT_AVATAR_ID + "\n" +
                "\n" +
                "}";

        String endpoint = Utils.getConfigPropertyByKey("jira.api.projectEndpoint");
        String accountId = getAccountId();

        ProjectModel projectModel = getRestAssured()
                .body(String.format(projectRequestBody, accountId))
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response()
                .as(ProjectModel.class);

        return projectModel;
    }

    public void deleteProject(String projectId) {

        String endpoint = Utils.getConfigPropertyByKey("jira.api.projectEndpoint") + projectId;

        Response response = getRestAssured()
                .delete(endpoint);

        int statusCode = response.statusCode();
        Assert.assertEquals(String.format("Wrong status code. Expected 204, but was %s", statusCode), 204, statusCode);

    }

    private String getAccountId() {

        Response response = getRestAssured()
                .header("Content-type", "application/json")
                .get(String.format(Utils.getConfigPropertyByKey("jira.api.userEndpoint"),
                        Utils.getConfigPropertyByKey("jira.username")));

        return response.body().jsonPath().getString("[0].accountId");
    }

}