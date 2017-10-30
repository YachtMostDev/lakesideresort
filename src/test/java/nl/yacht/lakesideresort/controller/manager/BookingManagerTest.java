package nl.yacht.lakesideresort.controller.manager;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.GuestRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

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

        List<Booking> bookingList = new ArrayList<>();
        Booking booking1 = new Booking();
        booking1.setBookingnumber(1);
        //booking1.setGuest(new Guest(1, "Henker", "Henk", "Somewhere 2", "8118PA", "There", "Nederland", "0612345678", "henk@henker.nl"));
        bookingList.add(booking1);
        Booking booking2 = new Booking();
        booking2.setBookingnumber(2);
        bookingList.add(booking2);

        when(bookingRepository.findAll()).thenReturn(bookingList);
        when(bookingRepository.findOne(2L)).thenReturn(booking2);
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
}