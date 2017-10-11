package nl.yacht.lakesideresort.controller.Gui;

/**
 * Created by njvan on 11-Oct-17.
 */
public class TripGui extends Command {
    @Override
    public void create() {
        System.out.println("Trip create");
    }

    @Override
    public void find() {
        System.out.println("Trip find");
    }

    @Override
    public void update() {
        System.out.println("Trip update");
    }

    @Override
    public void remove() {
        System.out.println("Trip remove");
    }
}
