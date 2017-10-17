package nl.yacht.lakesideresort.controller.rest;

import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/guest")
public class GuestController {
    @Autowired
    private GuestRepository guestRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Guest> getGuests(){
        return guestRepository.getGuestList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable long id){
        return guestRepository.findGuest(Long.valueOf(id).intValue());
    }
}
