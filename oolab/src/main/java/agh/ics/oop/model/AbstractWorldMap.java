package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{

    protected final Map<Vector2d, Animal> animals = new HashMap<>();

    protected final Map<Vector2d, Grass> grass = new HashMap<>();

    protected final MapVisualizer visualization;

    protected final Set<MapChangeListener> observers = new HashSet<>();


    protected AbstractWorldMap() {
        visualization = new MapVisualizer(this);
    }

    public void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    public void registerObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void deregisterObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    @Override
    public abstract Boundary getCurrentBounds();

    public Map<Vector2d, Animal> getAnimals() {
        return new HashMap<>(animals);
    }

    public Map<Vector2d, Grass> getGrass() {
        return new HashMap<>(grass);
    }

    @Override
    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d,WorldElement> list = new HashMap<>();
        list.putAll(grass);
        list.putAll(animals);
        return list;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return getElements().containsKey(position);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        var position = animal.getPosition();
        if (!canMoveTo(position)) throw new PositionAlreadyOccupiedException(position);
        animals.put(position, animal);
        mapChanged("Placed " + animal + " at " + position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsValue(animal)) {
            Vector2d originalPosition = animal.getPosition();
            MapDirection originalOrientation = animal.getOrientation();
            animal.move(direction, this);
            Vector2d newPosition = animal.getPosition();
            MapDirection newOrientation = animal.getOrientation();
            if (!originalPosition.equals(newPosition)) {
                animals.remove(originalPosition);
                animals.put(newPosition, animal);
                mapChanged(animal + " moved from " + originalPosition + " to " + newPosition);
            }
            else if (!originalOrientation.equals(newOrientation)) {
                mapChanged(animal + " changed its orientation from " + originalOrientation + " to " +
                           newOrientation);
            }
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (!animals.containsKey(position) && !grass.containsKey(position)) return null;
        if (animals.containsKey(position)) return animals.get(position);
        return grass.get(position);
    }

    @Override
    public String toString() {
        return visualization.draw(getCurrentBounds().lowerLeft(),getCurrentBounds().upperRight());
    }
}
