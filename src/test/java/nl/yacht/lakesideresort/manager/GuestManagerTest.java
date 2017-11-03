package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.exception.AlreadyExistException;
import nl.yacht.lakesideresort.repository.BookingRepository;
import nl.yacht.lakesideresort.repository.GuestRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GuestManagerTest {

    private GuestRepository guestRepository;
    private BookingRepository bookingRepository;
    private List<Guest> guestList;
    private GuestManager guestManager;

    @Before
    public void initialize(){
        guestRepository = mock(GuestRepository.class);
        bookingRepository = mock(BookingRepository.class);
        guestManager = new GuestManager(guestRepository, bookingRepository);

        Guest guest1 = new Guest(1, "Jansen", "Peter", "Astraat 12", "9876RK", "Groningen", "Nederland", "1234567890", "a.a@gmail.com");
        Guest guest2 = new Guest(2, "Vries", "Anne", "Astraat 12", "4567FL", "Zwolle", "Nederland", "1234567890", "a.a@gmail.com");

        guestList = new ArrayList<>();
        guestList.add(guest1);
        guestList.add(guest2);

        when(guestRepository.findAll()).thenReturn(guestList);
        when(guestRepository.findOne(1L)).thenReturn(guestList.get(1));
    }

    @Test
    public void getGuests() throws Exception {
        assertEquals(guestManager.getGuests(), guestList);
    }

    @Test
    public void getGuest() throws Exception {
        assertEquals(guestManager.getGuest(1L), guestList.get(1));
    }

    @Test
    public void insert() throws Exception {
        Guest guest = new Guest(3, "Tom", "Herensma", "Astraat 12", "9876RK", "Groningen", "Nederland", "1234567890", "a.a@gmail.com");

        when(guestRepository.findFirstBySurNameAndFirstNameAndAddressAndPostalCodeAndPhoneNumberAndMailAddress(
                guest.getSurName(),
                guest.getFirstName(),
                guest.getAddress(),
                guest.getPostalCode(),
                guest.getPhoneNumber(),
                guest.getMailAddress())).thenReturn(null);

        when(guestRepository.save(guest)).thenReturn(guest);
        assertTrue(guestManager.insert(guest).equals(guest));
    }

    @Test(expected = AlreadyExistException.class)
    public void insertExistingGuest() throws Exception {
        Guest guest = new Guest(1, "Jansen", "Peter", "Astraat 12", "9876RK", "Groningen", "Nederland", "1234567890", "a.a@gmail.com");
        when(guestRepository.findFirstBySurNameAndFirstNameAndAddressAndPostalCodeAndPhoneNumberAndMailAddress(
            guest.getSurName(),
            guest.getFirstName(),
            guest.getAddress(),
            guest.getPostalCode(),
            guest.getPhoneNumber(),
            guest.getMailAddress())
        ).thenReturn(guest);
        guestManager.insert(guest);
    }

    @Test
    public void delete() throws Exception {
        Guest guest = guestList.get(1);
        guestManager.delete(1);
    }

    @Test
    public void getGuestsArrivingToday() throws Exception {
        LocalDate localDate = LocalDate.now();
        Room room = new Room("201", Room.RoomType.LUXURY, Room.RoomSize.TWO_PERSON, localDate);

        Booking booking1 = new Booking(1L, guestList.get(0), room, LocalDate.now(), LocalDate.now());
        Booking booking2 = new Booking(2L, guestList.get(1), room, LocalDate.now(), LocalDate.now());

        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking1);
        bookingList.add(booking2);

        List<Guest> bookingGuestList = new ArrayList<>();
        bookingGuestList.add(guestList.get(0));
        bookingGuestList.add(guestList.get(1));

        when(bookingRepository.findAll()).thenReturn(bookingList);

        assertEquals(guestManager.getGuestsArrivingToday(), bookingGuestList);
    }

    @Test
    public void update() throws Exception {
        Guest guest1 = guestList.get(0);
        Guest guest2 = guestList.get(1);

        when(guestRepository.findOne(1L)).thenReturn(guest1);
        when(guestRepository.save(guest1)).thenReturn(guest1);
        when(guestRepository.exists(1L)).thenReturn(true);

        assertTrue(guestManager.update(1, guest2).equals(guest1));
    }
}