package nl.yacht.lakesideresort.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

public class TripTest {

    private Trip trip;


    @Before
    public void setUp() {
        this.trip = new Trip(3);
    }

    @Test
    public void testGetTripNumber() {

        final int expected = 112233;

        this.trip = new Trip(expected);

        Assert.assertEquals(expected, this.trip.getTripNumber());
    }

    @Test
    public void testGetDuration() {


        try {
            Thread.sleep(2000);

            this.trip.end();

            Duration duration = this.trip.getDuration();

            long aantalSeconden = duration.getSeconds();

            aantalSeconden -=2;

            aantalSeconden = Math.abs(aantalSeconden);

            Assert.assertTrue(aantalSeconden < 1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
