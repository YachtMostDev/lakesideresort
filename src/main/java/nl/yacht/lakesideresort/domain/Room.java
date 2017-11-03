package nl.yacht.lakesideresort.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // database id
    @NotEmpty
    private String roomNumber;
    @NotNull
    private RoomType roomType;
    @NotNull
    private RoomSize roomSize;
    @NotNull
    private LocalDate availableFrom;

    public enum RoomType{
        LUXURY, NORMAL, BUDGET
    }
    public enum RoomSize{
        ONE_PERSON, TWO_PERSON, THREE_FOUR_PERSON, FIVE_SIX_PERSON
    }

    public Room(){}

    public Room(String roomNumber, RoomType roomType, RoomSize roomSize, LocalDate availableFrom) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
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
