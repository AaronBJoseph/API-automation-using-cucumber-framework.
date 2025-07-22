package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import api.ApiMethods;
import pojo.JsonBody;


import static org.junit.Assert.*;

//import org.apache.poi.hpsf.Thumbnail;

public class PostRequestsFunctionality {
    
    private ApiMethods apiMethods;
    private JsonBody requestBody;
    private Response response;

    public PostRequestsFunctionality() {
        apiMethods = new ApiMethods();
    }

    @Given("User has a endpoint to create a resource with {string} {string} {string}")
    public void i_have_a_json_body_with(String title, String body, String userId) {
        requestBody = new JsonBody();
        requestBody.setTitle(title);
        requestBody.setBody(body);
        requestBody.setUserId(userId);
      
        }
    

    @When("User sends a POST request to the endpoint {string}")
    public void i_send_a_post_request_to_the_endpoint(String url) {
        response = apiMethods.sendPostRequest(url, requestBody);
        
        long responseTime = apiMethods.getResponseTime(response);
        System.out.println("Response Time: " + responseTime + " ms");
        
        response.prettyPrint();
    }

    @Then("the status code should be {int} the response should contain the created resource")
    public void response_status_code_should_be(Integer statusCode) {
        assertEquals((int) statusCode, response.getStatusCode());
        JsonPath jsonPath = response.jsonPath();
        System.out.println("\n=== Created Resource ===");
        System.out.println("Title: " + jsonPath.getString("title"));
        System.out.println("Body: " + jsonPath.getString("body"));
        System.out.println("User ID: " + jsonPath.getInt("userId"));

        assertNotNull(jsonPath.getString("id"));
    }
    
    @Then("the status code should be {int} and an error message should be returned")
    public void response_status_code_should_be_and_error_message_should_be_returned(Integer statusCode) {
        assertEquals((int) statusCode, response.getStatusCode());
        String result = response.getBody().asString();
        System.out.println("Error Message: " + result);
    }
}


