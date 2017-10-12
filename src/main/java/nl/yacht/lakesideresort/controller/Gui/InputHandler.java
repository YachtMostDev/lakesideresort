package nl.yacht.lakesideresort.controller.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by njvan on 11-Oct-17.
 */
public class InputHandler {
    public static Object[] handleInput(String[][] definition) throws IOException {
        Object[] result = new Object[definition.length];
        for(int index = 0; index < definition.length; index++){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            boolean correct = false;
            while(!correct){
                System.out.print(definition[index][0] + " ");
                String line = reader.readLine();
                Pattern r = Pattern.compile(definition[index][1]);
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
