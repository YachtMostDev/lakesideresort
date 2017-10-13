package nl.yacht.lakesideresort.controller.gui;

import nl.yacht.lakesideresort.controller.RoomController;
import nl.yacht.lakesideresort.domain.Boat;
import nl.yacht.lakesideresort.domain.Room;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by njvan on 11-Oct-17.
 */
@Gui(name="Room")
public class RoomGui extends Command {
    public void create() throws IOException {
        String[][] definition = {
            {"What type of room is it [Budget, Normal, Luxury]?","BUDGET|NORMAL|LUXURY"},
            {"What size is the room [1, 2, 3-4, 5-6]?","1|2|3-4|5-6"},
            {"When will the room be available [yyyy-MM-dd HH:mm]","\\d+"}
        };
        Object[] inputs = InputHandler.handleInput(definition, args);
        // this can be set to a default value, because these values will be set later.
        // this is handled by the regex match in the inputHandler
        Room.RoomSize roomSize = Room.RoomSize.ONE_PERSON;
        Room.RoomType roomType = Room.RoomType.NORMAL;
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
        RoomController rc = new RoomController();
        Room r = rc.createNewRoom(roomType, roomSize, LocalDateTime.now());
        System.out.println("Created room:\n" + r);
    }

    public void update(){

    }
    public void find() {

        System.out.print("What is the room number? ");
    }

    public void doSomething(){
        System.out.println("Doing something!");
    }
}
