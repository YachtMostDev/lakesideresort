package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.controller.gui.*;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by njvan on 11-Oct-17.
 */
public class CommandLineInterpreter {
    HashMap<String,Command> map;

    public CommandLineInterpreter(){
        map = new HashMap<>();
        loadGuiClasses();
    }

    private void loadGuiClasses(){
        Reflections reflections = new Reflections(ClasspathHelper.forPackage("nl.yacht.lakesideresort.controller.gui"));
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Gui.class);
        for(Class<?> kl : annotated){
            Gui gui = kl.getAnnotation(Gui.class);
            String name = gui.name().toUpperCase();
            try {
                map.put(name, (Command) kl.newInstance());
            } catch (Exception e) {
                System.out.println("Couldn't load class " + name);
            }
        }
    }

    public void runApplication() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println();
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
            String[] possibleMethods = map.keySet().toArray(new String[map.size()]);
            for(int index = 0; index < possibleMethods.length; index++){
                possibleMethods[index] = possibleMethods[index].substring(0, 1).toUpperCase() + possibleMethods[index].toLowerCase().substring(1);
            }
            String possibleMethodsString = String.join(", ", possibleMethods);

            System.out.println();
            System.out.println("You have to start commands by using one of the following " + map.keySet().size() + " keywords");
            System.out.println(possibleMethodsString);
            System.out.println("You can also request the help for one of the " + map.keySet().size() + " keywords with 'help [KEYWORD]'");
            System.out.println("Or you can type 'quit' to quit the program");
        } else {
            Command command = map.get(input[1].toUpperCase());
            if(command == null){
                displayInvalidCommandMessage();
            } else {
                String possibleMethods = String.join(", ", command.getMethodNames());
                System.out.println();
                System.out.println("The possible commands for this keywords are: ");
                System.out.println(possibleMethods);
            }
        }
    }

    public void parseController(String[] input) throws IOException {
        Command command = map.get(input[0].toUpperCase());
        String[] args = {};
        if(input.length > 2) {
            args = Arrays.copyOfRange(input, 2, input.length);
        }
        if(command == null){
            displayInvalidCommandMessage();
        } else {
            try {
                command.executeCommand(input[1], args);
            } catch (Exception e) {
                displayInvalidCommandMessage();
            }
        }
    }

    public void displayInvalidCommandMessage(){
        System.out.println("This is an invalid command, type 'help' to get help");
    }
}
