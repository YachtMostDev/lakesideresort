package nl.yacht.lakesideresort.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class TripTest {

    private Trip trip;


    @Before
    public void setUp() {
        this.trip = new Trip(3);
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

    @Test
    public void testMeerTocht(){
        Trip t = new LakeTrip(1);
        String type = t.getTripType();
        Boolean equals = false;
        if (type.equals("M")){
            equals = true;
        }
        Assert.assertTrue(equals);

    }
    @Test
    public void testRivierTocht(){
        Trip t = new RiverTrip(2);
        String type = t.getTripType();
        Boolean equals = type.equals("R");
        Assert.assertTrue(equals);

        // short version
        // Assert.assertTrue(new RiverTrip(2).getTripType().equals("R"));
    }

    @Test
    public void testRivierTochtDuration(){
        // we kunnen niet 30min gaan wachten hier
        // hoe lossen we dit op?
        RiverTrip t = new RiverTrip(1);
        LocalDateTime dtNow = LocalDateTime.now();
        LocalDateTime dtFuture = dtNow.plusMinutes(45);
        t.end();
        t.SetEndTime(dtFuture);

        Duration d = t.getDuration();
        long minutes = d.toMinutes();
        System.out.println(minutes);
        Assert.assertTrue(minutes == 15);

    }

}
