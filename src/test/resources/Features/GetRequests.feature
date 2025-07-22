@GetFunctionality
Feature: To validate the get request funtionality

  @GetComments @Regression
  Scenario Outline: To validate comments for a specific post
    Given User has a endpoint to fetch comments for a specific post "<pathParameter>"
    When User sends a GET request to the endpoint
    Then the status code should be <statusCode>
    And validate wheather the response contains the <numberOfComments> comments and print the comments

    Examples: 
      | statusCode | numberOfComments | pathParameter     |
      |        200 |                5 | /posts/1/comments |

  @GetPosts @Regression
  Scenario Outline: To validate the number of posts
    Given User has a endpoint to fetch all posts "<pathParameter>"
    When User sends a GET request to the endpoint
    Then the status code should be <statusCode>
    And the total number of posts should be <numberOfPosts> and print all posts

    Examples: 
      | statusCode | numberOfPosts | pathParameter |
      |        200 |           100 | /posts        |

  @GetPost_01
  Scenario Outline: To validate a post resource with ID one
    Given User has a endpoint to fetch details for a specific post "<pathParameter>"
    When User sends a GET request to the endpoint
    Then the status code should be <statusCode>
    And the post ID should be <postId> and print the post details

    Examples: 
      | statusCode | postId | pathParameter |
      |        200 |      1 | /posts/1      |

  @NegativeTC_01 @Regression
  Scenario Outline: To validate an attempt to fetch details for a non-existent post
    Given User has a endpoint to fetch details for a specific post "<pathParameter>"
    When User sends a GET request to the endpoint
    Then the status code should be <statusCode> and an error message would be returned

    Examples: 
      | statusCode | pathParameter |
      |        404 | /posts/9999   |
