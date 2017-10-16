package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Trip;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

public class BoatControllerTest {

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

        try {
            Method method = trip1.getClass().getDeclaredMethod("setEndTime", LocalDateTime.class);
            method.setAccessible(true);
            method.invoke(trip1, now.plusMinutes(55));
            method.invoke(trip2, now.plusMinutes(40));
            method.invoke(trip3, now.plusMinutes(34));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

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

        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        trip1.end();
        LocalDateTime lastYear = LocalDateTime.now().minusYears(1);
        trip2.end();

        try {
            Method method = trip1.getClass().getDeclaredMethod("setEndTime", LocalDateTime.class);
            method.setAccessible(true);
            method.invoke(trip1, yesterday);
            method.invoke(trip2, lastYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        trip3.end();

        rental.rent(false);
        rental.rent(false);
        rental.rent(false);

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
            rental.rent(false);
            rental.rent(false);
            rental.rent(false);

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