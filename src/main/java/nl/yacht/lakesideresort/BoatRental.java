package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.Boat;
import nl.yacht.lakesideresort.domain.Trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoatRental {

    //Trip ArrayList
    private List<Trip> trips = new ArrayList<>();
    private List<Boat> boatList = new ArrayList<>();

    public BoatRental(){
        // add boat for 1 to 10 to the boatList(ArrayList)
        for(int index = 1; index <= 10; index++){
            boatList.add(new Boat(index));
        }
    }

    public List<Boat> getBoatList() {
        return this.boatList;
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
}
