package stepDefinitions;

import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import api.ApiMethods;
import java.util.List;

public class GetRequestsFunctionality {

    private ApiMethods apiMethods;
    private Response response;
    private String endPoint;

    public GetRequestsFunctionality() {
        apiMethods = new ApiMethods();
    }

    @Given("User has a endpoint to fetch comments for a specific post {string}")
    public void i_have_a_valid_endpoint(String url) {
        this.endPoint = apiMethods.buildEndpoint(url);
    }
    
    @Given("User has a endpoint to fetch all posts {string}")
    public void user_has_to_retrieve_posts_from_the_endpoint(String url) {
    	this.endPoint = apiMethods.buildEndpoint(url);
    }
    
    @Given("User has a endpoint to fetch details for a specific post {string}")
    public void user_has_to_retrieve_the_body_of_resource_with_id_one_from_the_endpoint(String url) {
    	this.endPoint = apiMethods.buildEndpoint(url);
    }

    @When("User sends a GET request to the endpoint")
    public void i_send_a_get_request_to_the_endpoint() {
        response = apiMethods.sendGetRequest(endPoint);
        
        response.prettyPrint();
        
        long responseTime = apiMethods.getResponseTime(response);
        System.out.println("Response Time: " + responseTime + " ms");
    }

    @Then("the status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        assertEquals((int) statusCode, response.getStatusCode());
    }

    @Then("validate wheather the response contains the {int} comments and print the comments")
    public void validate_whether_the_response_contains_the_comments_and_print_the_comments(Integer expectedCount) {
        List<Object> comments = response.jsonPath().getList("$");
        assertEquals((int) expectedCount, comments.size());

        JsonPath jsonPath = response.jsonPath();
        List<String> commentBody = jsonPath.getList("body");

        System.out.println("\n=== Comments ===");
        for (String comment : commentBody) {
            System.out.println(comment);
        }
        
        
    }
    
    @Then("the total number of posts should be {int} and print all posts")
    public void the_total_number_of_posts_should_be_and_print_all_posts(Integer expectedCount) {
        List<Object> posts = response.jsonPath().getList("$");
        assertEquals((int) expectedCount, posts.size());
        
        JsonPath jsonPath = response.jsonPath();
        List<String> titles = jsonPath.getList("title");

        System.out.println("\n=== Posts ===");
        for (String title : titles) {
            System.out.println(title);
        }
        
        
    	
    }
    
    @Then("the post ID should be {int} and print the post details")
    public void the_resource_id_should_be_and_print_the_resource_details(Integer expectedId) {
        int actualId = response.jsonPath().getInt("id");
        assertEquals((int) expectedId, actualId);
        
        JsonPath jsonPath = response.jsonPath();
        System.out.println("\n=== Post Details ===");
        System.out.println("Title: " + jsonPath.getString("title"));
        System.out.println("Body: " + jsonPath.getString("body"));
    }
    
    @Then("the status code should be {int} and an error message would be returned")
    public void the_response_status_code_should_be_and_error_message_should_be_returned(Integer statusCode) {
        assertEquals((int) statusCode, response.getStatusCode());
        String result = response.getBody().asString();
        System.out.println("Error Message: " + result);
    }
    	
    }
