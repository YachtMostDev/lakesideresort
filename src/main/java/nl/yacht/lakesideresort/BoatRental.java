package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.Trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoatRental {

    //Trip ArrayList
    List<Trip> trips = new ArrayList<>();

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


}
