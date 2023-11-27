package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grass = new HashMap<>();

    public GrassField(int grassCount) {
        super();
        int maxWidth = (int) Math.sqrt(grassCount * 10);
        int maxHeight = (int) Math.sqrt(grassCount * 10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    public Map<Vector2d, Grass> getGrass() {
        return new HashMap<>(grass);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (!animals.containsKey(position) && !grass.containsKey(position)) return null;
        if (animals.containsKey(position)) return animals.get(position);
        return grass.get(position);
    }

    @Override
    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d,WorldElement> list = new HashMap<>();
        list.putAll(grass);
        list.putAll(animals);
        return list;
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
