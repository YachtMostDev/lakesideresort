package nl.yacht.lakesideresort.controller.Gui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by njvan on 11-Oct-17.
 */
public abstract class Command {
    public void executeCommand(String command) throws IOException, CommandNotSupportedException {
        boolean commandFound = false;

        // Get the Gui class
        Class<?> kl = this.getClass();
        Method[] methods = kl.getMethods();

        // Loop through methods
        for(Method method : methods){
            boolean isPublic = Modifier.isPublic(method.getModifiers());
            boolean namesMatch = method.getName().toLowerCase().equals(command);
            if(isPublic && namesMatch){
                try {
                    commandFound = true;
                    method.invoke(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!commandFound) {
            throw new CommandNotSupportedException();
        }
    }

    public class CommandNotSupportedException extends Throwable {
    }
}
