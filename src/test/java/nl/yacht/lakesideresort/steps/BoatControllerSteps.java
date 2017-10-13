package nl.yacht.lakesideresort.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.yacht.lakesideresort.controller.BoatController;
import nl.yacht.lakesideresort.domain.Trip;
import org.junit.Assert;

public class BoatControllerSteps {

    private BoatController boatController = new BoatController();

    private Trip trip;

    @Given("^I start a trip and rent a boat")
    public void iRentATrip() throws Throwable {
        this.trip = this.boatController.rent(true);
    }

    @When("^I end the trip$")
    public void iEndTheTrip() {
        this.trip.end();
    }

    @Then("^I have total (\\d+) trips today$")
    public void iHaveTotalTripsToday(int expectedTripsToday) throws Throwable {

        Assert.assertEquals(expectedTripsToday, this.boatController.getTodaysTrips().size());

    }

    @Then("^I have average (\\d+) trips today$")
    public void iHaveAverageTripsToday(double extectedTripsAverageToday) throws Throwable {



//        Assert.assertTrue(extectedTripsAverageToday == this.boatController.calculateAverageDuration());

    }

    @Then("^I have (\\d+) ended trips today$")
    public void iHaveEndedTripsToday(int expectedTodaysEndedTrips) throws Throwable {
        Assert.assertEquals(expectedTodaysEndedTrips, this.boatController.getNrEndedTrips());
    }
}
