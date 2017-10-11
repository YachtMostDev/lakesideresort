package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.controller.BoatController;

public class Main {
    public static void main(String[] args) {

        BoatController rental = new BoatController();
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
