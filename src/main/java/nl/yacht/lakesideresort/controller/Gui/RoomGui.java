package nl.yacht.lakesideresort.controller.Gui;

import nl.yacht.lakesideresort.domain.Boat;

import java.io.IOException;

/**
 * Created by njvan on 11-Oct-17.
 */
public class RoomGui extends Command {
    public void create() throws IOException {
        String[][] definition = {
            {"What type of room is it [Normal, Luxery]?","NORMAL|LUXERY"},
            {"How big is the room [Normal, Big]?","NORMAL|BIG"},
            {"How many windows?","\\d+"}
        };
        Object[] inputs = InputHandler.handleInput(definition);
        Boat boat = new Boat(Integer.parseInt(inputs[2].toString()));
        System.out.println(boat.getNumber());
    }

    public void find() {
        System.out.print("What is the room number? ");
    }

    public void doSomething(){
        System.out.println("Doing something!");
    }
}
