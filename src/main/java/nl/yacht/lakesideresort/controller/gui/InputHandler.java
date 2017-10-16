package nl.yacht.lakesideresort.controller.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by njvan on 11-Oct-17.
 */
public class InputHandler {
    /**
     * Handles arguments of commands (auto | question based)
     * @param definition
     * @param args
     * @return
     * @throws IOException
     */
    public static String[] handleInput(String[][] definition, String[] args) throws IOException {
        String[] result = new String[definition.length];

        int autoargs_parsed = parseAutoArgs(definition, args, result);
        parseArguments(definition, result, autoargs_parsed);

        return result;
    }

    /**
     * Takes optional arguments and fills them in for you instead of answering questions
     * @param definition
     * @param args
     * @param result
     * @return the amount of succesfully auto filled in arguments
     */
    private static int parseAutoArgs(String[][] definition, String[] args, Object[] result) {
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
        return autoargs_parsed;
    }

    /**
     * Parses remaining arguments by asking questions and handling input
     * @param definition
     * @param result
     * @param autoargs_parsed
     * @throws IOException
     */
    private static void parseArguments(String[][] definition, String[] result, int autoargs_parsed) throws IOException {
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
    }
}
