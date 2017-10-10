package nl.yacht.lakesideresort.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Trip {
    private long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int tripNumber;

    public Trip(int tripNumber){
        // Create the object
        this.tripNumber = tripNumber;
        this.startTime = LocalDateTime.now();
    }

    public void end(){
        // set endTime
        this.endTime = LocalDateTime.now();
    }

    // For testing purposes only
    void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }

    public Duration getDuration(){
        // calculate duration > difference between start and end time

        LocalTime startTime = this.startTime.toLocalTime();
        LocalTime endTime = this.endTime.toLocalTime();

        Duration difference = Duration.between(startTime, endTime);
        // or calculate only the minutes.
        // long minutes = this.startTime.until( this.endTime, ChronoUnit.MINUTES);
        return difference;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public int getTripNumber(){
        // return trip number
        return tripNumber;
    }
}
