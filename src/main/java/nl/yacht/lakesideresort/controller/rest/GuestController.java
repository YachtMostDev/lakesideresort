package nl.yacht.lakesideresort.controller.rest;

import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void insert(@RequestBody Guest guest){
        guestRepository.createNewGuest(guest.getSurName(), guest.getFirstName(), guest.getAddress(),
                guest.getPostalCode(), guest.getCity(), guest.getCountry(),
                guest.getPhoneNumber(), guest.getMailAddress());
    }

    @RequestMapping(value = "{guestNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable int guestNumber, @RequestBody Guest guest){

        for(Guest selectedGuest : this.guestRepository.getGuestList()){
            if(selectedGuest.getGuestNumber() == guestNumber){
                if(guest.getSurName() != null) selectedGuest.setSurName(guest.getSurName());
                if(guest.getFirstName() != null) selectedGuest.setFirstName(guest.getFirstName());
                if(guest.getAddress() != null) selectedGuest.setAddress(guest.getAddress());
                if(guest.getPostalCode() != null) selectedGuest.setPostalCode(guest.getPostalCode());
                if(guest.getCity() != null) selectedGuest.setCity(guest.getCity());
                if(guest.getCountry() != null) selectedGuest.setCountry(guest.getCountry());
                if(guest.getPhoneNumber() != null) selectedGuest.setPhoneNumber(guest.getPhoneNumber());
                if(guest.getMailAddress() != null) selectedGuest.setMailAddress(guest.getMailAddress());

                return;
            }
        }
    }

    @RequestMapping(value = "{guestNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int guestNumber){
        Guest foundGuest = null;
        for(Guest selectedGuest : this.guestRepository.getGuestList()){
            if(guestNumber == selectedGuest.getGuestNumber()){
                foundGuest = selectedGuest;
            }
        }
        if(foundGuest != null) this.guestRepository.getGuestList().remove(foundGuest);
    }
}
