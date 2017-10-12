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

    public Guest testAdress(String info, String method){
        String adress = "9181HB";
        for(Guest guest : guestList){
            if (guest.getAdress().equals(adress)){
                return guest;
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
        return 0;
    }
}
