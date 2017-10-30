package nl.yacht.lakesideresort.controller.manager;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookingManagerTest {

    private BookingManager bookingManager;

    @Before
    public void setUp() throws Exception {
        BookingRepository bookingRepository = mock(BookingRepository.class);
        GuestRepository guestRepository = mock(GuestRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        bookingManager = new BookingManager(bookingRepository, guestRepository, roomRepository);

        Guest guest = new Guest(1, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl");
        Room room = new Room(201, Room.RoomType.LUXURY, Room.RoomSize.FIVE_SIX_PERSON, LocalDate.now());
        room.setId(1);

        List<Booking> bookingList = new ArrayList<>();
        Booking booking1 = new Booking();
        booking1.setBookingnumber(1);
        //booking1.setGuest(new Guest(1, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl"));
        bookingList.add(booking1);
        Booking booking2 = new Booking();
        booking2.setBookingnumber(2);
        booking2.setStartDate(LocalDate.of(2017, 1, 1));
        booking2.setEndDate(LocalDate.of(2018, 1, 1));
        booking2.setGuest(guest);
        booking2.setRoom(room);
        bookingList.add(booking2);

        Booking booking3 = new Booking();
        booking3.setBookingnumber(3);

        when(bookingRepository.findAll()).thenReturn(bookingList);
        when(bookingRepository.findOne(2L)).thenReturn(booking2);
        when(guestRepository.findOne(1L)).thenReturn(guest);
        when(roomRepository.findOne(1L)).thenReturn(room);
        when(bookingRepository.save(booking3)).thenReturn(booking3);
    }

    @Test
    public void getBookings() throws Exception {
        Collection<Booking> bookings = (Collection<Booking>) bookingManager.getBookings();
        assertTrue(bookings.size() == 2);
    }

    @Test
    public void getBooking() throws Exception {
        Booking bookings = bookingManager.getBooking(2);
        assertTrue(bookings.getBookingnumber() == 2);
    }

    @Test(expected = NotFoundException.class)
    public void getNonExistingBooking() throws Exception {
        Booking booking = bookingManager.getBooking(99);
    }

    @Test
    public void insertBooking() throws Exception {
        Booking booking = new Booking();
        booking.setBookingnumber(3);
        Guest guest = new Guest(1, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl");
        Room room = new Room(201, Room.RoomType.LUXURY, Room.RoomSize.FIVE_SIX_PERSON, LocalDate.now());
        room.setId(1);
        booking.setGuest(guest);
        booking.setRoom(room);

        Booking newBooking = bookingManager.insertBooking(booking);
        assertTrue(newBooking.getBookingnumber() == 3);
    }

    @Test(expected = NotFoundException.class)
    public void insertNonExistingBooking() throws Exception {
        Booking newBooking = bookingManager.insertBooking(null);
    }

    @Test(expected = NotFoundException.class)
    public void insertNonExistingGuest() throws Exception {
        Booking booking = new Booking();
        booking.setBookingnumber(3);
        Guest guest = new Guest(99, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl");
        Room room = new Room(201, Room.RoomType.LUXURY, Room.RoomSize.FIVE_SIX_PERSON, LocalDate.now());
        room.setId(1);
        booking.setGuest(guest);
        booking.setRoom(room);

        Booking newBooking = bookingManager.insertBooking(booking);
        assertTrue(newBooking.getBookingnumber() == 3);
    }

    @Test(expected = NotFoundException.class)
    public void insertNonExistingRoom() throws Exception {
        Booking booking = new Booking();
        booking.setBookingnumber(3);
        Guest guest = new Guest(1, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl");
        Room room = new Room(201, Room.RoomType.LUXURY, Room.RoomSize.FIVE_SIX_PERSON, LocalDate.now());
        room.setId(99);
        booking.setGuest(guest);
        booking.setRoom(room);

        Booking newBooking = bookingManager.insertBooking(booking);
        assertTrue(newBooking.getBookingnumber() == 3);
    }

    @Test
    public void changeBooking() throws Exception {
        Booking booking = new Booking();
        booking.setBookingnumber(2);
        booking.setStartDate(LocalDate.of(2017, 1, 1));
        booking.setEndDate(LocalDate.of(2018, 8, 1));
        Guest guest = new Guest(1, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl");
        booking.setGuest(guest);
        Room room = new Room(201, Room.RoomType.LUXURY, Room.RoomSize.FIVE_SIX_PERSON, LocalDate.now());
        room.setId(1);
        booking.setRoom(room);

        Booking newBooking = bookingManager.changeBooking(2, booking);
        assertTrue(newBooking.getEndDate().equals(LocalDate.of(2018, 8, 1)));
    }

    @Test(expected = NotFoundException.class)
    public void changeNonExistingBooking() throws Exception {
        Booking booking = new Booking();
        Booking newBooking = bookingManager.changeBooking(99, booking);
    }

    @Test(expected = NotFoundException.class)
    public void changeBookingNonExistingGuest(){
        Booking booking = new Booking();
        booking.setBookingnumber(2);
        booking.setStartDate(LocalDate.of(2017, 1, 1));
        booking.setEndDate(LocalDate.of(2018, 8, 1));
        Guest guest = new Guest(2, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl");
        booking.setGuest(guest);
        Room room = new Room(201, Room.RoomType.LUXURY, Room.RoomSize.FIVE_SIX_PERSON, LocalDate.now());
        room.setId(1);
        booking.setRoom(room);

        Booking newBooking = bookingManager.changeBooking(2, booking);
    }

    @Test(expected = NotFoundException.class)
    public void changeBookingNonExistingRoom(){
        Booking booking = new Booking();
        booking.setBookingnumber(2);
        booking.setStartDate(LocalDate.of(2017, 1, 1));
        booking.setEndDate(LocalDate.of(2018, 8, 1));
        Guest guest = new Guest(2, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl");
        booking.setGuest(guest);
        Room room = new Room(201, Room.RoomType.LUXURY, Room.RoomSize.FIVE_SIX_PERSON, LocalDate.now());
        room.setId(99);
        booking.setRoom(room);

        Booking newBooking = bookingManager.changeBooking(99, booking);
    }

    @Test(expected = NotFoundException.class)
    public void changeBookingNullBooking(){
        Booking booking = bookingManager.changeBooking(2, null);
    }

    @Test
    public void deleteBooking(){
        bookingManager.deleteBooking(2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteBookingNonExisting(){
        bookingManager.deleteBooking(99);
    }
}