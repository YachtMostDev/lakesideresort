package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.LakeTrip;
import nl.yacht.lakesideresort.domain.RiverTrip;
import nl.yacht.lakesideresort.domain.Trip;

public class Main {
    public static void main(String[] args) {

        /// Create Trip
        // set tripnumber
        int tripNumber = 1;
        // create trip
        //Trip newTrip = new Trip(tripNumber);
        Trip meerTocht = new LakeTrip(1);



        // sleep for 4 seconds
        Sleep();

        Trip rivierTocht = new RiverTrip(2);
        Sleep();

        // end the trip
        meerTocht.end();
        rivierTocht.end();
        // print results
        System.out.println(meerTocht);
//        System.out.println("Trip: " + meerTocht.getTripNumber() + " has ended.");
//        System.out.println("Start: " + meerTocht.getStartTime());
        System.out.println("End: " + meerTocht.getEndTime());
        System.out.println("Duration: " + meerTocht.getDuration());

//        System.out.println("Type: " + meerTocht.getTripType());
//        System.out.println();
//        System.out.println("Trip: " + rivierTocht.getTripNumber() + " has ended.");
//        System.out.println("Start: " + rivierTocht.getStartTime());
//        System.out.println("End: " + rivierTocht.getEndTime());
//        System.out.println("Duration: " + rivierTocht.getDuration());
//        System.out.println("Type: " + rivierTocht.getTripType());
//        System.out.println();
    }

    static void Sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
