package nl.yacht.lakesideresort.domain;

import java.time.LocalDate;

public class Room {

    private long id; // database id
    private int roomNumber;
    private RoomType roomType;
    private RoomSize roomSize;
    private LocalDate availableFrom;

    public enum RoomType{
        LUXURY, NORMAL, BUDGET
    }
    public enum RoomSize{
        ONE_PERSON, TWO_PERSON, THREE_FOUR_PERSON, FIVE_SIX_PERSON
    }

    public Room(){

    }

    public Room(int roomNumber, RoomType roomType, RoomSize roomSize, LocalDate availableFrom) {
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
    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String ld){
        this.availableFrom = LocalDate.parse(ld);
    }
	public void setAvailableFrom(LocalDate ld){
		this.availableFrom = ld;
	}
}
