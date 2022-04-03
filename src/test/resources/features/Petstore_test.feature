Feature: Petstore test
  #https://petstore.swagger.io/

  Scenario: 1 - Create pet
    Given user has "postCreatePetRQ" request with with following parameters
      | id        | name   | status    |
      | 999888777 | HauHau | available |

    When user sends "POST" "postCreatePetRQ" request

    Then "postCreatePetRS" code is "200"

  Scenario: 2 - Find pets by status
    Given user has "getPetsWithStatusRQ" request with "available" pet-status

    When user sends "GET" "getPetsWithStatusRQ" request

    Then "getPetsWithStatusRS" code is "200"
    And there is at least "1" pet in "getPetsWithStatusRS" response

  Scenario: 3 - Get pet by Id
    Given user has "getPetByIdRQ" request with id "999888777"

    When user sends "GET" "getPetByIdRQ" request

    Then "getPetByIdRS" code is "200"
    And pet's name returned in "getPetByIdRS" contains expected "HauHau"

  Scenario: 4 - Creating user
    Given user has "postCreateUserRQ" with following parameters
      | userName | firstName | lastName | email                 | password | phone     | userStatus |
      | StayAway | Wawel     | Dragon   | dragon.cave@krakow.pl | secret   | 123456789 | 0          |

    When user sends "POST" "postCreateUserRQ" request

    And "postCreateUserRS" code is "200"
    And user has "getUserByUsernameRQ" request with name "StayAway"
    And user sends "GET" "getUserByUsernameRQ" request
    Then "getUserByUsernameRS" code is "200"


  Scenario: 5 - Get user by name
    Given user has "getUserByNameRQ" request with name "StayAway"

    When user sends "GET" "getUserByNameRQ" request

    Then "getUserByNameRS" code is "200"
    And username returned in "getUserByNameRS" contains expected "StayAway"