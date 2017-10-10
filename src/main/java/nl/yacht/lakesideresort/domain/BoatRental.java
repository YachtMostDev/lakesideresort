package nl.yacht.lakesideresort.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoatRental {

    private List<Trip> trips;

    public BoatRental() {
        this.trips = new ArrayList<>();
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    /**
     * Rent out a boat
     */
    public void rent(){
        int nr = getNewTripNumber();
        Trip trip = new Trip(nr);
        trips.add(trip);
    }

    /**
     * Loops through the trips and returns the maximum tripnumber for the day
     * @return
     */
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

    /**
     * Returns the amount of trips that are ended today
     * @return
     */
    private int getNrEndedTrips(){
        int counter = 0;
        for(Trip trip : trips){
            LocalDateTime endTime = trip.getEndTime();
            if(endTime != null){
                LocalDateTime now = LocalDateTime.now();

                if((endTime.getDayOfYear() == now.getDayOfYear()) && (endTime.getYear() == now.getYear())){
                    counter++;
                }
            }
        }
        return counter;
    }
}
