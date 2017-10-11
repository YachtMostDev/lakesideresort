package nl.yacht.lakesideresort.controller.Gui;

/**
 * Created by njvan on 11-Oct-17.
 */
public abstract class Command {
    public void executeCommand(String command) throws CommandNotSupportedException {
        switch (command.toUpperCase()){
            case "CREATE":
                create();
                break;
            case "FIND":
                find();
                break;
            case "UPDATE":
                update();
                break;
            case "REMOVE":
                remove();
                break;
            default:
                System.out.println("This is no valid command, type 'help' to get help");
                System.out.println();
        }
    }

    public void create() throws CommandNotSupportedException {
        throw new CommandNotSupportedException();
    }

    public void find() throws CommandNotSupportedException {
        throw new CommandNotSupportedException();
    }

    public void update() throws CommandNotSupportedException {
        throw new CommandNotSupportedException();
    }

    public void remove() throws CommandNotSupportedException {
        throw new CommandNotSupportedException();
    }

    public class CommandNotSupportedException extends Throwable {
    }
}
