package nl.yacht.lakesideresort.domain;

import nl.yacht.lakesideresort.controller.GuestController;
import org.junit.Test;
import org.junit.Assert;

public class GuestTest {

    @Test
    public void testGuestController(){

    GuestController guestController = new GuestController();

    Guest guest1 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
        guestController.getGuestList().add(guest1);

    Guest guest2 = new Guest(guestController.generateGuestNumber(), "Jansen", "Thomas", "Schoolstraat 5", "9813JF", "Leek", "the Netherlands", "0609123102", "thomas.jansen@gmail.com");
        guestController.getGuestList().add(guest2);
        guestController.getGuestList().get(1).setGuestNumber(12);

    Guest guest3 = new Guest(guestController.generateGuestNumber(), "de Vries", "Alex", "Doorweg 12", "7671GH", "Zwolle", "the Netherlands", "0693559272", "alex.de.vries@gmail.com");
        guestController.getGuestList().add(guest3);
        //assert.assertEquals
    }
}

