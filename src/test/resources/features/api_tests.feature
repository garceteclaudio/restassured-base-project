Feature: API Testing with Rest Assured


  Scenario: Verify POST request to create a post
    Given the API base URL is "https://jsonplaceholder.typicode.com"
    And I have a request body with title "foo" and body "bar" and userId 1
    When I send a POST request to "/posts"
    Then the response status code should be 201
    And the response should contain "title" with value "foo"
    And the response should contain "body" with value "bar"
    And the response should contain "userId" with value 1
    And the response should contain a non-null "id"


  Scenario: Verify GET request for the previously created post
    Given the API base URL is "https://jsonplaceholder.typicode.com"
    When I send a GET request to the created post
    Then the response status code should be 200
    And the response should contain "userId" with value 1
    And the response should contain "title" with value "foo"
    And the response should contain "body" with value "bar"
    And the response should contain "id" with the captured post ID