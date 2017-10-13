package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Boat;
import nl.yacht.lakesideresort.domain.LakeTrip;
import nl.yacht.lakesideresort.domain.RiverTrip;
import nl.yacht.lakesideresort.domain.Trip;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoatController {

    //Trip ArrayList
    private List<Trip> trips;
    private List<Boat> boatList;

    public BoatController(){
        trips = new ArrayList<>();
        boatList = new ArrayList<>();
        // add boat for 1 to 10 to the boatList(ArrayList)
        for(int index = 1; index <= 10; index++){
            boatList.add(new Boat(index));
        }
    }

    /**
     * List trips started today
     * @return
     */
    public List<Trip> getTodaysTrips() {
        return this.trips.stream()
            .filter(t -> t.startedToday())
            .collect(Collectors.toList());
    }

    public List<Boat> getBoatList() {
        return this.boatList;
    }

    public Trip findTrip(int tripNumber){
        Trip result = null;
        for(Trip t : trips){
            if(t.getTripNumber() == tripNumber) result = t;
        }
        return result;
    }

    //Berekenen gemiddelde duur Trip method
    public double calculateAverageDuration(){
        int total = 0;
        int amount = 0;


        for(Trip trip : trips){
            //Trip moet geendigd en van de huidige dag zijn
            if(trip.endedToday()){
                total += trip.getDuration().getSeconds() / 60;
                amount++;
            }
        }
        //*1.0 voor double respons
        return 1.0 * total / amount;
    }

    public void addTrip(Trip trip){
        this.trips.add(trip);
    }

    /**
     * Rent out a boat
     */
    public Trip rent(boolean riverTrip){
        int nr = getNewTripNumber();
        Trip trip = (riverTrip)?new RiverTrip(nr):new LakeTrip(nr);
        trips.add(trip);
        return trip;
    }

    private int getNewTripNumber() {
        int max = 0;
        for(Trip t : trips){
            int currentTripNumber = t.getTripNumber();
            if (currentTripNumber > max){
                max = currentTripNumber;
            }
        }
        return max + 1;
    }

    // Method to check if any boat is available
    public int checkBoats(){
        int availableBoats = 0;

        // For each boat in boatList
        for (Boat boat : boatList) {
            if (boat.isAvailable()){
                availableBoats++;
            }
        }
        return availableBoats;
    }

    // Print huidige duur & gemiddelde van alle boten
    public void printBoatTime(){

        System.out.println(calculateAverageDuration());

        for (Boat boat : boatList ){
            System.out.println("Boat " + boat.getNumber() + ": " + (boat.getTrip().getDuration().getSeconds()/60));
        }
    }

    /**
     * Returns the amount of trips that are ended today
     * @return
     */
    public int getNrEndedTrips(){
        int counter = 0;
        for(Trip trip : trips){
            LocalDateTime endTime = trip.getEndTime();
            if(endTime != null){
                if(trip.endedToday()){
                    counter++;
                }
            }
        }
        return counter;
    }
}
