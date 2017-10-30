package nl.yacht.lakesideresort.controller.manager;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingManager {

    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    BookingManager(BookingRepository bookingRepository, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    public Iterable<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    public Booking getBooking(long id) {
        Booking booking = bookingRepository.findOne(id);
        if (booking == null)
            throw new NotFoundException();
        return booking;
    }

    public void insertBooking(Booking b) {
        if(b == null) {
            System.out.println("booking is null");
            throw new NotFoundException();
        }
        Guest foundGuest = guestRepository.findOne(b.getGuest().getGuestNumber());
        if(foundGuest == null) {
            System.out.println("guest is null");
            throw new NotFoundException();
        }
        b.setGuest(foundGuest);
        Room foundRoom = roomRepository.findOne(b.getRoom().getId());
        if(foundRoom == null) {
            System.out.println("room is null");
            throw new NotFoundException();
        }
        b.setRoom(foundRoom);
        bookingRepository.save(b);
    }

    public void changeBooking(long id, Booking b){
        if(b == null)throw new NotFoundException();
        Booking foundBooking = bookingRepository.findOne(id);
        if (foundBooking != null){
            Guest g = guestRepository.findOne(b.getGuest().getGuestNumber());
            if (g != null){
                foundBooking.setGuest(b.getGuest());
            }
            Room r = roomRepository.findOne(b.getRoom().getId());
            if (r != null){
                foundBooking.setRoom(r);
            }
            foundBooking.setStartDate(b.getStartDate());
            foundBooking.setEndDate(b.getEndDate());
            bookingRepository.save(foundBooking);
        }
    }

    public void deleteBooking(long id) {
        Booking foundBooking = bookingRepository.findOne(id);
        if (foundBooking == null) throw new NotFoundException();
        bookingRepository.delete(foundBooking);
    }
}
