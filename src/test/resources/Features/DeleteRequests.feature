@DeleteFunctionality
Feature: To validate the delete request functionality

  @PositiveTC_01 @Smoke @Regression
  Scenario Outline: To delete a resource using DELETE call
    Given User has to delete a resource at endpoint "<pathParameter>"
    When User sends a DELETE request to the endpoint
    Then the status code has to be <statusCode> and resource should be deleted

    Examples: 
      | statusCode | pathParameter |
      |        200 | /posts/1      |

  @NegativeTC_01 @Regression
  Scenario Outline: To delete a non-existent resource using DELETE call
    Given User has to delete a resource at endpoint "<pathParameter>"
    When User sends a DELETE request to the endpoint
    Then the status code has to be <statusCode> and an error message should be returned

    Examples: 
      | statusCode | pathParameter |
      |        404 | /post/abc     |
