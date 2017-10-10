package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.BoatRental;
import nl.yacht.lakesideresort.domain.LakeTrip;
import nl.yacht.lakesideresort.domain.RiverTrip;
import nl.yacht.lakesideresort.domain.Trip;

public class Main {
    public static void main(String[] args) {

        BoatRental rental = new BoatRental();
        rental.rent();
        rental.rent();
        rental.rent();
    }

    static void Sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
