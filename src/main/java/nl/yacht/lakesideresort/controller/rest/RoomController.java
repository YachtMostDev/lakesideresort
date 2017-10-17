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

    @RequestMapping(method = RequestMethod.POST)
    public void insertRoom(@RequestBody Room r){
        roomRepository.insertRoom(r);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void changeRoom(@PathVariable long id, @RequestBody Room r) {
        int roomId = Long.valueOf(id).intValue();
        roomRepository.updateRoom(roomId, r);
        }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable long id){
        int roomId = Long.valueOf(id).intValue();
        roomRepository.deleteRoom(roomId);
    }



}
