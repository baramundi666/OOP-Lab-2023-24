package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TextMap implements AnyMap<String,Integer> {

    private final Map<Integer, String> strings = new HashMap<>();

    private int mapSize = 0;

    public Map<Integer, String> getStrings() {
        return new HashMap<>(strings);
    }

    public boolean canMoveTo(Integer position) {
        if (Objects.isNull(position)) return false;
        return position>=0 && position<mapSize;
    }

    public boolean place(String object) {
        if (Objects.isNull(object)) return false;
        strings.put(mapSize, object);
        mapSize++;
        return true;
    }

    public void move(String object, MoveDirection direction) {
        if (strings.containsValue(object)) {
            for(Integer position : strings.keySet()) {
                if (object.equals(strings.get(position))) {
                    Integer new_position = position;
                    switch(direction) {
                        case FORWARD -> new_position+=1;
                        case BACKWARD -> new_position-=1;
                        case LEFT, RIGHT -> new_position=null;
                    }
                    if (this.canMoveTo(new_position)) {
                        String other = strings.get(new_position);
                        strings.replace(position, other);
                        strings.replace(new_position, object);
                    }
                    break;
                }
            }

        }
    }

    public boolean isOccupied(Integer position) {
        return strings.containsKey(position);
    }

    public String objectAt(Integer position) {
        if (!strings.containsKey(position)) return null;
        return strings.get(position);
    }
}
