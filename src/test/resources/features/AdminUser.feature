Feature: Admin User Management

  Scenario: Add and delete a system user
    Given I open the OrangeHRM website
    When I login with username "Admin" and password "admin123"
    And I go to Admin section
    And I store the number of existing users
    And I add a new user
    Then the user count should increase by 1
    When I search for the created user
    And I delete the created user
    Then the user count should decrease by 1