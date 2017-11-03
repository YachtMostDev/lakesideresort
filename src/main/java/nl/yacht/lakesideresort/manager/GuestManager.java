package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.exception.AlreadyExistException;
import nl.yacht.lakesideresort.exception.NotFoundException;
import nl.yacht.lakesideresort.repository.BookingRepository;
import nl.yacht.lakesideresort.repository.GuestRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class GuestManager {

    private final GuestRepository guestRepository;

    private final BookingRepository bookingRepository;

    public GuestManager (GuestRepository guestRepository, BookingRepository bookingRepository){
        this.guestRepository = guestRepository;
        this.bookingRepository = bookingRepository;
    }

    public Iterable<Guest> getGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuest(long id){
        return guestRepository.findOne(id);
    }

    public Guest insert(Guest guest){
        if (guestRepository.findFirstBySurNameAndFirstNameAndAddressAndPostalCodeAndPhoneNumberAndMailAddress(
                guest.getSurName(),
                guest.getFirstName(),
                guest.getAddress(),
                guest.getPostalCode(),
                guest.getPhoneNumber(),
                guest.getMailAddress()) != null) throw new AlreadyExistException();
        return guestRepository.save(guest);
    }

    public void delete(long guestNumber) {
        guestRepository.delete(guestNumber);
    }

    public Iterable<Guest> getGuestsArrivingToday() {
        ArrayList<Guest> result = new ArrayList<>();
        Iterable<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            if (booking.getStartDate().isEqual(LocalDate.now())) {
                if (!result.contains(booking.getGuest())) {
                    result.add(booking.getGuest());
                }
            }
        }
        return result;
    }

    public Guest update(long guestNumber, Guest guest) {
        if (!guestRepository.exists(guestNumber)) throw new NotFoundException();
        Guest updateGuest = guestRepository.findOne(guestNumber);
        updateGuest.setSurName(guest.getSurName());
        updateGuest.setCountry(guest.getCountry());
        updateGuest.setAddress(guest.getAddress());
        updateGuest.setCity(guest.getCity());
        updateGuest.setFirstName(guest.getFirstName());
        updateGuest.setMailAddress(guest.getMailAddress());
        updateGuest.setPhoneNumber(guest.getPhoneNumber());
        updateGuest.setPostalCode(guest.getPostalCode());
        return guestRepository.save(updateGuest);
    }

    public Iterable<Guest> searchGuests(String searchString){
        ArrayList<Guest> result = new ArrayList<>();
        for(Guest guest : guestRepository.findAll()){
            if(guest.getFirstName().toLowerCase().contains(searchString.toLowerCase()) ||
                    guest.getSurName().toLowerCase().contains(searchString.toLowerCase())){
                result.add(guest);
            }
        }
        return result;
    }

    public Iterable<Guest> searchGuests(String searchString1, String searchString2){
        ArrayList<Guest> result = new ArrayList<>();
        ArrayList<Guest> searchResult1 = (ArrayList<Guest>) searchGuests(searchString1);
        ArrayList<Guest> searchResult2 = (ArrayList<Guest>) searchGuests(searchString2);
        for(Guest guest : searchResult1){
            if(searchResult2.contains(guest)) result.add(guest);
        }
        return result;
    }
}