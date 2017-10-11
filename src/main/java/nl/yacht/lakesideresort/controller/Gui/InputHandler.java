package nl.yacht.lakesideresort.controller.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                System.out.println(definition[index][1]);
                String line = reader.readLine();
                if("String".equalsIgnoreCase(definition[index][0])){
                    correct = true;
                    result[index] = line;
                } else if("Integer".equalsIgnoreCase(definition[index][0])){
                    try {
                        int value = Integer.parseInt(line);
                        correct = true;
                        result[index] = value;
                    } catch (Exception name) {
                        System.out.println("That an invalid number!");
                    }
                }
            }
        }
        return result;
    }
}
