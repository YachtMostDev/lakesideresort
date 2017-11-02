package nl.yacht.lakesideresort.repository;

import nl.yacht.lakesideresort.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
	Room findByRoomNumber(String roomNumber);
//    private Map<Integer, Room> roomMap = new HashMap<>();
//
//    public Room insertRoom(Room room){
//        this.roomMap.put(room.getRoomNumber(), room);
//        return room;
//    }
//
//    public void deleteRoom(int roomNumber){
//        this.roomMap.remove(roomNumber);
//    }
//    public Room createNewRoom(int roomNumber, Room.RoomType roomType, Room.RoomSize roomSize, LocalDate availableFrom){
//        Room r = new Room(roomNumber, roomType, roomSize, availableFrom);
//        this.roomMap.put(roomNumber, r);
//        return r;
//    }
//    public void updateRoom(int roomNumber, Room r){
//        updateRoom(roomNumber, r.getRoomNumber(), r.getRoomType(), r.getRoomSize());
//    }
//    public void updateRoom(int roomNumber, int newRoomNumber, Room.RoomType roomType, Room.RoomSize roomSize){
//        Room r = roomMap.get(roomNumber);
//        if(r != null){
//        	if (roomSize != null){
//		        r.setRoomSize(roomSize);
//	        }
//	        if (roomType != null) {
//		        r.setRoomType(roomType);
//	        }
//	        if (newRoomNumber > 0 && newRoomNumber != roomNumber) {
//		        r.setRoomNumber(newRoomNumber);
//		        roomMap.remove(roomNumber);
//		        roomMap.put(newRoomNumber, r);
//	        }
//        }
//    }
//
//    public void printRooms(){
//        for (Room r : this.roomMap.values()){
//            System.out.println("Room: " + r.getRoomNumber());
//        }
//    }
//	public Room getSingleRoom(int id){
//    	return this.roomMap.get(id);
//	}
//    public Iterable<Room> getRooms(){
//        return this.roomMap.values();
//    }

}
