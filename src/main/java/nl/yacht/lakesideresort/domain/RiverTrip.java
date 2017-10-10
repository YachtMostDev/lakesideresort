package nl.yacht.lakesideresort.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RiverTrip extends Trip {
    private String type = "R";

    public RiverTrip(int tripNumber) {
        super(tripNumber);
    }

    @Override
    public Duration getDuration(){
        Duration difference = super.getDuration();

        Duration duration30Min = Duration.ofMinutes(30);

        //if(difference.getSeconds() > 1800) {
        difference = difference.minus(duration30Min);
        // }
        return difference;
    }

    @Override
    public String getTripType() {
        return this.type;
    }
}
