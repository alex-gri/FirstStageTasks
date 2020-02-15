Feature: Yandex disk files

  Scenario: Document is created, saved and reopened correctly
    Given user is on Files page
    When user creates folder with <random folder name>
    And opens that folder
    And creates text document
    And user types "Hello World!" into it
    And user sets <random document name>
    And closes that document
    And opens that document
    Then user sees <text> that he typed earlier

  Scenario: Create folder, delete it, empty trash and it should be empty
    Given user is on Files page
    When user creates folder with <random folder name>
    And deletes that folder
    And user clicks <Trash>
    And user clicks Empty Trash
    Then trash should be empty

  Scenario: Document is created in correct folder
    Given user is on Files page
    When user creates folder with <random folder name>
    And opens that folder
    And creates text document
    And user sets <random document name>
    And closes that document
    Then user sees document inside folder with <random folder name>

  Scenario: Document is created, saved, deleted and should be only in trash
    Given user is on Files page
    When user creates folder with <random folder name>
    And opens that folder
    And creates text document
    And user sets <random document name>
    And closes that document
    And deletes that document
    Then user should not see document in the current folder
    But can see it in the trash

  Scenario: Create folder, open it and see it's correct name as a title
    Given user is on Files page
    When user creates folder with <random folder name>
    And opens that folder
    Then user should see correct <random folder name> in the title

  Scenario: Click Archive in menu and check if it opens
    Given user is on Files page
    When user clicks <Archive>
    Then he reaches the <Archive> page

  Scenario: Click Files in menu and check if it opens
    Given user is on Files page
    When user clicks <Archive>
    And user clicks <Files>
    Then he reaches the <Files> page

  Scenario: Click History in menu and check if it opens
    Given user is on Files page
    When user clicks <History>
    Then he reaches the <History> page

  Scenario: Click Photo in menu and check if it opens
    Given user is on Files page
    When user clicks <Photo>
    Then he reaches the <Photo> page

  Scenario: Click Recent in menu and check if it opens
    Given user is on Files page
    When user clicks <Recent>
    Then he reaches the <Recent> page

  Scenario: Click Shared in menu and check if it opens
    Given user is on Files page
    When user clicks <Shared>
    Then he reaches the <Shared> page

  Scenario: Click Trash in menu and check if it opens
    Given user is on Files page
    When user clicks <Trash>
    Then he reaches the <Trash> page


