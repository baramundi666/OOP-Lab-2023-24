    package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lower_left;
    private final Vector2d upper_right;

    public RectangularMap(int width, int height) {
        super();
        this.lower_left = new Vector2d(0, 0);
        this.upper_right = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.precedes(upper_right) &&
                position.follows(lower_left);
    }

    @Override
    public String toString() {
        return visualization.draw(lower_left, upper_right);
    }
}
