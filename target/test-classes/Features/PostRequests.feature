@PostFunctionality
Feature: To validate the post request functionality

  @PositiveTC_01 @Smoke @Regression
  Scenario Outline: Create a resource using POST call
    Given User has a endpoint to create a resource with "<title>" "<body>" "<userId>"
    When User sends a POST request to the endpoint "<pathParameter>"
    Then the status code should be <statusCode> the response should contain the created resource

    Examples: 
      | title | body | userId | statusCode | pathParameter |
      | foo   | bar  |      1 |        201 | /posts/       |

  @NegativeTC_01 @Regression
  Scenario Outline: Attempt to create a resource without required fields using POST call
    Given User has a endpoint to create a resource with "<title>" "<body>" "<userId>"
    When User sends a POST request to the endpoint "<pathParameter>"
    Then the status code should be <statusCode> and an error message should be returned

    Examples: 
      | title | body | userId | statusCode | pathParameter |
      |       |      |        |        404 | /post/        |
