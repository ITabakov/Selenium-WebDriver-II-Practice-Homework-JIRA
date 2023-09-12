package api;

import com.telerikacademy.testframework.Utils;
import io.restassured.response.Response;

public class ProjectModel{
    static JiraApi jiraApi = new JiraApi();
    private String id;
    private String key;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        String endpoint = Utils.getConfigPropertyByKey("jira.api.projectEndpoint") + id;
        Response response = jiraApi.getRestAssured()
                .get(endpoint);

        return response.getBody().jsonPath().getString("name");
    }

}
