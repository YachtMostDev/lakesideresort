package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Room;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RoomController {
    private Map<Integer, Room> roomMap = new HashMap<>();

    public Room createNewRoom(Room.RoomType roomType, Room.RoomSize roomSize, LocalDate availableFrom){
        int roomNumber = generateRoomNumber();
        Room r = new Room(roomNumber, roomType, roomSize, availableFrom);
        this.roomMap.put(roomNumber, r);
        return r;
    }

    public void updateRoom(int roomNumber, int newRoomNumber, Room.RoomType roomType, Room.RoomSize roomSize){
        Room r = roomMap.get(roomNumber);
        if(r != null){
            r.setRoomNumber(newRoomNumber);
            r.setRoomSize(roomSize);
            r.setRoomType(roomType);
            roomMap.remove(roomNumber);
            roomMap.put(newRoomNumber, r);
        }
    }

    public void printRooms(){
        for (Room r : this.roomMap.values()){
            System.out.println("Room: " + r.getRoomNumber());
        }
    }
    private int generateRoomNumber(){
        int nr = 0;
        for (Room r : this.roomMap.values()){
            if (r.getRoomNumber() > nr){
                nr = r.getRoomNumber();
            }
        }
        return nr + 1;
    }
}
