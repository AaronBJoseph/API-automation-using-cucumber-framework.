package stepDefinitions;

import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import api.ApiMethods;

public class DeleteRequestsFunctionality {

    private ApiMethods apiMethods;
    private Response response;
	private String endPoint;

    public DeleteRequestsFunctionality() {
        apiMethods = new ApiMethods();
    }

    @Given("User has to delete a resource at endpoint {string}")
    public void i_have_a_valid_endpoint_at_this_website(String url) {
        this.endPoint = apiMethods.buildEndpoint(url);
    }

    @When("User sends a DELETE request to the endpoint")
    public void i_send_a_delete_request_to_the_endpoint() {
        response = apiMethods.sendDeleteRequest(endPoint);
        
        response.prettyPrint();
        
        long responseTime = apiMethods.getResponseTime(response);
        System.out.println("Response Time: " + responseTime + " ms");
    }

    @Then("the status code has to be {int} and resource should be deleted")
    public void the_response_status_code_have_to_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
        String result = response.getBody().asString();
        assertEquals("{}", result);
        System.out.println("Resource has been marked as deleted.");
    }
    
    @Then("the status code has to be {int} and an error message should be returned")
    public void the_response_status_code_should_be_and_error_message_should_be_returned(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
        String result = response.getBody().asString();
        System.out.println("Error Message: " + result);
    }
}


