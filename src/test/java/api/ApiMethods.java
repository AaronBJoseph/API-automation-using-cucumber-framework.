package api;

//import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import pojo.JsonBody;

public class ApiMethods {
    private EnvironmentVariables environmentVariables;
    private String baseUrl;

    public ApiMethods() {
        
        environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
      
        baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("requestUrl");
    }

    public String buildEndpoint(String url) {
        return baseUrl.concat(url);
    }

    public Response sendDeleteRequest(String url) {
        return SerenityRest.given()
                .relaxedHTTPSValidation()
                .when()
                .delete(url);
    }

    public Response sendGetRequest(String url) {
        return SerenityRest.given()
               .relaxedHTTPSValidation()
                .when()
                .get(url);
    }

    public Response sendPostRequest(String url, JsonBody body) {
        return SerenityRest.given()
                .relaxedHTTPSValidation()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(body)
                .when()
                .post(buildEndpoint(url));
    }

    public long getResponseTime(Response response) {
        return response.time();
    }
    
   
}
