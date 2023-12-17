package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{

    private int updateCount = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        updateCount++;
        System.out.println("Map id: " + worldMap.getId());
        System.out.println("Update count: " + updateCount);
        System.out.println(message);
        System.out.println(worldMap.toString());
    }
}
