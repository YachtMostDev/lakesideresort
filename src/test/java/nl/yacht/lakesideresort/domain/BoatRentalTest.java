package nl.yacht.lakesideresort.domain;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BoatRentalTest {
    /**
     * Testing a private method using reflection
     */
    @Test
    public void testGetNewTripNumber(){
        BoatRental rental = new BoatRental();
        List<Trip> tripsMock = new ArrayList<>();
        Trip trip1 = new Trip(5);
        Trip trip2 = new Trip(2);
        Trip trip3 = new Trip(8);
        Trip trip4 = new Trip(1);

        try {
            Field field = rental.getClass().getDeclaredField("trips");
            field.setAccessible(true);
            field.set(rental, tripsMock);

            Method method = rental.getClass().getDeclaredMethod("getNewTripNumber", null);
            method.setAccessible(true);
            int tripNr = (int) method.invoke(rental);

            Assert.assertTrue(tripNr == 9);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
