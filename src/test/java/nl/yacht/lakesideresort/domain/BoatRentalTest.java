package nl.yacht.lakesideresort.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import nl.yacht.lakesideresort.BoatRental;
import java.time.LocalDateTime;

public class BoatRentalTest {
    private Trip trip1;
    private Trip trip2;
    private Trip trip3;

    @Before
    public void setUp() {
        this.trip1 = new Trip(3);
        this.trip2 = new Trip(4);
        this.trip3 = new Trip(5);
    }

    @Test
    public void testCalculateAverageDuration() {
        BoatRental boatRental = new BoatRental();
        boatRental.addTrip(trip1);
        boatRental.addTrip(trip2);
        boatRental.addTrip(trip3);

        LocalDateTime now = LocalDateTime.now();
        this.trip1.end();
        this.trip2.end();
        this.trip3.end();
        this.trip1.setEndTime(now.plusMinutes(55));
        this.trip2.setEndTime(now.plusMinutes(40));
        this.trip3.setEndTime(now.plusMinutes(34));

        double average = boatRental.calculateAverageDuration();

        Assert.assertTrue(average == 43);
    }
}