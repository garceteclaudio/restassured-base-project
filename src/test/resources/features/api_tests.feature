Feature: Person API Operations

  Scenario: Create a new person successfully
    Given I set the base API URL
    And I prepare a valid person payload with:
      | name      | John Doe |
      | age       | 30       |
      | profession| Engineer|
      | isAdmin   | false    |
    When I send a POST request to "/person"
    Then the response status code should be 201
    And the response should match the person schema
    And the response should contain the created person details

  Scenario: Get all persons
    Given I set the base API URL
    When I send a GET request to "/person"
    Then the response status code should be 200
    And the response should be a non-empty array
    And each item in the array should match the person schema