package nl.yacht.lakesideresort.controller.Gui;

import java.io.IOException;

/**
 * Created by njvan on 11-Oct-17.
 */
public class RoomGui extends Command {
    @Override
    public void create() throws IOException {
        String[][] definition = {
            {"String","What type of room is it [Normal, Luxery]?"},
            {"String","How big is the room [Normal, Big]?"},
            {"Integer","How many windows?"}
        };
        Object[] inputs = InputHandler.handleInput(definition);
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
