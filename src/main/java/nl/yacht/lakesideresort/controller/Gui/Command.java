package nl.yacht.lakesideresort.controller.Gui;

import java.io.IOException;

/**
 * Created by njvan on 11-Oct-17.
 */
public abstract class Command {
    public void executeCommand(String command) throws IOException {
        switch (command.toUpperCase()){
            case "CREATE": create();
                break;
            case "FIND": find();
                break;
            case "UPDATE": update();
                break;
            case "REMOVE": remove();
                break;
            default:
                System.out.println("This is no valid command, type 'help' to get help");
                System.out.println();
        }
    }

    public abstract void create() throws IOException;
    public abstract void find();
    public abstract void update();
    public abstract void remove();
}
