package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.controller.BookingRepository;
import nl.yacht.lakesideresort.controller.RoomRepository;
import nl.yacht.lakesideresort.domain.Booking;
import nl.yacht.lakesideresort.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by njvan on 30-Oct-17.
 */
@Component
public class AvailabilityManager {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public AvailabilityManager(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public HashMap<String, Boolean> getAvailabilityOfRoom(String roomString, LocalDate startDate, LocalDate endDate){

        HashMap<String, Boolean> result = new HashMap<>();
        Room room = roomRepository.findOne(new Long(roomString));

        for(Booking booking : bookingRepository.findAll()){
            if(booking.getRoom().equals(room)){
                LocalDate forStartDate = startDate;
                LocalDate forEndDate = endDate;
                for(;!forStartDate.isAfter(forEndDate);forStartDate = forStartDate.plusDays(1)){
                    if(booking.isBetween(forStartDate)){
                        result.put(forStartDate.toString(),true);
                    }
                }
            }
        }

        for(;!startDate.isAfter(endDate);startDate = startDate.plusDays(1)){
            result.putIfAbsent(startDate.toString(), false);
        }

        return result;
    }

    public HashMap<String, Boolean> getAvailabilityOnDate(LocalDate date){
        HashMap<String, Boolean> result = new HashMap<>();

        for(Booking booking : bookingRepository.findAll()){
            if(booking.isBetween(date)) result.put(String.valueOf(booking.getRoom().getRoomNumber()),true);
        }

        for(Room room : roomRepository.findAll()){
            result.putIfAbsent(String.valueOf(room.getRoomNumber()),false);
        }

        return result;
    }

    public HashMap<String, HashMap<String, Boolean>> getAvailabilityOfAllRooms(LocalDate startDate, LocalDate endDate){
        HashMap<String, HashMap<String, Boolean>> result = new HashMap<>();

        for(Room room : roomRepository.findAll()){
            HashMap<String, Boolean> availabilityOfRoom = getAvailabilityOfRoom(String.valueOf(room.getRoomNumber()), startDate, endDate);
            result.put(String.valueOf(room.getRoomNumber()),availabilityOfRoom);
        }

        return result;
    }
}
