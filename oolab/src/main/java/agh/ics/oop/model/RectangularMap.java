    package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        lower_left = new Vector2d(0, 0);
        upper_right = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.precedes(upper_right) &&
                position.follows(lower_left);
    }
}
