Feature: Using the BoatController using the TripGui

  Scenario: Create a trip and end a trip

    Given I start a trip and rent a boat
    When I end the trip
    Then I have total 1 trips today
    Then I have average 1 trips today
    Then I have 1 ended trips today
