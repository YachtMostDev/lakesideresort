package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Trip;

import java.util.ArrayList;
import java.util.List;

public class GuestController {

    private ArrayList<Guest> guestList = new ArrayList<>();

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void createNewGuest(String surname, String firstName, String address, String postalCode, String city, String country, String phoneNumber, String mailAdress){
        int guestNumber = generateGuestNumber();
        Guest g = new Guest(guestNumber, surname, firstName, address, postalCode, city, country, phoneNumber, mailAdress);
        this.guestList.add(g);
        System.out.println("successfully created guest with number: " + guestNumber);
    }

    public Guest getGuestFromList(int guestnumber){
        // loop door de lijst
        for(Guest g : this.guestList){
            // controleer of het ingevoerde nummber gelijk is aan gast g zijn/haar nummer
            if (guestnumber == g.getGuestNumber()){
                // als deze gelijk is returnen we gast g
                return g;
            }
        }
        return null;
    }

    public int generateGuestNumber(){

        int guestNumber = 1;

        for(Guest guest : guestList) {
            if (guest.getGuestNumber() != guestNumber) {
                return guestNumber;
            } else {
                guestNumber++;
            }
        }
        return guestNumber;
    }
}
