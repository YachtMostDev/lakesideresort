package nl.yacht.lakesideresort.controller.rest;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/guest")
public class GuestController {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Guest> getGuests(){
        return guestRepository.findAll();
    }

    @RequestMapping(value = "today", method = RequestMethod.GET)
    public Iterable<Guest> getGuestsArrivingToday(){
        ArrayList<Guest> result = new ArrayList<>();
        ArrayList<Booking> bookings = (ArrayList<Booking>) bookingRepository.findAll();
        for(Booking booking : bookings){
            if(booking.getStartDate().isEqual(LocalDate.now())) {
                if (!result.contains(booking.getGuest())) {
                    result.add(booking.getGuest());
                }
            }
        }
        return result;
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
            System.out.println(guest);
            if (guest.getSurName() != null)
                dataGuest.setSurName(guest.getSurName());
            if (guest.getCountry() != null)
                dataGuest.setCountry(guest.getCountry());
            if (guest.getAddress() != null)
                dataGuest.setAddress(guest.getAddress());
            if (guest.getCity() != null)
                dataGuest.setCity(guest.getCity());
            if (guest.getFirstName() != null)
                dataGuest.setFirstName(guest.getFirstName());
            if (guest.getMailAddress() != null)
                dataGuest.setMailAddress(guest.getMailAddress());
            if (guest.getPhoneNumber() != null)
                dataGuest.setPhoneNumber(guest.getPhoneNumber());
            if (guest.getPostalCode() != null)
                dataGuest.setPostalCode(guest.getPostalCode());
            guestRepository.save(dataGuest);
	    }
    }

    @RequestMapping(value = "{guestNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long guestNumber) {
		guestRepository.delete(guestNumber);
    }
}
