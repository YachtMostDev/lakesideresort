package nl.yacht.lakesideresort.controller.gui;

import nl.yacht.lakesideresort.domain.Boat;
import nl.yacht.lakesideresort.domain.Room;

import java.io.IOException;

/**
 * Created by njvan on 11-Oct-17.
 */
@Gui(name="Room")
public class RoomGui extends Command {
    public void create() throws IOException {
        String[][] definition = {
            {"What type of room is it [Budget, Normal, Luxury]?","BUDGET|NORMAL|LUXURY"},
            {"What size is the room [1, 2, 3-4, 5-6]?","1|2|3-4|5-6"},
            {"How many windows?","\\d+"}
        };
        Object[] inputs = InputHandler.handleInput(definition, args);
        // checken
        Room.RoomSize roomSize;
        Room.RoomType roomType;
        switch(inputs[0].toString()){
            case "LUXURY":
                roomType = Room.RoomType.LUXURY;
                break;
            case "BUDGET":
                roomType = Room.RoomType.BUDGET;
                break;
            case "NORMAL":
                roomType = Room.RoomType.NORMAL;
                break;
        }

        String s = inputs[1].toString();
        switch(s){
            case "1":
                roomSize = Room.RoomSize.ONE_PERSON;
            break;

            case "2":
                roomSize = Room.RoomSize.TWO_PERSON;
                break;

            case "3-4":
                roomSize = Room.RoomSize.THREE_FOUR_PERSON;
                break;

            case "5-6":
                roomSize = Room.RoomSize.FIVE_SIX_PERSON;
                break;
        }
//        Room r = new Room(Room)
    }

    public void find() {
        System.out.print("What is the room number? ");
    }

    public void doSomething(){
        System.out.println("Doing something!");
    }
}
