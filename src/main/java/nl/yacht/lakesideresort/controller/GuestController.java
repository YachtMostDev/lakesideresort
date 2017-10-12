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
