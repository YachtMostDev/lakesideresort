package nl.yacht.lakesideresort.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import nl.yacht.lakesideresort.controller.BoatController;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

public class BoatRentalTest {

    private BoatController boatRental;

    private Trip trip1;
    private Trip trip2;
    private Trip trip3;

    @Before
    public void setUp() {
        this.boatRental = new BoatController();
        this.trip1 = new Trip(3);
        this.trip2 = new Trip(4);
        this.trip3 = new Trip(5);
    }

    @Test
    public void testCalculateAverageDuration() {
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

    @Test
    public void testCheckBoats(){
        for (int index = 0; index < 10; index++){
            Assert.assertEquals(boatRental.checkBoats(), 10-index);
            boatRental.getBoatList().get(index).setTrip(trip1);
        }
        Assert.assertEquals(boatRental.checkBoats(), 0);
    }

    @Test
    public void testGetNrEndedTrips(){
        BoatController rental = new BoatController();

        rental.addTrip(trip1);
        rental.addTrip(trip2);
        rental.addTrip(trip3);

        // Trip 1 is yesterday
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        trip1.end();
        trip1.setEndTime(yesterday);

        // Trip 2 is last year
        LocalDateTime lastYear = LocalDateTime.now().minusYears(1);
        trip2.end();
        trip2.setEndTime(lastYear);

        trip3.end();

        rental.rent();
        rental.rent();
        rental.rent();

        int nr = rental.getNrEndedTrips();
        Assert.assertTrue(nr == 1);
        }

    /**
     * Test using reflection to access private field
     */
    @Test
    public void testRent(){
        try {
            BoatController rental = new BoatController();
            rental.rent();
            rental.rent();
            rental.rent();

            Field field = rental.getClass().getDeclaredField("trips");
            field.setAccessible(true);
            List<Trip> trips = (List<Trip>) field.get(rental);

            Assert.assertTrue(trips.size() == 3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}