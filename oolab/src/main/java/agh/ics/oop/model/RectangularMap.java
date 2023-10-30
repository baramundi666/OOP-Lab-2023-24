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


    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        LOWER_CORNER = new Vector2d(0, 0);
        UPPER_CORNER = new Vector2d(width, height);
    }

    public boolean canMoveTo(Vector2d position) {
        return position.getX() >= 0 && position.getY() >= 0 &&
                position.getX() <= this.width && position.getY() <= this.height;
    }

    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position);
    }

    public boolean place(Animal animal) {
        var origin = new Vector2d(0, 0);
        if (this.isOccupied(origin) || !this.canMoveTo(origin)) return false;
        this.animals.put(origin, animal);
        return true;
    }

    public void move(Animal animal, MoveDirection direction) {
        if (this.animals.containsValue(animal)) {
            Vector2d position = animal.getPosition();
            MoveValidator validator = new RectangularMap(this.width, this.height);
            animal.move(direction, validator);
            this.animals.remove(position);
            this.animals.put(animal.getPosition(), animal);
        }
    }

    public Animal objectAt(Vector2d position) {
        if (!this.animals.containsKey(position)) return null;
        return this.animals.get(position);
    }

    public String toString() {
        WorldMap map = new RectangularMap(this.width, this.height);
        var visualization = new MapVisualizer(map);
        return visualization.draw(LOWER_CORNER, UPPER_CORNER);
    }
}
