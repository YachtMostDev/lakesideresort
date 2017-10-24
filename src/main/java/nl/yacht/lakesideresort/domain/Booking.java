package nl.yacht.lakesideresort.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
}
