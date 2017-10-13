package nl.yacht.lakesideresort.controller.gui;

import nl.yacht.lakesideresort.controller.BoatController;
import nl.yacht.lakesideresort.domain.Trip;

import java.io.IOException;

/**
 * Created by njvan on 11-Oct-17.
 */
@Gui(name="Trip")
public class TripGui extends Command {
    private BoatController boatCtrl;
    public TripGui(){
        boatCtrl = new BoatController();
    }

    public void start() throws IOException {
        String[][] definition = {
            {"Is it a lake trip or a river trip [Lake, River]?","LAKE|RIVER"}
        };
        Object[] inputs = InputHandler.handleInput(definition);
        Trip trip = boatCtrl.rent("river".equalsIgnoreCase(inputs[0].toString()));
        System.out.println("A trip has started with trip number: " + trip.getTripNumber());
    }

    public void end() throws IOException {
        String[][] definition = {
            {"What is the tripnumber?","\\d+"}
        };
        Object[] inputs = InputHandler.handleInput(definition);
        Trip trip = boatCtrl.findTrip(Integer.parseInt(inputs[0].toString()));
        if(trip == null){
            System.out.println("This is an invalid tripnumber!");
        } else {
            trip.end();
//            System.out.println("Trip with tripnumber " + trip.getTripNumber() + " has ended.");
//            System.out.println(String.format("Trip with tripnumber %d has ended.",trip.getTripNumber()));
//            This last one needs to end with an %n if you want a newline
            System.out.printf("Trip with tripnumber %d has ended.%n",trip.getTripNumber());
        }
    }

    public void duration() throws IOException {
        String[][] definition = {
                {"What is the tripnumber?","\\d+"}
        };
        Object[] inputs = InputHandler.handleInput(definition);
        Trip trip = boatCtrl.findTrip(Integer.parseInt(inputs[0].toString()));
        if(trip == null){
            System.out.println("This is an invalid tripnumber!");
        } else {
            System.out.println(String.format("The duration of trip %d is: %d minutes",trip.getTripNumber(),(trip.getDuration().getSeconds()/60)));
        }
    }

    public void average(){
        System.out.println(String.format("The day average duration is: %f", boatCtrl.calculateAverageDuration()));
    }

    public void ended(){
        System.out.println(String.format("The amount of ended trips today is: %d", boatCtrl.getNrEndedTrips()));
    }
}
