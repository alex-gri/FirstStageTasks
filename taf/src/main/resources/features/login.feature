Feature: Yandex disk log in
  As a yandex disk user
  I want to see my login at profile context menu after successful log in

  Scenario: Log in with valid credentials
    Given user is on home page
    And user has valid credentials
      | login                 | password    |
      | taf.alexander.gritsok | WebDriverGo |
    When user clicks log in
    And user enters login
    And user clicks submit
    And user enters password
    And user clicks submit
    And user clicks on his profile avatar
    Then user sees his login

  Scenario: Log in with invalid login
    Given user is on home page
    And user has invalid login
    When user clicks log in
    And user enters login
    And user clicks submit
    Then user sees error message

  Scenario: Log in with empty login
    Given user is on home page
    When user clicks log in
    And user clicks submit
    Then user sees error message

  Scenario: Log in with invalid password
    Given user is on home page
    And user has valid login and invalid password
    When user clicks log in
    And user enters login
    And user clicks submit
    And user enters password
    And user clicks submit
    Then user sees error message
