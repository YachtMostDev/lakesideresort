package nl.yacht.lakesideresort.controller.rest;

import javassist.NotFoundException;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Room> getRooms(){
    	Iterable<Room> list = roomRepository.findAll();
        return list;
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public Room getSingleRoom(@PathVariable long id){
	    return roomRepository.findOne(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public void insertRoom(@RequestBody Room r){
	    roomRepository.save(r);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void changeRoom(@PathVariable long id, @RequestBody Room r) {
    	Room updateRoom = roomRepository.findOne(id);
    	if (updateRoom == null){
    		return;
    		// add not found exc
	    }
	    updateRoom.setAvailableFrom(r.getAvailableFrom());
    	updateRoom.setRoomType(r.getRoomType());
    	updateRoom.setRoomSize(r.getRoomSize());
    	// check if roomnumber is unique
    	updateRoom.setRoomNumber(r.getRoomNumber());
    	roomRepository.save(updateRoom);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable long id){
        roomRepository.delete(id);
    }



}
