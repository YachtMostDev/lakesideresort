package nl.yacht.lakesideresort.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    //variabelen
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookingnumber;
    @ManyToOne
    private Guest guest;
    @ManyToOne
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking() { //default constructor
    }

    public Booking(long bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

    public long getBookingnumber() {
        return bookingnumber;
    }

    public void setBookingnumber(long bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }
}
