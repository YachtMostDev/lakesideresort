package nl.yacht.lakesideresort.domain;

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

    public void rent(){
        int max = 0;
        for(Trip t : trips){
            int currentTripNumber = t.getTripNumber();
            if (currentTripNumber >max){
                max = currentTripNumber;
            }
        }
        Trip trip = new Trip(1);

        trips.add(trip);

    }
}
