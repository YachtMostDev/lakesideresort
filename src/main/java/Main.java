public class Main {
    public static void main(String[] args) {

        /// Create Trip
        // set tripnumber
        int tripNumber = 1;
        // create trip
        Trip newTrip = new Trip(tripNumber);

        // sleep for 4 seconds
        Sleep();

        // end the trip
        newTrip.end();

        // print results
        System.out.println("Trip: " + newTrip.getTripNumber() + " has ended.");
        System.out.println("Start: " + newTrip.getStartTime());
        System.out.println("End: " + newTrip.getEndTime());
        System.out.println("Duration: " + newTrip.getDuration());
        System.out.println();

    }

    static void Sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
