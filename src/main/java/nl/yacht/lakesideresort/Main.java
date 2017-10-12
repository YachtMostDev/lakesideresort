package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.controller.GuestController;


public class Main {
    public static void main(String[] args) {

//        BoatRental rental = new BoatRental();
//        rental.rent();
//        rental.rent();
//        rental.rent();

        GuestController guestController = new GuestController();

        //Guest guest1 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
        //guestController.getGuestList().add(guest1);

        guestController.createNewGuest("achternaam", "voornaam", "adres", "postcode", "stad", "land", "0599330930", "email adres");
        guestController.createNewGuest("huisman", "richard", "abc", "def", "gasd", "lansdfd", "5654231384", "emaisdfsdfl adres");

//        Guest g1 = guestController.getGuestFromList(1);
//        if (g1 != null){
//            g1.printGuest();
//        }
        Guest g2 = guestController.getGuestFromList(2);
        if (g2 != null){
           g2.printGuest();
           g2.updateGuest(g2.getSurname(),
                   "tom",
                   g2.getAdress(),
                   g2.getPostalCode(),
                   g2.getCity(),
                   "Belgie",
                   g2.getPhoneNumber(),
                   "mijnnieuweEmail@address.nl");
           g2.setFirstName("Tom");
           g2.updateGuest(null, null, null, null,null,null,null,"nieuwe@mailadres.nl");
        }


//        Guest guest2 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
//        guestController.getGuestList().add(guest2);
//        guestController.getGuestList().get(1).setGuestNumber(12);
//
//        Guest guest3 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
//        guestController.getGuestList().add(guest3);
//        guest1.printGuest();
//        guest2.printGuest();
//        guest3.printGuest();
    }

    static void Sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
