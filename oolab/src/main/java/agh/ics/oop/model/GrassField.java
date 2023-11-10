package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    private final int grassCount;

    private Vector2d lower_left;
    private Vector2d upper_right;

    public GrassField(int grassCount) {
        super();
        this.grassCount = grassCount;

        int maxWidth = (int) Math.sqrt(grassCount*10);
        int maxHeight = (int) Math.sqrt(grassCount*10);
        this.lower_left = new Vector2d(maxWidth, maxHeight);
        this.upper_right = new Vector2d(0, 0);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public String toString() {
        for (Vector2d position : getElements().keySet()) {
            lower_left=lower_left.lowerLeft(position);
            upper_right=upper_right.upperRight(position);
        }
        return visualization.draw(lower_left, upper_right);
    }
}
