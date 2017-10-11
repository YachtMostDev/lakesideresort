package nl.yacht.lakesideresort.controller.Gui;

/**
 * Created by njvan on 11-Oct-17.
 */
public class RoomGui extends Command {
    @Override
    public void create() {
        System.out.println("Room create");
    }

    @Override
    public void find() {
        System.out.print("What is the room number? ");

    }

    @Override
    public void update() {
        System.out.println("Room update");
    }

    @Override
    public void remove() {
        System.out.println("Room remove");
    }
}
