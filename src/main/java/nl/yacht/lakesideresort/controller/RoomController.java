package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.manager.RoomManager;
import nl.yacht.lakesideresort.repository.RoomRepository;
import nl.yacht.lakesideresort.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController{

    @Autowired
    private RoomManager roomManager;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Room> getRooms(){
    	return roomManager.getRooms();
    }

    /** return room id of found room*/
    @RequestMapping(value = "/search/{roomnumber}", method = RequestMethod.GET)
    public long findRoomByRoomNumber(@PathVariable int roomnumber){
		return roomManager.findRoomByRoomNumber(roomnumber);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public Room getSingleRoom(@PathVariable long id){
	    return roomManager.getSingleRoom(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean insertRoom(@RequestBody Room r){
		return roomManager.insertRoom(r);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void changeRoom(@PathVariable long id, @RequestBody Room room) {
    	roomManager.updateRoom(id, room);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable long id){
        roomManager.deleteRoom(id);
    }



}
