package nl.yacht.lakesideresort.controller.gui;

import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.controller.GuestController;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Gui(name="Guest")
public class GuestGui extends Command {
    private GuestController guestCtrl;
    public GuestGui()
    {
        guestCtrl = new GuestController();
    }

    public void create() throws IOException{
        String[][] definition = {
                {"Input surname","[a-zA-Z\\s]+"},
                {"Input firstname","[a-zA-Z\\s]+"},
                {"Input address","[a-zA-Z\\s]+ \\d+"},
                {"Input postal code","[1-9]\\d{3}\\s?[a-zA-Z]{2}"},
                {"Input city","[a-zA-Z\\s]+"},
                {"Input country","[a-zA-Z\\s]+"},
                {"Input phone number","\\d{10}"},
                {"Input mail address",".+"}
        };
        String[] inputs = InputHandler.handleInput(definition,args);
        Guest guest = guestCtrl.createNewGuest(inputs[0],inputs[1],inputs[2],inputs[3],inputs[4],inputs[5],inputs[6],inputs[7]);
        if (guest == null){
            System.out.println("This guest already exists");
        } else {
            System.out.printf("The new guest has been added with guestnumber: %d%n", guest.getGuestNumber());
        }
    }

    public void search() throws IOException{
        String[][] definition = {
                {"Input guestnumber","\\d?"},
                {"Input surname","(.+)?"},
                {"Input firstname","(.+)?"},
                {"Input address","(.+)?"},
        };
        String[] inputs = InputHandler.handleInput(definition,args);
        HashMap<String, String> guestData = new HashMap<>();
        if (!inputs[1].equals("")) guestData.put("surName", inputs[1]);
        if (!inputs[2].equals("")) guestData.put("firstName", inputs[2]);
        if (!inputs[3].equals("")) guestData.put("address", inputs[3]);

        if (!inputs[0].equals("")){
            Guest guest = guestCtrl.searchGuest(Integer.parseInt(inputs[0]), guestData);
            if (guest == null) {
                System.out.println("\nCould not find specified guest");
            } else {
                System.out.println("\nWe found this guest:");
                System.out.println(guest);
            }
        } else {
            ArrayList<Guest> guestList = guestCtrl.searchGuest(guestData);
            if (guestList.size() > 0){
                System.out.printf("We have found %d guest(s)%n", guestList.size());
                for (Guest guest: guestList){
                    System.out.println();
                    System.out.println(guest);
                }
            }
        }
    }

    public void change () throws IOException {
        String[][] definition = {
                {"Input guestnumber","\\d?"},
                {"What would you like to change \n[Surname, Firstname, Address, Postalcode, City, Country, Phonenumber, Mailaddress]?","SURNAME|FIRSTNAME|ADDRESS|POSTALCODE|CITY|COUNTRY|PHONENUMBER|MAILADDRESS"},
                {"What is the new value?",".+"}
        };
        String[] inputs = InputHandler.handleInput(definition,args);
        Guest guest = guestCtrl.findGuest(Integer.parseInt(inputs[0]));
        if(guest == null){
            System.out.println("Could not find this guest");
        } else {
            switch (inputs[1].toUpperCase()){
                case "SURNAME": guest.setSurname(inputs[2]);
                    break;
                case "FIRSTNAME" : guest.setFirstName(inputs[2]);
                    break;
                case "ADDRESS" : guest.setAddress(inputs[2]);
                    break;
                case "POSTALCODE" : guest.setPostalCode(inputs[2]);
                    break;
                case "CITY" : guest.setCity(inputs[2]);
                    break;
                case "COUNTRY" : guest.setCountry(inputs[2]);
                    break;
                case "PHONENUMBER" : guest.setPhoneNumber(inputs[2]);
                    break;
                case "MAILADDRESS" : guest.setMailAddress(inputs[2]);
                    break;
            }
            System.out.println("The guest has been changed");
            System.out.println("This is the updated guest\n");
            System.out.println(guest);
        }
    }
}
