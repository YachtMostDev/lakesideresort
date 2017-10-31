package nl.yacht.lakesideresort.controller.rest;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.manager.AvailabilityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by njvan on 27-Oct-17.
 */
@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    @Autowired
    AvailabilityManager availabilityManager;

    @RequestMapping(value = "all/{date1}/{date2}", method = RequestMethod.GET)
    public HashMap<String, HashMap<String, Boolean>> getAvailability(@PathVariable String date1, @PathVariable String date2){
        LocalDate startDate = LocalDate.parse(date1);
        LocalDate endDate = LocalDate.parse(date2);

        return availabilityManager.getAvailabilityOfAllRooms(startDate, endDate);
    }

    @RequestMapping(value = "byRoom/{roomString}/{date1}/{date2}", method = RequestMethod.GET)
    public HashMap<String, Boolean> getAvailability(@PathVariable String roomString, @PathVariable String date1, @PathVariable String date2){
        LocalDate startDate = LocalDate.parse(date1);
        LocalDate endDate = LocalDate.parse(date2);

        return availabilityManager.getAvailabilityOfRoom(roomString, startDate, endDate);
    }

    @RequestMapping(value = "byDate/{date1}", method = RequestMethod.GET)
    public HashMap<String, Boolean> getAvailability(@PathVariable String date1){
        LocalDate date = LocalDate.parse(date1);

        return availabilityManager.getAvailabilityOnDate(date);
    }

}
