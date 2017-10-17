package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Guest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GuestRepository {

    private List<Guest> guestList = new ArrayList<>();

    public List<Guest> getGuestList() {
        return guestList;
    }

    public Guest createNewGuest(String surname, String firstName, String address, String postalCode, String city, String country, String phoneNumber, String mailAddress){
        int guestNumber = generateGuestNumber();

        // maak van de gastdata een HashMap
        Map<String, String> guestData = new HashMap<>();
        guestData.put("surName", surname);
        guestData.put("firstName", firstName);

        Guest g = null;

        // maake een nieuwe gast aan als de gastdata niet overeenkomt met een andere gast
        if (searchGuest(guestData).size() == 0){
            g = new Guest(guestNumber, surname, firstName, address, postalCode, city, country, phoneNumber, mailAddress);
            this.guestList.add(g);
        }
        return g;
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

    public Guest findGuest(int guestNumber){
        for(Guest guest: guestList){
            if(guest.getGuestNumber() == guestNumber) return guest;
        }
        return null;
    }

    // zoek naar een gast met gastdata aangeleverd met een een HashMap
    public List<Guest> searchGuest(Map<String, String> searchData){
        int correct;
        List<Guest> result = new ArrayList<>();

        // voor alle gasten in de gastenlijst
        for(Guest guest : guestList) {
            correct = 0;
            // voor het aantal eigenschappen waar je de gast op gecontroleerd wordt
            for (String key: searchData.keySet()){
                if (key.equals("surName") && searchData.get(key).equals(guest.getSurname()))
                {
                    correct++;
                } else if (key.equals("firstName") && searchData.get(key).equals(guest.getFirstName()))
                {
                    correct++;
                } else if (key.equals("address") && searchData.get(key).equals(guest.getAddress())){
                    correct++;
                }
            }
            // als een gast alle opgevraagde eigenschappen heeft
            if (correct == searchData.size()){
                result.add(guest);
            }
        }
        return result;
    }

    // zoek naar een gast met een gastnummer in combinatie met gastdata
    public Guest searchGuest(int guestNumber, Map<String, String> searchData){
        List<Guest> guestList = searchGuest(searchData);

        for (Guest guest : guestList){
            if (getGuestFromList(guestNumber) == guest){
                return guest;
            }
        }
        return null;
    }

    // maak een gastnummer
    public int generateGuestNumber(){
        int guestNumber = 1;

        // ga bij alle gasten in de gastenlijst na op het nummer al gebruikt wordt
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