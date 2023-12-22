package agh.ics.oop;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileMapDisplay implements MapChangeListener {
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        String logFileName = worldMap.getId() + ".log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName, true))) {
            writer.println(message);
            writer.println(worldMap);
        } catch (IOException e) {
            System.err.println("Error writing to " + logFileName + " file");
        }
    }
}
