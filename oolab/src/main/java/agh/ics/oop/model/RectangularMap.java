package agh.ics.oop.model;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        super();
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    public Map<Vector2d, WorldElement> getElements() {
        return new HashMap<>(animals);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeft, upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.precedes(upperRight) &&
                position.follows(lowerLeft);
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        if (!animals.containsKey(position)) return Optional.empty();
        return Optional.of(animals.get(position));
    }
}
