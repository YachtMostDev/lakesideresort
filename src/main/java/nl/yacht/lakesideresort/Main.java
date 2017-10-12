package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.controller.GuestController;


public class Main {
    public static void main(String[] args) {

        BoatRental rental = new BoatRental();
        rental.rent();
        rental.rent();
        rental.rent();

        GuestController guestController = new GuestController();

        Guest guest1 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
        guestController.getGuestList().add(guest1);

        Guest guest2 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
        guestController.getGuestList().add(guest2);
        guestController.getGuestList().get(1).setGuestNumber(12);

        Guest guest3 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
        guestController.getGuestList().add(guest3);
        guest1.printGuest();
        guest2.printGuest();
        guest3.printGuest();
    }

    static void Sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
