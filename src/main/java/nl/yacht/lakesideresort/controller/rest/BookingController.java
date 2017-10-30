package nl.yacht.lakesideresort.controller.rest;

import nl.yacht.lakesideresort.controller.manager.BookingManager;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private BookingManager bookingManager = new BookingManager();

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Booking> getBookings(){
        return bookingManager.getBookings();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Booking getBooking(@PathVariable long id){
        return bookingManager.getBooking(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void insertBooking(@RequestBody Booking b) {
        bookingManager.insertBooking(b);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public void changeBooking(@PathVariable long id, @RequestBody Booking b){
        bookingManager.changeBooking(id, b);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void deleteBooking(@PathVariable long id){
        bookingManager.deleteBooking(id);
    }
}
