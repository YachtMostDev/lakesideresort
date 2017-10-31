package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
import nl.yacht.lakesideresort.repository.RoomRepository;
import org.springframework.stereotype.Component;

@Component
public class RoomManager {

	private final RoomRepository roomRepository;

	public RoomManager(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public Iterable<Room> getRooms(){
		Iterable<Room> rooms = roomRepository.findAll();
		return rooms;
	}

	public Room getSingleRoom(long id){
		Room r = roomRepository.findOne(id);
		return r;
	}

	public boolean insertRoom(Room r){
		Room foundRoom = getSingleRoom(r.getId());
		if (foundRoom == null){
			roomRepository.save(r);
			return true;
		}
		return false;
	}
	public void deleteRoom(long id){
		roomRepository.delete(id);
	}

	public long findRoomByRoomNumber(int roomnumber){
		Room r = roomRepository.findByRoomNumber(roomnumber);
		return r.getId();
	}

	public void updateRoom(long id, Room room){
//		Room updateRoom = getSingleRoom(id);

		if (!roomRepository.exists(id)){
			throw new NotFoundException();
		}

		roomRepository.save(room);
//
//		if (updateRoom == null){
//			return;
//			// add not found exc
//		}
//		updateRoom.update(room);
//		updateRoom.setAvailableFrom(room.getAvailableFrom());
//		updateRoom.setRoomType(room.getRoomType());
//		updateRoom.setRoomSize(room.getRoomSize());
//		// check if roomnumber is unique
//		updateRoom.setRoomNumber(room.getRoomNumber());
//		roomRepository.save(updateRoom);
	}


}
