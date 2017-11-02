package nl.yacht.lakesideresort.controller;


import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.manager.GuestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

    private final GuestManager guestManager;

    public GuestController(GuestManager guestManager){
        this.guestManager = guestManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Guest> getGuests(){
        return guestManager.getGuests();
    }

    @RequestMapping(value = "search/{searchString}", method = RequestMethod.GET)
    public Iterable<Guest> searchGuest(@PathVariable String searchString){
        String[] parts = searchString.split(" ");
        if(parts.length == 1){
            return guestManager.searchGuests(searchString);
        } else {
            return guestManager.searchGuests(parts[0],parts[1]);
        }
    }

    @RequestMapping(value = "today", method = RequestMethod.GET)
    public Iterable<Guest> getGuestsArrivingToday(){
        return guestManager.getGuestsArrivingToday();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable long id){
        return guestManager.getGuest(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void insert(@RequestBody Guest guest){
        guestManager.insert(guest);
    }

    @RequestMapping(value = "{guestNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable long guestNumber, @RequestBody Guest guest){
        guestManager.update(guestNumber, guest);
    }

    @RequestMapping(value = "{guestNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long guestNumber) {
		guestManager.delete(guestNumber);
    }
}
