package nl.yacht.lakesideresort.controller.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by njvan on 11-Oct-17.
 */
public class InputHandler {
    public static Object[] handleInput(String[][] definition, String[] args) throws IOException {
        Object[] result = new Object[definition.length];

        // Check for pre-filled-in arguments
        int autoargs_parsed = 0;
        for(int index = 0; index < definition.length; index++){
            if(index >= args.length) continue;
            if(args[index].toUpperCase().matches(definition[index][1])){
                result[index] = args[index];
                autoargs_parsed++;
            } else {
                continue;
            }
        }

        // Use buffered reader to read remaining arguments
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int index = autoargs_parsed; index < definition.length; index++){
            boolean correct = false;
            while(!correct){
                System.out.print(definition[index][0] + " ");
                String line = reader.readLine();
                if(line.toUpperCase().matches(definition[index][1])){
                    correct = true;
                    result[index] = line;
                } else {
                    System.out.println("This input is incorrect!");
                }
            }
        }
        return result;
    }
}
