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
        return guestRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable long id){
        return guestRepository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void insert(@RequestBody Guest guest){
        guestRepository.save(guest);
    }

    @RequestMapping(value = "{guestNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable long guestNumber, @RequestBody Guest guest){
		if(guestRepository.exists(guestNumber)){
		    Guest dataGuest = guestRepository.findOne(guestNumber);
            if (!guest.getSurName().equals(""))
                dataGuest.setSurName(guest.getSurName());
            if (!guest.getCountry().equals(""))
                dataGuest.setCountry(guest.getCountry());
            if (!guest.getAddress().equals(""))
                dataGuest.setAddress(guest.getAddress());
            if (!guest.getCity().equals(""))
                dataGuest.setCity(guest.getCity());
            if (!guest.getFirstName().equals(""))
                dataGuest.setFirstName(guest.getFirstName());
            if (!guest.getMailAddress().equals(""))
                dataGuest.setMailAddress(guest.getMailAddress());
            if (!guest.getPhoneNumber().equals(""))
                dataGuest.setPhoneNumber(guest.getPhoneNumber());
            if (!guest.getPostalCode().equals(""))
                dataGuest.setPostalCode(guest.getPostalCode());
            guestRepository.save(dataGuest);
	    }
    }

    @RequestMapping(value = "{guestNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long guestNumber) {
		guestRepository.delete(guestNumber);
    }
}
