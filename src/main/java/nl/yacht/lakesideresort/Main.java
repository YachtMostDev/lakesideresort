package nl.yacht.lakesideresort;

import nl.yacht.lakesideresort.controller.CommandLineInterpreter;
import nl.yacht.lakesideresort.domain.LakeTrip;
import nl.yacht.lakesideresort.domain.RiverTrip;
import nl.yacht.lakesideresort.domain.Trip;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandLineInterpreter cli = new CommandLineInterpreter();
        cli.runApplication();
    }
}
