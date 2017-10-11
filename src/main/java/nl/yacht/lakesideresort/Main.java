package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.domain.Room;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Room room1 = new Room(1, Room.RoomType.BUDGET, Room.RoomSize.THREE_FOUR_PERSON, LocalDateTime.now());
        Room room2 = new Room(2, Room.RoomType.NORMAL, Room.RoomSize.FIVE_SIX_PERSON, LocalDateTime.now());
        System.out.println(room1.toString());
        System.out.println(room2);
        room1.setRoomNumber(3);
        System.out.println(room1);

        BoatRental rental = new BoatRental();
        rental.rent();
        rental.rent();
        rental.rent();
    }

    static void Sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
