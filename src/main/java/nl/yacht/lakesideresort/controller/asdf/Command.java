package nl.yacht.lakesideresort.controller.asdf;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by njvan on 11-Oct-17.
 */
public abstract class Command {
    public void executeCommand(String command) throws IOException, CommandNotSupportedException {
        boolean commandFound = false;

        // Loop through methods of this class
        for(Method method : getMethods()){
            boolean isPublic = Modifier.isPublic(method.getModifiers());
            boolean namesMatch = method.getName().toLowerCase().equals(command);
            if(isPublic && namesMatch){
                try {
                    commandFound = true;
                    method.invoke(this);
                } catch (Exception e) {}
            }
        }
        if(!commandFound) {
            throw new CommandNotSupportedException();
        }
    }

    public Method[] getMethods(){
        return this.getClass().getMethods();
    }

    public String[] getMethodNames(){
        Method[] methods = getMethods();
        String[] result = new String[methods.length];
        for(int index = 0; index < methods.length; index++){
            result[index] = methods[index].getName();
        }
        return result;
    }

    public class CommandNotSupportedException extends Exception {
    }
}
