package nl.yacht.lakesideresort.domain;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

public class TripTest {

    private Trip trip;

    @Test
    public void testGetDuration() {

        this.trip = new Trip(3);

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
