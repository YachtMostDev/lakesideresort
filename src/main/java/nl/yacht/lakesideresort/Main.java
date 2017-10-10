package nl.yacht.lakesideresort;

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
