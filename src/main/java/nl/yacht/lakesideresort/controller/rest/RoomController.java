package nl.yacht.lakesideresort.controller.rest;

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
          return roomRepository.getRooms();
    }
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public Room getSingleRoom(@PathVariable long id){
    	return roomRepository.getSingleRoom(Long.valueOf(id).intValue());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void insertRoom(@RequestBody Room r){
        roomRepository.insertRoom(r);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void changeRoom(@PathVariable long id, @RequestBody Room r) {
        int roomNumber = Long.valueOf(id).intValue();
        roomRepository.updateRoom(roomNumber, r);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable long id){
        int roomNumber = Long.valueOf(id).intValue();
        roomRepository.deleteRoom(roomNumber);
    }



}
