package nl.yacht.lakesideresort.controller.rest;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Booking>getBookings(){
        Iterable<Booking> list = bookingRepository.findAll();
        return list;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Booking getBooking(@PathVariable long id){
        if(bookingRepository == null)throw new NotFoundException();
        return bookingRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void insertBooking(@RequestBody Booking b) {
        if(b == null)throw new NotFoundException();
        Guest foundGuest = guestRepository.findOne(b.getGuest().getGuestNumber());
        if(foundGuest == null) throw new NotFoundException();
        b.setGuest(foundGuest);
        Room foundRoom = roomRepository.findOne(b.getRoom().getId());
        if(foundRoom == null) throw new NotFoundException();
        b.setRoom(foundRoom);
        bookingRepository.save(b);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public void changeBooking(@PathVariable long id, @RequestBody Booking b){
        if(b == null)throw new NotFoundException();
        if(bookingRepository.exists(id)){
            bookingRepository.save(b);
        }
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void deleteBooking(@PathVariable long id){
        Booking foundBooking = bookingRepository.findOne(id);
        if(foundBooking == null) throw new NotFoundException();
        bookingRepository.delete(foundBooking);
        //foundBooking: if found then delete booking
    }
}
