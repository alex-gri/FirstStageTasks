Feature: Yandex disk log in
  As a yandex disk user
  I want to see my login at profile context menu after successful log in

  Scenario: Log in with valid credentials
    Given user is on home page
    And user has valid credentials
    When user clicks log in
    And user enters login
    And user clicks submit
    And user enters password
    And user clicks submit
    And user clicks on his profile avatar
    Then user sees his login
