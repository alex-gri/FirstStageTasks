Feature: Yandex disk text document

  Scenario: Document is created in correct folder
    Given user is on Files page
    When user creates folder with <random name>
    And opens that folder
    And clicks create text document
    And closes that document
    Then user sees document inside folder with <random name>