Feature: Using the BoatController using the TripGui

  Scenario: Create a trip and end a single trip

    Given I start a trip and rent a boat
    When I end the trip
    Then I have total 1 trips today
    And I have 1 ended trips today

  Scenario: Create two trips and end both trips :-)


    Given I start a trip and rent a boat
    And I end the trip
    And I start a trip and rent a boat
    And I end the trip
    Then I have total 2 trips today
    And I have 2 ended trips today


