package agh.ics.oop.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Optional<WorldElement> objectAt(Vector2d position) {
        if (!animals.containsKey(position) && !grass.containsKey(position)) return Optional.empty();
        if (animals.containsKey(position)) return Optional.of(animals.get(position));
        return Optional.of(grass.get(position));
    }

    @Override
    public Map<Vector2d, WorldElement> getElements() {
        return Stream.concat(animals.entrySet().stream(), grass.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (animal, grass) -> animal
                ));
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
