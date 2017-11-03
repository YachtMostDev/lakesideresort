package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.exception.AlreadyExistException;
import nl.yacht.lakesideresort.repository.BookingRepository;
import nl.yacht.lakesideresort.repository.GuestRepository;
import nl.yacht.lakesideresort.repository.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;

@Component
public class BookingManager {

    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final AvailabilityManager availabilityManager;

    BookingManager(BookingRepository bookingRepository, GuestRepository guestRepository, RoomRepository roomRepository, AvailabilityManager availabilityManager) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
        this.availabilityManager = availabilityManager;
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
        if(!checkAvailable(booking.getRoom().getRoomNumber(), booking.getStartDate(), booking.getEndDate())){
            System.out.println("room is not available");
            throw new AlreadyExistException();
        }
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

        if(!checkAvailable(foundBooking.getRoom().getRoomNumber(), foundBooking.getStartDate(), foundBooking.getEndDate())){
            System.out.println("room is not available");
            throw new AlreadyExistException();
        }

        bookingRepository.save(booking);

        return foundBooking;
    }

    public void deleteBooking(long id) {
        Booking foundBooking = bookingRepository.findOne(id);
        if (foundBooking == null) throw new NotFoundException();
        bookingRepository.delete(foundBooking);
    }

    private boolean checkAvailable(String roomNumber, LocalDate startDate, LocalDate endDate){
        boolean available = true;
        HashMap<String, Boolean> availability = availabilityManager.getAvailabilityOfRoom(roomNumber, startDate, endDate);
        LocalDate date = startDate;
        while((date.isBefore(endDate) || date.isEqual(endDate)) && available){
            date = date.plusDays(1);
            if(availability.getOrDefault(date.toString(),false)) available = false;
        }
        return available;
    }
}
