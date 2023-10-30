package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();

    private final int width;
    private final int height;

    private final Vector2d LOWER_CORNER;
    private final Vector2d UPPER_CORNER;

    private final MapVisualizer visualization;


    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        LOWER_CORNER = new Vector2d(0, 0);
        UPPER_CORNER = new Vector2d(width, height);
        visualization = new MapVisualizer(this);
    }

    public boolean canMoveTo(Vector2d position) {
        return !this.isOccupied(position) && position.precedes(UPPER_CORNER) &&
                position.follows(LOWER_CORNER);
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public boolean place(Animal animal) {
        var position = animal.getPosition();
        if (this.isOccupied(position) || !this.canMoveTo(position)) return false;
        animals.put(position, animal);
        return true;
    }

    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsValue(animal)) {
            Vector2d position = animal.getPosition();
            MoveValidator validator = this;
            animal.move(direction, validator);
            Vector2d new_position = animal.getPosition();
            if(!animals.containsKey(new_position)) {
                animals.remove(position);
                animals.put(new_position, animal);
            }
        }
    }

    public Animal objectAt(Vector2d position) {
        if (!animals.containsKey(position)) return null;
        return animals.get(position);
    }

    @Override
    public String toString() {
        return visualization.draw(LOWER_CORNER, UPPER_CORNER);
    }
}
