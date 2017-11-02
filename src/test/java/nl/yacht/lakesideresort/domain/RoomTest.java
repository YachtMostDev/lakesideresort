package nl.yacht.lakesideresort.domain;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDate;

public class RoomTest {

    @Test
    public void testRoomNumber(){
        String roomNumber = "2";
        Room room =new Room(roomNumber, Room.RoomType.BUDGET, Room.RoomSize.THREE_FOUR_PERSON, LocalDate.now());

        String checkRoomNumber = room.getRoomNumber();
        boolean result = (roomNumber == checkRoomNumber);
        Assert.assertTrue(result);
    }
    @Test
    public void testRoomTypeLuxury() {
        Room.RoomType r = Room.RoomType.LUXURY;
        Room room = new Room("2", r, Room.RoomSize.THREE_FOUR_PERSON, LocalDate.now());

        Room.RoomType checkR = room.getRoomType();
        boolean result = (r == checkR);
        Assert.assertTrue(result);
    }

    @Test
    public void testRoomTypeNormal() {
        Room.RoomType r = Room.RoomType.NORMAL;
        Room room = new Room("2", r, Room.RoomSize.THREE_FOUR_PERSON, LocalDate.now());

        Room.RoomType checkR = room.getRoomType();
        boolean result = (r == checkR);
        Assert.assertTrue(result);
    }
    @Test
    public void testRoomTypeBudget() {
        Room.RoomType r = Room.RoomType.BUDGET;
        Room room = new Room("2", r, Room.RoomSize.THREE_FOUR_PERSON, LocalDate.now());

        Room.RoomType checkR = room.getRoomType();
        boolean result = (r == checkR);
        Assert.assertTrue(result);
    }
    @Test
    public void testRoomSizeOnePerson(){
        Room.RoomSize s = Room.RoomSize.ONE_PERSON;
        Room room = new Room("2", Room.RoomType.LUXURY,s,LocalDate.now());

        Room.RoomSize checkS = room.getRoomSize();
        boolean result = (s == checkS);
        Assert.assertTrue(result);

    }

    @Test
    public void testRoomSizeTwoPerson(){
        Room.RoomSize s = Room.RoomSize.TWO_PERSON;
        Room room = new Room("2", Room.RoomType.LUXURY,s,LocalDate.now());

        Room.RoomSize checkS = room.getRoomSize();
        boolean result = (s == checkS);
        Assert.assertTrue(result);

    }
    @Test
    public void testRoomSizeThreeFourPerson(){
        Room.RoomSize s = Room.RoomSize.THREE_FOUR_PERSON;
        Room room = new Room("2", Room.RoomType.LUXURY,s,LocalDate.now());

        Room.RoomSize checkS = room.getRoomSize();
        boolean result = (s == checkS);
        Assert.assertTrue(result);

    }
    @Test
    public void testRoomSizeFiveSixPerson(){
        Room.RoomSize s = Room.RoomSize.FIVE_SIX_PERSON;
        Room room = new Room("2", Room.RoomType.LUXURY,s,LocalDate.now());

        Room.RoomSize checkS = room.getRoomSize();
        boolean result = (s == checkS);
        Assert.assertTrue(result);

    }
    @Test
    public void testLocalDate(){
        LocalDate l = LocalDate.now();
        Room room = new Room("2", Room.RoomType.NORMAL, Room.RoomSize.THREE_FOUR_PERSON, l);

        LocalDate checkLocalDate = room.getAvailableFrom();
        boolean result = (l == checkLocalDate);
        Assert.assertTrue(result);
    }


}
