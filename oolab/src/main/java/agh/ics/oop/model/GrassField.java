package agh.ics.oop.model;

import java.util.Iterator;

public class GrassField extends AbstractWorldMap {

    public GrassField(int grassCount) {
        super();
        int maxWidth = (int) Math.sqrt(grassCount * 10);
        int maxHeight = (int) Math.sqrt(grassCount * 10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public Boundary getCurrentBounds() {
        Iterator<Vector2d> positionsIterator = getElements().keySet().iterator();
        Vector2d initialPosition = positionsIterator.next();
        Vector2d lowerLeft = new Vector2d(initialPosition.getX(), initialPosition.getY());
        Vector2d upperRight = new Vector2d(initialPosition.getX(), initialPosition.getY());
        while (positionsIterator.hasNext()) {
            Vector2d currentPosition = positionsIterator.next();
            lowerLeft = lowerLeft.lowerLeft(currentPosition);
            upperRight = upperRight.upperRight(currentPosition);
        }
        return new Boundary(lowerLeft, upperRight);
    }
}
