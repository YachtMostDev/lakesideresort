package nl.yacht.lakesideresort.domain;

public class Boat {

    private int number;
    private Trip trip;

    public Boat(int number){
        this.number = number;
    }

    public Trip getTrip(){
        return this.trip;
    }

    public void setTrip(Trip trip){
        this.trip = trip;
    }

    public void removeTrip(){
        this.trip = null;
    }

    public boolean isAvailable() {
        return trip == null;
    }

    public int getNumber() {
        return number;
    }
}
