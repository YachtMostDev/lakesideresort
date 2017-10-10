package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoatRental {

    //Trip ArrayList
    List<Trip> trips;

    public BoatRental() {
        this.trips = new ArrayList<>();
    }

    //Functie die trip toevoegd aan ArrayList
    public void addTrip(Trip trip){
        this.trips.add(trip);
    }

    //Berekenen gemiddelde duur Trip method
    public double calculateAverageDuration(){
        int total = 0;
        int amount = 0;
        LocalDate today = LocalDate.now();

        for(Trip trip : trips){
            //Trip moet geendigd en van de huidige dag zijn
            if(trip.getEndTime() != null && trip.getEndTime().toLocalDate().equals(today)){
                total += trip.getDuration().getSeconds() / 60;
                amount++;
            }
        }
        //*1.0 voor double respons
        return 1.0 * total / amount;
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
    public int getNrEndedTrips(){
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
