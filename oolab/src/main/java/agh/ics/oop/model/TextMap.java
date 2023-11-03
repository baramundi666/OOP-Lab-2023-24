package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class TextMap implements WorldMap<String, Integer>{

    private final Map<String, Integer> strings = new HashMap<>();

    private int map_size = -1;

    @Override
    public boolean canMoveTo(Integer position) {
        return false;
    }

    @Override
    public boolean place(String object) {
        map_size++;
        strings.put(object, map_size);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {

    }

    @Override
    public boolean isOccupied(Integer position) {
        return false;
    }

    @Override
    public Animal objectAt(Integer position) {
        return null;
    }
}
