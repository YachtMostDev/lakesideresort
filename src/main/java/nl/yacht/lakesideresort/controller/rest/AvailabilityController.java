package nl.yacht.lakesideresort.controller.rest;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
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
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(value = "{roomString}/{dates}", method = RequestMethod.GET)
    public HashMap<String, HashMap<String, Boolean>> getAvailability(@PathVariable String roomString, @PathVariable String dates){
        HashMap<String, HashMap<String, Boolean>> result = new HashMap<>();
        ArrayList<Room> rooms = new ArrayList<>();
        if("all".equals(roomString)){
            rooms = (ArrayList<Room>) roomRepository.findAll();
        } else {
            rooms.add(roomRepository.findOne(new Long(roomString)));
        }
        String[] parts = dates.split(":");
        LocalDate startDate = LocalDate.parse(parts[0]);
        LocalDate endDate = LocalDate.parse(parts[1]);
        LocalDate forStartDate = startDate;
        LocalDate forEndDate = endDate;
        for(Booking booking : bookingRepository.findAll()){
            boolean overlap = false;
            if(rooms.contains(booking.getRoom())){
                boolean startDateBetween = betweenDates(booking.getStartDate(),booking.getEndDate(),startDate);
                boolean endDateBetween = betweenDates(booking.getStartDate(),booking.getEndDate(),endDate);
                if(startDateBetween){
                    overlap = true;
                    forStartDate = startDate;
                    if(endDateBetween){
                        forEndDate = endDate;
                    } else {
                        forEndDate = booking.getEndDate();
                    }
                } else if(endDateBetween){
                    overlap = true;
                    forStartDate = booking.getStartDate();
                    forEndDate = endDate;
                } else if(startDate.isBefore(booking.getStartDate()) && endDate.isAfter(booking.getEndDate())){
                    overlap = true;
                    startDate = booking.getStartDate();
                    endDate = booking.getEndDate();
                }
                if(overlap){
                    for(;!forStartDate.isAfter(forEndDate);forStartDate = forStartDate.plusDays(1)){
                        String roomNumber = Integer.toString(booking.getRoom().getRoomNumber());
                        result.putIfAbsent(roomNumber, new HashMap<>());
                        result.get(roomNumber).put(forStartDate.toString(), true);
                    }
                }
            }
        }
        for(Room r : rooms){
            String roomNumber = Integer.toString(r.getRoomNumber());
            result.putIfAbsent(roomNumber, new HashMap<>());
            for(startDate = LocalDate.parse(parts[0]);!startDate.isAfter(endDate);startDate = startDate.plusDays(1)){
                result.get(roomNumber).putIfAbsent(startDate.toString(), false);
            }
        }
        return result;
    }

    private boolean betweenDates(LocalDate startDate, LocalDate endDate, LocalDate checkDate){
        return (checkDate.isEqual(startDate) || checkDate.isAfter(startDate)) &&
                (checkDate.isEqual(endDate) || checkDate.isBefore(endDate));
    }
}
