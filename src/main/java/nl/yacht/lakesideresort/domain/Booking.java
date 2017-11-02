package nl.yacht.lakesideresort.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Entity
public class Booking {
    //variabelen
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookingnumber;
    @ManyToOne
    @NotEmpty
    private Guest guest;
    @ManyToOne
    @NotEmpty
    private Room room;
    @NotEmpty
    private LocalDate startDate;
    @NotEmpty
    private LocalDate endDate;

    public Booking() { //default constructor
    }

    public Booking(long bookingnumber, Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        this.bookingnumber = bookingnumber;
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking(long bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

    public Booking(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public boolean isBetween(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));

    }
}
