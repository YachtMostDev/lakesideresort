package nl.yacht.lakesideresort.controller.manager;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
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

    public Booking insertBooking(Booking booking) {
        if(booking == null) {
            System.out.println("booking is null");
            throw new NotFoundException();
        }
        Guest foundGuest = guestRepository.findOne(booking.getGuest().getGuestNumber());
        if(foundGuest == null) {
            System.out.println("guest is null");
            throw new NotFoundException();
        }
        booking.setGuest(foundGuest);
        Room foundRoom = roomRepository.findOne(booking.getRoom().getId());
        if(foundRoom == null) {
            System.out.println("room is null");
            throw new NotFoundException();
        }
        booking.setRoom(foundRoom);
        bookingRepository.save(booking);
        return booking;
    }

    public Booking changeBooking(long id, Booking booking){
        if(booking == null) throw new NotFoundException();

        Booking foundBooking = bookingRepository.findOne(id);
        if(foundBooking == null) throw new NotFoundException();

        Guest guest = guestRepository.findOne(booking.getGuest().getGuestNumber());
        if(guest == null) throw new NotFoundException();
        foundBooking.setGuest(guest);

        Room room = roomRepository.findOne(booking.getRoom().getId());
        if(room == null) throw new NotFoundException();
        foundBooking.setRoom(room);

        foundBooking.setStartDate(booking.getStartDate());
        foundBooking.setEndDate(booking.getEndDate());

        return foundBooking;
    }

    public void deleteBooking(long id) {
        Booking foundBooking = bookingRepository.findOne(id);
        if (foundBooking == null) throw new NotFoundException();
        bookingRepository.delete(foundBooking);
    }
}
