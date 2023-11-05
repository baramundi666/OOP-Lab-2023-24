package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    private final int grassCount;

    public GrassField(int grassCount) {
        super();
        this.grassCount = grassCount;

        int maxWidth = (int) Math.sqrt(grassCount*10);
        int maxHeight = (int) Math.sqrt(grassCount*10);
        //var init_map_lower_corner = new Vector2d(0, 0);
        // init_map_upper_corner = new Vector2d(maxWidth, maxHeight);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
        lower_left = new Vector2d(0, 0);
        upper_right = new Vector2d(maxWidth, maxHeight);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grass.containsKey(position);
    }

    @Override
    public String toString() {
        int lowerX = 10; // to be changed
        int lowerY = 10;
        int upperX = 0;
        int upperY = 0;

        for (Vector2d position : grass.keySet()) {
            if (position.getX()<=lowerX) lowerX = position.getX();
            if (position.getY()<=lowerY) lowerY = position.getY();
            if (position.getX()>=upperX) upperX = position.getX();
            if (position.getY()>=upperY) upperY = position.getY();
        }

        for (Vector2d position : animals.keySet()) {
            if (position.getX()<=lowerX) lowerX = position.getX();
            if (position.getY()<=lowerY) lowerY = position.getY();
            if (position.getX()>=upperX) upperX = position.getX();
            if (position.getY()>=upperY) upperY = position.getY();
        }

        lower_left = new Vector2d(lowerX, lowerY);
        upper_right = new Vector2d(upperX, upperY);
        return super.toString();
    }

//    @Override
//    public boolean equals(Object other) {
//        if (this == other) return true;
//        if (!(other instanceof GrassField))
//            return false;
//        GrassField that = (GrassField) other;
//        for (Grass grass : this.grass.values()) {
//            if (!that.grass.containsKey(grass.getPosition())) return false;
//        }
//        return true;
//    }
//
//    @Override
//    public final int hashCode() {
//        return Objects.hash(grass);
//    }
}
