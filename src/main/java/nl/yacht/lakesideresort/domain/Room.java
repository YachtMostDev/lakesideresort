package nl.yacht.lakesideresort.domain;

import java.time.LocalDateTime;

public class Room {

    private int roomNumber;
    private RoomType roomType;
    private RoomSize roomSize;
    private LocalDateTime availableFrom;

    public enum RoomType{
        LUXURY, NORMAL, BUDGET
    }
    public enum RoomSize{
        ONE_PERSON, TWO_PERSON, THREE_FOUR_PERSON, FIVE_SIX_PERSON
    }

    public Room(int roomNumber, RoomType roomType, RoomSize roomSize, LocalDateTime availableFrom) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.availableFrom = availableFrom;
    }

    @Override
    public String toString() {
        String s = "Room: " +this.roomNumber;
        s += "\nRoomtype: " +this.roomType;
        s += "\nRoomsize: " +this.roomSize;
        s += "\nAvailable from: " +this.availableFrom;
        return s;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomSize getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(RoomSize roomSize) {
        this.roomSize = roomSize;
    }
    //#For testing purposes
    public LocalDateTime getAvailableFrom() {
        return availableFrom;
    }
}
