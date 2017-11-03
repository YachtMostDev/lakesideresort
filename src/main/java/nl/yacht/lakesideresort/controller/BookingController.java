package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.manager.BookingManager;
import nl.yacht.lakesideresort.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private SimpMessagingTemplate websocketService;

    private final BookingManager bookingManager;

    public BookingController(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

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
        this.websocketService.convertAndSend("/booking", bookingManager.getBookings());
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public void changeBooking(@PathVariable long id, @RequestBody Booking b){
        bookingManager.changeBooking(id, b);
        this.websocketService.convertAndSend("/booking", bookingManager.getBookings());
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void deleteBooking(@PathVariable long id){
        bookingManager.deleteBooking(id);
        this.websocketService.convertAndSend("/booking", bookingManager.getBookings());
    }
}
