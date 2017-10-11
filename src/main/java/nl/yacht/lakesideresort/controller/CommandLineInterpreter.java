package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.controller.Gui.Command;
import nl.yacht.lakesideresort.controller.Gui.RoomGui;
import nl.yacht.lakesideresort.controller.Gui.TripGui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by njvan on 11-Oct-17.
 */
public class CommandLineInterpreter {
    HashMap<String,Command> map;

    public CommandLineInterpreter(){
        map = new HashMap<>();
        map.put("TRIP", new TripGui());
        map.put("ROOM", new RoomGui());
    }

    public void runApplication() throws IOException {
        BoatController boatController = new BoatController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.print("What do you want to do? ");
            String line = reader.readLine();
            String[] input = line.split(" ");
            if("quit".equalsIgnoreCase(input[0])){
                break;
            } else if("help".equalsIgnoreCase(input[0])){
                displayHelp(input);
            } else {
                parseController(input);
            }
        }
    }

    public void displayHelp(String[] input){
        if(input.length == 1) {
            System.out.println();
            System.out.println("You have to start commands by using one of the following 4 keywords");
            System.out.println(" Boat, Guest, Trip & Room");
            System.out.println("You can also request the help for one of the 4 keywords with 'help [KEYWORD]'");
            System.out.println("Or you can type 'quit' to quit the program");
            System.out.println();
        } else if(Arrays.asList("BOAT","GUEST","TRIP","ROOM").contains(input[1].toUpperCase())){
            System.out.println();
            System.out.println("You can use " + input[1] + " with the following commands: ");
            System.out.println(" Create, Find, Update and Remove");
            System.out.println();
        }
    }

    public void parseController(String[] input) throws IOException {
        Command command = map.get(input[0].toUpperCase());
        if(command == null){
            System.out.println("This is an invalid command, type 'help' to get help");
        } else {
            try {
                command.executeCommand(input[1]);
            } catch (Command.CommandNotSupportedException e) {
                System.out.println("This is an invalid command for this type of controller, type 'help' to get help");
            }
        }
    }
}
