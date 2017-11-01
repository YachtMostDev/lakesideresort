package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.repository.BookingRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Guest;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.repository.RoomRepository;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by njvan on 30-Oct-17.
 */
public class AvailabilityManagerTest {
    BookingRepository bookingRepository;
    RoomRepository roomRepository;
    AvailabilityManager availabilityManager;

    @Before
    public void setup(){
        ArrayList<Booking> bookingList = new ArrayList<>();
        ArrayList<Room> roomList = new ArrayList<>();

        LocalDate date1 = LocalDate.parse("2017-10-01");
        LocalDate date2 = LocalDate.parse("2017-10-04");
        LocalDate date3 = LocalDate.parse("2017-10-09");
        LocalDate date4 = LocalDate.parse("2017-10-12");
        LocalDate date5 = LocalDate.parse("2017-10-17");
        LocalDate date6 = LocalDate.parse("2017-10-20");

        Guest guest = new Guest();
        Room room1 = new Room(101, Room.RoomType.BUDGET, Room.RoomSize.ONE_PERSON,date1);
        Room room2 = new Room(102, Room.RoomType.BUDGET, Room.RoomSize.ONE_PERSON,date1);
        Room room3 = new Room(103, Room.RoomType.BUDGET, Room.RoomSize.ONE_PERSON,date1);
        Room room4 = new Room(104, Room.RoomType.BUDGET, Room.RoomSize.ONE_PERSON,date1);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);

        bookingList.add(new Booking(guest,room1,date1,date2));
        bookingList.add(new Booking(guest,room1,date3,date4));
        bookingList.add(new Booking(guest,room1,date5,date6));
        bookingList.add(new Booking(guest,room2,date2,date3));
        bookingList.add(new Booking(guest,room2,date4,date5));
        bookingList.add(new Booking(guest,room3,date1,date3));
        bookingList.add(new Booking(guest,room3,date4,date6));
        bookingList.add(new Booking(guest,room4,date3,date6));

        bookingRepository = mock(BookingRepository.class);
        roomRepository = mock(RoomRepository.class);
        availabilityManager = new AvailabilityManager(bookingRepository, roomRepository);

        when(bookingRepository.findAll()).thenReturn(bookingList);
        when(roomRepository.findAll()).thenReturn(roomList);
        when(roomRepository.findOne(new Long("101"))).thenReturn(room1);
        when(roomRepository.findOne(new Long("102"))).thenReturn(room2);
        when(roomRepository.findOne(new Long("103"))).thenReturn(room3);
        when(roomRepository.findOne(new Long("104"))).thenReturn(room4);
    }

    @Test
    public void getAvailabilityOfRoom() throws Exception {
        HashMap<String, Boolean> expectedResult = new HashMap<>();
        expectedResult.put("2017-10-05", false);
        expectedResult.put("2017-10-06", false);
        expectedResult.put("2017-10-07", false);
        expectedResult.put("2017-10-08", false);
        expectedResult.put("2017-10-09", true);
        expectedResult.put("2017-10-10", true);
        expectedResult.put("2017-10-11", true);
        expectedResult.put("2017-10-12", true);
        expectedResult.put("2017-10-13", false);
        expectedResult.put("2017-10-14", false);
        expectedResult.put("2017-10-15", false);

        LocalDate date1 = LocalDate.parse("2017-10-05");
        LocalDate date2 = LocalDate.parse("2017-10-15");

        HashMap<String, Boolean> result = availabilityManager.getAvailabilityOfRoom("101",date1,date2);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getAvailabilityOnDate() throws Exception {
        HashMap<String, Boolean> expectedResult = new HashMap<>();
        expectedResult.put("101", false);
        expectedResult.put("102", true);
        expectedResult.put("103", true);
        expectedResult.put("104", false);

        LocalDate date = LocalDate.parse("2017-10-05");
        HashMap<String, Boolean> result = availabilityManager.getAvailabilityOnDate(date);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getAvailabilityOnDate2() throws Exception {
        HashMap<String, Boolean> expectedResult = new HashMap<>();
        expectedResult.put("101", true);
        expectedResult.put("102", true);
        expectedResult.put("103", true);
        expectedResult.put("104", true);

        LocalDate date = LocalDate.parse("2017-10-17");
        HashMap<String, Boolean> result = availabilityManager.getAvailabilityOnDate(date);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getAvailabilityOfAllRooms() throws Exception {
        HashMap<String, HashMap<String, Boolean>> expectedResult = new HashMap<>();

        HashMap<String, Boolean> map1 = new HashMap<>();
        map1.put("2017-10-11", true);
        map1.put("2017-10-12", true);
        map1.put("2017-10-13", false);

        HashMap<String, Boolean> map2 = new HashMap<>();
        map2.put("2017-10-11", false);
        map2.put("2017-10-12", true);
        map2.put("2017-10-13", true);

        HashMap<String, Boolean> map3 = new HashMap<>();
        map3.put("2017-10-11", false);
        map3.put("2017-10-12", true);
        map3.put("2017-10-13", true);

        HashMap<String, Boolean> map4 = new HashMap<>();
        map4.put("2017-10-11", true);
        map4.put("2017-10-12", true);
        map4.put("2017-10-13", true);

        expectedResult.put("101", map1);
        expectedResult.put("102", map2);
        expectedResult.put("103", map3);
        expectedResult.put("104", map4);

        LocalDate date1 = LocalDate.parse("2017-10-11");
        LocalDate date2 = LocalDate.parse("2017-10-13");

        HashMap<String, HashMap<String, Boolean>> result = availabilityManager.getAvailabilityOfAllRooms(date1,date2);

        assertEquals(expectedResult, result);
    }

}