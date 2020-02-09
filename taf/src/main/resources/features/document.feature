Feature: Yandex disk text document

  Scenario: Document is created in correct folder
    Given user is on Files page
    When user creates folder with <random folder name>
    And opens that folder
    And creates text document
    And user sets <random document name>
    And closes that document
    Then user sees document inside folder with <random folder name>

  Scenario: Document is created, saved and reopened correctly
    Given user is on Files page
    When user creates folder with <random folder name>
    And opens that folder
    And creates text document
    And user types <text> into it
    And user sets <random document name>
    And closes that document
    And opens that document
    Then user sees <text> that he typed earlier

  Scenario: Document is created, saved, deleted and should be only in trash
    Given user is on Files page
    When user creates folder with <random folder name>
    And opens that folder
    And creates text document
    And user sets <random document name>
    And closes that document
    And deletes that document
    Then user should not see document in the current folder
    #But can see it in the trash