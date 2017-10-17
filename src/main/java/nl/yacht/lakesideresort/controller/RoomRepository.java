package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Room;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RoomRepository {
    private Map<Integer, Room> roomMap = new HashMap<>();

    public Room insertRoom(Room room){
        this.roomMap.put(room.getRoomNumber(), room);
        return room;
    }

    public void deleteRoom(int roomNumber){
        this.roomMap.remove(roomNumber);
    }
    public Room createNewRoom(int roomNumber, Room.RoomType roomType, Room.RoomSize roomSize, LocalDate availableFrom){
        Room r = new Room(roomNumber, roomType, roomSize, availableFrom);
        this.roomMap.put(roomNumber, r);
        return r;
    }
    public void updateRoom(int roomNumber, Room r){
        updateRoom(roomNumber, r.getRoomNumber(), r.getRoomType(), r.getRoomSize());
    }
    public void updateRoom(int roomNumber, int newRoomNumber, Room.RoomType roomType, Room.RoomSize roomSize){
        Room r = roomMap.get(roomNumber);
        if(r != null){
            r.setRoomNumber(newRoomNumber);
            r.setRoomSize(roomSize);
            r.setRoomType(roomType);
            roomMap.remove(roomNumber);
            roomMap.put(newRoomNumber, r);
            System.out.println("Room Updated");
        } else {
            System.out.println("This room does not exists");
        }
    }

    public void printRooms(){
        for (Room r : this.roomMap.values()){
            System.out.println("Room: " + r.getRoomNumber());
        }
    }

    public Iterable<Room> getRooms(){
        return this.roomMap.values();
    }

}
