package nl.yacht.lakesideresort.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.yacht.lakesideresort.controller.BoatController;
import nl.yacht.lakesideresort.domain.Boat;
import nl.yacht.lakesideresort.domain.Trip;

public class BoatCreateSteps {

    private BoatController boatController = new BoatController();



    @When("^I rent a trip")
    public void iRentATrip() throws Throwable {
        Trip trip = this.boatController.rent(true);
    }

    @Then("^I have a trip with id not null$")
    public void iHaveATripWithIdNotNull() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }



    @Then("^I have a boat with id (\\d+)$")
    public void iHaveBoatWithId(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
